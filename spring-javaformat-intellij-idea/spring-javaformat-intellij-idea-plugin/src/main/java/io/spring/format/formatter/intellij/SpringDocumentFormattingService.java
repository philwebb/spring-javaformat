/*
 * Copyright 2017-2020 the original author or authors.
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

package io.spring.format.formatter.intellij;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.intellij.formatting.FormattingContext;
import com.intellij.formatting.service.AbstractDocumentFormattingService;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author Phillip Webb
 */
public class SpringDocumentFormattingService extends AbstractDocumentFormattingService {

	private static final Set<Feature> FEATURES = Collections.emptySet();

	private static final FileType JAVA_FILE_TYPE = FileTypeManager.getInstance().getStdFileType("JAVA");

	@Override
	public @NotNull Set<Feature> getFeatures() {
		return FEATURES;
	}

	@Override
	public boolean canFormat(@NotNull PsiFile file) {
		return JAVA_FILE_TYPE.equals(file.getFileType());
	}

	@Override
	public void formatDocument(@NotNull Document document, @NotNull List<TextRange> formattingRanges,
			@NotNull FormattingContext formattingContext, boolean canChangeWhiteSpaceOnly, boolean quickFormat) {
	}

}
