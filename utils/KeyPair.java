package utils;

import java.util.ArrayList;

public class KeyPair {
	private int value1;
	private int value2;
	
	public KeyPair(int v1, int v2) {
		value1 = v1;
		value2 = v2;
	} 
	
	public int getValue1() {
		return value1;
	}
	public void setValue1(int value1) {
		this.value1 = value1;
	}
	public int getValue2() {
		return value2;
	}
	public void setValue2(int value2) {
		this.value2 = value2;
	}
	
	public ArrayList<Integer> getValue() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(value1);
		arr.add(value2);
		return arr;
	}
	
	public static boolean containVal2(int val2, ArrayList<KeyPair> pairs) {
		boolean found = false;
		int i = 0;
		while(i < pairs.size() && !found) {
			found = pairs.get(i).getValue2() == val2;
			i++;
		}
		return found;
	}
	
	public static boolean containVal1(int val1, ArrayList<KeyPair> pairs) {
		boolean found = false;
		int i = 0;
		while(i < pairs.size() && !found) {
			found = pairs.get(i).getValue1() == val1;
			i++;
		}
		return found;
	}
	
	public static boolean contain(KeyPair kp, ArrayList<KeyPair> pairs) {
		boolean found = false;
		int i = 0;
		while(i < pairs.size() && !found) {
			found = pairs.get(i).getValue1() == kp.getValue1() && pairs.get(i).getValue2() == kp.getValue2();
			i++;
		}
		return found;
	}
}
