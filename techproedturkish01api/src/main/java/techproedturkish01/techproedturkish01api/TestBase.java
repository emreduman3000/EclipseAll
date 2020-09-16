package techproedturkish01.techproedturkish01api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TestBase
{
	//pom.xml file'daki testleri commentledim. o sadece testlrede calıs demek oyuzden commentledim


	public RequestSpecification spec01,spec02;
	
	public void setUp01()
	{
		spec01=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();			
	}
	
	public void setUp02()
	{
		spec02=new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1/employees").build();			
	}
	

}
