package Appium.AppiumAutomation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;
import pageObject.CartPage;
import pageObject.HomePage;
import pageObject.ProductPage;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class homePageTest extends Basic {

	@BeforeTest
	public void killAllActiveServers() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(4000);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void settingCapability() throws IOException, InterruptedException {

		service = startServer();

		String productName[] = { "PG 3", "Jordan Lift Off", "Air Jordan 9 Retro" };

		AndroidDriver<AndroidElement> driver = Capability("GeneralStore");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		UtilityClass ut = new UtilityClass(driver);

		HomePage hp = new HomePage(driver);

		hp.NameField().sendKeys("Karthik");
		driver.hideKeyboard();
		hp.Female().click();
		hp.DropDown().click();
		ut.dropDownSearch("Canada");
		ut.countryToSelectInDropDown("Canada");
		hp.ShopButton().click();
		ProductPage p = new ProductPage(driver);
		Thread.sleep(4000);
		for (int i = 0; i < productName.length; i++) {
			ut.searchProductNameInProductList(p.FullProductList(), productName[i]);
			ut.addToCartBasedOnProduct(productName[i]);

		}
		p.CartButton().click();

		Thread.sleep(4000);

		Double totalAmount = 0.0;

		for (int i = 0; i < productName.length; i++) {
			String text = ut.searchProductNameInCartList(productName[i]);

			Assert.assertEquals(productName[i], text);
			String price = ut.productPriceBasedOnProduct(productName[i]);
			System.out.println(price);
			String price1 = price.substring(1);
			Double doublePrice = Double.parseDouble(price1);
			totalAmount = totalAmount + doublePrice;
		}
		System.out.println(totalAmount);

		CartPage c = new CartPage(driver);
		String finalAmount = c.TotalAmount().getText();
		finalAmount = finalAmount.substring(1);
		Double doubleFinalAmount = Double.parseDouble(finalAmount);

		Assert.assertEquals(doubleFinalAmount, totalAmount);
		c.EmailBox().click();
		TouchAction t = new TouchAction<>(driver);
		t.longPress(longPressOptions().withElement(element(c.TermsOfConditions())).withDuration(ofSeconds(2))).release()
				.perform();
		String titleOfAlert = c.AlertTitle().getText();
		Assert.assertEquals("Terms Of Conditions", titleOfAlert);
		c.AlertCloseBtn().click();

		service.stop();

	}

}
