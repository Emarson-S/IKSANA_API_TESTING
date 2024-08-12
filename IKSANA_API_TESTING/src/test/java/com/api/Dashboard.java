package com.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseClass;
import com.microsoft.playwright.APIResponse;


public class Dashboard extends BaseClass{


    // Admin Dashboard API

    @Test(enabled=true, description = "Dashboard_TC_01", priority = 1)
    public void admindashboard() throws IOException{

    try{  
    APIResponse response1=getRequestWithToken("rest/api/v1/admin-dashboard-summary-view", getAdminToken());
    int StatusCode1 =response1.status();
    System.out.println("StatusCode1-->"+StatusCode1);

    // <-------------- Dashboard_TC_01 --------------->

    try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 1, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 1, 10, "Fail");
    }

   // <-------------- Dashboard_TC_02 --------------->

    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 2, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 2, 10, "Fail");
  }


  List<String> l1=new ArrayList<>();
  l1.add(getCPToken());
  l1.add(getCMToken());
  l1.add(getVTToken());
  l1.add(getFMToken());
  l1.add(getUSVToken());
  l1.add(getSFToken());
  l1.add(getDRToken());
  l1.add(getCGToken());
  for(int i=0; i<8; i++){
  APIResponse response2=getRequestWithToken("rest/api/v1/admin-dashboard-summary-view", l1.get(i));
    int StatusCode2 =response2.status();
    System.out.println("StatusCode2-->"+StatusCode2);

    // <-------------- Dashboard_TC_03 --------------->

    try{
      Assert.assertEquals(StatusCode2, 403);
      resultsCreateNewCell("Dashboard", 3, 10, "Pass");
   }catch(AssertionError e) {
    resultsCreateNewCell("Dashboard", 3, 10, "Fail");
    break;
   }   
   
  }
  System.out.println("<-----------------Test1 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test1 completed --------->");
  }
  }


    // Doctor Dashboard API


    @Test(enabled = true, description = "Dashboard_TC_02", priority = 2)
    public void doctorDashboard() throws IOException{

      try{
      APIResponse response1=getRequestWithToken("rest/api/v1/doctor-dashboard-summary",getDRToken());
      int StatusCode1 =response1.status();
      System.out.println("StatusCode1 -->"+StatusCode1);
      // <-------------- Dashboard_TC_04 --------------->

    try{
      Assert.assertEquals(StatusCode1, 200);
      resultsCreateNewCell("Dashboard", 4, 10, "Pass");
  }catch(AssertionError e) {
    resultsCreateNewCell("Dashboard", 4, 10, "Fail");
  }

 // <-------------- Dashboard_TC_05 --------------->

  String s1=getBodyData(response1).toString();
  if (isJSONValid(s1) && StatusCode1 == 200) {
    resultsCreateNewCell("Dashboard", 5, 10, "Pass");
} else {
    resultsCreateNewCell("Dashboard", 5, 10, "Fail");
}

List<String> l1=new ArrayList<>();
  l1.add(getCPToken());
  l1.add(getCMToken());
  l1.add(getVTToken());
  l1.add(getFMToken());
  l1.add(getUSVToken());
  l1.add(getSFToken());
  l1.add(getAdminToken());
  l1.add(getCGToken());
  for(int i=0; i<8; i++){
  APIResponse response2=getRequestWithToken("rest/api/v1/doctor-dashboard-summary", l1.get(i));
    int StatusCode2 =response2.status();
    System.out.println("StatusCode2-->"+StatusCode2);

    // <-------------- Dashboard_TC_06 --------------->

    try{
      Assert.assertEquals(StatusCode2, 403);
      resultsCreateNewCell("Dashboard", 6, 10, "Pass");
   }catch(AssertionError e) {
    resultsCreateNewCell("Dashboard", 6, 10, "Fail");
    break;
   }
  }
  System.out.println("<-----------------Test2 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test2 completed --------->");
  }

}


    // Channel Partner Dashboard API


    @Test(enabled = true, description = "Dashboard_TC_03",priority = 3)
    public void channelpartnerDashboard() throws IOException{

      try{
        APIResponse response1=getRequestWithToken("rest/api/v1/channel-partner-summary", getCPToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);

        // <-------------- Dashboard_TC_07 --------------->
        try{
          Assert.assertEquals(StatusCode1, 200);
          resultsCreateNewCell("Dashboard", 7, 10, "Pass");
      }catch(AssertionError e) {
        resultsCreateNewCell("Dashboard", 7, 10, "Fail");
      }
    
     // <-------------- Dashboard_TC_08 --------------->
    
      String s1=getBodyData(response1).toString();
      if (isJSONValid(s1) && StatusCode1 == 200) {
        resultsCreateNewCell("Dashboard", 8, 10, "Pass");
    } else {
        resultsCreateNewCell("Dashboard", 8, 10, "Fail");
    }
    
    List<String> l1=new ArrayList<>();
      l1.add(getDRToken());
      l1.add(getCMToken());
      l1.add(getVTToken());
      l1.add(getFMToken());
      l1.add(getUSVToken());
      l1.add(getSFToken());
      l1.add(getAdminToken());
      l1.add(getCGToken());
      for(int i=0; i<8; i++){
      APIResponse response2=getRequestWithToken("rest/api/v1/channel-partner-summary", l1.get(i));
        int StatusCode2 =response2.status();
        System.out.println("StatusCode2-->"+StatusCode2);
    
        // <-------------- Dashboard_TC_09 --------------->
    
        try{
          Assert.assertEquals(StatusCode2, 403);
          resultsCreateNewCell("Dashboard", 9, 10, "Pass");
       }catch(AssertionError e) {
        resultsCreateNewCell("Dashboard", 9, 10, "Fail");
        break;
       }
      }
      System.out.println("<-----------------Test3 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test3 completed --------->");
  }
    }



    // Care manager Dashboard API

    @Test(enabled = true, description = "Dashboard_TC_04", priority = 4)
    public void careManagerdashboard() throws IOException{

      try{
        APIResponse response1 = getRequestWithToken("rest/api/v1/care-manager-dashboard-summary", getCMToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);

      // <-------------- Dashboard_TC_10 --------------->
      try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 10, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 10, 10, "Fail");
    }
  
   // <-------------- Dashboard_TC_11 --------------->
  
    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 11, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 11, 10, "Fail");
  }
  
  List<String> l1=new ArrayList<>();
    l1.add(getDRToken());
    l1.add(getCPToken());
    l1.add(getVTToken());
    l1.add(getFMToken());
    l1.add(getUSVToken());
    l1.add(getSFToken());
    l1.add(getAdminToken());
    l1.add(getCGToken());
    for(int i=0; i<8; i++){
    APIResponse response2=getRequestWithToken("rest/api/v1/care-manager-dashboard-summary", l1.get(i));
      int StatusCode2 =response2.status();
      System.out.println("StatusCode2-->"+StatusCode2);
  
      // <-------------- Dashboard_TC_12 --------------->
  
      try{
        Assert.assertEquals(StatusCode2, 403);
        resultsCreateNewCell("Dashboard", 12, 10, "Pass");
     }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 12, 10, "Fail");
      break;
     }
    }
    System.out.println("<-----------------Test4 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test4 completed --------->");
  }


    }


    // Veteran Dashboard API

    @Test(enabled = true, description = "Dashboard_TC_05",priority = 5)
    public void veterandashboard() throws IOException{

      try{
        APIResponse response1 = getRequestWithToken("rest/api/v1/veteran-dashboard-summary", getVTToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);
      // <-------------- Dashboard_TC_13 --------------->
      try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 13, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 13, 10, "Fail");
    }
  
   // <-------------- Dashboard_TC_14 --------------->
  
    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 14, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 14, 10, "Fail");
  }
  
  List<String> l1=new ArrayList<>();
    l1.add(getDRToken());
    l1.add(getCPToken());
    l1.add(getCMToken());
    l1.add(getFMToken());
    l1.add(getUSVToken());
    l1.add(getSFToken());
    l1.add(getAdminToken());
    l1.add(getCGToken());
    for(int i=0; i<8; i++){
    APIResponse response2=getRequestWithToken("rest/api/v1/veteran-dashboard-summary", l1.get(i));
      int StatusCode2 =response2.status();
      System.out.println("StatusCode2-->"+StatusCode2);
  
      // <-------------- Dashboard_TC_15 --------------->
  
      try{
        Assert.assertEquals(StatusCode2, 403);
        resultsCreateNewCell("Dashboard", 15, 10, "Pass");
     }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 15, 10, "Fail");
      break;
     }
    }
    System.out.println("<-----------------Test5 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test5 completed --------->");
  }

    }



    // Family member Dashboard API


    @Test(enabled = true, description = "Dashboard_TC_06", priority = 6)
    public void familyMemberdashboard() throws IOException{

      try{
        APIResponse response1 = getRequestWithToken("rest/api/v1/care-giver-dashboard-summary", getFMToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);
      // <-------------- Dashboard_TC_16 --------------->
      try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 16, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 16, 10, "Fail");
    }
  
   // <-------------- Dashboard_TC_17 --------------->
  
    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 17, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 17, 10, "Fail");
  }
  
  List<String> l1=new ArrayList<>();
    l1.add(getDRToken());
    l1.add(getCPToken());
    l1.add(getCMToken());
    l1.add(getVTToken());
    l1.add(getUSVToken());
    l1.add(getSFToken());
    l1.add(getAdminToken());
    l1.add(getCGToken());
    for(int i=0; i<8; i++){
    APIResponse response2=getRequestWithToken("rest/api/v1/care-giver-dashboard-summary", l1.get(i));
      int StatusCode2 =response2.status();
      System.out.println("StatusCode2-->"+StatusCode2);
  
      // <-------------- Dashboard_TC_18 --------------->
  
      try{
        Assert.assertEquals(StatusCode2, 403);
        resultsCreateNewCell("Dashboard", 18, 10, "Pass");
     }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 18, 10, "Fail");
      break;
     }
    }
    System.out.println("<-----------------Test6 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test6 completed --------->");
  }

    }


    // Caretaker member Dashboard API


    @Test(enabled = true, description = "Dashboard_TC_07", priority = 7)
    public void caretakerDashboard() throws IOException{

      try{
        APIResponse response1 = getRequestWithToken("rest/api/v1/caretaker-dashboard-summary", getCGToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);
      // <-------------- Dashboard_TC_19 --------------->
      try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 19, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 19, 10, "Fail");
    }
  
   // <-------------- Dashboard_TC_20 --------------->
  
    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 20, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 20, 10, "Fail");
  }
  
  List<String> l1=new ArrayList<>();
    l1.add(getDRToken());
    l1.add(getCPToken());
    l1.add(getCMToken());
    l1.add(getVTToken());
    l1.add(getUSVToken());
    l1.add(getSFToken());
    l1.add(getAdminToken());
    l1.add(getFMToken());
    for(int i=0; i<8; i++){
    APIResponse response2=getRequestWithToken("rest/api/v1/caretaker-dashboard-summary", l1.get(i));
      int StatusCode2 =response2.status();
      System.out.println("StatusCode2-->"+StatusCode2);
  
      // <-------------- Dashboard_TC_21 --------------->
  
      try{
        Assert.assertEquals(StatusCode2, 403);
        resultsCreateNewCell("Dashboard", 21, 10, "Pass");
     }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 21, 10, "Fail");
      break;
     }
    }
    System.out.println("<-----------------Test7 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test7 completed --------->");
  }
  }


    // Subscribed Veteran Dashboard API

    @Test(enabled = true, description = "Dashboard_TC_08",priority = 8)
    public void USVdashboard() throws IOException{

      try{
        APIResponse response1 = getRequestWithToken("rest/api/v1/subscribed-veteran-dashboard-summary", getUSVToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);
      // <-------------- Dashboard_TC_22 --------------->
      try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 22, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 22, 10, "Fail");
    }
  
   // <-------------- Dashboard_TC_23 --------------->
  
    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 23, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 23, 10, "Fail");
  }
  
  List<String> l1=new ArrayList<>();
    l1.add(getDRToken());
    l1.add(getCPToken());
    l1.add(getCMToken());
    l1.add(getVTToken());
    l1.add(getCGToken());
    l1.add(getSFToken());
    l1.add(getAdminToken());
    l1.add(getFMToken());
    for(int i=0; i<8; i++){
    APIResponse response2=getRequestWithToken("rest/api/v1/subscribed-veteran-dashboard-summary", l1.get(i));
      int StatusCode2 =response2.status();
      System.out.println("StatusCode2-->"+StatusCode2);
  
      // <-------------- Dashboard_TC_24 --------------->
  
      try{
        Assert.assertEquals(StatusCode2, 403);
        resultsCreateNewCell("Dashboard", 24, 10, "Pass");
     }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 24, 10, "Fail");
      break;
     }
    }
    System.out.println("<-----------------Test8 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test8 completed --------->");
  }

    }


    // Subscribed Family member Dashboard API

    @Test(enabled = true, description = "Dashboard_TC_09", priority = 9)
    public void subscribedFamilydashboard() throws IOException{

      try{
        APIResponse response1 = getRequestWithToken("rest/api/v1/subscribed-familyMember-dashboard-summary",getSFToken());
        int StatusCode1 =response1.status();
        System.out.println("StatusCode1-->"+StatusCode1);
      // <-------------- Dashboard_TC_25 --------------->
      try{
        Assert.assertEquals(StatusCode1, 200);
        resultsCreateNewCell("Dashboard", 25, 10, "Pass");
    }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 25, 10, "Fail");
    }
  
   // <-------------- Dashboard_TC_26 --------------->
  
    String s1=getBodyData(response1).toString();
    if (isJSONValid(s1) && StatusCode1 == 200) {
      resultsCreateNewCell("Dashboard", 26, 10, "Pass");
  } else {
      resultsCreateNewCell("Dashboard", 26, 10, "Fail");
  }
  
  List<String> l1=new ArrayList<>();
    l1.add(getDRToken());
    l1.add(getCPToken());
    l1.add(getCMToken());
    l1.add(getVTToken());
    l1.add(getCGToken());
    l1.add(getUSVToken());
    l1.add(getAdminToken());
    l1.add(getFMToken());
    for(int i=0; i<8; i++){
    APIResponse response2=getRequestWithToken("rest/api/v1/subscribed-familyMember-dashboard-summary", l1.get(i));
      int StatusCode2 =response2.status();
      System.out.println("StatusCode2-->"+StatusCode2);
  
      // <-------------- Dashboard_TC_27 --------------->
  
      try{
        Assert.assertEquals(StatusCode2, 403);
        resultsCreateNewCell("Dashboard", 27, 10, "Pass");
     }catch(AssertionError e) {
      resultsCreateNewCell("Dashboard", 27, 10, "Fail");
      break;
     }
    }System.out.println("<-----------------Test9 completed --------->");
  }catch (Exception e) {
    e.printStackTrace();
    System.out.println("<-----------------Test9 completed --------->");
  }

    }
  

}
