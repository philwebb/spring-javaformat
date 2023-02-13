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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import io.spring.javaformat.config.JavaFormatConfig;
import io.spring.javaformat.formatter.Formatter;
import io.spring.javaformat.org.eclipse.jface.text.Document;
import io.spring.javaformat.org.eclipse.jface.text.IDocument;

/**
 * Called from the Visual Studio Code extension to format source code.
 *
 * @author Phillip Webb
 */
public final class VisualStudioCode {

	private static final Pattern TRAILING_WHITESPACE = Pattern.compile(" +$", Pattern.MULTILINE);

	private VisualStudioCode() {
	}

	private void run(String[] args) throws Exception {
		if (args.length != 1) {
			throw new RuntimeException("Incorrect number of args");
		}
		String file = args[0];
		log(String.format("Loading formatter for '%s'", file));
		JavaFormatConfig config = JavaFormatConfig.findFrom(new File(file));
		Formatter formatter = new Formatter(config);
		String source = getSource();
		format(formatter, source);
		System.out.println(source);
	}

	private String format(Formatter formatter, String source) throws Exception {
		IDocument document = new Document(source);
		formatter.format(source).apply(document);
		String formattedContent = document.get();
		return trimTrailingWhitespace(formattedContent);
	}

	private String trimTrailingWhitespace(String content) {
		return TRAILING_WHITESPACE.matcher(content).replaceAll("");
	}

	private String getSource() throws IOException {
		StringBuilder source = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		String line;
		while ((line = in.readLine()) != null) {
			source.append(line);
			source.append("\n");
		}
		return source.toString();
	}

	private void log(String message) {
		System.err.println(message);
	}

	public static void main(String[] args) {
		try {
			new VisualStudioCode().run(args);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

}
