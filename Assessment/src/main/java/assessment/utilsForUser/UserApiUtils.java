package assessment.utilsForUser;

public class UserApiUtils 
{
	public static String getPostRequestBody (String emailAddress, String firstName,String lastName,String userid)
	{
		return "{\r\n"
				+ "  \"emailAddress\": \""+emailAddress+"\",\r\n"
				+ "  \"firstName\": \""+firstName+"\",\r\n"
				+ "  \"lastName\": \""+lastName+"\",\r\n"
				+ "  \"userId\": \""+userid+"\"\r\n"
				+ "}";
	}
	
	public static String getPutRequestBody()
	{
		return "{\r\n"
				+ "  \"emailAddress\": \"Mangesh97876@gmail.com\",\r\n"
				+ "  \"firstName\": \"Mangesha\",\r\n"
				+ "  \"lastName\": \"Kulkarni\",\r\n"
				+ "  \"userId\": \"Mangesh1234\"\r\n"
				+ "}";
	}
	
	

}
