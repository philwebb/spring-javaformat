/*
 * Copyright 2017-2021 the original author or authors.
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

package io.spring.format.formatter.intellij.xcodestyle;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.ChangedRangesInfo;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.util.IncorrectOperationException;

/**
 * {@link CodeStyleManager} to apply Spring Formatting conventions.
 *
 * @author Phillip Webb
 */
public class SpringCodeStyleManager extends DelegatingCodeStyleManager {

	private final SpringReformatter springReformatter;

	public SpringCodeStyleManager(CodeStyleManager delegate) {
		super(delegate);
		this.springReformatter = new SpringReformatter(() -> getProject());
	}

	SpringCodeStyleManager(CodeStyleManager delegate, SpringReformatter springReformatter) {
		super(delegate);
		this.springReformatter = springReformatter;
	}

	@Override
	public PsiElement reformat(PsiElement element) throws IncorrectOperationException {
		return reformat(element, false);
	}

	@Override
	public PsiElement reformat(PsiElement element, boolean canChangeWhiteSpacesOnly)
			throws IncorrectOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	@Override
	public PsiElement reformatRange(PsiElement element, int startOffset, int endOffset)
			throws IncorrectOperationException {
		return reformatRange(element, startOffset, endOffset, false);
	}

	@Override
	public PsiElement reformatRange(PsiElement element, int startOffset, int endOffset,
			boolean canChangeWhiteSpacesOnly) throws IncorrectOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	@Override
	public void reformatTextWithContext(PsiFile file, Collection<? extends TextRange> ranges)
			throws IncorrectOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	public void reformat(PsiFile file, Collection<? extends TextRange> ranges) {
		this.springReformatter.reformat(file, ranges);
	}

	@Override
	public void reformatText(PsiFile file, int startOffset, int endOffset) throws IncorrectOperationException {
		reformat(file, () -> Collections.singleton(new TextRange(startOffset, endOffset)),
				() -> super.reformatText(file, startOffset, endOffset));
	}

	@Override
	public void reformatText(PsiFile file, Collection<? extends TextRange> ranges) throws IncorrectOperationException {
		reformat(file, () -> ranges, () -> super.reformatText(file, ranges));
	}

	@Override
	public void reformatTextWithContext(PsiFile file, ChangedRangesInfo info) throws IncorrectOperationException {
		reformat(file, () -> info.allChangedRanges, () -> super.reformatTextWithContext(file, info));
	}

	private void reformat(PsiFile file, Supplier<Collection<? extends TextRange>> ranges, Runnable delegate) {
		if (this.springReformatter.canReformat(file)) {
			this.springReformatter.reformat(file, ranges.get());
		} else {
			delegate.run();
		}
	}

}
