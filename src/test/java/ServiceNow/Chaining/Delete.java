package ServiceNow.Chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Delete extends Base
{
    @Test(priority = 5, dependsOnMethods = "ServiceNow.Chaining.Update.updateIncident")
    public void deleteIncident()
    {
       inputRequest = RestAssured.given();
       resp = inputRequest.delete("incident/"+sysID);
    }

}
