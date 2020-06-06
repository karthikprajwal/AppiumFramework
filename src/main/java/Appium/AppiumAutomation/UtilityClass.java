package Appium.AppiumAutomation;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class UtilityClass {

	AndroidDriver<AndroidElement> driver;

	public UtilityClass(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void dropDownSearch(String ItemToSearch) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + ItemToSearch + "\"))");
	}

	public void searchProductNameInProductList(WebElement webElement, String productToSearch) {

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""
						+ productToSearch + "\").instance(0))");
	}

	public void countryToSelectInDropDown(String countryName) {
		driver.findElementByXPath("//*[@text='" + countryName + "']").click();
	}

	public void addToCartBasedOnProduct(String productName) {
		driver.findElementByXPath("//*[@text='" + productName
				+ "']/../android.widget.LinearLayout[2]/*[@resource-id='com.androidsample.generalstore:id/productAddCart']")
				.click();
	}

	public String productPriceBasedOnProduct(String productName) {
		String price = driver.findElementByXPath("//*[@text='" + productName
				+ "']/../android.widget.LinearLayout[2]/*[@resource-id='com.androidsample.generalstore:id/productPrice']")
				.getText();

		return price;
	}

	public String searchProductNameInCartList(String productTOSearch) {
		String productName = driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvCartProductList\")).scrollIntoView(new UiSelector().textMatches(\""
						+ productTOSearch + "\").instance(0))")
				.getText();

		return productName;
	}

}
