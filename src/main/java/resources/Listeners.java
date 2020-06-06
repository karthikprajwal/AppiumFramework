package resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import Appium.AppiumAutomation.Basic;

public class Listeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		
		String classname=result.getName();
		try {
			Basic.getScreenShot(classname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
