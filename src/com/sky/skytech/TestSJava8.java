package com.sky.skytech;

import java.util.Arrays;
import java.util.List;

public class TestSJava8 {

	public TestSJava8() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> aList = Arrays.asList("a7", "c3", "b2", "d4", "a5", "f6", "a1");
		
		aList.stream().filter(s -> s.startsWith("a")).map(String::toUpperCase).sorted().forEach(System.out::println);
		
		
	}

}
