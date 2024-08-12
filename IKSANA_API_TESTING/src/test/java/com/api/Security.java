package com.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseClass;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class Security extends BaseClass {


    // Verify the phone number

    @Test(enabled = true, description = "Login", priority = 1)
    public void LoginVerifyPhone() throws IOException {

        try {
            APIResponse response1 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1))
                            .setQueryParam("login", "true"));
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <-------------- Login_TC_01 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Login", 1, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 1, 10, "Fail");
            }

            // <-------------- Login_TC_02 --------------->

            String s = getBodyData(response1).toString();
            if (isJSONValid(s) && StatusCode1 == 200) {
                resultsCreateNewCell("Login", 2, 10, "Pass");
            } else {
                resultsCreateNewCell("Login", 2, 10, "Fail");
            }

            // <-------------- Login_TC_03 --------------->

            APIResponse response2 = request().post(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1))
                            .setQueryParam("login", "true"));
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2--->" + StatusCode2);

            

            try {
                Assert.assertEquals(StatusCode2, 405);
                resultsCreateNewCell("Login", 3, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 3, 10, "Fail");
            }

            // <-------------- Login_TC_04 --------------->

            APIResponse response3 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("login", "true"));
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 400);
                resultsCreateNewCell("Login", 4, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 4, 10, "Fail");
            }

            // <-------------- Login_TC_05 --------------->

            APIResponse response4 = request().get(DevOrigin + "validate-phone",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1))
                            .setQueryParam("login", "true"));
            int StatusCode4 = response4.status();
            System.out.println("StatusCode4--->" + StatusCode4);
            try {
                Assert.assertEquals(StatusCode4, 404);
                resultsCreateNewCell("Login", 5, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 5, 10, "Fail");
            }

            // <-------------- Login_TC_06 --------------->

            APIResponse response5 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json").setHeader("Application", "Iksana-Base")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", "2346567564")
                            .setQueryParam("login", "true"));
            int StatusCode5 = response5.status();
            System.out.println("StatusCode5---->" + StatusCode5);
            try {
                Assert.assertEquals(StatusCode5, 200);
                String s1 = getBodyData(response5).toString();
                JsonObject jsonObject3 = JsonParser.parseString(s1).getAsJsonObject();
                String d5 = jsonObject3.get("code").getAsString();
                String d6 = jsonObject3.get("message").getAsString();
                System.out.println("Code--->" + d5);
                System.out.println("Message---->" + d6);
                if (d5 != null && d6 != null && d5!="0000") {
                    resultsCreateNewCell("Login", 6, 10, "Pass");
                } else {
                    resultsCreateNewCell("Login", 6, 10, "Fail");
                }
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 6, 10, "Fail");
            }


            // <------------ Login_TC_07 ------------->

            APIResponse response6 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json").setHeader("Application", "Iksana-Base")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", "9800000010")
                            .setQueryParam("login", "true"));
            int StatusCode6 = response6.status();
            System.out.println("StatusCode6---->"+StatusCode6);
            try {
                Assert.assertEquals(StatusCode6, 200);
                String s1 = getBodyData(response6).toString();
                JsonObject jsonObject3 = JsonParser.parseString(s1).getAsJsonObject();
                String d5 = jsonObject3.get("code").getAsString();
                String d6 = jsonObject3.get("message").getAsString();
                System.out.println("Code--->" + d5);
                System.out.println("Message---->" + d6);
                if (d5 != null && d6 != null && d5!="0000") {
                    resultsCreateNewCell("Login", 7, 10, "Pass");
                } else {
                    resultsCreateNewCell("Login", 7, 10, "Fail");
                }
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 7, 10, "Fail");
            }

    
        // <------------ Login_TC_08 ------------->

        for (int i = 1; i < 8; i++) {
            APIResponse response7 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json").setHeader("Application", "Iksana-Base")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, i))
                            .setQueryParam("login", "true"));
            int StatusCode7 = response7.status();

            System.out.println("StatusCode7---->" + StatusCode7);

            try {
                Assert.assertEquals(StatusCode7, 200);
                String s4 = getBodyData(response7).toString();
                System.out.println(s4);
                JsonObject jsonObject2 = JsonParser.parseString(s4).getAsJsonObject();
                String code=jsonObject2.get("code").getAsString();
                String data = jsonObject2.get("data").getAsString();
                System.out.println("data--->" + data);
                if(code!=null && data!=null){
                toCreateNewCell("Registration", 1, i, data);
                resultsCreateNewCell("Login", 8, 10, "Pass");
                }else {
                    resultsCreateNewCell("Login", 8, 10, "Fail");
                }
            } catch (AssertionError e) {
                resultsCreateNewCell("Login", 8, 10, "Fail");
            }
        }

        System.out.println("<-------------Test1 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test1 Completed---------->");
        }
    }


    // Verify the login with phone number

    @Test(enabled = true, description = "Login", priority = 2)
    public void LoginWithOTP() throws IOException {
        try{
            String value = toReadDataFromExcel("Registration", 0, 1) + ":" + toReadDataFromExcel("Registration", 1, 1);
            String auth = "Basic " + encryption(value);
            APIResponse response1 = request().get(DevOrigin + "login-with-otp",
                    RequestOptions.create().setHeader("Authorization", auth).setHeader("channel", "Mobile").setHeader("Application", "Iksana-Base"));
            int statusCode1 = response1.status();
            System.out.println("statusCode1--->" + statusCode1);

            // <------------ Login_TC_09 ------------->

            try{
                Assert.assertEquals(statusCode1, 200);
                resultsCreateNewCell("Login", 9, 10, "Pass");           
            }catch(AssertionError e){
                resultsCreateNewCell("Login", 9, 10, "Fail"); 
            }

            // <------------ Login_TC_10 ------------->

            String s1=getBodyData(response1).toString();
            if(isJSONValid(s1)){
                resultsCreateNewCell("Login", 10, 10, "Pass");           
            }else{
                resultsCreateNewCell("Login", 10, 10, "Fail"); 
            }  

            // <------------ Login_TC_11 ------------->

            JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
            String code = jsonObject1.get("code").getAsString();
            String message = jsonObject1.get("message").getAsString();
            if(code!=null && message!=null){
                resultsCreateNewCell("Login", 11, 10, "Pass");           
            }else{
                resultsCreateNewCell("Login", 11, 10, "Fail"); 
            } 


            // <------------ Login_TC_12, Login_TC_13, Login_TC_14, Login_TC_15, Login_TC_16, Login_TC_17, Login_TC_18  ------------->


            for (int i = 1; i < 8; i++) {
                String value3 = toReadDataFromExcel("Registration", 0, i) + ":" + toReadDataFromExcel("Registration", 1, i);
                String auth3 = "Basic " +encryption(value3);;
                APIResponse response3 = request().get(DevOrigin + "login-with-otp",
                        RequestOptions.create().setHeader("Authorization", auth3).setHeader("channel", "Mobile").setHeader("Application", "Iksana-Base"));
                int statusCode3 = response3.status();
                System.out.println("statusCode3--->" + statusCode3);
                String s3 = getBodyData(response3).toString();
                if (statusCode3 == 200) {
                    JsonObject jsonObject3 = JsonParser.parseString(s3).getAsJsonObject();
                    String d1 = jsonObject3.get("code").getAsString();
                    if (d1.equals("0000")) {
                        JsonObject jsonObject4 = jsonObject3.get("data").getAsJsonObject();
                        String token = jsonObject4.get("token").getAsString();
                        toCreateNewCell("Users", 2, i, token);
                            JsonObject jsonObject5 = jsonObject4.get("user").getAsJsonObject();
                            String userId = jsonObject5.get("userId").getAsString();
                            String roleId = jsonObject5.get("roleId").getAsString();
                            toCreateNewCell("Users", 1, i, userId);
                            toCreateNewCell("Users", 3, i, roleId);
                        System.out.println("token--->" + token);
                        if(token != null){
                            resultsCreateNewCell("Login", 11+i, 10, "Pass");           
                        }else{
                            resultsCreateNewCell("Login", 11+i, 10, "Fail"); 
                        }
                    }
                        if (d1.equals("8888")) {
                            JsonObject jsonObject6 = jsonObject3.get("data").getAsJsonObject();
                            JsonObject jsonObject7 = jsonObject6.get("user").getAsJsonObject();
                            String userId = jsonObject7.get("userId").getAsString();
                            String email = jsonObject7.get("email").getAsString();
                            String phoneNo = jsonObject7.get("phoneNo").getAsString();
                            String roleId = jsonObject7.get("roleId").getAsString();
                            toCreateNewCell("Users", 1, i, userId);
                            toCreateNewCell("Users", 3, i, roleId);
                            Map<String, Object> data = new HashMap<String, Object>();
                            data.put("userId", userId);
                            data.put("email", email);
                            data.put("countryCode", "+91");
                            data.put("phoneNo", phoneNo);
                            data.put("roleId", roleId);
    
                            APIResponse response = postRequestWithoutToken("session-verification", "WEB", data);
                            int StatusCode1 = response.status();
                            System.out.println("statusCode1--->" + StatusCode1);
                            try {
                                Assert.assertEquals(StatusCode1, 200);
                                String s2 = getBodyData(response).toString();
                                JsonObject jsonObject4 = JsonParser.parseString(s2).getAsJsonObject();
                                JsonObject jsonObject5 = jsonObject4.get("data").getAsJsonObject();
                                String token = jsonObject5.get("token").getAsString();
                                toCreateNewCell("Users", 2, i, token);
                                System.out.println("token--->" + token);
                                if(token != null){
                                    resultsCreateNewCell("Login", 11+i, 10, "Pass");           
                                }else{
                                    resultsCreateNewCell("Login", 11+i, 10, "Fail"); 
                                }
                            } catch (AssertionError e1) {
                                resultsCreateNewCell("Login", 11+i, 10, "Fail"); 
                            }
                        }
                       }      
                    }
                    System.out.println("<-------------Test2 Completed---------->");
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("<-------------Test2 Completed---------->");
        }
    }
        
    // Login with Username and Password
    
    @Test(enabled = true, description = "Login", priority = 3)
    public void LoginWithUsernamePassword() throws IOException {
        try{
            String value = toReadDataFromExcel("Users", 5, 1) + ":" + toReadDataFromExcel("Users", 6, 1);
            String auth = "Basic " + encryption(value);
            APIResponse response1 = request().get(DevOrigin + "login",
                    RequestOptions.create().setHeader("Authorization", auth).setHeader("channel", toReadDataFromExcel("Users", 7, 1)).setHeader("Application", "Iksana-Base"));
            int statusCode1 = response1.status();
            System.out.println("statusCode1--->" + statusCode1);
               
            // <------------ Login_TC_19 ------------->

            try{
                Assert.assertEquals(statusCode1, 200);
                resultsCreateNewCell("Login", 19, 10, "Pass");           
            }catch(AssertionError e){
                resultsCreateNewCell("Login", 19, 10, "Fail"); 
            }

            // <------------ Login_TC_20 ------------->

            String s1=getBodyData(response1).toString();
            if(isJSONValid(s1)){
                resultsCreateNewCell("Login", 20, 10, "Pass");           
            }else{
                resultsCreateNewCell("Login", 20, 10, "Fail"); 
            } 

            // <------------ Login_TC_21 ------------->

            JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
            String code = jsonObject1.get("code").getAsString();
            String message = jsonObject1.get("message").getAsString();
            if(code!=null && message!=null){
                if(code.equals("0000") || code.equals("8888")){
                resultsCreateNewCell("Login", 21, 10, "Pass");
                }else{
                    resultsCreateNewCell("Login", 21, 10, "Fail"); 
                }           
            }else{
                resultsCreateNewCell("Login", 21, 10, "Fail"); 
            }


            // <------------ Login_TC_22,Login_TC_23, Login_TC_24, Login_TC_25, Login_TC_26, Login_TC_27, Login_TC_28, Login_TC_29, Login_TC_30,  ------------->                     

            for (int i = 1; i < 10; i++) {
                String value1 = toReadDataFromExcel("Users", 5, i) + ":" + toReadDataFromExcel("Users", 6, i);
                String auth1 = "Basic " + encryption(value1);
                APIResponse response2 = request().get(DevOrigin + "login",
                        RequestOptions.create().setHeader("Authorization", auth1).setHeader("channel", toReadDataFromExcel("Users", 7, i)).setHeader("Application", "Iksana-Base"));
                int statusCode2 = response2.status();
                System.out.println("statusCode2--->" + statusCode2);
                String s3 = getBodyData(response2).toString();
                if (statusCode2 == 200) {
                    JsonObject jsonObject3 = JsonParser.parseString(s3).getAsJsonObject();
                    String d1 = jsonObject3.get("code").getAsString();
                    if (d1.equals("0000")) {
                        JsonObject jsonObject4 = jsonObject3.get("data").getAsJsonObject();
                        String token = jsonObject4.get("token").getAsString();
                        toCreateNewCell("Users", 2, i, token);
                            JsonObject jsonObject5 = jsonObject4.get("user").getAsJsonObject();
                            String userId = jsonObject5.get("userId").getAsString();
                            String roleId = jsonObject5.get("roleId").getAsString();
                            toCreateNewCell("Users", 1, i, userId);
                            toCreateNewCell("Users", 3, i, roleId);                           
                        System.out.println("token--->1" + token);
                        if(token != null){
                            resultsCreateNewCell("Login", 21+i, 10, "Pass");           
                        }else{
                            resultsCreateNewCell("Login", 21+i, 10, "Fail"); 
                        } 
                    }
                        if (d1.equals("8888")) {
                            JsonObject jsonObject6 = jsonObject3.get("data").getAsJsonObject();
                            JsonObject jsonObject7 = jsonObject6.get("user").getAsJsonObject();
                            String userId = jsonObject7.get("userId").getAsString();
                            String email = jsonObject7.get("email").getAsString();
                            String phoneNo = jsonObject7.get("phoneNo").getAsString();
                            String roleId = jsonObject7.get("roleId").getAsString();
                            toCreateNewCell("Users", 1, i, userId);
                            toCreateNewCell("Users", 3, i, roleId);
                            System.err.println("userId "+userId);
                            System.err.println("roleId "+roleId);
                            Map<String, Object> data = new HashMap<String, Object>();
                            data.put("userId", userId);
                            data.put("email", email);
                            data.put("countryCode", "+91");
                            data.put("phoneNo", phoneNo);
                            data.put("roleId", roleId);
    
                            APIResponse response = postRequestWithoutToken("session-verification", toReadDataFromExcel("Users", 7, i), data);
                            int StatusCode1 = response.status();
                            System.out.println("statusCode1--->" + StatusCode1);
                            try {
                                Assert.assertEquals(StatusCode1, 200);
                                String s2 = getBodyData(response).toString();
                                JsonObject jsonObject4 = JsonParser.parseString(s2).getAsJsonObject();
                                JsonObject jsonObject5 = jsonObject4.get("data").getAsJsonObject();
                                String token = jsonObject5.get("token").getAsString();
                                toCreateNewCell("Users", 2, i, token);
                                System.out.println("token--->2" + token);
                                if(token != null){
                                    resultsCreateNewCell("Login", 21+i, 10, "Pass");           
                                }else{
                                    resultsCreateNewCell("Login", 21+i, 10, "Fail"); 
                                }
                            } catch (AssertionError e1) {
                            }
                        }
                    }
                }
                System.out.println("<-------------Test3 Completed---------->");
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("<-------------Test3 Completed---------->");
        }
    }



    

    @Test(enabled = false, priority = 3)
    public void Logout() throws IOException {
        for (int i = 1; i < 5; i++) {
            APIResponse response1 = request().put(DevOrigin + "signout",
                    RequestOptions.create().setHeader("Authorization", "Bearer " + toReadDataFromExcel("Users", 2, i)).setHeader("Application", "Iksana-Base"));
            int StatusCode1 = response1.status();
            System.out.println("statusCode1--->" + StatusCode1);
            String token=toReadDataFromExcel("Users", 2, i);
            System.out.println(token);
            try{
            if(StatusCode1==200){
                String s2 = getBodyData(response1).toString();
                JsonObject jsonObject4 = JsonParser.parseString(s2).getAsJsonObject();
                String d1=jsonObject4.get("code").getAsString();
                System.out.println(d1);
                if(d1.equals("0000")){
                toCreateNewCell("Users", 4, i, token);
                }
            }               
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        LoginVerifyPhone();
        LoginWithOTP();
        }
        }


       @Test(enabled=false)
       public void testlogin() throws IOException{

        String s=null;
        System.err.println(isJSONValid(s));

        
        
            }

       


    }

