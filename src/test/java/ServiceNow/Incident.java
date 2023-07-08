import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Incident 
{

    
    public void createIncidentwithStringBody() 
    {
        // Enter the End Point
        RestAssured.baseURI = "https://dev52133.service-now.com/api/now/table/";

        // Give Authorization
        RestAssured.authentication = RestAssured.basic("admin", "hZ^+3oyn0OUL");

        // Specify the header for content type
        RequestSpecification inputRequest = RestAssured.given().contentType("application/json").when().body("{\n" + //
                "    \"short_description\": \"QA Test SD by DC via Rest Assured\"\n" + //
                "}");

        // Send the Request using path param
        Response resp = inputRequest.post("incident");

        // Print the Response
        resp.prettyPrint();
    }

    public void createIncidentwithFileBody()
    {
                // Enter the End Point
                RestAssured.baseURI = "https://dev52133.service-now.com/api/now/table/";

                // Authentication
                RestAssured.authentication = RestAssured.basic("admin", "hZ^+3oyn0OUL");

                // Create Request Body
                File file = new File("src/test/resource/CreateIncident.json");
                RequestSpecification inputRequest = RestAssured.given().contentType("application/json").when().body(file);

                // Initiate the Request
                Response resp = inputRequest.post("incident");

                // Print the response
                resp.prettyPrint();

                // Extracting Value from response
                String sysID = resp.jsonPath().get("result.sys_id");

                System.out.println("\n System ID of the Created Incident is"+sysID);

    }

    
    @Test
    public void getAllIncidentusingQueryParam()
    {
        // Enter the End Point
        RestAssured.baseURI = "https://dev52133.service-now.com/api/now/table/";

        // Authentication
        RestAssured.authentication = RestAssured.basic("admin", "hZ^+3oyn0OUL"); 

/*         // Request Body with Single Query Param
        RequestSpecification inputRequest = RestAssured.given().queryParam("sysparm_fields", "number,sys_id,sys_created_on,short_description,sys_updated_by");
        Response resp = inputRequest.get("incident");
        resp.prettyPrint();

        // Request Body with Multiple Query Param
        inputRequest = RestAssured.given().
        queryParam("sysparm_fields", "number,sys_id,sys_updated_by").
        queryParam("sysparm_limit", "2");
        resp = inputRequest.get("incident");
        resp.prettyPrint(); */

        // Request Body with Query parms (Map)
        Map<String,String> querryparms = new HashMap<String,String>();
        querryparms.put("sysparm_limit", "10");
        querryparms.put("sysparm_fields", "number,sys_id"); 
        RequestSpecification inputRequest = RestAssured.given().queryParams(querryparms);
        Response resp = inputRequest.get("incident");
        // Traverse and get Multiple Values
        List<String> allIncidentSysID = resp.jsonPath().getList("result.sys_id");
        int size = allIncidentSysID.size();
        System.out.println("Total Incidents: "+size);
        System.out.println(allIncidentSysID);

        String statusLine = resp.getStatusLine();
        System.out.println("StatusLine: "+statusLine);
        int statusCode = resp.getStatusCode();
        System.out.println("StatusCode: "+statusCode);
        resp.prettyPrint();

        resp.then().assertThat().statusCode(200);

    }

    public void updateIncident()
    {
       // Enter the End Point
       RestAssured.baseURI = "https://dev52133.service-now.com/api/now/table/";

       // Authentication
       RestAssured.authentication = RestAssured.basic("admin", "hZ^+3oyn0OUL"); 

       // Request Body
       File file = new File("src/test/resource/UpdateIncident.json");
       RequestSpecification inputRequest = RestAssured.given().contentType("application/json").when().body(file); 

       // Intiate the Request
       Response resp = inputRequest.patch("incident/c64cde7a4733211016ecf164336d43a5");
       resp.prettyPrint();
    }
    
    public void deleteIncident()
    {
       // Enter the End Point
       RestAssured.baseURI = "https://dev52133.service-now.com/api/now/table/";

       // Authentication
       RestAssured.authentication = RestAssured.basic("admin", "hZ^+3oyn0OUL"); 

       // Request Body
       RequestSpecification inputRequest = RestAssured.given();
       Response resp = inputRequest.delete("incident/c7169a764733211016ecf164336d43a7");
       String statusLine = resp.getStatusLine();
       System.out.println(statusLine);
       int statusCode = resp.getStatusCode();
       System.out.println(statusCode);
    }

}