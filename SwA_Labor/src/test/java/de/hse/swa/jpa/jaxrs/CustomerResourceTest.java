package de.hse.swa.jpa.jaxrs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.MediaType;

import de.hse.swa.jpa.orm.model.Customer;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CustomerResourceTest {
    private static Customer firstCustomer = new Customer();
    private static Customer secondCustomer = new Customer();

    @BeforeEach
    public void clearUserList() {
        given().contentType(MediaType.APPLICATION_JSON).body(firstCustomer).delete("/CustomerResource/customer").then()
                .assertThat().statusCode(204);

        firstCustomer.setFirstname("First Customer");
        firstCustomer.setPassword("firstpasswd");
        firstCustomer.setUsername("firstUser");

        secondCustomer.setFirstname("Second Customer");
        secondCustomer.setPassword("secondpasswd");
        secondCustomer.setUsername("secondUser");
    }

    @Test
    public void testlogin_1() {
        given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .post("/CustomerResource/customer/login").then().assertThat().statusCode(200);
        Customer response = given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .post("/CustomerResource/customer/login").body().as(Customer.class);

        assertNotEquals("NotfirstUser", response.getUsername());
        assertNotEquals("Notfirstpasswd", response.getPassword());
    }
     
    @Test
    public void testGetCustomer(){
        given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .get("/CustomerResource/customer").then().assertThat().statusCode(200);
    }

    @Test
    public void testPut(){
        given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .put("/CustomerResource/customer").then().assertThat().statusCode(200);
    }
/*
    @Test
    public void testPut_2(){
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(firstCustomer)
				.when()
				.post("/CustomerResource/customer").then().assertThat().statusCode(200);
                
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(secondCustomer)
				.when()
				.post("/CustomerResource/customer").then().assertThat().statusCode(200);
		
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.when()
				.get("/CustomerResource/customer").then().assertThat().statusCode(200);
		
        Response response = given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .get("/CustomerResource/customer");

		List<Customer> users = Arrays.asList(response.getBody().as(Customer[].class));  
        assert(users != null && users.size() == 2);
		
		Customer user = users.get(1);
		user.setFirstname("John");

				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(user)
				.when()
				.put("/CustomerResource/customer").then().assertThat().statusCode(200);

        response = given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .get("/CustomerResource/customer");        
        users = Arrays.asList(response.getBody().as(Customer[].class));
		assert(users != null && users.size() == 2);
		assert(users.get(1).getFirstname().equals("John"));
		assert(users.get(1).getId().equals(2L));
    }
*/
}
