package Chaining;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base 
{

    public static RequestSpecification inputRequest;
    public static Response resp;
    public static String sysID;
    public static Map<String,String> querryparms = new HashMap<String,String>();

    public void querryparms()
    {
 
    }

    @BeforeMethod
    public void base()
    {
        // Enter the End Point
        RestAssured.baseURI = "https://dev52133.service-now.com/api/now/table/";

        // Authentication
        RestAssured.authentication = RestAssured.basic("admin", "hZ^+3oyn0OUL"); 

        querryparms.put("sysparm_limit", "10");
        querryparms.put("sysparm_fields", "number,sys_id");

    }

    @AfterMethod
    public void Assert()
    {
        String statusLine = resp.getStatusLine();
        System.out.println("StatusLine: "+statusLine);
        int statusCode = resp.getStatusCode();
        System.out.println("StatusCode: "+statusCode);
    }


    
}
