package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AnswerUnitTest {

	private static final String LABEL = "Some Label";
	private static final Integer VALUE = 5;

	private Answer testee = new Answer(LABEL, VALUE);

	@Test
	public void shouldGetLabel() {
		String label = testee.getLabel();

		assertThat(label, is(equalTo(LABEL)));
	}

	@Test
	public void shouldGetValue() {
		Object value = testee.getValue();

		assertThat(value, is(equalTo(VALUE)));
	}

}
