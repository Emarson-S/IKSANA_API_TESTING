package com.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseClass;
import com.base.Email;
import com.base.PaymentClass;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mailosaur.MailosaurException;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
public class Registration extends BaseClass {

    // Verfiy the Mobile number on User Explore process

    @Test(enabled = true, description = "UserExplore", priority = 1)
    public void phoneNumberverification() throws IOException {

        try {

            String a = toReadDataFromExcel("Registration", 0, 1);
            long b = Long.parseLong(a) + 1;
            String c = String.valueOf(b);
            toCreateNewCell("Registration", 0, 1, c);
            String a1 = toReadDataFromExcel("Registration", 0, 2);
            long b1 = Long.parseLong(a1) + 1;
            String c1 = String.valueOf(b1);
            toCreateNewCell("Registration", 0, 2, c1);

            APIResponse response1 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1))
                            .setQueryParam("login", "false"));
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <-------------- Registration_TC_01 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Registration", 1, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 1, 10, "Fail");
            }

            // <-------------- Registration_TC_02 --------------->

            String s = getBodyData(response1).toString();
            if (isJSONValid(s) && StatusCode1 == 200) {
                resultsCreateNewCell("Registration", 2, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 2, 10, "Fail");
            }
            JsonObject jsonObject2 = JsonParser.parseString(s).getAsJsonObject();
            String d4 = jsonObject2.get("data").getAsString();
            System.out.println("data--->" + d4);
            toCreateNewCell("Registration", 1, 1, d4);

            APIResponse response2 = request().post(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1))
                            .setQueryParam("login", "false"));
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2--->" + StatusCode2);

            // <-------------- Registration_TC_03 --------------->

            try {
                Assert.assertEquals(StatusCode2, 405);
                resultsCreateNewCell("Registration", 3, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 3, 10, "Fail");
            }

            // <-------------- Registration_TC_04 --------------->

            APIResponse response3 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("login", "false"));
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 400);
                resultsCreateNewCell("Registration", 4, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 4, 10, "Fail");
            }

            // <-------------- Registration_TC_05 --------------->

            APIResponse response4 = request().get(DevOrigin + "validate-phone",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1))
                            .setQueryParam("login", "false"));
            int StatusCode4 = response4.status();
            System.out.println("StatusCode4--->" + StatusCode4);
            try {
                Assert.assertEquals(StatusCode4, 404);
                resultsCreateNewCell("Registration", 5, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 5, 10, "Fail");
            }

            // <-------------- Registration_TC_06 --------------->

            APIResponse response5 = request().get(DevOrigin + "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", "2346567564")
                            .setQueryParam("login", "false"));
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
                if (d5 != null && d6 != null) {
                    resultsCreateNewCell("Registration", 6, 10, "Pass");
                } else {
                    resultsCreateNewCell("Registration", 6, 10, "Fail");
                }
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 6, 10, "Fail");
            }

            System.out.println("<-------------Test1 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test1 Completed---------->");
        }
    }

    // Verify the OTP on User explore process

    @Test(enabled = true, description = "UserExplore", priority = 2)
    public void userExploreOTPVerification() throws IOException {
        try {

            APIResponse response1 = request().get(DevOrigin + "otp-verification",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("otp", toReadDataFromExcel("Registration", 1, 1))
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1)));
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1--->" + StatusCode1);

            // <-------------- Registration_TC_07 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Registration", 7, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 7, 10, "Fail");
            }

            // <-------------- Registration_TC_08 --------------->

            String s = response1.toString();
            if (isJSONValid(s) && StatusCode1 == 200) {
                resultsCreateNewCell("Registration", 8, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 8, 10, "Pass");
            }

            // <-------------- Registration_TC_09 --------------->

            APIResponse response2 = request().post(DevOrigin + "otp-verification",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("otp", toReadDataFromExcel("Registration", 1, 1))
                            .setQueryParam("phoneNo", toReadDataFromExcel("Registration", 0, 1)));
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2--->" + StatusCode2);
            try {
                Assert.assertEquals(StatusCode2, 405);
                resultsCreateNewCell("Registration", 9, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 9, 10, "Fail");
            }

            // <-------------- Registration_TC_10 --------------->

            APIResponse response3 = request().get(DevOrigin + "otp-verification",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("otp", toReadDataFromExcel("Registration", 1, 1)));
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3--->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 400);
                resultsCreateNewCell("Registration", 10, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 10, 10, "Fail");
            }

            // <-------------- Registration_TC_11 --------------->

            List<String> list1 = new ArrayList<>();
            list1.add("");
            list1.add(null);
            list1.add("000000");
            list1.add(toReadDataFromExcel("Registration", 1, 1));
            list1.add(toReadDataFromExcel("Registration", 1, 1));
            list1.add(toReadDataFromExcel("Registration", 1, 1));

            List<String> list2 = new ArrayList<>();
            list2.add(toReadDataFromExcel("Registration", 0, 1));
            list2.add(toReadDataFromExcel("Registration", 0, 1));
            list2.add(toReadDataFromExcel("Registration", 0, 1));
            list2.add("");
            list2.add(null);
            list2.add("2345654345");

            for (int i = 0; i < list1.size(); i++) {
                APIResponse response4 = request().get(DevOrigin + "otp-verification",
                        RequestOptions.create().setHeader("Content-Type", "application/json")
                                .setQueryParam("otp", list1.get(i))
                                .setQueryParam("phoneNo", list2.get(i)));
                int StatusCode4 = response4.status();
                System.out.println("StatusCode4--->" + StatusCode4);
                if (StatusCode4 == 200) {
                    String s1 = getBodyData(response4).toString();
                    JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                    String d4 = jsonObject2.get("code").getAsString();
                    String d5 = jsonObject2.get("message").getAsString();
                    System.out.println("Code--->" + d4);
                    System.out.println("Message---->" + d5);
                    if (d4 != null && d5 != null && d4 != "0000") {
                        resultsCreateNewCell("Registration", 11, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Registration", 11, 10, "Fail");
                        break;
                    }
                } else {
                    resultsCreateNewCell("Registration", 11, 10, "Fail");
                    break;
                }
            }
            System.out.println("<-------------Test2 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test2 Completed---------->");
        }
    }

    // Verify the User-Explore form

    @Test(enabled = true, description = "UserExplore", priority = 3)
    public void userExploreForm() throws IOException {

        try {
            String email = Email.generateEmail();
            toCreateNewCell("Registration", 3, 1, email);
            Map<String, Object> map = new HashMap<>();
            map.put("fullName", toReadDataFromExcel("Registration", 2, 1));
            map.put("email", toReadDataFromExcel("Registration", 3, 1));
            map.put("phoneNo", toReadDataFromExcel("Registration", 0, 1));
            map.put("address", toReadDataFromExcel("Registration", 4, 1));
            map.put("countryCode", "+91");
            map.put("pincode", toReadDataFromExcel("Registration", 5, 1));

            APIResponse response1 = postRequestWithoutToken("rest/api/v1/user-explore", "Mobile", map);
            int Statuscode1 = response1.status();
            String s = getBodyData(response1).toString();
            System.out.println("StatusCode1 -->" + Statuscode1);

            // <-------------- Registration_TC_12 --------------->

            try {
                Assert.assertEquals(Statuscode1, 200);
                resultsCreateNewCell("Registration", 12, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 12, 10, "Fail");
            }

            // <-------------- Registration_TC_13 --------------->

            if (isJSONValid(s) && Statuscode1 == 200) {
                resultsCreateNewCell("Registration", 13, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 13, 10, "Fail");
            }

            // <-------------- Registration_TC_14 --------------->

            Map<String, Object> map1 = new HashMap<>();
            map1.put("lastName", toReadDataFromExcel("Registration", 2, 1));
            APIResponse response2 = postRequestWithoutToken("rest/api/v1/user-explore", "Mobile", map1);
            int Statuscode2 = response2.status();
            System.out.println("StatusCode2 -->" + Statuscode2);
            try {
                Assert.assertEquals(Statuscode2, 400);
                resultsCreateNewCell("Registration", 14, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 14, 10, "Fail");
            }

            // <-------------- Registration_TC_15 --------------->

            for (int i = 0; i < getContentTypes().size(); i++) {
                APIResponse response3 = request().post(DevOrigin + "rest/api/v1/user-explore",
                        RequestOptions.create().setHeader("Content-Type", getContentTypes().get(i)).setData(map));
                int StatusCode3 = response3.status();
                System.out.println("StatusCode3--->" + StatusCode3);
                try {
                    Assert.assertEquals(StatusCode3, 415);
                    resultsCreateNewCell("Registration", 15, 10, "Pass");
                } catch (AssertionError e) {
                    resultsCreateNewCell("Registration", 15, 10, "Fail");
                  //  break;
                }
            }
            System.out.println("<-------------Test3 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test3 Completed---------->");
        }

    }

    // Creating the user registation

    @Test(enabled = true, description = "Registration", priority = 4)
    public void getRegistration() throws IOException {

        try {
            String a = toReadDataFromExcel("Registration", 6, 1);
            long b = Long.parseLong(a) + 1;
            String c = String.valueOf(b);
            toCreateNewCell("Registration", 6, 1, c);

            APIResponse response1 = request().get(
                    DevOrigin + "rest/api/v1/get-registration/" + toReadDataFromExcel("Registration", 6, 1),
                    RequestOptions.create()
                            .setHeader("Content-Type", "application/json").setHeader("channel", "Mobile"));
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1--->" + StatusCode1);

            // <-------------- Registration_TC_16 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Registration", 16, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 16, 10, "Fail");
            }

            // <-------------- Registration_TC_17 --------------->

            String s = getBodyData(response1).toString();
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            JsonObject data = jsonObject1.get("data").getAsJsonObject();
            String d2 = data.get("registrationId").getAsString();
            toCreateNewCell("Registration", 7, 1, d2);
            System.out.println(d2);
            if (isJSONValid(s) && StatusCode1 == 200) {
                resultsCreateNewCell("Registration", 17, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 17, 10, "Fail");
            }

            // <-------------- Registration_TC_18 --------------->

            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Registration", 18, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 18, 10, "Fail");
            }

            // <-------------- Registration_TC_19 --------------->

            APIResponse response2 = request().get(DevOrigin + "rest/api/v1/get-registration/" + null);
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2--->" + StatusCode2);
            if (StatusCode2 == 200) {
                String s1 = getBodyData(response2).toString();
                JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                String d3 = jsonObject2.get("code").getAsString();
                String d4 = jsonObject2.get("message").getAsString();
                System.out.println("Code--->" + d3);
                System.out.println("Message---->" + d4);
                if (d3 != null && d4 != null) {
                    resultsCreateNewCell("Registration", 19, 10, "Pass");
                } else {
                    resultsCreateNewCell("Registration", 19, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Registration", 19, 10, "Fail");
            }
            System.out.println("<-------------Test4 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test4 Completed---------->");
        }
    }

    // Verify the email address on registration

    @Test(enabled = true, description = "Registration", priority = 5)
    public void emailVerification() throws IOException, MailosaurException {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("email", toReadDataFromExcel("Registration", 3, 1));
            map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
            map.put("value", "email");

            APIResponse response1 = postRequestWithoutToken("rest/api/v1/user-verification", "Mobile", map);
            int Statuscode1 = response1.status();
            System.out.println("Statuscode1--->" + Statuscode1);

            // <-------------- Registration_TC_20 --------------->

            try {
                Assert.assertEquals(Statuscode1, 200);
                resultsCreateNewCell("Registration", 20, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 20, 10, "Fail");
            }
            String s = getBodyData(response1).toString();
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            System.out.println(d1);
            System.out.println(d2);

            // <-------------- Registration_TC_21 --------------->

            if (isJSONValid(s) && Statuscode1 == 200) {
                resultsCreateNewCell("Registration", 21, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 21, 10, "Fail");
            }

            // <-------------- Registration_TC_22 --------------->

            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Registration", 22, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 22, 10, "Fail");
            }

            // <-------------- Registration_TC_23 --------------->

            List<String> list1 = new ArrayList<>();
            list1.add("");
            list1.add(null);
            list1.add("emarson.com");
            list1.add("prakash.dhanapalan@mavens-i.com");
            list1.add(toReadDataFromExcel("Registration", 3, 1));
            list1.add(toReadDataFromExcel("Registration", 3, 1));
            list1.add(toReadDataFromExcel("Registration", 3, 1));
            List<String> list2 = new ArrayList<>();
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add("");
            list2.add(null);
            list2.add("9876733535");
            Map<String, Object> map1 = new HashMap<>();
            for (int i = 0; i < list1.size(); i++) {
                map1.put("email", list1.get(i));
                map1.put("registrationId", list2.get(i));
                map1.put("value", "email");

                APIResponse response2 = postRequestWithoutToken("rest/api/v1/user-verification", "Mobile", map1);
                int Statuscode2 = response2.status();
                System.out.println("Statuscode2--->" + Statuscode2);
                if (Statuscode2 == 200) {
                    String s1 = getBodyData(response2).toString();
                    JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                    String d4 = jsonObject2.get("code").getAsString();
                    String d5 = jsonObject2.get("message").getAsString();
                    System.out.println("Code--->" + d4);
                    System.out.println("Message---->" + d5);
                    if (d4 != null && d5 != null && d4 != "0000") {
                        resultsCreateNewCell("Registration", 23, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Registration", 23, 10, "Fail");
                        break;
                    }
                } else {
                    resultsCreateNewCell("Registration", 23, 10, "Fail");
                    break;
                }
            }

            // <-------------- Registration_TC_24 --------------->

            Map<String, Object> map2 = new HashMap<>();
            map2.put("email", toReadDataFromExcel("Registration", 3, 1));
            map2.put("value", "email");
            APIResponse response3 = postRequestWithoutToken("rest/api/v1/user-verification", "Mobile", map2);
            int Statuscode3 = response3.status();
            System.out.println("Statuscode3--->" + Statuscode3);
            try {
                Assert.assertEquals(Statuscode3, 400);
                resultsCreateNewCell("Registration", 24, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 24, 10, "Fail");
            }

            // <-------------- Registration_TC_25 --------------->

            for (int i = 0; i < getContentTypes().size(); i++) {
                APIResponse response4 = request().post(DevOrigin + "rest/api/v1/user-verification",
                        RequestOptions.create().setHeader("Content-Type", getContentTypes().get(i)).setData(map));
                int Statuscode4 = response4.status();
                System.out.println("Statuscode4--->" + Statuscode4);
                try {
                    Assert.assertEquals(Statuscode4, 415);
                    resultsCreateNewCell("Registration", 25, 10, "Pass");
                } catch (AssertionError e) {
                    resultsCreateNewCell("Registration", 25, 10, "Fail");
                    break;
                }
            }

            System.out.println("<-------------Test5 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test5 Completed---------->");
        }
    }

    // Verify the Subscription List

    @Test(enabled = true, description = "Master", priority = 6)
    public void getSubscriptionList() throws IOException {

        try {
            String organizationCode = "WAPL";
            APIResponse response1 = getRequestWithoutToken("rest/api/v1/getSubscription/" + organizationCode);
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1--->" + StatusCode1);

            // <-------------- Registration_TC_26 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Registration", 26, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 26, 10, "Fail");
            }

            // <-------------- Registration_TC_27 --------------->

            String s = getBodyData(response1).toString();
            if (isJSONValid(s)) {
                resultsCreateNewCell("Registration", 27, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 27, 10, "Fail");
            }

            // <-------------- Registration_TC_28 --------------->

            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            JsonArray jsonaArray = jsonObject1.get("data").getAsJsonArray();
            JsonObject jsonObject = jsonaArray.get(0).getAsJsonObject();
            String d3 = jsonObject.get("subscriptionCode").getAsString();
            toCreateNewCell("Registration", 23, 1, d3);
            String d4 = jsonObject.get("subscriptionName").getAsString();
            toCreateNewCell("Registration", 24, 1, d4);
            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Registration", 28, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 28, 10, "Fail");
            }

            // <-------------- Registration_TC_29 --------------->

            APIResponse response2 = getRequestWithoutToken("rest/api/v1/getSubscription/" + null);
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2--->" + StatusCode2);
            if (StatusCode2 == 200) {
                String s1 = getBodyData(response2).toString();
                System.out.println(s1);
                JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                String d5 = jsonObject2.get("code").getAsString();
                String d6 = jsonObject2.get("message").getAsString();
                if (d5 != null && d6 != null && d5 != "0000") {
                    resultsCreateNewCell("Registration", 29, 10, "Pass");
                } else {
                    resultsCreateNewCell("Registration", 29, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Registration", 29, 10, "Fail");
            }

            System.out.println("<-------------Test6 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test6 Completed---------->");
        }
    }

    // Verify Subscription duration List

    @Test(enabled = true, description = "Master", priority = 7)
    public void getSubsDurationList() throws IOException {

        try {
            APIResponse response1 = getRequestWithoutToken(
                    "rest/api/v1/getPlan-duration/" + toReadDataFromExcel("Registration", 23, 1));
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1-->" + StatusCode1);

            // <-------------- Registration_TC_30 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Registration", 30, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 30, 10, "Fail");
            }

            // <-------------- Registration_TC_31 --------------->

            String s = getBodyData(response1).toString();
            if (isJSONValid(s)) {
                resultsCreateNewCell("Registration", 31, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 31, 10, "Fail");
            }

            // <-------------- Registration_TC_32 --------------->

            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            JsonArray jsonaArray = jsonObject1.get("data").getAsJsonArray();
            JsonObject jsonObject = jsonaArray.get(0).getAsJsonObject();
            String d3 = jsonObject.get("duration").getAsString();
            String d4 = jsonObject.get("taxes").getAsString();
            String d5 = jsonObject.get("durationCost").getAsString();
            Float f1 = Float.parseFloat(d4);
            Integer tax = f1.intValue();
            Float f2 = Float.parseFloat(d5);
            Integer cost = f2.intValue();
            toCreateNewCell("Registration", 27, 1, d3);
            toCreateNewCell("Registration", 26, 1, String.valueOf(tax));
            toCreateNewCell("Registration", 25, 1, String.valueOf(cost));
            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Registration", 32, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 32, 10, "Fail");
            }

            // <-------------- Registration_TC_33 --------------->

            APIResponse response2 = getRequestWithoutToken("rest/api/v1/getPlan-duration/" + null);
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2--->" + StatusCode2);
            if (StatusCode2 == 200) {
                String s1 = getBodyData(response2).toString();
                System.out.println(s1);
                JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                String d6 = jsonObject2.get("code").getAsString();
                String d7 = jsonObject2.get("message").getAsString();
                if (d6 != null && d7 != null && d6 != "0000") {
                    resultsCreateNewCell("Registration", 33, 10, "Pass");
                } else {
                    resultsCreateNewCell("Registration", 33, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Registration", 33, 10, "Fail");
            }

            System.out.println("<-------------Test7 Completed---------->");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test7 Completed---------->");
        }

    }

    // Email verification code is verified on registration

    @Test(enabled = true, description = "Registration", priority = 8)
    public void emailCodeVerification() throws IOException, MailosaurException {

        try {
            String data = Email.readEmail(toReadDataFromExcel("Registration", 3, 1));
            toCreateNewCell("Registration", 14, 1, data);

            Map<String, Object> map = new HashMap<>();
            map.put("email", toReadDataFromExcel("Registration", 3, 1));
            map.put("verificationCode", toReadDataFromExcel("Registration", 14, 1));
            map.put("value", "email");

            APIResponse response1 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", map);
            int Statuscode1 = response1.status();
            System.err.println("Statuscode1--->" + Statuscode1);
            String s = getBodyData(response1).toString();

            // <-------------- Registration_TC_34 --------------->

            try {
                Assert.assertEquals(Statuscode1, 200);
                resultsCreateNewCell("Registration", 34, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 34, 10, "Fail");
            }

            // <-------------- Registration_TC_35 --------------->

            if (isJSONValid(s)) {
                resultsCreateNewCell("Registration", 35, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 35, 10, "Fail");
            }

            // <-------------- Registration_TC_36 --------------->

            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();

            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Registration", 36, 10, "Pass");
            } else {
                resultsCreateNewCell("Registration", 36, 10, "Fail");
            }

            // <-------------- Registration_TC_37 --------------->

            List<String> list1 = new ArrayList<>();
            list1.add("");
            list1.add(null);
            list1.add(toReadDataFromExcel("Registration", 3, 1));
            list1.add(toReadDataFromExcel("Registration", 3, 1));
            list1.add(toReadDataFromExcel("Registration", 3, 1));
            List<String> list2 = new ArrayList<>();
            list2.add(toReadDataFromExcel("Registration", 14, 1));
            list2.add(toReadDataFromExcel("Registration", 14, 1));
            list2.add("");
            list2.add(null);
            list2.add("9876733");
            Map<String, Object> map1 = new HashMap<>();
            for (int i = 0; i < list1.size(); i++) {
                map1.put("email", list1.get(i));
                map1.put("verificationCode", list2.get(i));
                map1.put("value", "email");
                APIResponse response2 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", map1);
                int Statuscode2 = response2.status();
                System.out.println("Statuscode2--->" + Statuscode2);
                String s1 = getBodyData(response2).toString();
                if (Statuscode2 == 200) {
                    JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                    String d4 = jsonObject2.get("code").getAsString();
                    String d5 = jsonObject2.get("message").getAsString();
                    System.out.println("Code--->" + d4);
                    System.out.println("Message---->" + d5);
                    if (d4 != null && d5 != null && d4 != "0000") {
                        resultsCreateNewCell("Registration", 37, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Registration", 37, 10, "Fail");
                        break;
                    }
                } else {
                    resultsCreateNewCell("Registration", 37, 10, "Fail");
                    break;
                }
            }

            // <-------------- Registration_TC_38 --------------->

            Map<String, Object> map2 = new HashMap<>();
            map2.put("email", toReadDataFromExcel("Registration", 3, 1));
            map2.put("value", "email");
            APIResponse response3 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", map2);
            int Statuscode3 = response3.status();
            System.out.println("Statuscode3--->" + Statuscode3);
            try {
                Assert.assertEquals(Statuscode3, 400);
                resultsCreateNewCell("Registration", 38, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 38, 10, "Fail");
            }

            // <-------------- Registration_TC_39 --------------->

            for (int j = 0; j < getContentTypes().size(); j++) {
                APIResponse response4 = request().post(DevOrigin + "rest/api/v1/code-verification",
                        RequestOptions.create().setHeader("Content-Type", getContentTypes().get(j)).setData(map));
                int Statuscode4 = response4.status();
                System.out.println("Statuscode4--->" + Statuscode4);
                try {
                    Assert.assertEquals(Statuscode4, 415);
                    resultsCreateNewCell("Registration", 39, 10, "Pass");
                } catch (AssertionError e) {
                    resultsCreateNewCell("Registration", 39, 10, "Fail");
                    break;
                }
            }

            System.out.println("<-------------Test8 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test8 Completed---------->");
        }
    }

    // Verify the contact number on registration

    @Test(enabled = true, description = "Registration", priority = 9)
    public void PhoneNumberVerification() throws IOException {

        try{
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNo", toReadDataFromExcel("Registration", 0, 1));
        map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        map.put("value", "phone");
        map.put("countryCode", "+91");

        APIResponse response = postRequestWithoutToken("rest/api/v1/user-verification", "Mobile", map);
        int Statuscode = response.status();

        try {
            Assert.assertEquals(Statuscode, 200);
        } catch (AssertionError e) {
        }
            String s = getBodyData(response).toString();
            System.out.println(s);
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            String d3 = jsonObject1.get("data").getAsString();
            System.out.println(d3);
            toCreateNewCell("Registration", 1, 1, d3);
            if (d1.equalsIgnoreCase("0000")) {
                System.out.println("OTP sent successfully");
            } else if (d1.equalsIgnoreCase("1111")) {
                System.out.println("Invalid phone number");
            } else {
                System.out.println(d2);
            }
            System.out.println("<-------------Test9 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test9 Completed---------->");
        }

    }

    // Phone number OTP verified on registration

    @Test(enabled = true, description = "Registration", priority = 10)
    public void OTPCodeVerification() throws IOException {

        try{
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNo", toReadDataFromExcel("Registration", 0, 1));
        map.put("verificationCode", toReadDataFromExcel("Registration", 1, 1));
        map.put("value", "phone");

        APIResponse response = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", map);
        int Statuscode = response.status();

        try {
            Assert.assertEquals(Statuscode, 200);
        } catch (AssertionError e) {
        }
            String s = getBodyData(response).toString();
            System.out.println(s);
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            if (d1.equalsIgnoreCase("0000")) {
                System.out.println("code verified successfully");
            } else if (d1.equalsIgnoreCase("1111")) {
                System.out.println("Invalid code");
            } else {
                System.out.println(d2);
            }
            System.out.println("<-------------Test10 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test10 Completed---------->");
        }

    }

    // Verify the pincode on registration
    @Test(enabled = true, description = "Registration", priority = 11)
    public void verfiyPincode() throws IOException {

        try{
        Map<String, Object> map = new HashMap<>();
        map.put("pinCode", toReadDataFromExcel("Registration", 5, 1));
        map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));

        APIResponse response1 = postRequestWithoutToken("rest/api/v1/verify-pincode", "Mobile", map);
        int Statuscode1 = response1.status();
        System.out.println(Statuscode1);

        // <-------------- Registration_TC_40 --------------->

        try {
            Assert.assertEquals(Statuscode1, 200);
            resultsCreateNewCell("Registration", 40, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Registration", 40, 10, "Fail");
        }

        // <-------------- Registration_TC_41 --------------->

        String s = getBodyData(response1).toString();
        if (isJSONValid(s)) {
            resultsCreateNewCell("Registration", 41, 10, "Pass");
        } else {
            resultsCreateNewCell("Registration", 41, 10, "Fail");
        }

        // <-------------- Registration_TC_42 --------------->

        JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
        String d1 = jsonObject1.get("code").getAsString();
        System.out.println(d1);
        String d2 = jsonObject1.get("message").getAsString();
        if (d1 != null && d2 != null) {
            resultsCreateNewCell("Registration", 42, 10, "Pass");
        } else {
            resultsCreateNewCell("Registration", 42, 10, "Fail");
        }

        // <-------------- Registration_TC_43 --------------->

        List<String> list1 = new ArrayList<>();
            list1.add("");
            list1.add(null);
            list1.add("000000");
            list1.add(toReadDataFromExcel("Registration", 5, 1));
            list1.add(toReadDataFromExcel("Registration", 5, 1));
            list1.add(toReadDataFromExcel("Registration", 5, 1));
            List<String> list2 = new ArrayList<>();
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add(toReadDataFromExcel("Registration", 7, 1));
            list2.add("");
            list2.add(null);
            list2.add("9876733");
            Map<String, Object> map1 = new HashMap<>();
            for (int i = 0; i < list1.size(); i++) {
                map1.put("pinCode", list1.get(i));
                map1.put("registrationId", list2.get(i));
                APIResponse response2 = postRequestWithoutToken("rest/api/v1/verify-pincode", "Mobile", map1);
                int Statuscode2 = response2.status();
                System.out.println("Statuscode2--->" + Statuscode2);
                String s1 = getBodyData(response2).toString();
                if (Statuscode2 == 200) {
                    JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
                    String d4 = jsonObject2.get("code").getAsString();
                    String d5 = jsonObject2.get("message").getAsString();
                    System.out.println("Code--->" + d4);
                    System.out.println("Message---->" + d5);
                    if (d4 != null && d5 != null && d4 != "0000") {
                        resultsCreateNewCell("Registration", 43, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Registration", 43, 10, "Fail");
                        break;
                    }
                } else {
                    resultsCreateNewCell("Registration", 43, 10, "Fail");
                    break;
                }
            }

            // <-------------- Registration_TC_44 --------------->

            Map<String, Object> map2 = new HashMap<>();
            map2.put("pinode", toReadDataFromExcel("Registration", 3, 1));
            map2.put("registranId", "email");
            APIResponse response3 = postRequestWithoutToken("rest/api/v1/verify-pincode", "Mobile", map2);
            int Statuscode3 = response3.status();
            System.out.println("Statuscode3--->" + Statuscode3);
            try {
                Assert.assertEquals(Statuscode3, 400);
                resultsCreateNewCell("Registration", 44, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Registration", 44, 10, "Fail");
            }

            // <-------------- Registration_TC_45 --------------->

            for (int j = 0; j < getContentTypes().size(); j++) {
                APIResponse response4 = request().post(DevOrigin + "rest/api/v1/verify-pincode",
                        RequestOptions.create().setHeader("Content-Type", getContentTypes().get(j)).setData(map));
                int Statuscode4 = response4.status();
                System.out.println("Statuscode4--->" + Statuscode4);
                try {
                    Assert.assertEquals(Statuscode4, 415);
                    resultsCreateNewCell("Registration", 45, 10, "Pass");
                } catch (AssertionError e) {
                    resultsCreateNewCell("Registration", 45, 10, "Fail");
                    break;
                }
            }
            System.out.println("<-------------Test11 Completed---------->");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test11 Completed---------->");
        }
    }



    // Verify the territory list on registration
    @Test(enabled = true, description = "Registration", priority = 12)
    public void territoryList() throws IOException {

        try{
        APIResponse response = request().get(
                "https://api.postalpincode.in/pincode/" + toReadDataFromExcel("Registration", 5, 1),
                RequestOptions.create());
        System.out.println(response.status());
        System.out.println(response.statusText());
        int Statuscode = response.status();
        try {
            Assert.assertEquals(Statuscode, 200);
        } catch (AssertionError e) {
        }
            String s = getBodyData(response).toString();
            JsonArray jsonarray1 = JsonParser.parseString(s).getAsJsonArray();
            JsonObject jsonObject = jsonarray1.get(0).getAsJsonObject();
            JsonArray jsonarray2 = jsonObject.get("PostOffice").getAsJsonArray();
            JsonObject jsonObject3 = jsonarray2.get(0).getAsJsonObject();
            String Country = jsonObject3.get("Country").getAsString();
            toCreateNewCell("Registration", 16, 1, Country);
            String State = jsonObject3.get("State").getAsString();
            toCreateNewCell("Registration", 17, 1, State);
            String District = jsonObject3.get("District").getAsString();
            toCreateNewCell("Registration", 18, 1, District);
            String Name = jsonObject3.get("Name").getAsString();
            toCreateNewCell("Registration", 19, 1, Name);
            System.out.println(jsonarray2.toString());

            System.out.println("<-------------Test12 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test12 Completed---------->");
        }

    }

    @Test(enabled = true, description = "Registration", priority = 13)
    public void updateRegistrationWithoutVRTID() throws IOException {
        try{
        Map<String, Object> addressDetails = new HashMap<>();
        addressDetails.put("address1", toReadDataFromExcel("Registration", 20, 1));
        addressDetails.put("address2", toReadDataFromExcel("Registration", 21, 1));
        addressDetails.put("state", toReadDataFromExcel("Registration", 17, 1));
        addressDetails.put("country", toReadDataFromExcel("Registration", 16, 1));
        addressDetails.put("city", toReadDataFromExcel("Registration", 18, 1));
        addressDetails.put("pinCode", toReadDataFromExcel("Registration", 5, 1));
        addressDetails.put("territory", toReadDataFromExcel("Registration", 19, 1));
        addressDetails.put("landMark", toReadDataFromExcel("Registration", 22, 1));
        Map<String, Object> subMap2 = new HashMap<>();
        subMap2.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        subMap2.put("firstName", toReadDataFromExcel("Registration", 8, 1));
        subMap2.put("lastName", toReadDataFromExcel("Registration", 9, 1));
        subMap2.put("gender", toReadDataFromExcel("Registration", 10, 1));
        subMap2.put("emailId", toReadDataFromExcel("Registration", 3, 1));
        subMap2.put("phoneNo", toReadDataFromExcel("Registration", 0, 1));
        subMap2.put("dateOfBirth", toReadDataFromExcel("Registration", 11, 1));
        subMap2.put("age", toReadDataFromExcel("Registration", 12, 1));
        subMap2.put("status", "draft");
        subMap2.put("paid", "false");
        subMap2.put("addressDetails", addressDetails);
        subMap2.put("countryCode", "+91");
        subMap2.put("subscriptionCode", toReadDataFromExcel("Registration", 23, 1));
        subMap2.put("subscriptionName", toReadDataFromExcel("Registration", 24, 1));
        subMap2.put("cost", toReadDataFromExcel("Registration", 25, 1));
        subMap2.put("duration", toReadDataFromExcel("Registration", 27, 1));
        subMap2.put("title", "Mr");
        subMap2.put("emailVerification", "true");
        subMap2.put("phoneNoVerificatio", "true");
        subMap2.put("tax", toReadDataFromExcel("Registration", 26, 1));
        subMap2.put("abhaNumber", toReadDataFromExcel("Registration", 30, 1));
        subMap2.put("abhaMobileNo", toReadDataFromExcel("Registration", 31, 1));
        subMap2.put("veteranRegistrationId", null);
        List<Object> veteranRegistration = new ArrayList<>();
        veteranRegistration.add(subMap2);
        String email = Email.generateEmail();
        toCreateNewCell("Registration", 3, 2, email);
        Map<String, Object> caregiver = new HashMap<>();
        caregiver.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        caregiver.put("firstName", toReadDataFromExcel("Registration", 8, 2));
        caregiver.put("lastName", toReadDataFromExcel("Registration", 9, 2));
        caregiver.put("gender", toReadDataFromExcel("Registration", 10, 2));
        caregiver.put("emailId", toReadDataFromExcel("Registration", 3, 2));
        caregiver.put("phoneNo", toReadDataFromExcel("Registration", 0, 2));
        caregiver.put("dateOfBirth", toReadDataFromExcel("Registration", 11, 2));
        caregiver.put("age", toReadDataFromExcel("Registration", 12, 2));
        caregiver.put("countryCode", "+91"); 
        caregiver.put("addressDetails", addressDetails);
        caregiver.put("familyMemberId", null);
        caregiver.put("emailVerification", "true");
        caregiver.put("phoneNoVerificatio", "true");
        List<Object> careGivers = new ArrayList<>();
        careGivers.add(caregiver);
        Map<String, Object> map = new HashMap<>();
        map.put("meAndMyFamily", "true");
        map.put("deviceId", toReadDataFromExcel("Registration", 6, 1));
        map.put("status", "draft");
        map.put("paid", "false");
        map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        map.put("formStatus", "Home screen");
        map.put("veteranRegistration", veteranRegistration);
        map.put("careGivers", careGivers);
        map.put("promoCode", null);
        map.put("totalCost", toReadDataFromExcel("Registration", 28, 1));
        map.put("totalTax", toReadDataFromExcel("Registration", 29, 1));

        APIResponse response = postRequestWithoutToken("rest/api/v1/registraion-update", "Mobile", map);
        int Statuscode = response.status();
        System.out.println("Statuscode--->"+Statuscode);

        //         <------------- Registration_TC_46 ----------->

        try {
            Assert.assertEquals(Statuscode, 200);
            resultsCreateNewCell("Registration", 46, 10, "Pass");
        } catch (AssertionError e) {
            resultsCreateNewCell("Registration", 46, 10, "Fail");
        }

        //         <------------- Registration_TC_47 ----------->

        String s=getBodyData(response).toString();
        if(isJSONValid(s)){
            resultsCreateNewCell("Registration", 47, 10, "Pass");
        }else{
            resultsCreateNewCell("Registration", 47, 10, "Fail");
        }
        

        //         <------------- Registration_TC_48 ----------->

        JsonObject jsonObject1=JsonParser.parseString(s).getAsJsonObject();
        String code=jsonObject1.get("code").getAsString();
        String message=jsonObject1.get("message").getAsString();
        JsonObject jsonObject2=jsonObject1.get("data").getAsJsonObject();
        JsonArray jsonArray1=jsonObject2.get("veteranRegistration").getAsJsonArray();
        JsonObject jsonObject3=jsonArray1.get(0).getAsJsonObject();
        String d1=jsonObject3.get("veteranRegistrationId").getAsString();
        JsonArray jsonArray2=jsonObject2.get("careGivers").getAsJsonArray();
        JsonObject jsonObject4=jsonArray2.get(0).getAsJsonObject();
        String d2=jsonObject4.get("familyMemberId").getAsString();
       toCreateNewCell("Registration", 13, 1, d1);
       toCreateNewCell("Registration", 13, 2, d2);
        if(code!=null && message!=null){
            resultsCreateNewCell("Registration", 48, 10, "Pass");
        }else{
            resultsCreateNewCell("Registration", 48, 10, "Fail");
        }
            System.out.println("<-------------Test13 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test13 Completed---------->");
        }

    }

    // Update the draft registration with veteran registration ID

    @Test(enabled = true, description = "Registration", priority = 14)
    public void updateRegistrationWithVRTID() throws IOException {
        try{
        Map<String, Object> addressDetails = new HashMap<>();
        addressDetails.put("address1", toReadDataFromExcel("Registration", 20, 1));
        addressDetails.put("address2", toReadDataFromExcel("Registration", 21, 1));
        addressDetails.put("state", toReadDataFromExcel("Registration", 17, 1));
        addressDetails.put("country", toReadDataFromExcel("Registration", 16, 1));
        addressDetails.put("city", toReadDataFromExcel("Registration", 18, 1));
        addressDetails.put("pinCode", toReadDataFromExcel("Registration", 5, 1));
        addressDetails.put("territory", toReadDataFromExcel("Registration", 19, 1));
        addressDetails.put("landMark", toReadDataFromExcel("Registration", 22, 1));
        Map<String, Object> subMap2 = new HashMap<>();
        subMap2.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        subMap2.put("firstName", toReadDataFromExcel("Registration", 8, 1));
        subMap2.put("lastName", toReadDataFromExcel("Registration", 9, 1));
        subMap2.put("gender", toReadDataFromExcel("Registration", 10, 1));
        subMap2.put("emailId", toReadDataFromExcel("Registration", 3, 1));
        subMap2.put("phoneNo", toReadDataFromExcel("Registration", 0, 1));
        subMap2.put("dateOfBirth", toReadDataFromExcel("Registration", 11, 1));
        subMap2.put("age", toReadDataFromExcel("Registration", 12, 1));
        subMap2.put("status", "draft");
        subMap2.put("paid", "false");
        subMap2.put("addressDetails", addressDetails);
        subMap2.put("countryCode", "+91");
        subMap2.put("subscriptionCode", toReadDataFromExcel("Registration", 23, 1));
        subMap2.put("subscriptionName", toReadDataFromExcel("Registration", 24, 1));
        subMap2.put("cost", toReadDataFromExcel("Registration", 25, 1));
        subMap2.put("duration", toReadDataFromExcel("Registration", 27, 1));
        subMap2.put("title", "Mr");
        subMap2.put("emailVerification", "true");
        subMap2.put("phoneNoVerificatio", "true");
        subMap2.put("tax", toReadDataFromExcel("Registration", 26, 1));
        subMap2.put("abhaNumber", toReadDataFromExcel("Registration", 30, 1));
        subMap2.put("abhaMobileNo", toReadDataFromExcel("Registration", 31, 1));
        subMap2.put("veteranRegistrationId", toReadDataFromExcel("Registration", 13, 1));
        List<Object> veteranRegistration = new ArrayList<>();
        veteranRegistration.add(subMap2);
        Map<String, Object> caregiver = new HashMap<>();
        caregiver.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        caregiver.put("firstName", toReadDataFromExcel("Registration", 8, 2));
        caregiver.put("lastName", toReadDataFromExcel("Registration", 9, 2));
        caregiver.put("gender", toReadDataFromExcel("Registration", 10, 2));
        caregiver.put("email", toReadDataFromExcel("Registration", 3, 2));
        caregiver.put("phoneNo", toReadDataFromExcel("Registration", 0, 2));
        caregiver.put("dateOfBirth", toReadDataFromExcel("Registration", 11, 2));
        caregiver.put("age", toReadDataFromExcel("Registration", 12, 2));
        caregiver.put("countryCode", "+91"); 
        caregiver.put("addressDetails", addressDetails);
        caregiver.put("emailVerification", "true");
        caregiver.put("phoneNoVerificatio", "true");
        caregiver.put("familyMemberId", toReadDataFromExcel("Registration", 13, 2));
        List<Object> careGivers = new ArrayList<>();
        careGivers.add(caregiver);
        Map<String, Object> map = new HashMap<>();
        map.put("meAndMyFamily", "true");
        map.put("deviceId", toReadDataFromExcel("Registration", 6, 1));
        map.put("status", "draft");
        map.put("paid", "false");
        map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        map.put("formStatus", "/my-cart-subscription");
        map.put("veteranRegistration", veteranRegistration);
        map.put("careGivers", careGivers);
        map.put("promoCode", null);
        map.put("totalCost", toReadDataFromExcel("Registration", 28, 1));
        map.put("totalTax", toReadDataFromExcel("Registration", 29, 1));

        APIResponse response = postRequestWithoutToken("rest/api/v1/registraion-update", "Mobile", map);
        int Statuscode = response.status();
        System.out.println("Statuscode--->"+Statuscode);
        String s=getBodyData(response).toString();

        //         <------------- Registration_TC_49 ----------->
        try {
            Assert.assertEquals(Statuscode, 200);
            if(isJSONValid(s)){
                JsonObject jsonObject1=JsonParser.parseString(s).getAsJsonObject();
                String code=jsonObject1.get("code").getAsString();
                String message=jsonObject1.get("message").getAsString();
                if(code!=null && message!=null && code=="0000"){
                    resultsCreateNewCell("Registration", 49, 10, "Pass");
                }else{
                    resultsCreateNewCell("Registration", 49, 10, "Fail");
                }              
            }else{
                resultsCreateNewCell("Registration", 49, 10, "Fail");
            }          
        } catch (AssertionError e) {
            resultsCreateNewCell("Registration", 49, 10, "Fail");
        }
            System.out.println("<-------------Test14 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test14 Completed---------->");
        }

    }


    // Verify the payment process Started

    @Test(enabled = false, description = "Registration", priority = 16)
    public void getPaymentRequest() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("amount", toReadDataFromExcel("Payments", 1, 1));
        map.put("pageName", "RegistrationCart");
        map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        map.put("gatewayCode","HDFC");

        APIResponse response = postRequestWithoutToken("rest/api/v1/payment_option", "Mobile", map);
        int Statuscode = response.status();
        System.out.println(Statuscode);
        try {
            Assert.assertEquals(Statuscode, 200);
        } catch (AssertionError e) {
        }
        try {
            String s = getBodyData(response).toString();
            System.out.println(s);
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("data").getAsString();
            String[] a = d2.split("&");
            String[] b = a[1].split("=");
            String c = b[1];
            String d = PaymentClass.decryption(c);
            System.out.println("d --->" + d);
            String[] e = d.split("&");
            String[] f = e[1].split("=");
            String g = f[1];
            toCreateNewCell("Payments", 2, 1, g);
            System.out.println("g --->" + g);
            String encyptValue = PaymentClass.paymentInput(g);
            System.out.println("encyptValue--->" + encyptValue);
            toCreateNewCell("Payments", 3, 1, encyptValue);

            if (d1.equalsIgnoreCase("0000")) {
                System.out.println("Payment started successfully");
            } else if (d1.equalsIgnoreCase("1111")) {
                System.out.println("Payment faild");
            } else {
                System.out.println(d1);
            }
            System.out.println("<-------------Test15 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test15 Completed---------->");
        }

    }

    // Verify the payment process completed

    @Test(enabled = false, description = "Registration", priority = 17)
    public void getPaymentSuccess() throws IOException {

        String payload = "encResp="
                + toReadDataFromExcel("Payments", 3, 1)
                + "&orderNo=" + toReadDataFromExcel("Payments", 4, 1);

        APIResponse response1 = request().post(DevOrigin + "rest/api/v1/payment-gateway-page",
                (RequestOptions.create().setHeader("Content-Type", "application/x-www-form-urlencoded"))
                        .setData(payload));
        try {
            int Statuscode1 = response1.status();
            System.out.println(Statuscode1);
            try {
                Assert.assertEquals(Statuscode1, 200);
            } catch (AssertionError e1) {
            }
            byte[] s1 = response1.body();
            System.out.println(s1);
            System.out.println("<-------------Test16 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-------------Test16 Completed---------->");
        }
    }

    // Complete the registration

    @Test(enabled = false, description = "Registration", priority = 18)
    public void UpdateRegistration() throws IOException {

        try{
        Map<String, Object> addressDetails = new HashMap<>();
        addressDetails.put("address1", toReadDataFromExcel("Registration", 20, 1));
        addressDetails.put("address2", toReadDataFromExcel("Registration", 21, 1));
        addressDetails.put("state", toReadDataFromExcel("Registration", 17, 1));
        addressDetails.put("country", toReadDataFromExcel("Registration", 16, 1));
        addressDetails.put("city", toReadDataFromExcel("Registration", 18, 1));
        addressDetails.put("pinCode", toReadDataFromExcel("Registration", 5, 1));
        addressDetails.put("territory", toReadDataFromExcel("Registration", 19, 1));
        addressDetails.put("landMark", toReadDataFromExcel("Registration", 22, 1));
        Map<String, Object> subMap2 = new HashMap<>();
        subMap2.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        subMap2.put("firstName", toReadDataFromExcel("Registration", 8, 1));
        subMap2.put("lastName", toReadDataFromExcel("Registration", 9, 1));
        subMap2.put("gender", toReadDataFromExcel("Registration", 10, 1));
        subMap2.put("email", toReadDataFromExcel("Registration", 3, 1));
        subMap2.put("phoneNo", toReadDataFromExcel("Registration", 0, 1));
        subMap2.put("dateOfBirth", toReadDataFromExcel("Registration", 11, 1));
        subMap2.put("age", toReadDataFromExcel("Registration", 12, 1));
        subMap2.put("status", "Completed");
        subMap2.put("paid", "true");
        subMap2.put("addressDetails", addressDetails);
        subMap2.put("countryCode", "+91");
        subMap2.put("subscriptionCode", toReadDataFromExcel("Registration", 23, 1));
        subMap2.put("subscriptionName", toReadDataFromExcel("Registration", 24, 1));
        subMap2.put("cost", toReadDataFromExcel("Registration", 25, 1));
        subMap2.put("duration", toReadDataFromExcel("Registration", 27, 1));
        subMap2.put("title", "Mr");
        subMap2.put("emailVerification", "true");
        subMap2.put("phoneNoVerificatio", "true");
        subMap2.put("tax", toReadDataFromExcel("Registration", 26, 1));
        subMap2.put("abhaNumber", toReadDataFromExcel("Registration", 30, 1));
        subMap2.put("abhaMobileNo", toReadDataFromExcel("Registration", 31, 1));
        subMap2.put("veteranRegistrationId", toReadDataFromExcel("Registration", 13, 1));
        List<Object> veteranRegistration = new ArrayList<>();
        veteranRegistration.add(subMap2);
        Map<String, Object> caregiver = new HashMap<>();
        caregiver.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        caregiver.put("firstName", toReadDataFromExcel("Registration", 8, 2));
        caregiver.put("lastName", toReadDataFromExcel("Registration", 9, 2));
        caregiver.put("gender", toReadDataFromExcel("Registration", 10, 2));
        caregiver.put("emailId", toReadDataFromExcel("Registration", 3, 2));
        caregiver.put("phoneNo", toReadDataFromExcel("Registration", 0, 2));
        caregiver.put("dateOfBirth", toReadDataFromExcel("Registration", 11, 2));
        caregiver.put("age", toReadDataFromExcel("Registration", 12, 2));
        caregiver.put("countryCode", "+91"); 
        caregiver.put("addressDetails", addressDetails);
        caregiver.put("emailVerification", "true");
        caregiver.put("phoneNoVerificatio", "true");
        caregiver.put("familyMemberId", toReadDataFromExcel("Registration", 13, 2));
        List<Object> careGivers = new ArrayList<>();
        careGivers.add(caregiver);
        Map<String, Object> map = new HashMap<>();
        map.put("meAndMyFamily", "true");
        map.put("deviceId", toReadDataFromExcel("Registration", 6, 1));
        map.put("status", "Completed");
        map.put("paid", "true");
        map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
        map.put("formStatus", "");
        map.put("veteranRegistration", veteranRegistration);
        map.put("careGivers", careGivers);
        map.put("promoCode", null);
        map.put("totalCost", toReadDataFromExcel("Registration", 28, 1));
        map.put("totalTax", toReadDataFromExcel("Registration", 29, 1));

        APIResponse response = postRequestWithoutToken("rest/api/v1/registraion-update", "Mobile", map);
        int StatusCode = response.status();
        System.out.println("StatusCode---->"+StatusCode);
        String s=getBodyData(response).toString();
        //         <------------- Registration_TC_49 ----------->
        try {
            Assert.assertEquals(StatusCode, 200);
            if(isJSONValid(s)){
                JsonObject jsonObject1=JsonParser.parseString(s).getAsJsonObject();
                String code=jsonObject1.get("code").getAsString();
                String message=jsonObject1.get("message").getAsString();
                if(code!=null && message!=null && code=="0000"){
                    resultsCreateNewCell("Registration", 49, 10, "Pass");
                }else{
                    resultsCreateNewCell("Registration", 49, 10, "Fail");
                }              
            }else{
                resultsCreateNewCell("Registration", 49, 10, "Fail");
            }          
        } catch (AssertionError e) {
            resultsCreateNewCell("Registration", 49, 10, "Fail");
        }
        
        
        System.out.println("<-------------Test17 Completed---------->");
    }catch(Exception e){
        System.out.println("<-------------Test17 Completed---------->");
    }
    }


    // Verify the Aadhar number on registration
    @Test(enabled = false, description = "Registration_TC_14", priority = 14)
    public void AadharVerification() {

        Map<String, Object> map = new HashMap<>();
        map.put("aadhaar", "899228213606");
        APIResponse response = postRequestWithoutToken("rest/api/v1/aadaar-verification", "Mobile", map);
        int Statuscode = response.status();
        System.out.println(Statuscode);
        try {
            Assert.assertEquals(Statuscode, 200);
        } catch (AssertionError e) {
        }
        try {
            String s = getBodyData(response).toString();
            System.out.println(s);
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            // String d2 = jsonObject1.get("message").getAsString();
            if (d1.equalsIgnoreCase("0000")) {
                System.out.println("Aadhar verified successfully");
            } else if (d1.equalsIgnoreCase("1111")) {
                System.out.println("Invalid Aadhar");
            } else {
                System.out.println(d1);
            }
        } catch (Exception e) {
            System.out.println("StatusCode--->" + Statuscode);
        }
    }

    // Verify the Aadhar OTP on registration
    @Test(enabled = false, description = "Registration_TC_15", priority = 15)
    public void verifyAadharOTP() {

        Map<String, Object> map = new HashMap<>();
        map.put("mobile", "9159141890");
        map.put("otp", "423584");
        map.put("txnId", "00f11b00-d0eb-4efa-8e5b-");
        APIResponse response = postRequestWithoutToken("rest/api/v1/ahba-id-generation", "Mobile", map);
        int Statuscode = response.status();
        System.out.println(Statuscode);
        try {
            Assert.assertEquals(Statuscode, 200);
        } catch (AssertionError e) {
        }
        try {
            String s = getBodyData(response).toString();
            System.out.println(s);
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            // String d2 = jsonObject1.get("message").getAsString();
            if (d1.equalsIgnoreCase("0000")) {
                System.out.println("Aadhar OTP verified successfully");
            } else if (d1.equalsIgnoreCase("1111")) {
                System.out.println("Invalid OTP");
            } else {
                System.out.println(d1);
            }
        } catch (Exception e) {
            System.out.println("StatusCode--->" + Statuscode);
        }

    }
}


