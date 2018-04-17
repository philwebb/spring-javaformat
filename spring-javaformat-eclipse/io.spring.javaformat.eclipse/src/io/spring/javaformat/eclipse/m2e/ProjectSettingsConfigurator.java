/*
 * Copyright 2012-2016 the original author or authors.
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

package io.spring.javaformat.eclipse.m2e;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.configurator.AbstractProjectConfigurator;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;

/**
 * {@link AbstractProjectConfigurator} to apply project specific settings.
 *
 * @author Phillip Webb
 */
public class ProjectSettingsConfigurator extends AbstractProjectConfigurator {

	@Override
	public void configure(ProjectConfigurationRequest arg0, IProgressMonitor arg1)
			throws CoreException {
	}

	// @Override
	// public void configure(ProjectConfigurationRequest request, IProgressMonitor
	// monitor)
	// throws CoreException {
	// try {
	// List<ProjectSettingsFile> settingsFiles = getSettingsFiles(request);
	// copyToProject(request, monitor, settingsFiles);
	// }
	// catch (Exception ex) {
	// rethrowCoreException(ex);
	// }
	// }
	//
	// private void rethrowCoreException(Exception ex) throws CoreException {
	// if (ex instanceof CoreException) {
	// throw (CoreException) ex;
	// }
	// String msg = NLS.bind(Messages.springFormatProjectSettingsError, ex.getMessage());
	// throw new CoreException(new Status(IStatus.ERROR, Plugin.ID, -1, msg, ex));
	// }
	//
	// private List<ProjectSettingsFile> getSettingsFiles(
	// ProjectConfigurationRequest request) {
	// return null;
	// }
	//
	// private void copyToProject(ProjectConfigurationRequest request,
	// IProgressMonitor monitor, List<ProjectSettingsFile> settingsFiles)
	// throws Exception {
	// for (ProjectSettingsFile settingsFile : settingsFiles) {
	// IFile destination = request.getProject()
	// .getFile(".settings/" + settingsFile.getName());
	// if (destination.exists()) {
	// try {
	// destination.delete(true, monitor);
	// }
	// catch (CoreException ex) {
	// }
	// }
	// try (InputStream content = settingsFile.getContent()) {
	// destination.create(new BufferedInputStream(content), true, monitor);
	// }
	// }
	//
	// }

}
