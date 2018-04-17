package io.spring.javaformat.eclipse.plugin.maven.projectsettings;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.project.configurator.AbstractProjectConfigurator;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.osgi.util.NLS;

import io.spring.javaformat.eclipse.plugin.Plugin;

/**
 * {@link AbstractProjectConfigurator} to apply project specific settings.
 *
 * @author Phillip Webb
 */
public class ProjectSettingsConfigurator extends AbstractProjectConfigurator {

	@Override
	public void configure(ProjectConfigurationRequest request, IProgressMonitor monitor)
			throws CoreException {
		try {
			List<ProjectSettingsFile> settingsFiles = getSettingsFiles(request);
			copyToProject(request, monitor, settingsFiles);
		}
		catch (Exception ex) {
			rethrowCoreException(ex);
		}
	}

	private void rethrowCoreException(Exception ex) throws CoreException {
		if (ex instanceof CoreException) {
			throw (CoreException) ex;
		}
		String msg = NLS.bind(Messages.springFormatProjectSettingsError, ex.getMessage());
		throw new CoreException(new Status(IStatus.ERROR,
				Plugin.ID, -1, msg, ex));
	}

	private List<ProjectSettingsFile> getSettingsFiles(
			ProjectConfigurationRequest request) {
		return null;
	}

	private void copyToProject(ProjectConfigurationRequest request,
			IProgressMonitor monitor, List<ProjectSettingsFile> settingsFiles)
			throws Exception {
		for (ProjectSettingsFile settingsFile : settingsFiles) {
			IFile destination = request.getProject()
					.getFile(".settings/" + settingsFile.getName());
			if (destination.exists()) {
				try {
					destination.delete(true, monitor);
				}
				catch (CoreException ex) {
				}
			}
			try (InputStream content = settingsFile.getContent()) {
				destination.create(new BufferedInputStream(content), true, monitor);
			}
		}

	}

}
