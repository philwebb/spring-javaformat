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

package io.spring.javaformat.eclipse.projectsettings;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A collection of {@link ProjectSettingsFile project setting files}.
 *
 * @author Phillip Webb
 */
public class ProjectSettingsFiles implements Iterable<ProjectSettingsFile> {

	@Override
	public Iterator<ProjectSettingsFile> iterator() {
		return null;
	}

	/**
	 * Apply the settings files to the given eclipse project.
	 * @param project the project to apply the settings to
	 * @param monitor a progress monitor
	 */
	public void applyToProject(IProject project, IProgressMonitor monitor) {
	}

}
