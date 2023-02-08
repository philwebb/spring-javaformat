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

package io.spring.format.formatter.intellij.codestyle.dunno;

import java.util.Set;

import com.intellij.formatting.service.AsyncDocumentFormattingService;
import com.intellij.formatting.service.AsyncFormattingRequest;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author pwebb
 */
public class Dunno extends AsyncDocumentFormattingService {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.intellij.formatting.service.FormattingService#canFormat(com.intellij.psi.
	 * PsiFile)
	 */
	@Override
	public boolean canFormat(@NotNull PsiFile arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.intellij.formatting.service.FormattingService#getFeatures()
	 */
	@Override
	public @NotNull Set<Feature> getFeatures() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.intellij.formatting.service.AsyncDocumentFormattingService#
	 * createFormattingTask(com.intellij.formatting.service.AsyncFormattingRequest)
	 */
	@Override
	protected @Nullable FormattingTask createFormattingTask(@NotNull AsyncFormattingRequest arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.intellij.formatting.service.AsyncDocumentFormattingService#getName()
	 */
	@Override
	protected @NotNull @NlsSafe String getName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.intellij.formatting.service.AsyncDocumentFormattingService#
	 * getNotificationGroupId()
	 */
	@Override
	protected @NotNull String getNotificationGroupId() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

}
