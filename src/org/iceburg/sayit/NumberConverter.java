package org.iceburg.sayit;

import java.util.HashMap;

public class NumberConverter {

	private HashMap<Integer, String> ones, teens, tens, magnitudes;
	private HashMap<String, Integer> onesI, teensI, tensI, magnitudesI;
	
	public NumberConverter(){
		buildHashMaps();
	}
	
	private void buildHashMaps() {
		buildOnes();
		buildTeens();
		buildTens();
		buildMagnitude();
	}

	private void buildOnes() {
		ones = new HashMap<Integer, String>();
		ones.put(1, "one");
		ones.put(2, "two");
		ones.put(3, "three");
		ones.put(4, "four");
		ones.put(5, "five");
		ones.put(6, "six");
		ones.put(7, "seven");
		ones.put(8, "eight");
		ones.put(9, "nine");
		
		onesI = buildInverse(ones);
	}
	
	private void buildTeens() {
		teens = new HashMap<Integer, String>();
		teens.put(0, "ten");
		teens.put(1, "eleven");
		teens.put(2, "twelve");
		teens.put(3, "thirteen");
		teens.put(4, "fourteen");
		teens.put(5, "fifteen");
		teens.put(6, "sixteen");
		teens.put(7, "seventeen");
		teens.put(8, "eighteen");
		teens.put(9, "nineteen");
		
		teensI = buildInverse(teens);
	}
	private void buildTens() {
		tens = new HashMap<Integer, String>();
		tens.put(1, "ten");
		tens.put(2, "twenty");
		tens.put(3, "thirty");
		tens.put(4, "forty");
		tens.put(5, "fifty");
		tens.put(6, "sixty");
		tens.put(7, "seventy");
		tens.put(8, "eightty");
		tens.put(9, "ninety");
		
		tensI = buildInverse(tens);
	}
	private void buildMagnitude() {
		magnitudes = new HashMap<Integer, String>();
		magnitudes.put(1, "thousand");
		magnitudes.put(2, "million");
		magnitudes.put(3, "billion");
		magnitudes.put(4, "trillion");
		
		magnitudesI = buildInverse(magnitudes);
	}
	
	private HashMap<String, Integer> buildInverse(HashMap<Integer, String> inMap){
		 HashMap<String, Integer> inverse = new HashMap<String, Integer>();
		 for (int key : inMap.keySet()){
			 inverse.put(inMap.get(key), key);
		 }
		 return inverse;
	}
	
	
	public String numberToWord(int number){
		String numberIn = Integer.toString(number);
		return numberToWord(numberIn, 0);
	}
	private String numberToWord(String numberIn , int magnitude){
		String nextMag = "";
		if (numberIn.length() > 3){
			int cutoff = numberIn.length()-3;
			String nextMagIn = numberIn.substring(0, cutoff);
			nextMag = numberToWord(nextMagIn, magnitude+ 1);
			numberIn = numberIn.substring(cutoff);
		}
		String[] numberWords = new String[numberIn.length()];
		
		int place = 1;
		for (int i= numberIn.length()-1; i >=0; i--){
			int letter = getLetter(numberIn, i);
			if (place == 1 && ones.containsKey(letter)){
				numberWords[i] = ones.get(letter);
			} else if (place == 10){
				//handle teens
				if (letter == 1 && teens.containsKey(letter)){
					numberWords[i+1] = null;
					numberWords[i] = teens.get(getLetter(numberIn, i+1));
				} else if (tens.containsKey(letter)){
					numberWords[i] = tens.get(letter);
				}
			} else if (place == 100 && ones.containsKey(letter)){
				numberWords[i] = ones.get(letter) + " hundred";
			}
			
			place *= 10;
		}
		
		String sentance = combineWords(numberWords);
		
		String magWord = "";
		if (magnitude != 0 && !sentance.isEmpty() && magnitudes.containsKey(magnitude)){
			magWord = " " + magnitudes.get(magnitude);
		}
		
		if (!nextMag.isEmpty() && !sentance.isEmpty() && !sentance.startsWith(" ")){
			nextMag = nextMag + " "; 
		}
		
		return nextMag + sentance + magWord;
	}
	
	private int getLetter(String numberString, int i){
		return Integer.parseInt(numberString.substring(i, i+1));
	}
	

	private String combineWords(String[] numberWords) {
		String ret = "";
		for (int i=0; i<numberWords.length; i++){
			String word = numberWords[i];
			if (word != null && !word.equals("")){
				if (i != 0){
					ret += " ";
				}
				ret += word;
			}
		}
		return ret;
	}

	
	
	public int wordToNumber(String number){
		String[] words = number.split(" ");
		return 0;
	}
}
