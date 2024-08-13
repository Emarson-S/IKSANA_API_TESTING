package com.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.base.BaseClass;
import com.microsoft.playwright.APIResponse;

public class Profiles extends BaseClass {



@Test(enabled = false, description = "Profile",priority = 1)
public void updateAdminProfile() throws IOException{

     Map<String, String> User =new HashMap<>();
     User.put("firstName", toReadDataFromExcel("Profile", 1, 9));
     User.put("lastName", toReadDataFromExcel("Profile", 2, 9));
     User.put("title", toReadDataFromExcel("Profile", 3, 9));
     User.put("gender", toReadDataFromExcel("Profile", 4, 9));
    //  User.put("dateOfBirth", toReadDataFromExcel("Profile", 5, 9));
    //  User.put("age", toReadDataFromExcel("Profile", 6, 9));
     User.put("imageUrl", toReadDataFromExcel("Profile", 26, 9));
     User.put("userId", "1707286485303");


     Map<String, Object> map =new HashMap<>();

     map.put("user", User);
     map.put("userOnboardData",null);
     map.put("veterans",null);


     APIResponse response1=postRequestWithToken("rest/api/v1/updateMyProfile", getAdminToken(), map);
     int StatusCode1=response1.status();
     System.out.println("StatusCode1 "+StatusCode1);

}


@Test(enabled = false, description = "Profile",priority = 1)
public void updateCPProfile() throws IOException{

     Map<String, String> User =new HashMap<>();
     User.put("firstName", toReadDataFromExcel("Profile", 1, 8));
     User.put("lastName", toReadDataFromExcel("Profile", 2, 8));
     User.put("title", toReadDataFromExcel("Profile", 3, 8));
     User.put("gender", toReadDataFromExcel("Profile", 4, 8));
     User.put("dateOfBirth", toReadDataFromExcel("Profile", 5, 8));
     User.put("age", toReadDataFromExcel("Profile", 6, 8));
     User.put("address", toReadDataFromExcel("Profile", 7, 8));
     User.put("address1", toReadDataFromExcel("Profile", 8, 8));
     User.put("country", toReadDataFromExcel("Profile", 9, 8));
     User.put("state", toReadDataFromExcel("Profile", 10, 8));
     User.put("city", toReadDataFromExcel("Profile", 11, 8));
     User.put("pinCode", toReadDataFromExcel("Profile", 12, 8));
     User.put("territory", toReadDataFromExcel("Profile", 13, 8));
     User.put("govtType", toReadDataFromExcel("Profile", 14, 8));
     User.put("govtId", toReadDataFromExcel("Profile", 15, 8));
     User.put("imageUrl", toReadDataFromExcel("Profile", 26, 8));
     User.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 8));
     User.put("userId", "1716989670177");

     Map<String, String> userOnboardData =new HashMap<>();
     userOnboardData.put("degree", toReadDataFromExcel("Profile", 23, 8));
     userOnboardData.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 8));
     userOnboardData.put("govtType", toReadDataFromExcel("Profile", 14, 8));
     userOnboardData.put("govtId", toReadDataFromExcel("Profile", 15, 8));
     userOnboardData.put("userId", "1716989670177");
     Map<String, Object> map =new HashMap<>();
     map.put("user", User);
     map.put("userOnboardData",userOnboardData);


     APIResponse response1=postRequestWithToken("rest/api/v1/updateMyProfile", getCPToken(), map);
     int StatusCode1=response1.status();
     System.out.println("StatusCode1 "+StatusCode1);

}


}
