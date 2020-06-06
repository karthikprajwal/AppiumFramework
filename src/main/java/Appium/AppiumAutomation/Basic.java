package Appium.AppiumAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Basic {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(25000);
	}

	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;

	}

	public static AndroidDriver<AndroidElement> Capability(String AppName) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Appium\\AppiumAutomation\\Global.properties");
		Properties pro = new Properties();
		pro.load(fis);

		File f = new File("src");
		File fs = new File(f, (String) pro.get(AppName));

		DesiredCapabilities capabilities = new DesiredCapabilities();
		// String deviceName = (String) pro.get("DeviceName");
		// Now fetching device name from run time maven command
		String deviceName = System.getProperty("deviceName");
		if (deviceName.contains("Emulator")) {
			startEmulator();
		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, pro.get("DeviceName"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		return driver;

	}

	public static void getScreenShot(String classname) throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,
				new File("C:\\Users\\kp\\eclipse-workspace\\AppiumAutomation\\test-output\\Failure-ScreenShots\\"
						+ classname + ".png"));
	}

}
