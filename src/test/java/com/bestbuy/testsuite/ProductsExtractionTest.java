package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit

    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("The limit is : " + limit);
    }

    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("The total is : " + total);
    }

    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of 5th product :" + name);
    }

    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> names = response.extract().path("data.name");
        System.out.println("The names of all the products : " + names);
    }
    //25. Extract the productId of all the products
    @Test
    public void test025(){
      List<Integer> ids = response.extract().path("data.id");
        System.out.println("The productId of all the products : " + ids);
    }

    //26. Print the size of the data list
    @Test
    public void test026() {
        List<Integer> ids = response.extract().path("data");
        int num = ids.size();
        System.out.println("The size of the data list : " + num);
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void test027(){
        List<List<String>> product = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("All the value of the product where product name = Energizer - MAX Batteries AA (4- Pack) : " + product);
        }


    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)
    @Test
    public void test028() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) : " + model);
    }

    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<HashMap<String, ?>> category = response.extract().path("data[7].categories");
        System.out.println(" All the categories of 8th products : " + category);
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030(){
        List<HashMap<String, ?>> id =  response.extract().path("data.findAll{it.id == 150115}");
        System.out.println("All the categories of the store where product id : " + id );
    }

    //31. Get all the descriptions of all the products
    @Test
    public void testo31() {
        List<String> description = response.extract().path("data.description");
        System.out.println("The descriptions of all the products : " + description);
    }


    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<String> ids = response.extract().path("data.categories.id");
        System.out.println("Id of all the all categories of all the products : " + ids);
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> name = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("The product names Where type = HardGood : " + name);
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        List<HashMap<String, String>> data = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.totalNumber");
        System.out.println("Total number of categories for the product where product name : " + data );
    }
    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035(){
        List<String> products = response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println("all products whose price < 5.49" +products);
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
    //Pack)”
    @Test
    public void test036() {
        List<String> names = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println(" name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)" + names);

    }
    //37. Find the manufacturer of all the products
    @Test
    public void test037(){
        List<String> manufacture=response.extract().path("data.manufacturer");
        System.out.println("the manufacturer of all the products "+manufacture);
    }


    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test038(){
        List<String> img = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("The imge of products whose manufacturer is = Energizer : " + img);
    }
    //39. Find the createdAt for all categories products whose price > 5.99

    @Test
    public void test039(){
        List<String> products = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("The createdAt for all categories products whose price > 5.99 : " + products);
    }
    //40. Find the uri of all the products
    @Test
    public void test040(){
        List<String> url =  response.extract().path("data.url");
        System.out.println("The uri of all the products : " +url);
    }
}

