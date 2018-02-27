package com.courses.roman.mock;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyClassMock {

	@Test
	public void test1()  {
		//  create mock
		MyClass test = mock(MyClass.class);

		// define return value for method getUniqueId()
		when(test.getUniqueId()).thenReturn(43L);

		// use mock in test....
		Assertions.assertTrue(test.getUniqueId() == 43);
	}

	@Test
	public void testLinkedListSpyWrong() {
		// Lets mock a LinkedList
		List<String> list = new LinkedList<>();
		List<String> spy = spy(list);

		// this does not work
		// real method is called so spy.get(0)
		// throws IndexOutOfBoundsException (list is still empty)
		//when(spy.get(0)).thenReturn("foo");
		doReturn("foo").when(spy).get(0);

		MatcherAssert.assertThat("foo", is(spy.get(0)));
	}

	@Test
	public void testVerify()  {
		// create and configure mock
		MyClass test = Mockito.mock(MyClass.class);
		when(test.getUniqueId()).thenReturn(43L);


		// call method testing on the mock with parameter 12
		test.testing(12);
		test.getUniqueId();
		test.getUniqueId();


		// now check if method testing was called with the parameter 12
		//verify(test).testing(ArgumentMatchers.eq(12));

		// was the method called twice?
		verify(test, times(2)).getUniqueId();

		// other alternatives for verifiying the number of method calls for a method
		verify(test, never()).someMethod(anyString());
		test.someMethod("");
		verify(test, atLeastOnce()).someMethod(anyString());
		test.someMethod("");
		test.someMethod("");
		verify(test, atLeast(2)).someMethod(anyString());
		test.someMethod("");
		test.someMethod("");
		test.someMethod("");
		test.someMethod("");
		test.someMethod("");
		verify(test, times(8)).someMethod(anyString());
		//verify(test, atMost(3)).someMethod(anyString());

		// This let's you check that no other methods where called on this object.
		// You call it after you have verified the expected method calls.
		//verifyNoMoreInteractions(test);
	}
}
