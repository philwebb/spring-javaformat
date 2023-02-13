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

package io.spring.format.vscode;

import java.io.File;

import io.spring.javaformat.config.JavaFormatConfig;
import io.spring.javaformat.formatter.Formatter;
import io.spring.javaformat.org.eclipse.text.edits.TextEdit;

/**
 * Called from the Visual Studio Code extension to format source code.
 *
 * @author Phillip Webb
 */
public final class VisualStudioCode {

	private VisualStudioCode() {
		JavaFormatConfig config = JavaFormatConfig.findFrom(new File("."));
		Formatter formatter = new Formatter(config);
		String source = "";
		TextEdit format = formatter.format(source);
	}

	public static void main(String[] args) {
	}

}
