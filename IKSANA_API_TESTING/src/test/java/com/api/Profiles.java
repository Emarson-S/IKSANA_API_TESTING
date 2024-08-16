package com.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;

public class Profiles extends BaseClass {

    // Admin profile Update API

    @Test(enabled = true, description = "Profile", priority = 1)
    public void updateAdminProfile() throws IOException {

        Map<String, String> User = new HashMap<>();
        User.put("firstName", toReadDataFromExcel("Profile", 1, 9));
        User.put("lastName", toReadDataFromExcel("Profile", 2, 9));
        User.put("title", toReadDataFromExcel("Profile", 3, 9));
        User.put("gender", toReadDataFromExcel("Profile", 4, 9));
        User.put("dateOfBirth", toReadDataFromExcel("Profile", 5, 9));
        User.put("age", toReadDataFromExcel("Profile", 6, 9));
        User.put("address", toReadDataFromExcel("Profile", 7, 9));
        User.put("address1", toReadDataFromExcel("Profile", 8, 9));
        User.put("country", toReadDataFromExcel("Profile", 9, 9));
        User.put("state", toReadDataFromExcel("Profile", 10, 9));
        User.put("city", toReadDataFromExcel("Profile", 11, 9));
        User.put("pinCode", toReadDataFromExcel("Profile", 12, 9));
        User.put("territory", toReadDataFromExcel("Profile", 13, 9));
        User.put("imageUrl", toReadDataFromExcel("Profile", 26, 9));
        User.put("userId", toReadDataFromExcel("Users", 1, 8));
        User.put("roleId", toReadDataFromExcel("Users", 3, 8));
        User.put("email", toReadDataFromExcel("Users", 5, 8));
        User.put("countryCode", "+91");
        User.put("phoneNo", "8400000001");
        User.put("govtProof", null);
        User.put("abhaNumber", null);
        User.put("emergencyContactNo", null);

        Map<String, Object> map = new HashMap<>();

        map.put("user", User);
        map.put("userOnboardData", null);
        map.put("veterans", null);

        APIResponse response1 = postRequestWithToken("rest/api/v1/updateMyProfile", getAdminToken(), map);
        int StatusCode1 = response1.status();
        System.out.println("StatusCode1 " + StatusCode1);

        // <--------- Profile_TC_01 --------------->

        try {
            Assert.assertEquals(StatusCode1, 200);
            resultsCreateNewCell("Profile", 1, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 1, 10, "Fail");
        }

        // <----------------Profile_TC_02------------>

        String s1 = getBodyData(response1).toString();
        if (isJSONValid(s1)) {
            resultsCreateNewCell("Profile", 2, 10, "Pass");
        } else {
            resultsCreateNewCell("Profile", 2, 10, "Fail");
        }

        // <----------------Profile_TC_03------------>

        if (s1 != null && StatusCode1 == 200) {
            JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            if (d1 != null && d1.equals("0000")) {
                resultsCreateNewCell("Profile", 3, 10, "Pass");
            } else {
                resultsCreateNewCell("Profile", 3, 10, "Fail");
            }
        } else {
            resultsCreateNewCell("Profile", 3, 10, "Fail");
        }

        APIResponse response2 = postRequestWithToken("rest/api/v1/updateMyProfile", getCPToken(), map);
        int StatusCode2 = response2.status();
        System.out.println("StatusCode2 " + StatusCode2);
        System.out.println(getBodyData(response2).toPrettyString());
        // <--------- Profile_TC_04 --------------->

        try {
            Assert.assertEquals(StatusCode2, 200);
            String s2 = getBodyData(response2).toString();
            if (s2 != null && StatusCode2 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 4, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 4, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 4, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 4, 10, "Fail");
        }

        APIResponse response3 = postRequestWithToken("rest/api/v1/updateMyProfile", toReadDataFromExcel("Users", 4, 8),
                map);
        int StatusCode3 = response3.status();
        System.out.println("StatusCode3 " + StatusCode3);
        System.out.println(getBodyData(response3).toPrettyString());

        // <--------- Profile_TC_05 --------------->

        try {
            Assert.assertEquals(StatusCode3, 504);
            resultsCreateNewCell("Profile", 5, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 5, 10, "Fail");

        }

        Map<String, String> User1 = new HashMap<>();
        User1.put("firstName", toReadDataFromExcel("Profile", 1, 9));
        User1.put("lastName", toReadDataFromExcel("Profile", 2, 9));
        User1.put("userId", toReadDataFromExcel("Users", 1, 8));
        User1.put("roleId", toReadDataFromExcel("Users", 3, 8));
        User1.put("email", toReadDataFromExcel("Users", 5, 8));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user", User1);

        APIResponse response4 = postRequestWithToken("rest/api/v1/updateMyProfile", getAdminToken(), map1);
        int StatusCode4 = response4.status();
        System.out.println("StatusCode4 " + StatusCode4);
        System.out.println(getBodyData(response4).toPrettyString());

        // <--------- Profile_TC_06 --------------->

        try {
            Assert.assertEquals(StatusCode4, 200);
            String s2 = getBodyData(response4).toString();
            if (s2 != null && StatusCode4 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 6, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 6, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 6, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 6, 10, "Fail");
        }

    }

    // Channel partner Profile update API

    @Test(enabled = true, description = "Profile", priority = 2)
    public void updateCPProfile() throws IOException {

        Map<String, String> User = new HashMap<>();
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
        User.put("degree", toReadDataFromExcel("Profile", 23, 8));
        User.put("imageUrl", toReadDataFromExcel("Profile", 26, 8));
        User.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 8));
        User.put("userId", toReadDataFromExcel("Users", 1, 9));
        User.put("roleId", toReadDataFromExcel("Users", 3, 9));
        User.put("email", toReadDataFromExcel("Users", 5, 9));
        User.put("countryCode", "+91");
        User.put("phoneNo", "7675456457");
        User.put("govtProof", null);
        User.put("abhaNumber", null);
        User.put("emergencyContactNo", null);

        Map<String, String> userOnboardData = new HashMap<>();
        userOnboardData.put("degree", toReadDataFromExcel("Profile", 23, 8));
        userOnboardData.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 8));
        userOnboardData.put("govtType", toReadDataFromExcel("Profile", 14, 8));
        userOnboardData.put("govtId", toReadDataFromExcel("Profile", 15, 8));
        userOnboardData.put("userId", toReadDataFromExcel("Users", 1, 9));
        userOnboardData.put("status", "Active");
        userOnboardData.put("govtProof",
                "https://s3-dev-iksana-files-images.s3.ap-south-1.amazonaws.com/1716989670177/ae2f80ec-0a17-47ad-bf59-9d49cf4e08dd");
        userOnboardData.put("personalDetails", null);
        userOnboardData.put("user", null);

        Map<String, Object> map = new HashMap<>();
        map.put("user", User);
        map.put("userOnboardData", userOnboardData);
        map.put("veterans", null);

        APIResponse response1 = postRequestWithToken("rest/api/v1/updateMyProfile", getCPToken(), map);
        int StatusCode1 = response1.status();
        System.out.println("StatusCode1 " + StatusCode1);

        // <--------- Profile_TC_07 --------------->

        try {
            Assert.assertEquals(StatusCode1, 200);
            resultsCreateNewCell("Profile", 7, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 7, 10, "Fail");
        }

        // <----------------Profile_TC_08------------>

        String s1 = getBodyData(response1).toString();
        if (isJSONValid(s1)) {
            resultsCreateNewCell("Profile", 8, 10, "Pass");
        } else {
            resultsCreateNewCell("Profile", 8, 10, "Fail");
        }

        // <----------------Profile_TC_09 ------------>

        if (s1 != null && StatusCode1 == 200) {
            JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            if (d1 != null && d1.equals("0000")) {
                resultsCreateNewCell("Profile", 9, 10, "Pass");
            } else {
                resultsCreateNewCell("Profile", 9, 10, "Fail");
            }
        } else {
            resultsCreateNewCell("Profile", 9, 10, "Fail");
        }

        APIResponse response2 = postRequestWithToken("rest/api/v1/updateMyProfile", getAdminToken(), map);
        int StatusCode2 = response2.status();
        System.out.println("StatusCode2 " + StatusCode2);

        // <--------- Profile_TC_10 --------------->

        try {
            Assert.assertEquals(StatusCode2, 200);
            String s2 = getBodyData(response2).toString();
            if (s2 != null && StatusCode2 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 10, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 10, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 10, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 10, 10, "Fail");
        }

        APIResponse response3 = postRequestWithToken("rest/api/v1/updateMyProfile", toReadDataFromExcel("Users", 4, 9),
                map);
        int StatusCode3 = response3.status();
        System.out.println("StatusCode3 " + StatusCode3);
        System.out.println(getBodyData(response3).toPrettyString());

        // <--------- Profile_TC_11 --------------->

        try {
            Assert.assertEquals(StatusCode3, 504);
            resultsCreateNewCell("Profile", 11, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 11, 10, "Fail");

        }

        Map<String, String> User1 = new HashMap<>();
        User1.put("firstName", toReadDataFromExcel("Profile", 1, 8));
        User1.put("lastName", toReadDataFromExcel("Profile", 2, 8));
        User1.put("userId", toReadDataFromExcel("Users", 1, 9));
        User1.put("roleId", toReadDataFromExcel("Users", 3, 9));
        User1.put("email", toReadDataFromExcel("Users", 5, 9));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user", User1);
        map1.put("userOnboardData", userOnboardData);
        map1.put("veterans", null);

        APIResponse response4 = postRequestWithToken("rest/api/v1/updateMyProfile", getCPToken(), map1);
        int StatusCode4 = response4.status();
        System.out.println("StatusCode4 " + StatusCode4);
        System.out.println(getBodyData(response4).toPrettyString());

        // <--------- Profile_TC_12 --------------->

        try {
            Assert.assertEquals(StatusCode4, 200);
            String s2 = getBodyData(response4).toString();
            if (s2 != null && StatusCode4 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 12, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 12, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 12, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 12, 10, "Fail");
        }

    }

    // Care Manager Profile update API

    @Test(enabled = true, description = "Profile", priority = 3)
    public void updateCMProfile() throws IOException {

        Map<String, String> User = new HashMap<>();
        User.put("firstName", toReadDataFromExcel("Profile", 1, 7));
        User.put("lastName", toReadDataFromExcel("Profile", 2, 7));
        User.put("title", toReadDataFromExcel("Profile", 3, 7));
        User.put("gender", toReadDataFromExcel("Profile", 4, 7));
        User.put("dateOfBirth", toReadDataFromExcel("Profile", 5, 7));
        User.put("age", toReadDataFromExcel("Profile", 6, 7));
        User.put("address", toReadDataFromExcel("Profile", 7, 7));
        User.put("address1", toReadDataFromExcel("Profile", 8, 7));
        User.put("country", toReadDataFromExcel("Profile", 9, 7));
        User.put("state", toReadDataFromExcel("Profile", 10, 7));
        User.put("city", toReadDataFromExcel("Profile", 11, 7));
        User.put("pinCode", toReadDataFromExcel("Profile", 12, 7));
        User.put("territory", toReadDataFromExcel("Profile", 13, 7));
        User.put("govtType", toReadDataFromExcel("Profile", 14, 7));
        User.put("govtId", toReadDataFromExcel("Profile", 15, 7));
        User.put("degree", toReadDataFromExcel("Profile", 23, 7));
        User.put("imageUrl", toReadDataFromExcel("Profile", 26, 7));
        User.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 7));
        User.put("userId", toReadDataFromExcel("Users", 1, 5));
        User.put("roleId", toReadDataFromExcel("Users", 3, 5));
        User.put("email", toReadDataFromExcel("Users", 5, 5));
        User.put("countryCode", "+91");
        User.put("phoneNo", "7675456457");
        User.put("govtProof", null);
        User.put("abhaNumber", null);
        User.put("emergencyContactNo", null);

        Map<String, String> userOnboardData = new HashMap<>();
        userOnboardData.put("degree", toReadDataFromExcel("Profile", 23, 7));
        userOnboardData.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 7));
        userOnboardData.put("govtType", toReadDataFromExcel("Profile", 14, 7));
        userOnboardData.put("govtId", toReadDataFromExcel("Profile", 15, 7));
        userOnboardData.put("userId", toReadDataFromExcel("Users", 1, 5));
        userOnboardData.put("status", "Active");
        userOnboardData.put("govtProof",
                "https://s3-dev-iksana-files-images.s3.ap-south-1.amazonaws.com/1716989670177/ae2f80ec-0a17-47ad-bf59-9d49cf4e08dd");
        userOnboardData.put("personalDetails", null);
        userOnboardData.put("user", null);

        Map<String, Object> map = new HashMap<>();
        map.put("user", User);
        map.put("userOnboardData", userOnboardData);
        map.put("veterans", null);

        APIResponse response1 = postRequestWithToken("rest/api/v1/updateMyProfile", getCMToken(), map);
        int StatusCode1 = response1.status();
        System.out.println("StatusCode1 " + StatusCode1);

        // <--------- Profile_TC_13 --------------->

        try {
            Assert.assertEquals(StatusCode1, 200);
            resultsCreateNewCell("Profile", 13, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 13, 10, "Fail");
        }

        // <----------------Profile_TC_14------------>

        String s1 = getBodyData(response1).toString();
        if (isJSONValid(s1)) {
            resultsCreateNewCell("Profile", 14, 10, "Pass");
        } else {
            resultsCreateNewCell("Profile", 14, 10, "Fail");
        }

        // <----------------Profile_TC_15 ------------>

        if (s1 != null && StatusCode1 == 200) {
            JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            if (d1 != null && d1.equals("0000")) {
                resultsCreateNewCell("Profile", 15, 10, "Pass");
            } else {
                resultsCreateNewCell("Profile", 15, 10, "Fail");
            }
        } else {
            resultsCreateNewCell("Profile", 15, 10, "Fail");
        }

        APIResponse response2 = postRequestWithToken("rest/api/v1/updateMyProfile", getDRToken(), map);
        int StatusCode2 = response2.status();
        System.out.println("StatusCode2 " + StatusCode2);

        // <--------- Profile_TC_16 --------------->

        try {
            Assert.assertEquals(StatusCode2, 200);
            String s2 = getBodyData(response2).toString();
            if (s2 != null && StatusCode2 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 16, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 16, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 16, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 16, 10, "Fail");
        }

        APIResponse response3 = postRequestWithToken("rest/api/v1/updateMyProfile", toReadDataFromExcel("Users", 4, 5),
                map);
        int StatusCode3 = response3.status();
        System.out.println("StatusCode3 " + StatusCode3);
        System.out.println(getBodyData(response3).toPrettyString());

        // <--------- Profile_TC_17 --------------->

        try {
            Assert.assertEquals(StatusCode3, 504);
            resultsCreateNewCell("Profile", 17, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 17, 10, "Fail");

        }

        Map<String, String> User1 = new HashMap<>();
        User1.put("firstName", toReadDataFromExcel("Profile", 1, 7));
        User1.put("lastName", toReadDataFromExcel("Profile", 2, 7));
        User1.put("userId", toReadDataFromExcel("Users", 1, 5));
        User1.put("roleId", toReadDataFromExcel("Users", 3, 5));
        User1.put("email", toReadDataFromExcel("Users", 5, 5));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user", User1);
        map1.put("userOnboardData", userOnboardData);
        map1.put("veterans", null);

        APIResponse response4 = postRequestWithToken("rest/api/v1/updateMyProfile", getCMToken(), map1);
        int StatusCode4 = response4.status();
        System.out.println("StatusCode4 " + StatusCode4);
        System.out.println(getBodyData(response4).toPrettyString());

        // <--------- Profile_TC_18 --------------->

        try {
            Assert.assertEquals(StatusCode4, 200);
            String s2 = getBodyData(response4).toString();
            if (s2 != null && StatusCode4 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 18, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 18, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 18, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 18, 10, "Fail");
        }

    }

    // Doctor Profile update API

    @Test(enabled = true, description = "Profile", priority = 4)
    public void updateDRProfile() throws IOException {

        Map<String, String> User = new HashMap<>();
        User.put("firstName", toReadDataFromExcel("Profile", 1, 5));
        User.put("lastName", toReadDataFromExcel("Profile", 2, 5));
        User.put("title", toReadDataFromExcel("Profile", 3, 5));
        User.put("gender", toReadDataFromExcel("Profile", 4, 5));
        User.put("dateOfBirth", toReadDataFromExcel("Profile", 5, 5));
        User.put("age", toReadDataFromExcel("Profile", 6, 5));
        User.put("address", toReadDataFromExcel("Profile", 7, 5));
        User.put("address1", toReadDataFromExcel("Profile", 8, 5));
        User.put("country", toReadDataFromExcel("Profile", 9, 5));
        User.put("state", toReadDataFromExcel("Profile", 10, 5));
        User.put("city", toReadDataFromExcel("Profile", 11, 5));
        User.put("pinCode", toReadDataFromExcel("Profile", 12, 5));
        User.put("territory", toReadDataFromExcel("Profile", 13, 5));
        User.put("govtType", toReadDataFromExcel("Profile", 14, 5));
        User.put("govtId", toReadDataFromExcel("Profile", 15, 5));
        User.put("degree", toReadDataFromExcel("Profile", 23, 5));
        User.put("imageUrl", toReadDataFromExcel("Profile", 26, 5));
        User.put("aboutYourShelf", toReadDataFromExcel("Profile", 25, 5));
        User.put("userId", toReadDataFromExcel("Users", 1, 6));
        User.put("roleId", toReadDataFromExcel("Users", 3, 6));
        User.put("email", toReadDataFromExcel("Users", 5, 6));
        User.put("countryCode", "+91");
        User.put("phoneNo", "7675456457");
        User.put("govtProof", null);
        User.put("abhaNumber", null);
        User.put("emergencyContactNo", null);

        Map<String, String> address = new HashMap<>();
        address.put("address1", toReadDataFromExcel("Profile", 7, 5));
        address.put("address2", toReadDataFromExcel("Profile", 7, 5));
        address.put("state", toReadDataFromExcel("Profile", 7, 5));
        address.put("city", toReadDataFromExcel("Profile", 7, 5));
        address.put("country", toReadDataFromExcel("Profile", 7, 5));
        address.put("pinCode", toReadDataFromExcel("Profile", 7, 5));
        address.put("territory", toReadDataFromExcel("Profile", 7, 5));
        Map<String, Object> personalDetails = new HashMap<>();
        personalDetails.put("address", address);
        personalDetails.put("title", toReadDataFromExcel("Profile", 3, 5));
        personalDetails.put("firstName", toReadDataFromExcel("Profile", 1, 5));
        personalDetails.put("lastName", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("profilePicture", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("gender", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("dateOfBirth", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("age", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("phoneNo", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("countryCode", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("email", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("emergencyContactNo", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("govtType", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("govtId", toReadDataFromExcel("Profile", 2, 5));
        personalDetails.put("govtProof", toReadDataFromExcel("Profile", 2, 5));

        Map<String, Object> professionalDetails = new HashMap<>();
        professionalDetails.put("degree", toReadDataFromExcel("Profile", 23, 5));
        professionalDetails.put("specialty", toReadDataFromExcel("Profile", 23, 5));
        professionalDetails.put("aboutYourShelf", toReadDataFromExcel("Profile", 23, 5));
        professionalDetails.put("registrationYear", toReadDataFromExcel("Profile", 23, 5));
        professionalDetails.put("registrationNumber", toReadDataFromExcel("Profile", 23, 5));
        professionalDetails.put("registrationType", toReadDataFromExcel("Profile", 23, 5));
        professionalDetails.put("registrationBoard", toReadDataFromExcel("Profile", 23, 5));

        Map<String, Object> clinicDetails = new HashMap<>();
        clinicDetails.put("clinic", toReadDataFromExcel("Profile", 23, 5));
        clinicDetails.put("clinicName", toReadDataFromExcel("Profile", 23, 5));
        clinicDetails.put("contactNo", toReadDataFromExcel("Profile", 23, 5));
        clinicDetails.put("emailId", toReadDataFromExcel("Profile", 23, 5));
        clinicDetails.put("address", toReadDataFromExcel("Profile", 23, 5));

        Map<String, Boolean> days = new HashMap<>();
        days.put("mon", true);
        days.put("tue", true);
        days.put("wed", true);
        days.put("thu", true);
        days.put("fri", true);
        days.put("sat", true);
        days.put("sun", true);
        List<Object> daysAvailable = new ArrayList<>();
        daysAvailable.add(days);
        Map<String, Object> setSchedule = new HashMap<>();
        setSchedule.put("daysAvailable", daysAvailable);
        setSchedule.put("startTime", "06:00 AM");
        setSchedule.put("endTime", "10:00 PM");

        Map<String, Object> userOnboardData = new HashMap<>();
        userOnboardData.put("govtType", toReadDataFromExcel("Profile", 14, 5));
        userOnboardData.put("govtId", toReadDataFromExcel("Profile", 15, 5));
        userOnboardData.put("userId", toReadDataFromExcel("Users", 1, 6));
        userOnboardData.put("status", "Active");
        userOnboardData.put("govtProof",
                "https://s3-dev-iksana-files-images.s3.ap-south-1.amazonaws.com/1716989670177/ae2f80ec-0a17-47ad-bf59-9d49cf4e08dd");
        userOnboardData.put("personalDetails", personalDetails);
        userOnboardData.put("professionalDetails", professionalDetails);
        userOnboardData.put("clinicDetails", clinicDetails);
        userOnboardData.put("setSchedule", setSchedule);
        userOnboardData.put("user", null);

        Map<String, Object> map = new HashMap<>();
        map.put("user", User);
        map.put("userOnboardData", userOnboardData);
        map.put("veterans", null);

        APIResponse response1 = postRequestWithToken("rest/api/v1/updateMyProfile", getDRToken(), map);
        int StatusCode1 = response1.status();
        System.out.println("StatusCode1 " + StatusCode1);

        // <--------- Profile_TC_19 --------------->

        try {
            Assert.assertEquals(StatusCode1, 200);
            resultsCreateNewCell("Profile", 19, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 19, 10, "Fail");
        }

        // <----------------Profile_TC_20------------>

        String s1 = getBodyData(response1).toString();
        if (isJSONValid(s1)) {
            resultsCreateNewCell("Profile", 20, 10, "Pass");
        } else {
            resultsCreateNewCell("Profile", 20, 10, "Fail");
        }

        // <----------------Profile_TC_21 ------------>

        if (s1 != null && StatusCode1 == 200) {
            JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            if (d1 != null && d1.equals("0000")) {
                resultsCreateNewCell("Profile", 21, 10, "Pass");
            } else {
                resultsCreateNewCell("Profile", 21, 10, "Fail");
            }
        } else {
            resultsCreateNewCell("Profile", 21, 10, "Fail");
        }

        APIResponse response2 = postRequestWithToken("rest/api/v1/updateMyProfile", getAdminToken(), map);
        int StatusCode2 = response2.status();
        System.out.println("StatusCode2 " + StatusCode2);

        // <--------- Profile_TC_22 --------------->

        try {
            Assert.assertEquals(StatusCode2, 200);
            String s2 = getBodyData(response2).toString();
            if (s2 != null && StatusCode2 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 22, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 22, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 22, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 22, 10, "Fail");
        }

        APIResponse response3 = postRequestWithToken("rest/api/v1/updateMyProfile", toReadDataFromExcel("Users", 4, 6),
                map);
        int StatusCode3 = response3.status();
        System.out.println("StatusCode3 " + StatusCode3);
        System.out.println(getBodyData(response3).toPrettyString());

        // <--------- Profile_TC_23 --------------->

        try {
            Assert.assertEquals(StatusCode3, 504);
            resultsCreateNewCell("Profile", 23, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 23, 10, "Fail");

        }

        Map<String, String> User1 = new HashMap<>();
        User1.put("firstName", toReadDataFromExcel("Profile", 1, 5));
        User1.put("lastName", toReadDataFromExcel("Profile", 2, 5));
        User1.put("userId", toReadDataFromExcel("Users", 1, 6));
        User1.put("roleId", toReadDataFromExcel("Users", 3, 6));
        User1.put("email", toReadDataFromExcel("Users", 5, 6));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user", User1);
        map1.put("userOnboardData", userOnboardData);
        map1.put("veterans", null);

        APIResponse response4 = postRequestWithToken("rest/api/v1/updateMyProfile", getDRToken(), map1);
        int StatusCode4 = response4.status();
        System.out.println("StatusCode4 " + StatusCode4);
        System.out.println(getBodyData(response4).toPrettyString());

        // <--------- Profile_TC_24 --------------->

        try {
            Assert.assertEquals(StatusCode4, 200);
            String s2 = getBodyData(response4).toString();
            if (s2 != null && StatusCode4 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s2).getAsJsonObject();
                String d1 = jsonObject1.get("code").getAsString();
                if (d1 != null && d1.equals("1111")) {
                    resultsCreateNewCell("Profile", 24, 10, "Pass");
                } else {
                    resultsCreateNewCell("Profile", 24, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Profile", 24, 10, "Fail");
            }
        } catch (AssertionError e) {
            resultsCreateNewCell("Profile", 24, 10, "Fail");
        }

    }

}
