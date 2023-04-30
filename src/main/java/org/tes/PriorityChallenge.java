package org.tes;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.base.BaseClass;

public class PriorityChallenge extends BaseClass{
	@BeforeClass
	private void launchTheBrowser() {
		launchBrowser();
	}
	@Test(priority = 12)
	private void tc1() {
		launchUrl("https://myaccount.google.com/");
	}
	@Test(priority = -14)
	private void tc2() {
		launchUrl("https://www.inmakeslh.in/");
	}
	@Test
	private void tc3() {
		launchUrl("https://www.redbus.in/");
	}
	@Test(priority = 4)
	private void tc4() {
		launchUrl("https://www.flipkart.com/");
	}
	@Test(priority = -6)
	private void tc5() {
		launchUrl("https://www.amazon.in/");
	}
	@Test(priority = 2)
	private void tc6() {
		launchUrl("https://en-gb.facebook.com/login/");
	}
	@Test(priority = 1)
	private void tc7() {
		launchUrl("https://www.youtube.com/");

	}
}
