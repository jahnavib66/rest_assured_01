package Rest_Testing;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
public class rest_assured {    // it is a class
	public static String baseurl="https://api.trello.com";
	public String id;
	@Test(priority=0)
	public void createBoard()          // it is a method
	{
		RestAssured.baseURI=baseurl;
	Response response=given().queryParam("name", "it is me")
		.queryParam("key", "01714c1f8016517240b4401e23f6a1a1")
		.queryParam("token", "26b1277bbffa6701a55d20438838ea958a3d493d5e92d46e7bcb83894ab16ec4")
		.header("Content-Type", "application/json")
		.when()
		.post("/1/boards/")
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		JsonPath js= new JsonPath(jsonresponse);
		id = js.get("id");
		System.out.println(id);		
	}
	@Test(priority=1)
	public void getBoard()
	{
		RestAssured.baseURI=baseurl;
	Response response =	given()
			.queryParam("key", "01714c1f8016517240b4401e23f6a1a1")
			.queryParam("token", "26b1277bbffa6701a55d20438838ea958a3d493d5e92d46e7bcb83894ab16ec4")
			.header("Content-Type", "application/json")
			.when()
			.get("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
			String jsonresponse = response.asString();
			
			System.out.println(jsonresponse);
		}
	@Test(priority = 2)
	public void DeleteBoard()
	{
		RestAssured.baseURI = baseurl;
		Response response =	given()
				.queryParam("key", "01714c1f8016517240b4401e23f6a1a1")
				.queryParam("token", "26b1277bbffa6701a55d20438838ea958a3d493d5e92d46e7bcb83894ab16ec4")
				.header("Content-Type", "application/json")
				.when()
				.delete("/1/boards/"+id)
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();	
				String jsonresponse = response.asString();
				
				System.out.println(jsonresponse);
			}
	}
