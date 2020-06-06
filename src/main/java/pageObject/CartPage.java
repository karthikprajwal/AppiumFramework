package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.ElementOption;

public class CartPage {

	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvCartProductList")
	WebElement fullCartProductList;

	@AndroidFindBy(xpath = "//*[@resource-id='com.androidsample.generalstore:id/totalAmountLbl']")
	WebElement totalAmount;

	@AndroidFindBy(xpath = "//*[contains(@text,'Send me e-mails')]")
	WebElement emailCheckBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	WebElement termsOfConditions;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	WebElement completePurchaseBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	WebElement alertTitle;

	@AndroidFindBy(xpath = "//*[@text='CLOSE']")
	WebElement alertCloseBtn;

	public WebElement FullCartProductList() {
		return fullCartProductList;
	}

	public WebElement TotalAmount() {
		return totalAmount;
	}

	public WebElement EmailBox() {
		return emailCheckBox;
	}

	public WebElement TermsOfConditions() {
		return termsOfConditions;
	}

	public WebElement FinalPurchaseBtn() {
		return completePurchaseBtn;
	}

	public WebElement AlertTitle() {
		return alertTitle;
	}

	public WebElement AlertCloseBtn() {
		return alertCloseBtn;
	}

}
