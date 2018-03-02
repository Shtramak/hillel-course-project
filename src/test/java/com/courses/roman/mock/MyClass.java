package com.courses.roman.mock;

public class MyClass {

	private Long uniqueId;

	public Long getUniqueId() {
		return uniqueId;
	}


	public void testing(int input) {
		someMethod(Integer.toString(input));
	}

	public void someMethod(String input) {
		System.out.println(input);
	}

	public void setUniqueId(final Long uniqueId) {
		this.uniqueId = uniqueId;
	}
}
