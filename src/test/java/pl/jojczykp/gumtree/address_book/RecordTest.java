package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RecordTest {

	@Test
	public void shouldParseGenderMale() {
		Record testee = new Record("Marek Nowak, Male, 10/12/1997");

		assertThat(testee.getGender(), is(Record.Gender.MALE));
	}

	@Test
	public void shouldParseGenderFemale() {
		Record testee = new Record("Hanna Kowalska, Female, 03/06/2001");

		assertThat(testee.getGender(), is(Record.Gender.FEMALE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionOnParsingError() {
		new Record("Abc Def, Zzz, 08/01/1999");
	}

}