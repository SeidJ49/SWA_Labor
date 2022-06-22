/*package de.hse.swa.jpa.jaxrs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import de.hse.swa.jpa.orm.model.Customer;
import de.hse.swa.jpa.orm.model.Department;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
public class DepartmentResourceTest {
     private static Customer firstCustomer = new Customer();
    private static Customer secondCustomer = new Customer();

    @BeforeEach
    public void clearUserList() {
        given().contentType(MediaType.APPLICATION_JSON).body(firstCustomer).delete("/CustomerResource/companies").then()
                .assertThat().statusCode(204);

        firstCustomer.setFirstname("First Customer");
        firstCustomer.setPassword("firstpasswd");
        firstCustomer.setUsername("firstUser");

        secondCustomer.setFirstname("Second Customer");
        secondCustomer.setPassword("secondpasswd");
        secondCustomer.setUsername("secondUser");
    }

    @Test
    public void testGetCustomer(){
        given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .post("/CustomerResource/companies").then().assertThat().statusCode(200);
    }

    @Test
    public void testPut(){
        given().contentType(MediaType.APPLICATION_JSON)
                .body(firstCustomer)
                .when()
                .put("/CustomerResource/companies").then().assertThat().statusCode(200);
    }
}
*/