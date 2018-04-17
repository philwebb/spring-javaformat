package io.spring.javaformat.eclipse.plugin.maven.projectsettings;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "io.spring.javaformat.eclipse.plugin.maven.projectsettings"; //$NON-NLS-1$

	public static String springFormatProjectSettingsError;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}

}
