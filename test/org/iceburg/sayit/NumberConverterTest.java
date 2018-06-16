package org.iceburg.sayit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumberConverterTest {
	private NumberConverter converter;
	
	@Before
	public void setup(){
		converter = new NumberConverter();
	}
	
	
	@Test
	public void testNumberToWord(){
		assertEquals("one", converter.numberToWord(1));
		assertEquals("ten", converter.numberToWord(10));
		assertEquals("eleven", converter.numberToWord(11));
		assertEquals("twenty five", converter.numberToWord(25));
		assertEquals("one hundred two", converter.numberToWord(102));
		assertEquals("one hundred forty three", converter.numberToWord(143));
		assertEquals("ten thousand two hundred twenty five", converter.numberToWord(10225));
		assertEquals("one thousand twelve", converter.numberToWord(1012));
		assertEquals("two hundred thousand seven hundred eleven", converter.numberToWord(200711));
		assertEquals("nine hundred ninety nine thousand nine hundred ninety nine", converter.numberToWord(999999));
		assertEquals("one million one hundred eleven thousand one hundred eleven", converter.numberToWord(1111111));
	}
	
	@Test
	public void testWordToNumber(){
		assertEquals(1, converter.wordToNumber("one"));
		assertEquals(10, converter.wordToNumber("ten"));
		assertEquals(11, converter.wordToNumber("eleven"));
		assertEquals(25, converter.wordToNumber("twenty five"));
		assertEquals(102, converter.wordToNumber("one hundred two"));
		assertEquals(143, converter.wordToNumber("one hundred forty three"));
		assertEquals(10225, converter.wordToNumber("ten thousand two hundred twenty five"));
		assertEquals(1012, converter.wordToNumber("one thousand twelve"));
		assertEquals(200711, converter.wordToNumber("two hundred thousand seven hundred eleven"));
		assertEquals(999999, converter.wordToNumber("nine hundred ninety nine thousand nine hundred ninety nine"));
		assertEquals(1111111, converter.wordToNumber("one million one hundred eleven thousand one hundred eleven"));
	}

}
