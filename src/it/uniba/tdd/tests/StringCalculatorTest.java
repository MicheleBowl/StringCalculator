package it.uniba.tdd.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniba.tdd.StringCalculator;
import it.uniba.tdd.StringCalculatorException;

public class StringCalculatorTest {
	private String s;
	private StringCalculator calc;

	@Before
	public void setUp(){
		calc = new StringCalculator();
	}
	
	@Test
	public void theSumOfZeroNumbersIsZero() throws StringCalculatorException {
		// Act
		s = "";
		// Assert
		assertEquals(0, calc.add(s));

	}

	@Test
	public void theSumOfOneNumbersIsTheNumber() throws StringCalculatorException {
		// Act
		s = "100";
		// Assert
		assertEquals(100, calc.add(s));

	}

	@Test
	public void theSumOfTwoNumbersIsTheSum() throws StringCalculatorException {
		// Act
		s = "1,22";
		// Assert
		assertEquals(23, calc.add(s));

	}

	@Test
	public void theSumOfTenNumbersIsThemSum() throws StringCalculatorException {
		// Act
		s = "1,22,3,24,3,2,1,2,3,2";
		// Assert
		assertEquals(63, calc.add(s));

	}

	@Test
	public void theNumbersCanBeSeparatedAlsoBySlashN() throws StringCalculatorException {
		// Act
		s = "1,22\n32";
		// Assert
		assertEquals(55, calc.add(s));
	}

	@Test(expected = StringCalculatorException.class)
	public void theNumbersCantBeSeparatedByCommaAndSlashN() throws StringCalculatorException {
		// Act
		s = "1,\n22";
		// Assert
		calc.add(s);

	}

	@Test(expected = StringCalculatorException.class)
	public void theNumbersCantBeSeparatedBySlashNAndComma() throws StringCalculatorException {
		// Act
		s = "1\n,22";
		// Assert
		calc.add(s);

	}

	@Test
	public void theNumbersCanBeSeparatedByAPersonalizedSeparator() throws StringCalculatorException {
		// Act
		s = "//;\n1;22;32";
		// Assert
		assertEquals(55, calc.add(s));
	}

	@Test
	public void theNumbersAreSeparatedByN() throws StringCalculatorException {
		// Act
		s = "//N\n5N22N32";
		// Assert
		assertEquals(59, calc.add(s));
	}

	@Test(expected = StringCalculatorException.class)
	public void ifTheNumbersContainNegativeNumberIsTrhowsAnException() throws StringCalculatorException {
		// Act
		s = "//;\n1;-22;32";
		// Assert
		calc.add(s);

	}

	@Test
	public void theNumbersMajorThanOneThousandWithStandardSeparatorAreIgnored() throws StringCalculatorException {
		// Act
		s = "1,1022\n32";
		// Assert
		assertEquals(33, calc.add(s));
	}

	@Test
	public void theNumbersMajorThanOneThousandWithPersonalizedSeparatorAreIgnored() throws StringCalculatorException {
		// Act
		s = "//;\n1;1022;32";
		// Assert
		assertEquals(33, calc.add(s));
	}

	@Test
	public void ifTheNumbersIsOneThousandWithStandardSeparatorIsAccepted() throws StringCalculatorException {
		// Act
		s = "1,1000\n32";
		// Assert
		assertEquals(1033, calc.add(s));
	}

	@Test
	public void ifTheNumbersIsOneThousandWithPersonalizedSeparatorIsAccepted() throws StringCalculatorException {
		// Act
		s = "//;\n1;1000;32";
		// Assert
		assertEquals(1033, calc.add(s));
	}
	
	@Test
	public void theNumbersIsSeparatedByAPhraseDelimiter() throws StringCalculatorException {
		// Act
		s = "//[ciao]\n1ciao1ciao1";
		// Assert
		assertEquals(3, calc.add(s));
	}
	
	@Test
	public void theNumbersIsSeparatedByTwoPhraseDelimiter() throws StringCalculatorException {
		// Act
		s = "//[c][a]\n1c1a1a2c2";
		// Assert
		assertEquals(7, calc.add(s));
	}
	
	@Test
	public void theNumbersIsSeparatedByTwoPhraseDelimiterLongerThanOneChar() throws StringCalculatorException {
		// Act
		s = "//[ci][aa]\n1ci1aa1aa2ci2";
		// Assert
		assertEquals(7, calc.add(s));
	}
	
	@Test
	public void theNumbersIsSeparatedByThreePhraseDelimiterLongerThanOneChar2() throws StringCalculatorException {
		// Act
		s = "//[ci][aa][,a][-a][è]\n1ci2ci34aa7aa8,a8-a9è";
		// Assert
		assertEquals(69, calc.add(s));
	}
	
	
	
}
