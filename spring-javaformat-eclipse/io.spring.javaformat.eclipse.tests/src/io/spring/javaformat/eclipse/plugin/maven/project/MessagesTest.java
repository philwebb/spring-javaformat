package io.spring.javaformat.eclipse.plugin.maven.project;

import io.spring.javaformat.eclipse.plugin.maven.projectsettings.Messages;
import org.eclipse.osgi.util.NLS;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link Messages}.
 *
 * @author Phillip Webb
 */
public class MessagesTest {

	private static final String EXPECTED_MESSAGE = "Error importing maven-eclipse-plugin settings; "
			+ "test.file";

	@Test
	public void shouldHaveConfiguratorErrorMessage() {
		String msg = NLS.bind(Messages.springFormatProjectSettingsError, "test.txt");
		assertEquals(EXPECTED_MESSAGE, msg);
	}

}
