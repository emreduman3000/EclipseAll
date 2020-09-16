package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;


public class GetRequest01  {
	
	//By using Rest-Assured, I will do API Testing  
	@Test
	public void getMethod01()
	{
		given().// by manuel, add this - import static io.restassured.RestAssured.*;
		when().//when den sonra postmandaki methodlardan birini koyarız asagıya
			get("https://restful-booker.herokuapp.com/booking").//postman'daki get burdaki get method ile aynı (end point required- url address)
	    then().
	    	assertThat().
	    	statusCode(200);//successful https code - statusCode 200 diye dogrula
		
		//end point ten data almak istedim, aldıysam 200 olmalı, 200 se kontrol et dedim ve 1/1 pass oldu
			
	}

	@Test
	public void getMethod02()
	{
		//import io.restassured.response.Response;
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/7");
		//the code above must return a data 
		
		System.out.println("Status Code: " + response.getStatusCode());//Status Code: 200
		
		response.prettyPrint();//Respond Body'i console'da görmek için
	
		System.out.println("Status Line: " +response.getStatusLine());//Status Line: HTTP/1.1 200 OK
		
		System.out.println("Content Line - method1 : " +response.getContentType());//Content Line: application/json; charset=utf-8
		System.out.println("Content Line - method2 : " +response.getHeader("Content-Type"));//Content Line: application/json; charset=utf-8

		System.out.println("Date: " + response.getTime());//Date: 468
		
		System.out.println("Headers: " + response.getHeaders());//all infos at header
		/*
		    Headers: Server=Cowboy
			Connection=keep-alive
			X-Powered-By=Express
			Content-Type=application/json; charset=utf-8
			Content-Length=196
			Etag=W/"c4-sInv/se8XT3bwozuq9WfySTqhHM"
			Date=Sun, 16 Aug 2020 18:33:49 GMT
			Via=1.1 vegur
		 */
		
		System.out.println("Headers' date: " + response.getHeader("Date"));//specific info at header - Headers' date: Sun, 16 Aug 2020 18:33:49 GMT

		
	}	
	
	@Test
	public void assertion()
	{
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/7");
		
		response.then().assertThat().//asagıdaki 3  seyi test edecek
		statusCode(200).
		statusLine("HTTP/1.1 200 OK").
		contentType("application/json; charset=utf-8");
		
		response.prettyPrint();
	}
	
	
	
	/*
	 Positive Scenario:
	 when() Bir GET Request asagida verilen Endpoint'e yollanir
	        https://restful-booker.herokuapp.com/booking 
	 and()  Accept Type'i "application/json" dir.
	 then() status code 200'dur 
	 and()  content type  "application/json" dir.        
	*/
	
	@Test
	public void get01() {		
		given().
		   accept("application/json").
		when(). 
		   get("https://restful-booker.herokuapp.com/booking").
       then().
          assertThat().
          statusCode(200).
          contentType("application/json");
	}
	
	/*
	 Negative Scenario:
	 when() Bir GET Request asagida verilen Endpoint'e yollanir
	        https://restful-booker.herokuapp.com/booking/1001 
	 and()  Accept Type'i "application/json" dir.
	 then() status code 404'dur.    
	 */
	
	@Test
	public void get02() {
		Response response = given().
		                       accept("application/json").
		                    when().
		                       get("https://restful-booker.herokuapp.com/booking/1001");
		
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(404);
	}
	
	
	
	/*
	 Negative Scenario:
	 when() Bir GET Request asagida verilen Endpoint'e yollanir
	        https://restful-booker.herokuapp.com/booking/1001 
	 then() status code 404'dur
	 and()  Response body'de "Not Found" var  
	 and()  Response body'de "Suleyman" yok
	 */
	
	
	
	@Test
	public void get03() {
		Response response = given().
                           when().
                              get("https://restful-booker.herokuapp.com/booking/1001");
		
		response.prettyPrint();
		
		assertEquals(404, response.getStatusCode());
		assertTrue(response.asString().contains("Not Found"));
		assertFalse(response.asString().contains("Suleyman"));		
	}
	
	
	
	/*
	 Positive Scenario:
	 When Asagidaki Endpoint'e bir GET request yolladim 
	 https://restful-booker.herokuapp.com/booking/7   
    And Accept type “application/json” dir
    Then 
    HTTP Status Code 200
    And Response format "application/json"
    And firstname "Sally"
    And lastname "Jackson"
    And checkin date "2017-04-19"
    And checkout date "2020-03-22"
	*/
	
	@Test
	public void positiveScenario02() {
		
		Response response = given().
				               accept("application/json").
				            when().
				               get("https://restful-booker.herokuapp.com/booking/11");
		response.prettyPrint();
		
		//Status code icin 1.Yol:
		response.
		then().
		assertThat().
		statusCode(200).
		contentType("application/json").
		body("firstname", Matchers.equalTo("komando")).
		body("lastname", Matchers.equalTo("mavibere")).
		body("totalprice", Matchers.equalTo(6666)).
		body("depositpaid", Matchers.equalTo(false)).
		body("bookingdates.checkin", Matchers.equalTo("2020-05-02")).
		body("bookingdates.checkout", Matchers.equalTo("2020-05-05")).
		body("additionalneeds", Matchers.equalTo("Wifiiiiiiii"));
		
		//Tekrarli body() kullanmadan nasil yapilir?
		response.
		then().
		assertThat().
		statusCode(200).
		contentType("application/json").
		body("firstname", Matchers.equalTo("komando"),
			 "lastname", Matchers.equalTo("mavibere"),
			 "totalprice", Matchers.equalTo(6666),
			 "depositpaid", Matchers.equalTo(false),
			 "bookingdates.checkin", Matchers.equalTo("2020-05-02"),
			 "bookingdates.checkout", Matchers.equalTo("2020-05-05"),
			 "additionalneeds", Matchers.equalTo("Wifiiiiiiii"));

		//Status code icin 2.Yol:
		assertEquals(200, response.getStatusCode());
	
	}
	
	
	

	/*
	 *   Positive Scenario:
		 When I send a GET request to REST API URL
		 http://dummy.restapiexample.com/api/v1/employees
	     And Accept type is “application/JSON”
	     Then
	     HTTP Status Code should be 200
	     And Response format should be “application/JSON”
	     And there should be 24 employees
	     And “Ashton Cox” should be one of the employees
	     And 21, 61, and 23 should be among the employee ages
	 */
	
	@Test
    public void positiveScenario03() {
        Response response = given().
                               accept(ContentType.JSON).//"application/JSON" da kullanilir 
                               //import io.restassured.http.ContentType;

                            when().
                               get("http://dummy.restapiexample.com/api/v1/employees"); 
        response.prettyPrint();
        
        response.
           then().
           assertThat().
           statusCode(200).
           contentType(ContentType.JSON).//"application/JSON" da kullanilir ama bu daha iyi
           body(
        		   "data.id", Matchers.hasSize(24),
        		   "data.employee_name",Matchers.hasItem("Ashton Co"),
        		   "employee_salary", Matchers.hasItems("21","61","23")
    		   );

	}
	
	
/*
 * When I send a GET request to REST API URL 
   https://restful-booker.herokuapp.com/booking/5  
   Then HTTP Status Code 200 olsun
   And Response content type “application/JSON” olsun
   And “firstname” “Jim” olsun
   And “totalprice” 602 olsun
   And “checkin” “2015-06-12" olsun
 */

	@Test
    public void positiveScenario04() 
	{
		Response response = given().                              
                            when().
                               get("https://restful-booker.herokuapp.com/booking/5"); 
        response.prettyPrint();
        
        response.
           then().
           assertThat().
           statusCode(200).
           contentType(ContentType.JSON).//"application/JSON" da kullanilir ama bu daha iyi
           body(
        		   "firstname", Matchers.equalTo("Jim"),
        		   "totalprice",Matchers.equalTo(528),
        		   "bookingdates.checkin", Matchers.equalTo("2019-05-05")
    		   );

	}
	
	

	
}





