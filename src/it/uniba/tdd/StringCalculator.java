package it.uniba.tdd;

public class StringCalculator {
	public int add(String numbersStr) throws StringCalculatorException {
		// Returns the sum of the numbers given in numbersStr
		// not yet implemented
		int sum = 0;
		String myNumbersStr = numbersStr;
		String[] numbers = null;
		String separator = "\n|,";

		if (myNumbersStr.contains("//") && myNumbersStr.contains("\n")) {
			int end = myNumbersStr.indexOf("\n");
			
			if (myNumbersStr.contains("//[") && myNumbersStr.contains("]\n")) {
				if(myNumbersStr.contains("][") ){
					String tempSeparator = myNumbersStr.substring(3, end -1 );
					separator = "";
					for(int i=0; i<tempSeparator.length(); i++){
						if(tempSeparator.charAt(i)==']' && tempSeparator.charAt(i + 1)=='['){
							separator += "|";
						} else if(tempSeparator.charAt(i)!='['){
							separator += tempSeparator.charAt(i);
						}
						
					}
					
				} else {
					separator = myNumbersStr.substring(3, end -1 );
				}
			} else {
				separator = myNumbersStr.substring(2, 3);
			}
			
			myNumbersStr = myNumbersStr.substring(end + 1);
		}

		if (myNumbersStr.length() == 0) {
			sum = 0;
		} else {
			if (myNumbersStr.contains(",\n") || myNumbersStr.contains("\n,")) {
				throw new StringCalculatorException();
			}
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
}