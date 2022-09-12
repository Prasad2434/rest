package assessment.fileController;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class fileUpload_fileDownload 
{
	@BeforeTest
	public void baseUri()
	{
		RestAssured.baseURI="http://13.234.202.14:8088";
		
	}
	
	@Test(priority = 1)
	public void testFileUpload(ITestContext fileContext)
	{
		
		
String responseBody=
		given()
		.log()
		.all()
		.contentType(ContentType.MULTIPART)
		.multiPart("fileName", new File("variables.txt")).
	when()
		.post("/api/files/upload/database").
	then()
		.log()
		.all()
		.assertThat()
		.statusCode(200)
		.extract()
		.response()
		.asString();
		
		
		JsonPath jsonpath = new JsonPath(responseBody);
		
		Boolean uploadstatus=jsonpath.getBoolean("uploadStatus");
		
		assertTrue(uploadstatus);
		String fileID = jsonpath.getString("fileId");
		fileContext.setAttribute("fileId", fileID);
	
	}
	
	@Test(priority = 2)
	public void testfileDownload(ITestContext fileContext)
	{
		String fileId=(String) fileContext.getAttribute("fileId");
	   Headers downloadfile = given()
		       .log()
		       .all()
		       .pathParam("fileId", fileId).
		when()
		       .get("/api/files/download/database/{fileId}").
		 then()
		       .log()
		       .all()
		       .assertThat()
		       .statusCode(200)
		       .extract()
		       .headers();
		       
		       
		String ContentDesposition = downloadfile.getValue("Content-Disposition");

		// System.out.println(ContentDesposition);
		int index = ContentDesposition.lastIndexOf("=");
		String nameofDownloadedFile = ContentDesposition.substring(index + 2);

		int index1 = nameofDownloadedFile.lastIndexOf(".");
		String DownloadFileType = nameofDownloadedFile.substring(index1 + 1);
		System.out.println(DownloadFileType);
		long sizeofDownloadedfile = Long.valueOf(downloadfile.getValue("Content-Length"));
		File uploadedfile = new File("variables.txt");

		long sizeofUploadedfile = uploadedfile.length();
		String nameofUploadedFile = uploadedfile.getName();
		int index2 = nameofUploadedFile.lastIndexOf(".");

		String UploadFileType = nameofUploadedFile.substring(index2 + 1);
		System.out.println("Size of File Uploaded =" + sizeofUploadedfile);
		System.out.println("Size of File download =" + sizeofDownloadedfile);
		System.out.println("Name of Uploaded File= " + nameofUploadedFile);
		System.out.println("Name of downloaded File= " + nameofDownloadedFile);
        System.out.println("Type of Upload File= "+UploadFileType);
        System.out.println("Type of download File= "+UploadFileType);
		assertEquals(nameofUploadedFile, nameofDownloadedFile);
		assertEquals(sizeofUploadedfile, sizeofDownloadedfile);
		assertEquals(UploadFileType, DownloadFileType);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
