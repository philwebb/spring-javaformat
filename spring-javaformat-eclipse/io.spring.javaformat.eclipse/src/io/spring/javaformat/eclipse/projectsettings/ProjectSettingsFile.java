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

package io.spring.javaformat.eclipse.projectsettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A project settings file that can be copied to the project {@code .settings} folder.
 *
 * @author Phillip Webb
 */
final class ProjectSettingsFile {

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
		return this.contentSupplier.getContent();
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
	private interface ContentSupplier {

		InputStream getContent() throws IOException;

	}

}
