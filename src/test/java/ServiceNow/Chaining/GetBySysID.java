package Chaining;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBySysID extends Base
{
    @Test(priority = 3,dependsOnMethods = "Chaining.Create.createIncident") 
    public void getIncidentBySysId()
    {
        inputRequest = RestAssured.given().queryParams(querryparms);
                
        Response resp = inputRequest.patch("incident/"+sysID);
        resp.prettyPrint();
        // Traverse and get Multiple Values
        resp.then().assertThat().body("result.sys_id",Matchers.equalTo(sysID));
        


    }
   
}
