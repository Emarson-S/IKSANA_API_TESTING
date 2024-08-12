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

public class Services extends BaseClass {



    // Emergency Service


    @Test(enabled=false,description="Services_TC_01")
    public void EmergencyServices() throws IOException{

        Map<String ,Object> map=new HashMap<>();
        map.put("eventType", "Fall");
        map.put("ruleId", "1687933126207");
        map.put("specialServiceCode", "Emergency");
        map.put("veteranId","1707484162423");

        APIResponse response=postRequestWithToken("rest/api/v1/event-notification", getUSVToken(), map);
        int StatusCode=response.status();
        try{
        Assert.assertEquals(StatusCode, 200);
        }catch (AssertionError e) {
        System.out.println("StatusCode---->"+StatusCode);
        }
        String s=getBodyData(response).toString();
        System.out.println(s);
        JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
        String d1 = jsonObject1.get("code").getAsString();
        if(d1.equalsIgnoreCase("0000")){
            System.out.println("Emergency alarm created");
        }
        else if(d1.equalsIgnoreCase("1111")) {
            System.out.println("Invalid inputs");
        }
        else
        {
            System.out.println(d1);
        }
    }


    // Doctor Consultation

    @Test(enabled=false,description="Services_TC_02")
    public void DoctorConsultation() throws IOException{

        Map<String ,Object> map=new HashMap<>();
        map.put("specialServiceCode", "Consultation");
        map.put("veteranId", "1707484162423");

        APIResponse response=postRequestWithToken("rest/api/v1/doctor-consultation-limit", getUSVToken(), map);
        int StatusCode=response.status();
        try{
        Assert.assertEquals(StatusCode, 200);
        System.out.println("StatusCode---->"+StatusCode);
        }catch (AssertionError e) {
        System.out.println("StatusCode---->"+StatusCode);
        }
        String s=getBodyData(response).toString();
        System.out.println(s);
        JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
        String d1 = jsonObject1.get("code").getAsString();
        if(d1.equalsIgnoreCase("0000")){
            System.out.println("Doctor Call Triggered");
        }
        else if(d1.equalsIgnoreCase("1111")) {
            System.out.println("Invalid inputs");
        }
        else
        {
            System.out.println(d1);
        }

    }


    // Get medical records

    @Test(enabled=false,description="Services_TC_03")
    public void MedicalReportUpload() throws IOException{

        APIResponse response=getRequestWithToken("rest/api/v1/medical-reports", getUSVToken());
        int StatusCode=response.status();
        try{
            Assert.assertEquals(StatusCode, 200);
            System.out.println("StatusCode---->"+StatusCode);
            }catch (AssertionError e) {
            System.out.println("StatusCode---->"+StatusCode);
            }
            String s=getBodyData(response).toString();
            System.out.println(s);
    }


    // Upload medical files

    @Test(enabled=false,description="Services_TC_04")
    public void UploadMedicalRecords() throws IOException{

        List<Object> fileName=new ArrayList<>();
        fileName.add("https://s3-dev-iksana-files-images.s3.ap-south-1.amazonaws.com/1707315710090/cda89406-d8e2-4541-b772-5733c4882ea9");
        List<Object> originalFileName=new ArrayList<>();
        originalFileName.add("IMG-20240228-WA0000.jpg");
        Map<String ,Object> map=new HashMap<>();
        map.put("category", "Consultation");
        map.put("veteranId", "1707810583437");
        map.put("fileName", fileName);
        map.put("originalFileName", originalFileName);

        APIResponse response=postRequestWithToken("rest/api/v1/medical-report", getVTToken(), map);
        int StatusCode=response.status();
        try{
        Assert.assertEquals(StatusCode, 200);
        System.out.println("StatusCode---->"+StatusCode);
        }catch (AssertionError e) {
        System.out.println("StatusCode---->"+StatusCode);
        }
        String s=getBodyData(response).toString();
        System.out.println(s);
        JsonObject jsonObject1 = JsonParser.parseString(s).getAsJsonObject();
        String d1 = jsonObject1.get("code").getAsString();
        if(d1.equalsIgnoreCase("0000")){
            System.out.println("Upload successfully");
        }
        else if(d1.equalsIgnoreCase("1111")) {
            System.out.println("Invalid inputs");
        }
        else
        {
            System.out.println(d1);
        }
        
    }


    @Test(enabled=false,description="Services_TC_05")
    public void getSubscriptionDetails() throws IOException{
        
        APIResponse response=getRequestWithToken("rest/api/v1/getSubscribed-veteran/"+getUSVuserID(), getUSVToken());
        int StatusCode=response.status();
        try{
        Assert.assertEquals(StatusCode, 200);
        System.out.println("StatusCode---->"+StatusCode);
        }catch (AssertionError e) {
        System.out.println("StatusCode---->"+StatusCode);
        }
        String s=getBodyData(response).toString();
        System.out.println(s);
    }


@Test(enabled=false,description="Services_TC_06")
public void getServiceList() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/iksana-services/"+getUSVuserID(),getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
    String s=getBodyData(response).toString();
    System.out.println(s);
}



@Test(enabled=false,description="Services_TC_07")
public void getPaidServiceList() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/getSpecial-serive-paid/SP0001", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
    String s=getBodyData(response).toString();
    System.out.println(s);
}



@Test(enabled=false,description="Services_TC_08")
public void getPendingPaymentList() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/get-payment-pending", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
    String s=getBodyData(response).toString();
    System.out.println(s);
}



@Test(enabled=false,description="Services_TC_09")
public void getPaymentHistoryList() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/get-payment-com-can", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
    String s=getBodyData(response).toString();
    System.out.println(s);
}



@Test(enabled=false,description="Services_TC_10")
public void FamilyMemberList() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/managefamily", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
    String s=getBodyData(response).toString();
    System.out.println(s);
}




@Test(enabled=false,description="Services_TC_11")
public void TeamsAndConditions() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/generalDocument/TERMS-CONDITIONS", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
}


@Test(enabled=false,description="Services_TC_12")
public void PrivacyPolicy() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/generalDocument/PRIVACY-POLICY", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
}


@Test(enabled=false,description="Services_TC_13")
public void FAQs() throws IOException{

    APIResponse response=getRequestWithToken("rest/api/v1/faqs", getUSVToken());
    int StatusCode=response.status();
    try{
    Assert.assertEquals(StatusCode, 200);
    System.out.println("StatusCode---->"+StatusCode);
    }catch (AssertionError e) {
    System.out.println("StatusCode---->"+StatusCode);
    }
}

@Test(enabled = false)
public void SubscriptionUpgrade() throws IOException{

    Map<String, Object> map=new HashMap<>();
    map.put("subscriptionCode", "SP0002");
    map.put("cost", "4494");
    map.put("subscriptionName", "PREMIUM PLAN");
    map.put("userId", "1711547610995");
    map.put("tax", "0");
    map.put("planStatus", "Active");
    map.put("duration", "6");
    APIResponse response=putRequestWithToken("rest/api/v1/update-veteranSubscription", getUSVToken(), map);
    int StatusCode=response.status();
    System.out.println(StatusCode);
    String body=getBodyData(response).toString();
    System.out.println(body);

}

@Test(enabled = false)
public void SubscriptionRenewal() throws IOException{

    Map<String, Object> map=new HashMap<>();
    map.put("registrationId", "1711535683388");
    map.put("cost", "2094");
    map.put("tax", "376.92");
    map.put("userId", "1711547610549");
    map.put("paid", true);
    map.put("duration", "6");
    APIResponse response=putRequestWithToken("rest/api/v1/plan-renewal", getUSVToken(), map);
    int StatusCode=response.status();
    System.out.println(StatusCode);
    String body=getBodyData(response).toString();
    System.out.println(body);

}


}
