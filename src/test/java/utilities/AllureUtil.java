package utilities;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class AllureUtil {

    public static void attachScreenshot(WebDriver driver) {

        System.out.println("========= Allure Screenshot Called =========");

        byte[] screenshot =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(
                "Failure Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );
    }
}