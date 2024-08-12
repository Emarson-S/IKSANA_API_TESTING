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

public class Master extends BaseClass {

     // verify the organization creations

     @Test(enabled = true, description = "Master", priority = 1)
     public void OrganizationCreation() throws IOException {

          try {
               Map<String, Object> map1 = new HashMap<>();
               map1.put("organizationName", toReadDataFromExcel("Master", 1, 1));
               map1.put("organizationCode", toReadDataFromExcel("Master", 2, 1) + "A");
               map1.put("promoCodeType", "Common");
               map1.put("status", "Active");

               APIResponse response1 = postRequestWithToken("rest/api/v1/organization",getAdminToken(), map1);
               int StatusCode1 = response1.status();
               System.out.println("StatusCode1--->" + StatusCode1);

               // <------------ Master_TC_01 ---------->
               try {
                    Assert.assertEquals(StatusCode1, 200);
                    resultsCreateNewCell("Master", 1, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 1, 10, "Fail");
               }

               // <------------ Master_TC_02 ---------->
               String s1 = getBodyData(response1).toString();
               if (isJSONValid(s1)) {
                    resultsCreateNewCell("Master", 2, 10, "Pass");
               } else {
                    resultsCreateNewCell("Master", 2, 10, "Fail");
               }

               // <------------ Master_TC_03 ---------->
               if (!s1.equals(null)) {
                    JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
                    String code1 = jsonObject1.get("code").toString();
                    String message1 = jsonObject1.get("message").toString();
                    System.out.println("Code1--->" + code1);
                    System.out.println("message1--->" + message1);
                    JsonObject jsonObject2 = jsonObject1.get("data").getAsJsonObject();
                    String Id = jsonObject2.get("id").toString();
                    toCreateNewCell("Master", 3, 1, Id);
                    System.out.println("Id--->" + Id);
                    if (code1 != null && message1 != null && code1.equals("0000")) {
                         resultsCreateNewCell("Master", 3, 10, "Pass");
                    } else {
                         resultsCreateNewCell("Master", 3, 10, "Fail");
                    }
               } else {
                    resultsCreateNewCell("Master", 3, 10, "Fail");
               }

               // <------------ Master_TC_04 ---------->
               Map<String, Object> map2 = new HashMap<>();
               map2.put("organizationName", "HomeParamedic.com");
               map2.put("organiCode", "HDFC");
               map2.put("promoCe", "Common");
               map2.put("status", "Active");
               APIResponse response2 = postRequestWithToken("rest/api/v1/organization", getAdminToken(), map2);
               int StatusCode2 = response2.status();
               System.out.println("StatusCode2--->" + StatusCode2);
               System.out.println(getBodyData(response2).toString());
               try {
                    Assert.assertEquals(StatusCode2, 400);
                    resultsCreateNewCell("Master", 4, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 4, 10, "Fail");
               }

               // <------------ Master_TC_05 ---------->
               List<String> organizationName = new ArrayList<>();
               organizationName.add("");
               organizationName.add(null);
               organizationName.add(toReadDataFromExcel("Master", 1, 1));
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".one");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".two");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".three");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".four");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".five");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".six");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".sevan");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".eight");
               organizationName.add(toReadDataFromExcel("Master", 1, 1) + ".nine");
               List<String> organizationCode = new ArrayList<>();
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "B");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "C");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "D");
               organizationCode.add("");
               organizationCode.add(null);
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "E");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "F");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "G");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "H");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "I");
               organizationCode.add(toReadDataFromExcel("Master", 2, 1) + "J");
               List<String> promoCodeType = new ArrayList<>();
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               promoCodeType.add("");
               promoCodeType.add(null);
               promoCodeType.add("test");
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               promoCodeType.add("Common");
               List<String> status = new ArrayList<>();
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("");
               status.add(null);
               status.add("test");
               for (int i = 0; i < status.size(); i++) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("organizationName", organizationName.get(i));
                    map3.put("organizationCode", organizationCode.get(i));
                    map3.put("promoCodeType", promoCodeType.get(i));
                    map3.put("status", status.get(i));

                    APIResponse response3 = postRequestWithToken("rest/api/v1/organization", getAdminToken(), map3);
                    int StatusCode3 = response3.status();
                    System.out.println("StatusCode3--->" + StatusCode3);

                    try {
                         Assert.assertEquals(StatusCode3, 200);
                         String body3 = getBodyData(response3).toString();
                         System.out.println("body3--->" + body3);
                         if (!body3.equals(null)) {
                              JsonObject jsonObject3 = JsonParser.parseString(body3).getAsJsonObject();
                              String code2 = jsonObject3.get("code").toString();
                              String message2 = jsonObject3.get("message").toString();
                              System.out.println("Code2--->" + code2);
                              System.out.println("message2--->" + message2);
                              if (code2 != null && message2 != null && !code2.equals("0000")) {
                                   resultsCreateNewCell("Master", 5, 10, "Pass");
                              } else {
                                   resultsCreateNewCell("Master", 5, 10, "Fail");
                                   break;
                              }
                         } else {
                              resultsCreateNewCell("Master", 5, 10, "Fail");
                              break;
                         }
                    } catch (AssertionError e) {
                         resultsCreateNewCell("Master", 5, 10, "Fail");
                         break;
                    }
               }

               // <------------ Master_TC_06 ---------->
               Map<String, Object> map4 = new HashMap<>();
               map4.put("organizationName", toReadDataFromExcel("Master", 1, 1));
               map4.put("organizationCode", toReadDataFromExcel("Master", 2, 1) + "L");
               map4.put("promoCodeType", "Common");
               map4.put("status", "Active");
               APIResponse response4 = postRequestWithToken("rest/api/v1/organization", "Bearer "+toReadDataFromExcel("Users",4, 8), map4);
               int StatusCode4 = response4.status();
               System.out.println("StatusCode4--->" + StatusCode4);
               try {
                    Assert.assertEquals(StatusCode4, 504);
                    String body4 = getBodyData(response4).toString();
                    System.out.println("body4--->" + body4);
                    if (body4.equals(null)) {
                         resultsCreateNewCell("Master", 6, 10, "Pass");
                    } else {
                         resultsCreateNewCell("Master", 6, 10, "Fail");
                    }
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 6, 10, "Fail");
               }

               // <------------ Master_TC_07 ---------->
               APIResponse response5 = postRequestWithToken("rest/api/v1/organization", getCMToken(), map1);
               int StatusCode5 = response5.status();
               System.out.println("StatusCode5--->" + StatusCode5);
               try {
                    Assert.assertEquals(StatusCode5, 403);
                    resultsCreateNewCell("Master", 7, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 7, 10, "Fail");
               }

               System.out.println("<------------Test1 Completed------------->");

          } catch (Exception e) {
               e.printStackTrace();
               System.out.println("<------------Test1 Completed------------->");
          }
     }

     // Verify the update organization

     @Test(enabled = true, description = "Master", priority = 2)
     public void UpdateOrganizationList() throws IOException {

          try {
               Map<String, Object> map1 = new HashMap<>();
               map1.put("organizationName", toReadDataFromExcel("Master", 1, 1) + ".Ten");
               map1.put("organizationCode", toReadDataFromExcel("Master", 2, 1) + "A");
               map1.put("promoCodeType", "Common");
               map1.put("status", "Active");
               map1.put("id", toReadDataFromExcel("Master", 3, 1));

               APIResponse response1 = putRequestWithToken("rest/api/v1/organization", getAdminToken(), map1);
               int StatusCode1 = response1.status();
               System.out.println("StatusCode1--->" + StatusCode1);

               // <------------ Master_TC_08 ---------->
               try {
                    Assert.assertEquals(StatusCode1, 200);
                    resultsCreateNewCell("Master", 8, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 8, 10, "Fail");
               }

               // <------------ Master_TC_09 ---------->
               String s1 = getBodyData(response1).toString();
               if (isJSONValid(s1)) {
                    resultsCreateNewCell("Master", 9, 10, "Pass");
               } else {
                    resultsCreateNewCell("Master", 9, 10, "Fail");
               }

               // <------------ Master_TC_10 ---------->
               if (!s1.equals(null)) {
                    JsonObject jsonObject1 = JsonParser.parseString(s1).getAsJsonObject();
                    String code1 = jsonObject1.get("code").toString();
                    String message1 = jsonObject1.get("message").toString();
                    System.out.println("Code1--->" + code1);
                    System.out.println("message1--->" + message1);
                    if (code1 != null && message1 != null && code1.equals("0000")) {
                         resultsCreateNewCell("Master", 10, 10, "Pass");
                    } else {
                         resultsCreateNewCell("Master", 10, 10, "Fail");
                    }
               } else {
                    resultsCreateNewCell("Master", 10, 10, "Fail");
               }

               // <------------ Master_TC_11 ---------->
               Map<String, Object> map2 = new HashMap<>();
               map2.put("organizationName", "HomeParamedic.com");
               map2.put("organiCode", "HDFC");
               map2.put("promoCe", "Common");
               map2.put("status", "Active");
               map2.put("id", toReadDataFromExcel("Master", 3, 1));
               APIResponse response2 = putRequestWithToken("rest/api/v1/organization", getAdminToken(), map2);
               int StatusCode2 = response2.status();
               System.out.println("StatusCode2--->" + StatusCode2);
               System.out.println(getBodyData(response2).toString());
               try {
                    Assert.assertEquals(StatusCode2, 400);
                    resultsCreateNewCell("Master", 11, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 11, 10, "Fail");
               }

               // <------------ Master_TC_12 ---------->
               List<String> organizationName1 = new ArrayList<>();
               organizationName1.add("");
               organizationName1.add(null);
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Eleven");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               organizationName1.add(toReadDataFromExcel("Master", 1, 1) + ".Ten");
               List<String> id = new ArrayList<>();
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add("");
               id.add(null);
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               id.add(toReadDataFromExcel("Master", 3, 1));
               List<String> organizationCode1 = new ArrayList<>();
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add("");
               organizationCode1.add(null);
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "K");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               organizationCode1.add(toReadDataFromExcel("Master", 2, 1) + "A");
               List<String> promoCodeType1 = new ArrayList<>();
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               promoCodeType1.add("");
               promoCodeType1.add(null);
               promoCodeType1.add("Common");
               promoCodeType1.add("Common");
               List<String> status = new ArrayList<>();
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("Active");
               status.add("");
               status.add(null);
               Map<String, Object> createMap = new HashMap<>();
               createMap.put("organizationName", toReadDataFromExcel("Master", 1, 1) + ".Eleven");
               createMap.put("organizationCode", toReadDataFromExcel("Master", 2, 1) + "K");
               createMap.put("promoCodeType", "Common");
               createMap.put("status", "Active");
               APIResponse createOrganization = postRequestWithToken("rest/api/v1/organization", getAdminToken(), createMap);
               int createStatus = createOrganization.status();
               System.out.println("createStatus--->" + createStatus);
               if (createStatus == 200) {
                    for (int i = 0; i < status.size(); i++) {
                         Map<String, Object> map3 = new HashMap<>();
                         map3.put("id", id.get(i));
                         map3.put("organizationName", organizationName1.get(i));
                         map3.put("organizationCode", organizationCode1.get(i));
                         map3.put("promoCodeType", promoCodeType1.get(i));
                         map3.put("status", status.get(i));
                         System.out.println("organizationName1--->" + organizationName1.get(i));
                         System.out.println("organizationCode1--->" + organizationCode1.get(i));
                         System.out.println("promoCodeType1--->" + promoCodeType1.get(i));
                         System.out.println("status--->" + status.get(i));
                         System.out.println("id--->" + id.get(i));
                         APIResponse response3 = postRequestWithToken("rest/api/v1/organization", getAdminToken(), map3);
                         int StatusCode3 = response3.status();
                         System.out.println("StatusCode3--->" + StatusCode3);

                         try {
                              Assert.assertEquals(StatusCode3, 200);
                              String s2 = getBodyData(response3).toString();
                              if (!s2.equals(null)) {
                                   JsonObject jsonObject3 = JsonParser.parseString(s2).getAsJsonObject();
                                   String code2 = jsonObject3.get("code").toString();
                                   String message2 = jsonObject3.get("message").toString();
                                   System.out.println("Code2--->" + code2);
                                   System.out.println("message2--->" + message2);
                                   if (code2 != null && message2 != null && !code2.equals("0000")) {
                                        resultsCreateNewCell("Master", 12, 10, "Pass");
                                   } else {
                                        resultsCreateNewCell("Master", 12, 10, "Fail");
                                        break;
                                   }
                              } else {
                                   resultsCreateNewCell("Master", 12, 10, "Fail");
                                   break;
                              }
                         } catch (AssertionError e) {
                              resultsCreateNewCell("Master", 12, 10, "Fail");
                              break;
                         }
                    }
               } else {
                    resultsCreateNewCell("Master", 12, 10, "Fail");
               }

               // <------------Master_TC_13--------->
               Map<String, Object> map3 = new HashMap<>();
               map3.put("organizationName", toReadDataFromExcel("Master", 1, 1) + ".Twelve");
               map3.put("organizationCode", toReadDataFromExcel("Master", 2, 1) + "A");
               map3.put("promoCodeType", "Common");
               map3.put("status", "Active");
               map3.put("id", toReadDataFromExcel("Master", 3, 1));
               APIResponse response3 = putRequestWithToken("rest/api/v1/organization", "Bearer "+toReadDataFromExcel("Users",4, 8), map3);
               int StatusCode3 = response3.status();
               System.out.println("StatusCode3--->" + StatusCode3);
               System.out.println(getBodyData(response3).toString());
               try {
                    Assert.assertEquals(StatusCode3, 504);
                    String body3 = getBodyData(response3).toString();
                    if (body3.equals(null)) {
                         resultsCreateNewCell("Master", 13, 10, "Pass");
                    } else {
                         resultsCreateNewCell("Master", 13, 10, "Fail");
                    }

               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 13, 10, "Fail");
               }

               // <------------Master_TC_14--------->
               Map<String, Object> map4 = new HashMap<>();
               map4.put("organizationName", toReadDataFromExcel("Master", 1, 1) + ".Thirteen");
               map4.put("organizationCode", toReadDataFromExcel("Master", 2, 1) + "A");
               map4.put("promoCodeType", "Common");
               map4.put("status", "Active");
               map4.put("id", toReadDataFromExcel("Master", 3, 1));
               APIResponse response4 = putRequestWithToken("rest/api/v1/organization", getCMToken(), map4);
               int StatusCode4 = response4.status();
               System.out.println("StatusCode4--->" + StatusCode4);
               System.out.println(getBodyData(response4).toString());
               try {
                    Assert.assertEquals(StatusCode4, 403);
                    resultsCreateNewCell("Master", 14, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 14, 10, "Fail");
               }

               System.out.println("<------------Test2 Completed------------->");
          } catch (Exception e) {
               e.printStackTrace();
               System.out.println("<------------Test2 Completed------------->");

          }
     }

     @Test(enabled = true, description = "Master", priority = 3)
     public void getOrganizationList() {
          try {
               APIResponse response1 = request().get(DevOrigin + "rest/api/v1/organizationss", RequestOptions.create()
                         .setQueryParam("page", "0").setQueryParam("value", "").setHeader("Authorization", getAdminToken()));
               int StstusCode1 = response1.status();
               System.out.println("StstusCode1--->" + StstusCode1);

               // <----------Master_TC_15------>
               try {
                    Assert.assertEquals(StstusCode1, 200);
                    resultsCreateNewCell("Master", 15, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 15, 10, "Fail");
               }

               // <----------Master_TC_16------>
               String body1 = getBodyData(response1).toString();
               if (isJSONValid(body1)) {
                    resultsCreateNewCell("Master", 16, 10, "Pass");
               } else {
                    resultsCreateNewCell("Master", 16, 10, "Fail");
               }

               // <----------Master_TC_17------>
               if (!body1.equals(null)) {
                    JsonObject jsonObject1 = JsonParser.parseString(body1).getAsJsonObject();
                    String Code1 = jsonObject1.get("code").toString();
                    String message1 = jsonObject1.get("message").toString();
                    if (Code1 != null && message1 != null && Code1.equals("0000")) {
                         resultsCreateNewCell("Master", 17, 10, "Pass");
                    } else {
                         resultsCreateNewCell("Master", 17, 10, "Fail");
                    }
               } else {
                    resultsCreateNewCell("Master", 17, 10, "Fail");
               }

               // <----------Master_TC_18------>
               APIResponse response2 = request().get(DevOrigin + "rest/api/v1/organizationss", RequestOptions.create()
                         .setQueryParam("page", "0").setQueryParam("value", "")
                         .setHeader("Authorization", "Bearer "+toReadDataFromExcel("Users",4, 8)));
               int StstusCode2 = response2.status();
               System.out.println("StstusCode2--->" + StstusCode2);
               try {
                    Assert.assertEquals(StstusCode2, 504);
                    resultsCreateNewCell("Master", 18, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 18, 10, "Fail");
               }

               // <----------Master_TC_19------>
               APIResponse response3 = request().get(DevOrigin + "rest/api/v1/organizationss", RequestOptions.create()
                         .setQueryParam("page", "0").setQueryParam("value", "").setHeader("Authorization", getCMToken()));
               int StstusCode3 = response3.status();
               System.out.println("StstusCode3--->" + StstusCode3);
               try {
                    Assert.assertEquals(StstusCode3, 403);
                    resultsCreateNewCell("Master", 19, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 19, 10, "Fail");
               }

               // <----------Master_TC_20------>
               APIResponse response4 = request().get(DevOrigin + "rest/api/v1/organizationss", RequestOptions.create()
                         .setQueryParam("pe", "0").setQueryParam("vue", "").setHeader("Authorization", getCMToken()));
               int StstusCode4 = response4.status();
               System.out.println("StstusCode4--->" + StstusCode4);
               try {
                    Assert.assertEquals(StstusCode4, 400);
                    resultsCreateNewCell("Master", 20, 10, "Pass");
               } catch (AssertionError e) {
                    resultsCreateNewCell("Master", 20, 10, "Fail");
               }

               // <----------Master_TC_21------>
               List<String> page = new ArrayList<>();
               page.add("");
               page.add(null);
               for (int i = 0; i < page.size(); i++) {
                    APIResponse response5 = request().get(DevOrigin + "rest/api/v1/organizationss",
                              RequestOptions.create()
                                        .setQueryParam("page", page.get(i)).setQueryParam("value", "")
                                        .setHeader("Authorization", getAdminToken()));
                    int StstusCode5 = response5.status();
                    System.out.println("StstusCode5--->" + StstusCode5);
                    try {
                         Assert.assertEquals(StstusCode5, 200);
                         String body2 = getBodyData(response5).toString();
                         if (!body2.equals(null)) {
                              JsonObject jsonObject2 = JsonParser.parseString(body2).getAsJsonObject();
                              String Code2 = jsonObject2.get("code").toString();
                              String message2 = jsonObject2.get("message").toString();
                              if (Code2 != null && message2 != null && !Code2.equals("0000")) {
                                   resultsCreateNewCell("Master", 21, 10, "Pass");
                              } else {
                                   resultsCreateNewCell("Master", 21, 10, "Fail");
                              }
                         } else {
                              resultsCreateNewCell("Master", 21, 10, "Fail");
                         }
                    } catch (AssertionError e) {
                         resultsCreateNewCell("Master", 21, 10, "Fail");
                    }
               }

               System.out.println("<------------Test3 Completed------------->");
          } catch (Exception e) {
               e.printStackTrace();
               System.out.println("<------------Test3 Completed------------->");
          }

     }

     // Verify the subscription plan creation

     @Test(enabled = true, description = "Master", priority = 4)
      public void createSubscriptionPlan() throws IOException{

          List<String> screens=new ArrayList<>();
          screens.add("Screen1");
          screens.add("Screen2");
          List<String> description=new ArrayList<>();
          description.add("Free subscription");
          Map<String, Object> map1=new HashMap<>();
          map1.put("subscriptionName", toReadDataFromExcel("Master", 4, 1));
          map1.put("organizationCode", toReadDataFromExcel("Master", 2, 1));
          map1.put("cost",199);
          map1.put("taxes", "18");
          map1.put("duration", "1");
          map1.put("description", description);
          map1.put("screens", screens);
          map1.put("status", "Active");

          APIResponse response1=postRequestWithToken("rest/api/v1/subscriptionPlan", getAdminToken(), map1);
          int StatusCode1=response1.status();
          System.out.println("StatusCode1--->"+StatusCode1);

          // <--------- Master_TC_22 -------------->
          try {
               Assert.assertEquals(StatusCode1, 200);
               resultsCreateNewCell("Master", 22, 10, "Pass");
          } catch (AssertionError e) {
               resultsCreateNewCell("Master", 22, 10, "Fail");
          }

          // <--------- Master_TC_23 -------------->
          String body1=getBodyData(response1).toString();
          System.out.println("body1-->"+body1);
          if(isJSONValid(body1)){
               resultsCreateNewCell("Master", 23, 10, "Pass");
          } else {
               resultsCreateNewCell("Master", 23, 10, "Fail");
          }

          // <--------- Master_TC_24 -------------->
          if(body1!=null){
          JsonObject jsonObject2 = JsonParser.parseString(body1).getAsJsonObject();
          String Code1 = jsonObject2.get("code").toString();
          String message1 = jsonObject2.get("message").toString();
          if(Code1!=null && message1!=null && Code1.equals("0000")){
               JsonObject jsonObject3=jsonObject2.get("data").getAsJsonObject();
               String subscriptionCode=jsonObject3.get("subscriptionCode").toString();
               toCreateNewCell("Master", 7, 1, subscriptionCode);
               resultsCreateNewCell("Master", 24, 10, "Pass");
          }} else {
               resultsCreateNewCell("Master", 24, 10, "Fail");
          }

          // <--------- Master_TC_25 -------------->
          APIResponse response2=postRequestWithToken("rest/api/v1/subscriptionPlan", "Bearer "+toReadDataFromExcel("Users",4, 8), map1);
          int StatusCode2=response2.status();
          System.out.println("StatusCode2--->"+StatusCode2);
          try {
               Assert.assertEquals(StatusCode2, 504);
               resultsCreateNewCell("Master", 25, 10, "Pass");
          } catch (AssertionError e) {
               resultsCreateNewCell("Master", 25, 10, "Fail");
          }

          // <--------- Master_TC_26 -------------->
          APIResponse response3=postRequestWithToken("rest/api/v1/subscriptionPlan", getCMToken(), map1);
          int StatusCode3=response3.status();
          System.out.println("StatusCode3--->"+StatusCode3);
          try {
               Assert.assertEquals(StatusCode3, 403);
               resultsCreateNewCell("Master", 26, 10, "Pass");
          } catch (AssertionError e) {
               resultsCreateNewCell("Master", 26, 10, "Fail");
          }

          // <--------- Master_TC_26 -------------->
          List<String> subscriptionName =new ArrayList<>();
          subscriptionName.add("");
          subscriptionName.add(null);
          subscriptionName.add(toReadDataFromExcel("Master", 4, 1));
          subscriptionName.add(toReadDataFromExcel("Master", 4, 1)+"one");
          subscriptionName.add(toReadDataFromExcel("Master", 4, 1)+"Two");
          subscriptionName.add(toReadDataFromExcel("Master", 4, 1)+"three");
          subscriptionName.add(toReadDataFromExcel("Master", 4, 1)+"four");
          List<String> organizationCode =new ArrayList<>();
          organizationCode.add(toReadDataFromExcel("Master", 2, 1));
          organizationCode.add(toReadDataFromExcel("Master", 2, 1));
          organizationCode.add(toReadDataFromExcel("Master", 2, 1));
          organizationCode.add("");
          organizationCode.add(null);
          organizationCode.add(toReadDataFromExcel("Master", 2, 1));
          organizationCode.add(toReadDataFromExcel("Master", 2, 1));
          List<Object> status =new ArrayList<>();
          status.add("Active");
          status.add("Active");
          status.add("Active");
          status.add("Active");
          status.add("Active");
          status.add("");
          status.add(null);
          for(int i=0; i<status.size(); i++){
          Map<String, Object> map2=new HashMap<>();
          map2.put("subscriptionName",subscriptionName.get(i));
          map2.put("organizationCode",organizationCode.get(i));
          map2.put("cost",499);
          map2.put("taxes",18);
          map2.put("duration",1);
          map2.put("description", description);
          map2.put("screens", screens);
          map2.put("status", status.get(i));

          APIResponse response5=postRequestWithToken("rest/api/v1/subscriptionPlan", getAdminToken(), map2);
          int StatusCode5=response5.status();
          System.out.println("StatusCode5--->"+StatusCode5);
          try {
               Assert.assertEquals(StatusCode5, 200);
               String body2=getBodyData(response5).toString();
               if(!body2.equals(null)){
                    JsonObject jsonObject4 = JsonParser.parseString(body2).getAsJsonObject();
                    String code2=jsonObject4.get("code").toString();
                    String message2=jsonObject4.get("message").toString();
                    if(code2!=null && message2!=null && !code2.equals("0000")){
                         resultsCreateNewCell("Master", 27, 10, "Pass");
                    }else{
                         resultsCreateNewCell("Master", 27, 10, "Fail");
                         break;
                    }
               }else{
                    resultsCreateNewCell("Master", 27, 10, "Fail");
                    break;
               }
          } catch (AssertionError e) {
               resultsCreateNewCell("Master", 27, 10, "Fail");
               break;
          }
          }

     }
          





     

}
