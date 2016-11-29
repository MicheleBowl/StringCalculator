package it.uniba.tdd;

public class StringCalculator {
	public int add(String numbersStr) throws StringCalculatorException {
		// Returns the sum of the numbers given in numbersStr
		// not yet implemented
		int sum = 0;
		String myNumbersStr = numbersStr;
		String[] numbers = null;
		String separator = "\n|,";

		// preparation of separators
		if (myNumbersStr.contains("//") && myNumbersStr.contains("\n")) {
			int end = myNumbersStr.indexOf("\n");
			String myTempSeparator = myNumbersStr.substring(2, end);
			int i = 0;
			separator = "";
			while (i < myTempSeparator.length()) {
				if (myTempSeparator.charAt(i) == '[') {
					i++;
				}
				if (myTempSeparator.charAt(i) == ']' && i != myTempSeparator.length() - 1) {
					separator += "|";
				} else if (myTempSeparator.charAt(i) != ']') {
					separator += myTempSeparator.charAt(i);
				}
				i++;
			}
			myNumbersStr = myNumbersStr.substring(end + 1);
		}

		// computing of numbers
		if (myNumbersStr.length() == 0) {
			sum = 0;
		} else {
			checkIllegalSequence(separator, myNumbersStr);
			numbers = myNumbersStr.split(separator);
			for (String num : numbers) {
				if (num.contains("-")) {
					throw new StringCalculatorException();
				}
			}

			if (numbers.length == 1) {
				sum = Integer.parseInt(numbers[0]);
			} else {
				for (String n : numbers) {
					if (n.length() < 4 || n.equals("1000")) {
						sum += Integer.parseInt(n);
					}
				}
			}
		}
		return sum;
	}

	private void checkIllegalSequence(String separator, String sequence) throws StringCalculatorException {
		String[] separators = separator.split("\\|");//ci|aa
		
		for (int i = 0; i < separators.length; i++) {
			for (String sep : separators) {
				if (sequence.contains(separators[i] + sep)) {
					throw new StringCalculatorException();
				}

			}
		}

	}
}