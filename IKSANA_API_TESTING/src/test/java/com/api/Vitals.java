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
import com.microsoft.playwright.options.RequestOptions;

public class Vitals extends BaseClass {

    // Get Vital details By Veteran

    @Test(enabled = true, description = "Vitals", priority = 1)
    public void getVitalsByVeteran() throws IOException {

        try {
            APIResponse response1 = getRequestWithToken("rest/api/v1/get-vital-details", getUSVToken());
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <----------------Vitals_TC_01------------>
            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Vitals", 1, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 1, 10, "Fail");
            }

            // <----------------Vitals_TC_02------------>

            String s1 = getBodyData(response1).toString();
            if (isJSONValid(s1)) {
                resultsCreateNewCell("Vitals", 2, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 2, 10, "Fail");
            }

            // <----------------Vitals_TC_03------------>

            JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
            String d1 = jsonObject2.get("code").getAsString();
            String d2 = jsonObject2.get("message").getAsString();
            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Vitals", 3, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 3, 10, "Fail");
            }

            // <----------------Vitals_TC_04------------>

            APIResponse response2 = getRequestWithToken("rest/api/v1/get-vital-details", getCMToken());
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2---->" + StatusCode2);
            try {
                Assert.assertEquals(StatusCode2, 403);
                resultsCreateNewCell("Vitals", 4, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 4, 10, "Fail");
            }

            // <----------------Vitals_TC_05------------>

            APIResponse response3 = getRequestWithToken("rest/api/v1/get-vital-details",
                    "Bearer " + toReadDataFromExcel("Users", 4, 3));
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 504);
                resultsCreateNewCell("Vitals", 5, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 5, 10, "Fail");
            }
            System.out.println("< ------------ Test1 Completed ---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("< ------------ Test1 Completed ---------->");
        }

    }

    // Create the Vital details

    @Test(enabled = true, description = "Vitals", priority = 2)
    public void createVitals() throws IOException {

        try {
            Map<String, Object> bodyTemperature = new HashMap<>();
            bodyTemperature.put("celsius", "37");
            bodyTemperature.put("fahrenheit", "97");
            Map<String, Object> bloodPressure = new HashMap<>();
            bloodPressure.put("systolic", "120");
            bloodPressure.put("diastolic", "80");
            Map<String, Object> bloodGlucose = new HashMap<>();
            bloodGlucose.put("value", "78");
            Map<String, Object> bloodO2 = new HashMap<>();
            bloodO2.put("value", "67");
            Map<String, Object> heartRate = new HashMap<>();
            heartRate.put("value", "72");
            Map<String, Object> respiratoryRate = new HashMap<>();
            respiratoryRate.put("value", "78");
            Map<String, Object> heartVariability = new HashMap<>();
            heartVariability.put("pNN50", "49");
            heartVariability.put("rMSSD", "50");
            Map<String, Object> map = new HashMap<>();
            map.put("bodyTemperature", bodyTemperature);
            map.put("bloodPressure", bloodPressure);
            map.put("bloodGlucose", bloodGlucose);
            map.put("bloodO2", bloodO2);
            map.put("heartRate", heartRate);
            map.put("respiratoryRate", respiratoryRate);
            map.put("heartVariability", heartVariability);
            map.put("veteranId", getUSVuserID());
            map.put("mealTime", "BeforeMeal");

            APIResponse response1 = postRequestWithToken("rest/api/v1/veteran-vital", getUSVToken(), map);
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <----------------Vitals_TC_06------------>

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Vitals", 6, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 6, 10, "Fail");
            }

            // <----------------Vitals_TC_07------------>

            String s = getBodyData(response1).toString();
            if (isJSONValid(s)) {
                resultsCreateNewCell("Vitals", 7, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 7, 10, "Fail");
            }

            // <----------------Vitals_TC_08------------>

            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Vitals", 8, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 8, 10, "Fail");
            }

            // <----------------Vitals_TC_09------------>

            APIResponse response2 = postRequestWithToken("rest/api/v1/veteran-vital", getCPToken(), map);
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2---->" + StatusCode2);
            try {
                Assert.assertEquals(StatusCode2, 403);
                resultsCreateNewCell("Vitals", 9, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 9, 10, "Fail");
            }

            // <----------------Vitals_TC_10------------>

            APIResponse response3 = postRequestWithToken("rest/api/v1/veteran-vital",
                    "Bearer " + toReadDataFromExcel("Users", 4, 1), map);
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 504);
                resultsCreateNewCell("Vitals", 10, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 10, 10, "Fail");
            }

            // <----------------Vitals_TC_11------------>

            Map<String, Object> bodyTemperature1 = new HashMap<>();
            bodyTemperature1.put("celus", "37");
            bodyTemperature1.put("fahrenheit", "97");
            Map<String, Object> map1 = new HashMap<>();
            map1.put("bodyrature", bodyTemperature1);
            map1.put("veteranId", getUSVuserID());
            map1.put("mealTime", "BeforeMeal");
            APIResponse response4 = postRequestWithToken("rest/api/v1/veteran-vital", getUSVToken(), map1);
            int StatusCode4 = response4.status();
            System.out.println("StatusCode4---->" + StatusCode4);
            try {
                Assert.assertEquals(StatusCode4, 400);
                resultsCreateNewCell("Vitals", 11, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 11, 10, "Fail");
            }

            // <----------------Vitals_TC_12------------>

            for (int i = 0; i < getContentTypes().size(); i++) {
                APIResponse response5 = request().post(DevOrigin + "rest/api/v1/veteran-vital",
                        RequestOptions.create().setData(map).setHeader("Authorization", getUSVToken())
                                .setHeader("Content-Type", getContentTypes().get(i)));
                int StatusCode5 = response5.status();
                System.out.println("StatusCode5---->" + StatusCode5);
                try {
                    Assert.assertEquals(StatusCode5, 415);
                    resultsCreateNewCell("Vitals", 12, 10, "Pass");
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", 12, 10, "Fail");
                    break;
                }
            }

            // <----------------Vitals_TC_13------------>

            List<String> list1 = new ArrayList<>();
            list1.add(getFMToken());
            list1.add(getUSVToken());
            list1.add(getSFToken());
            List<String> list2 = new ArrayList<>();
            list2.add(getVTuserID());
            list2.add(getUSVuserID());
            list2.add(getUSVuserID());

            for (int i = 0; i < list1.size(); i++) {
                Map<String, Object> bodyTemperature2 = new HashMap<>();
                bodyTemperature2.put("celsius", "30");
                bodyTemperature2.put("fahrenheit", "90");
                Map<String, Object> map2 = new HashMap<>();
                map2.put("bodyTemperature", bodyTemperature);
                map2.put("veteranId", list2.get(i));
                map2.put("mealTime", "BeforeMeal");
                APIResponse response6 = postRequestWithToken("rest/api/v1/veteran-vital", list1.get(i), map2);
                int StatusCode6 = response6.status();
                System.out.println("StatusCode6---->" + StatusCode6);
                try {
                    Assert.assertEquals(StatusCode6, 200);
                    String s2 = getBodyData(response6).toString();
                    JsonObject jsonObject3 = JsonParser.parseString(s2).getAsJsonObject();
                    String d3 = jsonObject3.get("code").getAsString();
                    String d4 = jsonObject3.get("message").getAsString();
                    if (isJSONValid(s) && d3 != null && d4 != null) {
                        resultsCreateNewCell("Vitals", 13, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Vitals", 13, 10, "Fail");
                        break;
                    }
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", 13, 10, "Fail");
                    break;
                }
            }
            System.out.println("<--------------- Test2 Completed ------------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<--------------- Test2 Completed ------------->");
        }
    }

    // Get Vital details by Family members

    @Test(enabled = true, description = "Vitals", priority = 3)
    public void getVitalsByFamily() throws IOException {

        try {
            APIResponse response1 = getRequestWithToken("rest/api/v1/get-vital-caregive-details", getSFToken());
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <----------------Vitals_TC_14------------>

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Vitals", 14, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 14, 10, "Fail");
            }

            // <----------------Vitals_TC_15------------>

            String s1 = getBodyData(response1).toString();
            if (isJSONValid(s1)) {
                resultsCreateNewCell("Vitals", 15, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 15, 10, "Fail");
            }

            // <----------------Vitals_TC_16------------>

            JsonObject jsonObject2 = JsonParser.parseString(s1).getAsJsonObject();
            String d1 = jsonObject2.get("code").getAsString();
            String d2 = jsonObject2.get("message").getAsString();
            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Vitals", 16, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 16, 10, "Fail");
            }

            // <----------------Vitals_TC_17------------>

            APIResponse response2 = getRequestWithToken("rest/api/v1/get-vital-caregive-details", getCGToken());
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2---->" + StatusCode2);
            try {
                Assert.assertEquals(StatusCode2, 403);
                resultsCreateNewCell("Vitals", 17, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 17, 10, "Fail");
            }

            // <----------------Vitals_TC_18------------>

            APIResponse response3 = getRequestWithToken("rest/api/v1/get-vital-caregive-details",
                    "Bearer " + toReadDataFromExcel("Users", 4, 4));
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 504);
                resultsCreateNewCell("Vitals", 18, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 18, 10, "Fail");
            }
            System.out.println("< ------------ Test3 Completed ---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("< ------------ Test3 Completed ---------->");
        }

    }

    // Update the vitals by veteran

    @Test(enabled = true, description = "Vitals", priority = 4)
    public void updateVitalDetails() {

        try {
            Map<String, Object> bodyTemperature = new HashMap<>();
            bodyTemperature.put("celsius", "37");
            bodyTemperature.put("fahrenheit", "97");
            Map<String, Object> bloodPressure = new HashMap<>();
            bloodPressure.put("systolic", "120");
            bloodPressure.put("diastolic", "80");
            Map<String, Object> bloodGlucose = new HashMap<>();
            bloodGlucose.put("value", "78");
            Map<String, Object> bloodO2 = new HashMap<>();
            bloodO2.put("value", "67");
            Map<String, Object> heartRate = new HashMap<>();
            heartRate.put("value", "72");
            Map<String, Object> respiratoryRate = new HashMap<>();
            respiratoryRate.put("value", "78");
            Map<String, Object> heartVariability = new HashMap<>();
            heartVariability.put("pNN50", "49");
            heartVariability.put("rMSSD", "50");
            Map<String, Object> map = new HashMap<>();
            map.put("bodyTemperature", bodyTemperature);
            map.put("bloodPressure", bloodPressure);
            map.put("bloodGlucose", bloodGlucose);
            map.put("bloodO2", bloodO2);
            map.put("heartRate", heartRate);
            map.put("respiratoryRate", respiratoryRate);
            map.put("heartVariability", heartVariability);
            map.put("veteranId", getUSVuserID());
            map.put("mealTime", "BeforeMeal");

            APIResponse response1 = putRequestWithToken("rest/api/v1/veteran-vital", getUSVToken(), map);
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <----------------Vitals_TC_19------------>

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Vitals", 19, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 19, 10, "Fail");
            }

            // <----------------Vitals_TC_20------------>

            String s = getBodyData(response1).toString();
            if (isJSONValid(s)) {
                resultsCreateNewCell("Vitals", 20, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 20, 10, "Fail");
            }

            /// <----------------Vitals_TC_21------------>

            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            String d1 = jsonObject1.get("code").getAsString();
            String d2 = jsonObject1.get("message").getAsString();
            if (d1 != null && d2 != null) {
                resultsCreateNewCell("Vitals", 21, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 21, 10, "Fail");
            }

            // <----------------Vitals_TC_22------------>

            APIResponse response2 = putRequestWithToken("rest/api/v1/veteran-vital", getDRToken(), map);
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2---->" + StatusCode2);
            try {
                Assert.assertEquals(StatusCode2, 403);
                resultsCreateNewCell("Vitals", 22, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 22, 10, "Fail");
            }

            // <----------------Vitals_TC_23------------>

            APIResponse response3 = putRequestWithToken("rest/api/v1/veteran-vital",
                    "Bearer " + toReadDataFromExcel("Users", 4, 1), map);
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 504);
                resultsCreateNewCell("Vitals", 23, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 23, 10, "Fail");
            }

            // <----------------Vitals_TC_24------------>

            Map<String, Object> bodyTemperature1 = new HashMap<>();
            bodyTemperature1.put("celus", "37");
            bodyTemperature1.put("fahrenheit", "97");
            Map<String, Object> map1 = new HashMap<>();
            map1.put("bodyrature", bodyTemperature1);
            map1.put("veteranId", getUSVuserID());
            map1.put("mealTime", "BeforeMeal");
            APIResponse response4 = putRequestWithToken("rest/api/v1/veteran-vital", getUSVToken(), map1);
            int StatusCode4 = response4.status();
            System.out.println("StatusCode4---->" + StatusCode4);
            try {
                Assert.assertEquals(StatusCode4, 400);
                resultsCreateNewCell("Vitals", 24, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 24, 10, "Fail");
            }

            // <----------------Vitals_TC_25------------>

            for (int i = 0; i < getContentTypes().size(); i++) {
                APIResponse response5 = request().put(DevOrigin + "rest/api/v1/veteran-vital",
                        RequestOptions.create().setData(map).setHeader("Authorization", getUSVToken())
                                .setHeader("Content-Type", getContentTypes().get(i)));
                int StatusCode5 = response5.status();
                System.out.println("StatusCode5---->" + StatusCode5);
                try {
                    Assert.assertEquals(StatusCode5, 415);
                    resultsCreateNewCell("Vitals", 25, 10, "Pass");
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", 25, 10, "Fail");
                    break;
                }
            }

            // <----------------Vitals_TC_26------------>

            List<String> list1 = new ArrayList<>();
            list1.add(getFMToken());
            list1.add(getUSVToken());
            list1.add(getSFToken());
            List<String> list2 = new ArrayList<>();
            list2.add(getVTuserID());
            list2.add(getUSVuserID());
            list2.add(getUSVuserID());

            for (int i = 0; i < list1.size(); i++) {
                Map<String, Object> bodyTemperature2 = new HashMap<>();
                bodyTemperature2.put("celsius", "30");
                bodyTemperature2.put("fahrenheit", "90");
                Map<String, Object> map2 = new HashMap<>();
                map2.put("bodyTemperature", bodyTemperature);
                map2.put("veteranId", list2.get(i));
                map2.put("mealTime", "BeforeMeal");
                APIResponse response6 = putRequestWithToken("rest/api/v1/veteran-vital", list1.get(i), map2);
                int StatusCode6 = response6.status();
                System.out.println("StatusCode6---->" + StatusCode6);
                try {
                    Assert.assertEquals(StatusCode6, 200);
                    String s2 = getBodyData(response6).toString();
                    JsonObject jsonObject3 = JsonParser.parseString(s2).getAsJsonObject();
                    String d3 = jsonObject3.get("code").getAsString();
                    String d4 = jsonObject3.get("message").getAsString();
                    if (isJSONValid(s) && d3 != null && d4 != null) {
                        resultsCreateNewCell("Vitals", 26, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Vitals", 26, 10, "Fail");
                        break;
                    }
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", 26, 10, "Fail");
                    break;
                }
            }
            System.out.println("<--------------- Test4 Completed ------------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<--------------- Test4 Completed ------------->");
        }

    }

    // Check the Normal and Abnormal level of vitals

    @Test(enabled = true, description = "Vitals", priority = 5)
    public void createNormalVitalDetails() throws IOException {

        try {

            for (int i = 1; i < 5; i++) {
                Map<String, Object> bodyTemperature = new HashMap<>();
                bodyTemperature.put("celsius", toReadDataFromExcel("Vitals", 1, i));
                bodyTemperature.put("fahrenheit", toReadDataFromExcel("Vitals", 2, i));
                Map<String, Object> bloodPressure = new HashMap<>();
                bloodPressure.put("systolic", toReadDataFromExcel("Vitals", 3, i));
                bloodPressure.put("diastolic", toReadDataFromExcel("Vitals", 4, i));
                Map<String, Object> bloodGlucose = new HashMap<>();
                bloodGlucose.put("value", toReadDataFromExcel("Vitals", 5, i));
                Map<String, Object> bloodO2 = new HashMap<>();
                bloodO2.put("value", toReadDataFromExcel("Vitals", 6, i));
                Map<String, Object> heartRate = new HashMap<>();
                heartRate.put("value", toReadDataFromExcel("Vitals", 7, i));
                Map<String, Object> respiratoryRate = new HashMap<>();
                respiratoryRate.put("value", toReadDataFromExcel("Vitals", 8, i));
                Map<String, Object> heartVariability = new HashMap<>();
                heartVariability.put("pNN50", toReadDataFromExcel("Vitals", 9, i));
                heartVariability.put("rMSSD", toReadDataFromExcel("Vitals", 10, i));

                Map<String, Object> map = new HashMap<>();
                map.put("bodyTemperature", bodyTemperature);
                map.put("bloodPressure", bloodPressure);
                map.put("bloodGlucose", bloodGlucose);
                map.put("bloodO2", bloodO2);
                map.put("heartRate", heartRate);
                map.put("respiratoryRate", respiratoryRate);
                map.put("heartVariability", heartVariability);
                map.put("veteranId", getVTuserID());
                map.put("mealTime", "BeforeMeal");

                APIResponse response1 = postRequestWithToken("rest/api/v1/veteran-vital", getVTToken(), map);
                int StatusCode1 = response1.status();

                System.out.println("StatusCode1---->" + StatusCode1);

                // <----------------Vitals_TC_27,Vitals_TC_28, Vitals_TC_29, Vitals_TC_30
                // ------------>

                try {
                    Assert.assertEquals(StatusCode1, 200);
                    String s1 = getBodyData(response1).toString();
                    JsonObject jsonObject3 = JsonParser.parseString(s1).getAsJsonObject();
                    String d3 = jsonObject3.get("code").getAsString();
                    System.out.println("code :" + d3);
                    System.out.println(jsonObject3.get("message").getAsString());
                    if (i == 1 || i == 2) {
                        if (isJSONValid(s1) && d3 != null && d3.equals("0000")) {
                            resultsCreateNewCell("Vitals", i + 26, 10, "Pass");
                        } else {
                            resultsCreateNewCell("Vitals", i + 26, 10, "Fail");
                        }
                    }
                    if (i == 3 || i == 4) {
                        if (isJSONValid(s1) && d3 != null && d3.equals("7777")) {
                            resultsCreateNewCell("Vitals", i + 26, 10, "Pass");
                        } else {
                            resultsCreateNewCell("Vitals", i + 26, 10, "Fail");
                        }
                    }
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", i + 26, 10, "Fail");

                }
            }

            for (int i = 1; i < 5; i++) {
                Map<String, Object> bodyTemperature = new HashMap<>();
                bodyTemperature.put("celsius", toReadDataFromExcel("Vitals", 1, i));
                bodyTemperature.put("fahrenheit", toReadDataFromExcel("Vitals", 2, i));
                Map<String, Object> bloodPressure = new HashMap<>();
                bloodPressure.put("systolic", toReadDataFromExcel("Vitals", 3, i));
                bloodPressure.put("diastolic", toReadDataFromExcel("Vitals", 4, i));
                Map<String, Object> bloodGlucose = new HashMap<>();
                bloodGlucose.put("value", toReadDataFromExcel("Vitals", 5, i));
                Map<String, Object> bloodO2 = new HashMap<>();
                bloodO2.put("value", toReadDataFromExcel("Vitals", 6, i));
                Map<String, Object> heartRate = new HashMap<>();
                heartRate.put("value", toReadDataFromExcel("Vitals", 7, i));
                Map<String, Object> respiratoryRate = new HashMap<>();
                respiratoryRate.put("value", toReadDataFromExcel("Vitals", 8, i));
                Map<String, Object> heartVariability = new HashMap<>();
                heartVariability.put("pNN50", toReadDataFromExcel("Vitals", 9, i));
                heartVariability.put("rMSSD", toReadDataFromExcel("Vitals", 10, i));

                Map<String, Object> map = new HashMap<>();
                map.put("bodyTemperature", bodyTemperature);
                map.put("bloodPressure", bloodPressure);
                map.put("bloodGlucose", bloodGlucose);
                map.put("bloodO2", bloodO2);
                map.put("heartRate", heartRate);
                map.put("respiratoryRate", respiratoryRate);
                map.put("heartVariability", heartVariability);
                map.put("veteranId", getVTuserID());
                map.put("mealTime", "BeforeMeal");

                APIResponse response1 = putRequestWithToken("rest/api/v1/veteran-vital", getVTToken(), map);
                int StatusCode1 = response1.status();

                System.out.println("StatusCode1---->" + StatusCode1);

                // <----------------Vitals_TC_31,Vitals_TC_32, Vitals_TC_33, Vitals_TC_34
                // ------------>

                try {
                    Assert.assertEquals(StatusCode1, 200);
                    String s1 = getBodyData(response1).toString();
                    JsonObject jsonObject3 = JsonParser.parseString(s1).getAsJsonObject();
                    String d3 = jsonObject3.get("code").getAsString();
                    System.out.println("code :" + d3);
                    System.out.println(jsonObject3.get("message").getAsString());
                    if (i == 1 || i == 2) {
                        if (isJSONValid(s1) && d3 != null && d3.equals("0000")) {
                            resultsCreateNewCell("Vitals", i + 30, 10, "Pass");
                        } else {
                            resultsCreateNewCell("Vitals", i + 30, 10, "Fail");
                        }
                    }
                    if (i == 3 || i == 4) {
                        if (isJSONValid(s1) && d3 != null && d3.equals("2222")) {
                            resultsCreateNewCell("Vitals", i + 30, 10, "Pass");
                        } else {
                            resultsCreateNewCell("Vitals", i + 30, 10, "Fail");
                        }
                    }
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", i + 30, 10, "Fail");

                }

            }
            System.out.println("<-----------Test5 Completed---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<-----------Test5 Completed---------->");
        }

    }

    // vital report

    @Test(enabled = true, description = "Vitals", priority = 6)
    public void bloodPressureVitalReport() throws IOException {

        try {
            APIResponse response1 = getRequestWithToken(
                    "rest/api/v1/getVitalStatistics/" + getVTuserID() + "/BloodPressure/oneWeek", getVTToken());
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <----------------Vitals_TC_35------------>

            try {
                Assert.assertEquals(StatusCode1, 200);
                resultsCreateNewCell("Vitals", 35, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 35, 10, "Fail");
            }

            // <----------------Vitals_TC_36------------>

            String s1 = getBodyData(response1).toString();
            if (isJSONValid(s1)) {
                resultsCreateNewCell("Vitals", 36, 10, "Pass");
            } else {
                resultsCreateNewCell("Vitals", 36, 10, "Fail");
            }

            // <----------------Vitals_TC_37------------>

            if (s1 != null && StatusCode1 == 200) {
                JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
                String d1 = jsonObject1.get("lastVital").getAsString();
                if (d1 != null) {
                    resultsCreateNewCell("Vitals", 37, 10, "Pass");
                } else {
                    resultsCreateNewCell("Vitals", 37, 10, "Fail");
                }
            } else {
                resultsCreateNewCell("Vitals", 37, 10, "Fail");
            }

            // <----------------Vitals_TC_38------------>

            APIResponse response2 = getRequestWithToken(
                    "rest/api/v1/getVitalStatistics/" + getVTToken() + "/BloodPressure/oneWeek", getCMToken());
            int StatusCode2 = response2.status();
            System.out.println("StatusCode2---->" + StatusCode2);
            try {
                Assert.assertEquals(StatusCode2, 403);
                resultsCreateNewCell("Vitals", 38, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 38, 10, "Fail");
            }

            // <----------------Vitals_TC_39------------>

            APIResponse response3 = getRequestWithToken(
                    "rest/api/v1/getVitalStatistics/" + getVTuserID() + "/BloodPressure/oneWeek",
                    "Bearer " + toReadDataFromExcel("Users", 4, 4));
            int StatusCode3 = response3.status();
            System.out.println("StatusCode3---->" + StatusCode3);
            try {
                Assert.assertEquals(StatusCode3, 504);
                resultsCreateNewCell("Vitals", 39, 10, "Pass");
            } catch (AssertionError e) {
                resultsCreateNewCell("Vitals", 39, 10, "Fail");
            }

            // <----------------Vitals_TC_40------------>

            List<String> vitalList = new ArrayList<>();
            vitalList.add("BloodPressure");
            vitalList.add("BloodGlucose");
            vitalList.add("HeartRate");
            vitalList.add("BloodO2");
            vitalList.add("RespiratoryRate");
            vitalList.add("HeartVariability");
            List<String> DurationList = new ArrayList<>();
            DurationList.add("oneWeek");
            DurationList.add("oneDay");
            DurationList.add("oneMonth");
            DurationList.add("threeMonth");
            DurationList.add("sixMonth");

            for (int i = 0; i < vitalList.size(); i++) {
                for (int j = 0; j < DurationList.size(); j++) {
                    APIResponse response4 = getRequestWithToken(
                            "rest/api/v1/getVitalStatistics/" + getVTuserID() + "/" + vitalList.get(i) + "/"
                                    + DurationList.get(j),
                            getVTToken());
                    int StatusCode4 = response4.status();
                    System.out.println("StatusCode4---->" + StatusCode4);
                    try {
                        Assert.assertEquals(StatusCode4, 200);
                        String s2 = getBodyData(response4).toString();
                        JsonObject jsonObject2 = JsonParser.parseString(s2).getAsJsonObject();
                        String d4 = jsonObject2.get("lastVital").getAsString();
                        if (d4 != null) {
                            resultsCreateNewCell("Vitals", 40, 10, "Pass");
                        } else {
                            resultsCreateNewCell("Vitals", 40, 10, "Fail");
                            break;
                        }
                    } catch (AssertionError e) {
                        resultsCreateNewCell("Vitals", 40, 10, "Fail");
                        break;
                    }
                }
            }

            // <----------------Vitals_TC_41------------>

            List<String> List1 = new ArrayList<>();
            List1.add(" ");
            List1.add("756375434");
            List1.add(getVTuserID());
            List1.add(getVTuserID());
            List1.add(getVTuserID());
            List1.add(getVTuserID());
            List1.add(getVTuserID());
            List<String> List2 = new ArrayList<>();
            List2.add("BloodPressure");
            List2.add("BloodPressure");
            List2.add("BloodPressure");
            List2.add(" ");
            List2.add("Blooessure");
            List2.add("BloodPressure");
            List2.add("BloodPressure");
            List<String> List3 = new ArrayList<>();
            List3.add("oneWeek");
            List3.add("oneWeek");
            List3.add("oneWeek");
            List3.add("oneWeek");
            List3.add("oneWeek");
            List3.add("oneWeek");
            List3.add("Week");

            for (int i = 0; i < List1.size(); i++) {
                APIResponse response5 = getRequestWithToken(
                        "rest/api/v1/getVitalStatistics/" + List1.get(i) + "/" + List2.get(i) + "/" + List3.get(i),
                        getVTToken());
                int StatusCode5 = response5.status();
                System.out.println("StatusCode5---->" + StatusCode5);
                try {
                    Assert.assertEquals(StatusCode5, 200);
                    String s3 = getBodyData(response5).toString();
                    if(s3!=null){
                    JsonObject jsonObject3 = JsonParser.parseString(s3).getAsJsonObject();
                    String d6 = jsonObject3.get("lastVital").getAsString();
                    if (d6 != null) {
                        resultsCreateNewCell("Vitals", 41, 10, "Pass");
                    } else {
                        resultsCreateNewCell("Vitals", 41, 10, "Fail");
                        break;
                    }
                }else{
                    resultsCreateNewCell("Vitals", 41, 10, "Fail");
                    break;
                }
                } catch (AssertionError e) {
                    resultsCreateNewCell("Vitals", 41, 10, "Fail");
                    break;
                }
            }
            System.out.println("< ------------ Test6 Completed ---------->");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("< ------------ Test6 Completed ---------->");
        }

    }

}
