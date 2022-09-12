package assessment.UserController;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import assessment.utilsForUser.UserApiUtils;
import assessment.utilsForUser.UserPojoClass;


public class user
{

	
	public static String fileNam="FinalSheet.xlsx";
	public static String SheetName="Sheet1";
	public static String TestCaseLiteral="TestCases";
	public static String getUserData1="user1";
	public static String getUserData2="user2";
	
	@BeforeTest()
	public void baseUrl()
	{
		RestAssured.baseURI="http://13.234.202.14:8088";
	}
	
	
	@Test(priority = 1,dataProvider = "addUserData")
	public void addUsertoDatabase(String emailAddress, String firstName,String lastName,String userId, ITestContext userApi)
	{
		
		
		UserPojoClass userResponse=
		given()
		       .log()
		       .all()
		       .headers("Content-Type","application/json")
		       .body(assessment.utilsForUser.UserApiUtils.getPostRequestBody(emailAddress, firstName, lastName, userId)).
		when()
		      .post("/api/users").
		 then()
		       .log()
		       .all()
		       .assertThat()
		       .statusCode(201)
		       .extract()
		       .as(UserPojoClass.class);
		
		String Userid=userResponse.getUserId();
        userApi.setAttribute("userId", Userid);
		 }
	
	
	@Test(priority = 2)
	public void updateUser(ITestContext userApi) throws IOException
	{
		
		String user=String.valueOf(userApi.getAttribute("userId"));
		String EmailIdFromPost=String.valueOf(userApi.getAttribute("emailAddress"));

		
		UserPojoClass UserResponse=
		given()
		      .log()
		      .all()
		      .headers("Content-Type","application/json")
		      .pathParam("userId",user)
		      .body(UserApiUtils.getPutRequestBody()).
		 when()
		       .put("/api/users/{userId}").
		  then()
		       .log()
		       .all()
		       .assertThat()
		       .statusCode(200)
		       .extract()
		       .as(UserPojoClass.class);
		System.out.println("Changed First Name= "+UserResponse.getFirstName());
		System.out.println("User Id= "+UserResponse.getUserId());
		
		String UseridFromPut=UserResponse.getUserId();
         String emailIDPutRequest = UserResponse.getEmailAddress();
		 assertEquals(EmailIdFromPost, emailIDPutRequest);
		assertEquals(user, UseridFromPut);
	}
	@Test(priority = 3)
	public void getUserById(ITestContext userApi)
	{
		
		String user=String.valueOf(userApi.getAttribute("userId"));
		
		
		given()
		       .log()
		       .all()
		       .headers("Content-Type","application/json")
		       .pathParam("userId", user).
	    when().
	           get("/api/users/{userId}").
	    then()
	         .log()
	         .all()
	         .assertThat()
	         .statusCode(200);	
		}
	
	@Test(priority = 4)
	public void getAllUsers()
	{
		
		
		
		given()
		      .log()
		      .all()
		      .headers("Content-Type","application/json").
		 when()
		       .get("api/users").
		 then()
		       .log()
		       .all()
		       .assertThat()
		       .statusCode(200)
		       .extract()
		       .asString();
		
		}
	@Test(priority = 5)
	public void deleteUser(ITestContext userApi)
	    {
		
		
		String user=String.valueOf(userApi.getAttribute("userId"));
		given()
		       .log()
		       .all()
		       .pathParam("userId",user ).
		when()
		       .delete("/api/users/{userId}").
		then()
		       .log()
		       .all()
		       .assertThat()
		       .statusCode(204);
		}
	
	
	
	
	
	



}
