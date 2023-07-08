package ServiceNow.Chaining;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class Create extends Base
{
    @Test(priority = 1)
    public void createIncident()
    {

         // Specify the header for content type
        inputRequest = RestAssured.given().contentType("application/json").when().body("{\n" + //
                "    \"short_description\": \"QA Chain Test via Rest Assured\"\n" + //
                "}").queryParams(querryparms);
        // Send the Request using path param
        resp = inputRequest.post("incident");

        resp.prettyPrint();

        sysID = resp.jsonPath().get("result.sys_id");
        // Print the Response       

    }    
}
