package org.tes;

import org.testng.annotations.Test;

public class PriorityConcept {
	
	@Test(priority = 7)
	private void tc7() {
		System.out.println("Test Case 7");
	}
	@Test//default value is 0
	private void tc2() {
		System.out.println("Test Case 2");
	}
	@Test(priority = -15)//most -ve will be run first
	private void tc4() {
		System.out.println("Test Case 4");
	}
	@Test(priority = -2)
	private void tc6() {
		System.out.println("Test Case 6");
	}
	@Test(priority = 24)//most +ve with high value will be run last
	private void tc3() {
		System.out.println("Test Case 3");
	}
	@Test(priority = -2)
	private void tc1() {
		System.out.println("Test Case 1");
	}
	@Test(priority = 4)
	private void tc5(){
		System.out.println("Test Case 5");
	}

}
