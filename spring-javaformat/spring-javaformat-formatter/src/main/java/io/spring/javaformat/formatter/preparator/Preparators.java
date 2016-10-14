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

package io.spring.javaformat.formatter.preparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.spring.javaformat.eclipse.formatter.Preparator;

/**
 * {@link Preparator} instances that are registered with the formatter.
 *
 * @author Phillip Webb
 */
public class Preparators implements Iterable<Preparator> {

	private final List<Preparator> preparators;

	public Preparators() {
		List<Preparator> preparators = new ArrayList<>();
		preparators.add(new JavadocLineBreakPreparator());
		preparators.add(new CodeLineBreakPreparator());
		this.preparators = Collections.unmodifiableList(preparators);
	}

	@Override
	public Iterator<Preparator> iterator() {
		return this.preparators.iterator();
	}

}
