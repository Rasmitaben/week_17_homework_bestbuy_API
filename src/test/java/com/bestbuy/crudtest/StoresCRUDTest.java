package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {

    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String type = "textBox" + TestUtils.getRandomValue();
    static String address = "B1" + TestUtils.getRandomValue();
    static String address2 = "wood street" + TestUtils.getRandomValue();
    static String City = "Harrow";
    static String State = "London";
    static String Zip = "546734";
    static Double lat = 12.875;
    static Double lag = 34.56748;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;


    @Test
    public void test001() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(State);
        storePojo.setZip(Zip);
        storePojo.setLat(lat);
        storePojo.setLng(lag);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        storeId =response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id No is" +storeId);
        response.prettyPrint();

    }



    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void test004() {
        Response response = given()
                .when()
                .get("/stores" +"/"+storeId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

        @Test
        public void test003() {
            StorePojo storePojo = new StorePojo();
            storePojo.setHours("Mon: 10-10; Tue: 10-8; Wed: 10-7; Thurs: 10-6; Fri: 10-6; Sat: 10-9; Sun: 10-8");
            storePojo.setName(name);
            storePojo.setType(type);
            storePojo.setAddress(address);
            storePojo.setAddress2(address2);
            storePojo.setCity(City);
            storePojo.setState(State);
            storePojo.setZip(Zip);
            storePojo.setLat(lat);
            storePojo.setLng(lag);
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .body(storePojo)
                    .patch("/stores" +"/"+storeId);
            response.prettyPrint();
            response.then().log().all().statusCode(200);
        }
    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/stores" +"/" +storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

}
