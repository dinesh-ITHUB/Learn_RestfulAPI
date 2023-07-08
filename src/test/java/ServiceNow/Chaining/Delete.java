package Chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Delete extends Base
{
    @Test(priority = 5, dependsOnMethods = "Chaining.GetBySysID.getIncidentBySysId")
    public void deleteIncident()
    {
       inputRequest = RestAssured.given();
       resp = inputRequest.delete("incident/"+sysID);
    }

}
