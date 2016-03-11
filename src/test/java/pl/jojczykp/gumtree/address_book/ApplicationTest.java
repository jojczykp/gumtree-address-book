package pl.jojczykp.gumtree.address_book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

	@Mock private Streamer streamer;
	@Mock private Processor processor;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void shouldProcess() {
		Application testee = new Application(streamer, processor);
		when(processor.process(any())).thenReturn("someOutput");

		testee.run("someFile");

		assertThat(outContent.toString().trim(), is(equalTo("someOutput")));
		assertThat(errContent.toString(), isEmptyString());
	}

	@Test
	public void shouldPrintHelpIfWrongNumberOfArguments() {
		Application.main(new String[0]);

		assertThat(outContent.toString(), isEmptyString());
		assertThat(errContent.toString(), not(isEmptyString()));
	}

}
