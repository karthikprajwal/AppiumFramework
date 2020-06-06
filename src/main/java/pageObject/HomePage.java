package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	WebElement radioMale;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	WebElement radioFemale;

	@AndroidFindBy(xpath = "//*[@resource-id='com.androidsample.generalstore:id/spinnerCountry']")
	WebElement dropDown;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	WebElement shopBtn;

	public WebElement NameField() {
		return nameField;
	}

	public WebElement Male() {
		return radioMale;
	}

	public WebElement Female() {
		return radioFemale;
	}

	public WebElement DropDown() {
		return dropDown;
	}

	public WebElement ShopButton() {
		return shopBtn;
	}

}
