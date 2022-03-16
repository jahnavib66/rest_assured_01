package Rest_Testing;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import io.restassured.response.ResponseBodyData;
public class ecommerce {
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accesstoken;
	public static String UserId;
	public static String emailId;
	
	//Get All users
	public static String users;
	public static String id;
	public static String count;
	
	
	
	@Test(priority = 0, enabled = false)
	public void signup()
	{
		RestAssured.baseURI = baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"savi1@gmail.com\",\n"
			+ "	\"password\": \"savi123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/signup")
			
			.then()
			.assertThat().statusCode(201).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	message = js.get("message");
	System.out.println(message);		
}
	
	
	@Test(priority = 1)
	public void Login()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"savi1@gmail.com\",\n"
			+ "	\"password\": \"savi123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/login")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	accesstoken = js.get("accessToken");
	System.out.println(accesstoken);
  }
	
	
	@Test(priority = 2, dependsOnMethods = "Login", alwaysRun = true)
	public void getallusers()
	{
		RestAssured.baseURI =baseurl;
		
			Response resp = given()
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer "+accesstoken)
			
			.when()
			.get("/user")
			
			
			.then()
			.contentType(ContentType.JSON)
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
			String jsonresponse = resp.asString();
			//i want to convert the response in to json format
			//why do i use jsonpath to convert the string in to a json format
			JsonPath js = new JsonPath(jsonresponse);
			//nw i have to fetch the id
			System.out.println(jsonresponse);
			UserId = js.get("users[69]._id");
			System.out.println("user[69]"+UserId);
			emailId = js.get("users[69].email");
			System.out.println("user[69].email"+emailId);
  }
	
	@Test(priority = 3, dependsOnMethods = "getallusers", alwaysRun = true)
	public void delete()
	{
		RestAssured.baseURI =baseurl;
		
			Response resp = given()
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer "+accesstoken)
			.when()
			.delete("/user/"+UserId)
			
			.then()
			.contentType(ContentType.JSON)
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resp.asString();
	JsonPath js = new JsonPath(jsonresponse);
	message = js.get("message");
	System.out.println(message);
	System.out.println("User holding this email got deleted:"+emailId);
  }
}
