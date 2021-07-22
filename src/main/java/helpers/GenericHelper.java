package helpers;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenericHelper {

     public static WebDriver getChromeDriver() {
        File driverFile = new File("src/main/java/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        WebDriver chromeDriver = new ChromeDriver(capabilities);
        return chromeDriver;
    }

    public static WebDriver getEdgeDriver() {
        File driverFile = new File("src/main/java/drivers/msedgedriver.exe");
        System.setProperty("webdriver.edge.driver", driverFile.getAbsolutePath());
        DesiredCapabilities capabilities = DesiredCapabilities.edge();
        WebDriver edgeDriver = new EdgeDriver(capabilities);
        return edgeDriver;
    }

    public void saveResponse(final Response response, String path){
        try {

            File createFile = new File( path );
            if(createFile.exists())
            {
                createFile.delete();
            }

            String directory = System.getProperty("user.dir")+"\\target\\api-response-files";
            File createDir = new File(directory);
            if(!createDir.isDirectory()) {
                createDir.mkdirs();
            }

            createFile.createNewFile();

            FileWriter file = new FileWriter(path);
            //file.write("____________HEADER____________");
            //file.write("/n");
            file.write(response.getHeaders().toString());
            file.write("/n");
                /*file.write("_____________BODY_____________");
                file.write("/n");*/
            file.write(response.getBody().prettyPrint());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    }

