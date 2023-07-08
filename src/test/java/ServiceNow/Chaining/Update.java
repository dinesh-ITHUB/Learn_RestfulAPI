package Chaining;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Update extends Base
{
    @Test(priority = 4,dependsOnMethods = "Chaining.Create.createIncident")
    public void updateIncident()

    {
        File file = new File("src/test/resource/UpdateIncident.json");
        inputRequest = RestAssured.given().contentType("application/json").when().body(file); 

        // Intiate the Request
        Response resp = inputRequest.patch("incident/"+sysID);
        resp.prettyPrint();

        resp.then().assertThat().body("result.sys_id",Matchers.equalTo(sysID));
        resp.then().assertThat().body(Matchers.containsStringIgnoringCase("INC"));
    }
    
}
