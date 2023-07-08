package Chaining;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetAll extends Base 
{
        @Test(priority = 2,dependsOnMethods = "Chaining.Create.createIncident")
        public void GetAllIncident()
        {
        inputRequest = RestAssured.given().queryParams(querryparms);
        resp = inputRequest.get("incident");
        // Traverse and get Multiple Values
        List<String> allIncidentSysID = resp.jsonPath().getList("result.sys_id");
        int size = allIncidentSysID.size();
        System.out.println("Total Incidents: "+size);
        System.out.println(allIncidentSysID);
        }
}
