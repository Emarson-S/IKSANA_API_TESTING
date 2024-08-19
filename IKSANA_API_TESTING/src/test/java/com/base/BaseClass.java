package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class BaseClass {

	public static String DevOrigin = "https://api-dev.iksana.life/";
	public static String Generateotp = "https://2factor.in/API/V1/de54a588-09d5-11ee-addf-0200cd936042/SMS/"
			+ "countryCode" + "phoneNo" + "/AUTOGEN2/WelldercareOTP";
	public static String Validateotp = "https://2factor.in/API/V1/de54a588-09d5-11ee-addf-0200cd936042/SMS/VERIFY/"
			+ "sessionId" + "/" + "otp";

	public static List<String> getContentTypes() {
		List<String> contentType = new ArrayList<>();
		contentType.add("*/*");
		contentType.add("application/java-archive");
		contentType.add("application/octet-stream");
		contentType.add("application/pdf");
		contentType.add("application/xhtml+xml");
		contentType.add("application/ld+json");
		contentType.add("application/xml");
		contentType.add("application/x-www-form-urlencoded");
		contentType.add("audio/mpeg");
		contentType.add("image/x-icon");
		contentType.add("image/jpeg");
		contentType.add("multipart/form-data");
		contentType.add("text/javascript");
		contentType.add("application/vnd.ms-excel");
		return contentType;
	}

	public static APIRequestContext request() {
		Playwright playwright = Playwright.create();
		APIRequest Apirequest = playwright.request();
		APIRequestContext content = Apirequest.newContext();
		return content;
	}

	// Get request with Authendication

	public static APIResponse getRequestWithToken(String URL, String authendication) {
		Playwright playwright = Playwright.create();
		APIRequest Apirequest = playwright.request();
		APIRequestContext content = Apirequest.newContext();
		APIResponse response = content.get(DevOrigin + URL,
				RequestOptions.create().setHeader("Authorization", authendication).setHeader("Application",
						"Iksana-Base"));
		return response;
	}

	// Get request without Authendication

	public static APIResponse getRequestWithoutToken(String URL) {
		Playwright playwright = Playwright.create();
		APIRequest Apirequest = playwright.request();
		APIRequestContext content = Apirequest.newContext();
		APIResponse response = content.get(DevOrigin + URL,
				RequestOptions.create());
		return response;
	}

	// Post request with Authendication

	public static APIResponse postRequestWithToken(String URL, String authendication, Object data) {
		Playwright playwright = Playwright.create();
		APIRequest Apirequest = playwright.request();
		APIRequestContext content = Apirequest.newContext();
		APIResponse response = content.post(DevOrigin + URL,
				RequestOptions.create().setHeader("Authorization", authendication)
						.setHeader("Content-Type", "application/json").setHeader("Application", "Iksana-Base")
						.setData(data));
		return response;
	}

	// Post request without Authendication

	public static APIResponse postRequestWithoutToken(String URL, String Type, Object data) {
		Playwright playwright = Playwright.create();
		APIRequest Apirequest = playwright.request();
		APIRequestContext content = Apirequest.newContext();
		APIResponse response = content.post(DevOrigin + URL,
				RequestOptions.create().setHeader("Content-Type", "application/json").setHeader("channel", Type)
						.setHeader("Application", "Iksana-Base").setData(data));
		return response;
	}

	// Put request with Authendication

	public static APIResponse putRequestWithToken(String URL, String authendication, Object data) {
		Playwright playwright = Playwright.create();
		APIRequest Apirequest = playwright.request();
		APIRequestContext content = Apirequest.newContext();
		APIResponse response = content.put(DevOrigin + URL,
				RequestOptions.create().setHeader("Authorization", authendication)
						.setHeader("Content-Type", "application/json").setData(data));
		return response;
	}

	// get response body data from API

	public static JsonNode getBodyData(APIResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode results = objectMapper.readTree(response.body());
		return results;
	}

	// create new Excel Sheet
	public static void tocreateExcelSheet(String fileName, String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		File f = new File("D:\\API_TEST\\IKSANA_API_TESTING\\Files\\Iksana_Inputs\\" + fileName + ".xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.createSheet(sheetName);
		Row r = sh.createRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);

	}

	// create new row in old Sheet
	public static void toCreateNewRow(String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		File f = new File("D:\\API_TEST\\IKSANA_API_TESTING\\Files\\Iksana_Inputs.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.getSheet(sheetName);
		Row r = sh.createRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);

	}

	// create new cell in old row

	public static void toCreateNewCell(String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		File f = new File("D:\\API_TEST\\IKSANA_API_TESTING\\Files\\Iksana_Inputs.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.getSheet(sheetName);
		Row r = sh.getRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);
	}

	public static void resultsCreateNewCell(String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		File f = new File("D:\\API_TEST\\IKSANA_API_TESTING\\Files\\Iksana_API_Testing_Testcases_v0.1.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fil);
		Sheet sh = b.getSheet(sheetName);
		Row r = sh.getRow(rowNo);
		Cell c = r.createCell(cellNo);
		c.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(f);
		b.write(fo);
	}

	public static String toReadDataFromExcel(String sheetName, int rowNo, int cellNo)
			throws IOException {

		String fileLocation=folderLocation();		
		File f = new File(fileLocation+"\\Iksana_Inputs.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fin);
		Sheet sh = b.getSheet(sheetName);
		Cell c = sh.getRow(rowNo).getCell(cellNo);
		int type = c.getCellType();
		String res;
		if (type == 1) {
			res = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date da = c.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
			res = sim.format(da);
		} else {
			double d = c.getNumericCellValue();
			long l = (long) d;
			res = String.valueOf(l);
		}
		return res;

	}

	public static String resultsReadDataFromExcel(String sheetName, int rowNo, int cellNo)
			throws IOException {


        String fileLocation=folderLocation();
		File f = new File(fileLocation+"\\Iksana_API_Testing_Testcases_v0.1.xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook b = new XSSFWorkbook(fin);
		Sheet sh = b.getSheet(sheetName);
		Cell c = sh.getRow(rowNo).getCell(cellNo);
		int type = c.getCellType();
		String res;
		if (type == 1) {
			res = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date da = c.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
			res = sim.format(da);
		} else {
			double d = c.getNumericCellValue();
			long l = (long) d;
			res = String.valueOf(l);
		}
		return res;

	}

	// User Token methods

	public static String token;

	public static String getUSVToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 1);
		return token;
	}

	public static String getSFToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 2);
		return token;
	}

	public static String getVTToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 3);
		return token;
	}

	public static String getFMToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 4);
		return token;
	}

	public static String getAdminToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 8);
		return token;
	}

	public static String getCPToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 9);
		return token;
	}

	public static String getCMToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 5);
		return token;
	}

	public static String getDRToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 6);
		return token;
	}

	public static String getCGToken() throws IOException {
		token = "Bearer " + toReadDataFromExcel("Users", 2, 7);
		return token;
	}

	// UserID methods

	public static String userId;

	public static String getUSVuserID() throws IOException {
		userId = toReadDataFromExcel("Users", 1, 1);
		return userId;
	}

	public static String getSFuserID() throws IOException {
		userId = toReadDataFromExcel("Users", 1, 2);
		return userId;
	}

	public static String getVTuserID() throws IOException {
		userId = toReadDataFromExcel("Users", 1, 3);
		return userId;
	}

	public static String getFMuserID() throws IOException {
		userId = toReadDataFromExcel("Users", 1, 4);
		return userId;
	}

	// User RoleID methods

	public static String roleId;

	public static String getUSVRoleID() throws IOException {
		roleId = toReadDataFromExcel("Users", 3, 1);
		return roleId;
	}

	public static String getSFRoleID() throws IOException {
		roleId = toReadDataFromExcel("Users", 3, 2);
		return roleId;
	}

	public static String getVTRoleID() throws IOException {
		roleId = toReadDataFromExcel("Users", 3, 3);
		return roleId;
	}

	public static String getFMRoleID() throws IOException {
		roleId = toReadDataFromExcel("Users", 3, 4);
		return roleId;
	}

	// Base64 encryption

	public static String encryption(String data) {
		String code = Base64.getEncoder().encodeToString(data.getBytes());
		return code;
	}

	// Json verification

	public boolean isJSONValid(String test) {

		if (test == null) {
			return false;
		}
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}


	// Find Folder Locations

	public static String folderLocation() {
        File folder = new File("Files");

        
        if (folder.exists() && folder.isDirectory()) {
            String folderLocation = folder.getAbsolutePath();

            // File[] files = folder.listFiles();
            // if (files != null) {
            //     System.out.println("Files in the folder:");
            //     for (File file : files) {
            //         System.out.println(file.getName());
            //     }
            // } else {
            //     System.out.println("The folder is empty or an I/O error occurred.");
            // }

			return folderLocation;

        } else {
            System.out.println("The folder does not exist or is not a directory.");
        }
		return null;
    }
}

