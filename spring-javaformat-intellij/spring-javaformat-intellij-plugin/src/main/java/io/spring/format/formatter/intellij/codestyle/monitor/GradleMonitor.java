/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.format.formatter.intellij.codestyle.monitor;

import java.util.Collection;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.externalSystem.model.DataNode;
import com.intellij.openapi.externalSystem.model.ExternalProjectInfo;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListenerAdapter;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskType;
import com.intellij.openapi.externalSystem.model.task.TaskData;
import com.intellij.openapi.externalSystem.service.notification.ExternalSystemProgressNotificationManager;
import com.intellij.openapi.externalSystem.service.project.ProjectDataManager;
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataImportListener;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;
import io.spring.format.formatter.intellij.codestyle.monitor.Trigger.State;
import org.jetbrains.plugins.gradle.util.GradleConstants;

/**
 * {@link Monitor} that looks for a {@code spring-javaformat-gradle-plugin} declaration in
 * the build.gradle file.
 *
 * @author Phillip Webb
 */
public class GradleMonitor extends Monitor {

	public GradleMonitor(Project project, Trigger trigger) {
		super(project, trigger);
		ServiceManager.getService(ExternalSystemProgressNotificationManager.class)
				.addNotificationListener(
						new ExternalSystemTaskNotificationListenerAdapter() {

							@Override
							public void onEnd(ExternalSystemTaskId id) {
								if (GradleConstants.SYSTEM_ID
										.equals(id.getProjectSystemId())
										&& id.getType() == ExternalSystemTaskType.RESOLVE_PROJECT) {
									// check();
								}
							}

						});
		MessageBusConnection bus = project.getMessageBus().connect();
		bus.subscribe(ProjectDataImportListener.TOPIC, (path) -> check());
	}

	private void check() {
		ProjectDataManager projectDataManager = ServiceManager
				.getService(ProjectDataManager.class);
		boolean hasFormatPlugin = hasFormatPlugin(projectDataManager
				.getExternalProjectsData(getProject(), GradleConstants.SYSTEM_ID));
		getTrigger().updateState(hasFormatPlugin ? State.ACTIVE : State.NOT_ACTIVE);
	}

	private boolean hasFormatPlugin(Collection<ExternalProjectInfo> projectInfos) {
		for (ExternalProjectInfo projectInfo : projectInfos) {
			if (hasFormatPlugin(projectInfo.getExternalProjectStructure())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasFormatPlugin(DataNode<?> node) {
		if (node == null) {
			return false;
		}
		Object data = node.getData();
		if (data instanceof TaskData && isFormatPlugin((TaskData) data)) {
			return true;
		}
		for (DataNode<?> child : node.getChildren()) {
			if (hasFormatPlugin(child)) {
				return true;
			}
		}
		return false;
	}

	private boolean isFormatPlugin(TaskData data) {
		return "io.spring.javaformat.gradle.FormatTask".equals(data.getType());
	}

	public static Factory factory() {
		return (project, trigger) -> {
			return new GradleMonitor(project, trigger);
		};
	}

}
