package io.spring.javaformat.eclipse.plugin.maven.projectsettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class ProjectSettingsFile {

	private final String name;

	private final ContentSupplier contentSupplier;

	private ProjectSettingsFile(String name, ContentSupplier contentSupplier) {
		this.name = name;
		this.contentSupplier = contentSupplier;
	}

	public String getName() {
		return this.name;
	}

	public InputStream getContent() throws IOException {
		return contentSupplier.getContent();
	}

	public static ProjectSettingsFile fromFile(File file) {
		return new ProjectSettingsFile(file.getName(), () -> new FileInputStream(file));
	}

	public static ProjectSettingsFile fromClasspathResource(String name,
			String resourceLocation) {
		return new ProjectSettingsFile(name,
				() -> ProjectSettingsFile.class.getResourceAsStream(resourceLocation));
	}

	@FunctionalInterface
	static interface ContentSupplier {

		InputStream getContent() throws IOException;

	}

}
