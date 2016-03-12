package pl.jojczykp.gumtree.address_book;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RecordUnitTest {

	@Test
	public void shouldParseGenderMale() {
		Record testee = new Record("Marek Nowak, Male, 10/12/97");

		assertThat(testee.getGender(), is(Record.Gender.MALE));
	}

	@Test
	public void shouldParseName() {
		Record testee = new Record("Norman Davies, Male, 08/06/39");

		assertThat(testee.getName(), is(equalTo("Norman Davies")));
	}

	@Test
	public void shouldParseGenderFemale() {
		Record testee = new Record("Hanna Kowalska, Female, 03/06/88");

		assertThat(testee.getGender(), is(Record.Gender.FEMALE));
	}

	@Test
	public void shouldParseBirth() {
		Record testee = new Record("Andrzej Duda, Female, 16/05/72");

		assertThat(testee.getBirth(), is(equalTo(LocalDate.of(1972, 5, 16))));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionOnGenderParsingError() {
		new Record("Abc Def, Zzz, 08/01/99");
	}

	@Test(expected = DateTimeParseException.class)
	public void shouldThrowExceptionOnBirthParsingError() {
		new Record("Abc Def, Male, 12/34");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionOnGeneralParsingError() {
		new Record("foo bar");
	}

}