package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

  static  RequestSpecification requestSpecification;

    public RequestSpecification setRequestSpecification(String baseURi, String paramKey, String paramValue){
        try {

            if(requestSpecification==null) {
                PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
                requestSpecification = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log)).setBaseUri(baseURi).addQueryParam(paramKey, paramValue)
                        .setContentType("application/json")
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return requestSpecification;
    }

    public Properties getGlobalProperties(){
        Properties properties = null;
        try {
            properties = new Properties();
            FileInputStream fileInputStream= new FileInputStream("./src/main/resources/global.properties");
            properties.load(fileInputStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return properties;
    }
}
