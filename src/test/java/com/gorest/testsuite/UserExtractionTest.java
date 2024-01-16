package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
@Test
    public void test001(){
    List<Integer> listOfAllID = response.extract().path("id");
    System.out.println(listOfAllID);
}
    //2. Extract the all Names
    @Test
    public void test002(){
        List<String> listOfAllNames = response.extract().path("name");
        System.out.println(listOfAllNames);
    }
    //3. Extract the name of 5th object
    @Test
    public void test003(){
        String name = response.extract().path("[4].name");
        System.out.println(name);
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println(names);
    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005(){
        List<String> listOfGender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println(listOfGender);
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test006(){
        List<String> listOfName = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println(listOfName);
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007(){
        List<?> listOfEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println(listOfEmails);
    }
    //8. Get the ids of the object where gender = male
    @Test
    public void test008(){
        List<Integer> listOfIDs = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println(listOfIDs);
    }
    //9. Get all the status
    @Test
    public void test009(){
        List<?> listOfStatus = response.extract().path("status");
        System.out.println(listOfStatus);
    }
    //10. Get email of the object where name = Lal Dwivedi
    @Test
    public void test010(){
        List<?> listOfEmail = response.extract().path("findAll{it.name == 'Paramartha Kocchar'}.email");
        System.out.println(listOfEmail);
    }
    //11. Get gender of id = 5914189
    @Test
    public void test011(){
        List<?> listOfGender = response.extract().path("findAll{it.id == '5914139'}.gender");
        System.out.println(listOfGender);
    }
}
