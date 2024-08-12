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
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class UserCreation extends BaseClass {


    @Test(enabled = true, description = "Registration", priority = 13)
    public void updateRegistrationWithoutVRTID() throws IOException {

        for(int i=0; i<1; i++){
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
            String s = getBodyData(response1).toString();
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            JsonObject data = jsonObject1.get("data").getAsJsonObject();
            String d2 = data.get("registrationId").getAsString();
            toCreateNewCell("Registration", 7, 1, d2);
        }catch(Exception e){
            e.printStackTrace();
        }
            try{
            String a = toReadDataFromExcel("Registration", 0, 1);
            long b = Long.parseLong(a) + 1;
            String c = String.valueOf(b);
            toCreateNewCell("Registration", 0, 1, c);

            String a1 = toReadDataFromExcel("Registration", 0, 2);
            long b1 = Long.parseLong(a1) + 1;
            String c1 = String.valueOf(b1);
            toCreateNewCell("Registration", 0, 2, c1);

            String email1 = Email.generateEmail();
            toCreateNewCell("Registration", 3, 1, email1);
            
            String email2 = Email.generateEmail();
            toCreateNewCell("Registration", 3, 2, email2);

            String a2 = toReadDataFromExcel("Registration", 9, 1);
            long b2 = Long.parseLong(a2) + 1;
            String c2 = String.valueOf(b2);
            toCreateNewCell("Registration", 9, 1, c2);

            String a3 = toReadDataFromExcel("Registration", 9, 2);
            long b3 = Long.parseLong(a3) + 1;
            String c3 = String.valueOf(b3);
            toCreateNewCell("Registration", 9, 2, c3);

            String a4 = toReadDataFromExcel("Registration", 30, 1);
            long b4 = Long.parseLong(a4) + 1;
            String c4 = String.valueOf(b4);
            toCreateNewCell("Registration", 30, 1, c4);

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
            int Statuscode2 = response.status();
            System.out.println("Statuscode2--->" + Statuscode2);
            String s = getBodyData(response).toString();
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.get("data").getAsJsonObject();
            JsonArray jsonArray1 = jsonObject2.get("veteranRegistration").getAsJsonArray();
            JsonObject jsonObject3 = jsonArray1.get(0).getAsJsonObject();
            String d1 = jsonObject3.get("veteranRegistrationId").getAsString();
            JsonArray jsonArray2 = jsonObject2.get("careGivers").getAsJsonArray();
            JsonObject jsonObject4 = jsonArray2.get(0).getAsJsonObject();
            String d2 = jsonObject4.get("familyMemberId").getAsString();
            toCreateNewCell("Registration", 13, 1, d1);
            toCreateNewCell("Registration", 13, 2, d2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("amount", toReadDataFromExcel("Payments", 1, 1));
            map.put("pageName", "RegistrationCart");
            map.put("registrationId", toReadDataFromExcel("Registration", 7, 1));
            map.put("gatewayCode", "HDFC");
            APIResponse response = postRequestWithoutToken("rest/api/v1/payment_option", "Mobile", map);
            int Statuscode = response.status();
            System.out.println("Statuscode---->"+Statuscode);
            Assert.assertEquals(Statuscode, 200);
            String s = getBodyData(response).toString();
            System.out.println(s);
            JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
            JsonObject jsonObject2 = jsonObject1.get("data").getAsJsonObject();
            String d2=jsonObject2.get("hdfcRoutingUrl").getAsString();
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String payload = "encResp="
                    + toReadDataFromExcel("Payments", 3, 1)
                    + "&orderNo=" + toReadDataFromExcel("Payments", 4, 1);

            APIResponse response1 = request().post(DevOrigin + "rest/api/v1/payment-gateway-page-hdfc",
                    (RequestOptions.create().setHeader("Content-Type", "application/x-www-form-urlencoded"))
                            .setData(payload));
            int Statuscode1 = response1.status();
            System.out.println(Statuscode1);
            Assert.assertEquals(Statuscode1, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
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
            System.out.println("StatusCode---->" + StatusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

}
