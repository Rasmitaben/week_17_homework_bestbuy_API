package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("The limit is : " + limit);
    }

    //2. Extract the total
    @Test
    public void test002(){
        int total = response.extract().path("total");
        System.out.println("The total is : " + total);

    }

    //3. Extract the name of 5th store
    @Test
    public void test003(){
        String name = response.extract().path("data[4].name");
        System.out.println("The name of 5th store is : " + name);
    }
    //4. Extract the names of all the store
    @Test
    public void test004(){
        List<String> names = response.extract().path("data.name");
        System.out.println("The name of all stores are : " + names);
    }
    //5. Extract the storeId of all the store
    @Test
    public void test005(){
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("All store's Id are : " + ids);
    }
    //6. Print the size of the data list
    @Test
    public void test006(){
        List<?> data = response.extract().path("data");
        int size = data.size();
        System.out.println("The size of the data list is : " + size);
    }
    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007(){
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("All the value of the store where store name = St Cloud  " + value );
    }
    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008(){
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("The address of the store where store name = Rochester : " + address);
    }
    //9. Get all the services of 8th store
    @Test
    public void test09(){
        List<List<String>> services = response.extract().path("data[7].services");
        System.out.println("List of all the services of 8th store " + services);
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010(){
        //List<List<String>> storeServices = response.extract().path("data.findAll{it.name == 'Windows Store'}.services.name");
        List<HashMap<String, ?>> windowsStoreServices = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }.services.storeservices");
        System.out.println("Storeservices of the store where service name = Windows Store is " + windowsStoreServices);

    }

    //11. Get all the storeId of all the store
    @Test
    public void test011(){
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("Get all the storeId of all the store : " + storeId);
    }
    //12. Get id of all the store
    @Test
    public void test012(){
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("Get id of all the store : " + ids);
    }
    //13. Find the store names Where state = ND
    @Test
    public void test013(){
        List<String> name = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("Store names Where state = ND " + name );
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){
        List<List<?>> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        int number = services.size();
        System.out.println("Total number of services : " + number);
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){
       // List<String> create = response.extract().path("data.findAll{it.name == 'Windows Store'}!= null }.services.createdAt");
        List<HashMap<String, ?>> createdAt = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }.services.createdAt");
        System.out.println("the createdAt for all services whose name = “Windows Store” :" + createdAt );
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){
        List<HashMap<String, ?>> name = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("The name of all services Where store name = “Fargo” :" + name);

    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println("zip of all the store " + zip);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println(" Zip of store name = Roseville " + zip);
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
       // List<String> services = response.extract().path("data.findAll{it.services.name == 'Magnolia Home Theater'} !=null.storeservices");
        List<HashMap<String,?>> storeservices = response.extract().path("data.findAll { it.services.find { it.name == 'Magnolia Home Theater' } != null }.services.storeservices");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater : " + storeservices);
    }


    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("The lat of all the stores " + lat);
    }

}
