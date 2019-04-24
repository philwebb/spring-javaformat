/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.javaformat.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * Internal task that prints out the formatter settings for the IDE plugins to use.
 *
 * @author Phillip Webb
 */
public class FormatSettingsTask extends DefaultTask {

	/**
	 * The name of the task.
	 */
	public static final String NAME = "formatSettings";

	private static final String PREFIX = "io.spring.javaformat.setting.";

	@TaskAction
	public void printSettings() {
		SpringJavaFormatExtension extension = getProject().getExtensions()
				.getByType(SpringJavaFormatExtension.class);
		getLogger().lifecycle(PREFIX + "style=" + extension.getStyle());
	}

}
