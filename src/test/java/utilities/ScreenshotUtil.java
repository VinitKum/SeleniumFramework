package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) throws IOException {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String folder = "test-output/screenshots";

        File dir = new File(folder);

        if (!dir.exists())
        {
            dir.mkdirs();
        }

        String path = folder + "/" + testName + ".png";

        Files.copy(src.toPath(),
                   new File(path).toPath(),
                   StandardCopyOption.REPLACE_EXISTING);

        return path;
    }
}