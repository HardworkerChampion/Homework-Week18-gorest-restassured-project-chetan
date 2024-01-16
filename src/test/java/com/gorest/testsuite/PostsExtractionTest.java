package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void test001(){
        String title = response.extract().path("");
        System.out.println(title);
    }
    //2. Extract the total number of record
    @Test
    public void test002(){
        List<?> listOfTotalNumber = response.extract().path("");
        System.out.println(listOfTotalNumber.size());
    }

    //3. Extract the body of 15th record
    @Test
    public void test003(){
        String body = response.extract().path("[14].body");
        System.out.println(body);
    }
    //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<Integer> listOfUserId = response.extract().path("id");
        System.out.println(listOfUserId);
    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> listOfAllTitle = response.extract().path("title");
        System.out.println(listOfAllTitle);
    }
    //6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test006(){
        List<?> title = response.extract().path("findAll{it.user_id == '5914249'}.title");
        System.out.println(title);
    }
    //7. Extract the body of all records whose id = 93957
    @Test
    public void test007(){
        List<String> body = response.extract().path("findAll{it.id == '93997'}.body");
        System.out.println(body);
    }
}
