package com.blooregard.clocks;

import org.joda.time.LocalTime;

public class BinaryClock {

	LocalTime time;
	TYPE type;
	
	public enum TYPE {
		BINARY,
		BCD;
	}
	
	private enum PLACE {
		ONES,
		TENS;
	}
	
	{
		time = new LocalTime();
		type = TYPE.BINARY;
	}
	
	private char[] calcBinaryValue(int value) {
		char[] ret = {'0','0','0','0','0','0'};
		
		convertToArray(ret, value);
		
		return ret;
	}
	
	private char[] calcBCDValue(int value, PLACE place) {
		char[] ret = {'0','0','0','0'};
		
		if(place == PLACE.ONES) {
			value = value % 10;
		} else {
			value = value / 10;
		}
		
		convertToArray(ret, value);
		
		return ret;
	}
	
	private void convertToArray(char[] mask, int value) {
		char[] ca = Integer.toBinaryString(value).toCharArray();		
	
		System.arraycopy(ca, 0, mask, mask.length - ca.length, ca.length);
	}
	
	public static void main(String[] args) {
		BinaryClock clock = new BinaryClock();
		
		//System.out.println(clock.calcBCDValue(59, PLACE.TENS));
		//System.out.println(clock.calcBCDValue(59, PLACE.ONES));
		
		System.out.println(clock.calcBinaryValue(clock.time.getHourOfDay()));
		System.out.println(clock.calcBinaryValue(clock.time.getMinuteOfHour()));
		System.out.println(clock.calcBinaryValue(clock.time.getSecondOfMinute()));
		
		char[] hoursTens= clock.calcBCDValue(clock.time.getHourOfDay(), PLACE.TENS);
		char[] hoursOnes= clock.calcBCDValue(clock.time.getHourOfDay(), PLACE.ONES);
		
		char[] minsTens= clock.calcBCDValue(clock.time.getMinuteOfHour(), PLACE.TENS);
		char[] minsOnes= clock.calcBCDValue(clock.time.getMinuteOfHour(), PLACE.ONES);
		
		char[] secsTens= clock.calcBCDValue(clock.time.getSecondOfMinute(), PLACE.TENS);
		char[] secsOnes= clock.calcBCDValue(clock.time.getSecondOfMinute(), PLACE.ONES);
		
		for (int i = 0; i < hoursTens.length ; i++) {
			System.out.printf("%c%c %c%c %c%c\n", hoursTens[i], hoursOnes[i], minsTens[i], minsOnes[i], secsTens[i], secsOnes[i]);
		}
		int x= 24;
		System.out.println(x % 12 == 0 ? 12 : x % 12);
	}

}
