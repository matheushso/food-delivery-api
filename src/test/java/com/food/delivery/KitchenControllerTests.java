package com.food.delivery;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.food.delivery.domain.model.Kitchen;

import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenControllerTests {

	@LocalServerPort
	private Integer port;

	@Test
	public void shouldReturnStatus200_WhenConsultKitchens() {
		given()
			.basePath("/kitchens")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnStatus200_WhenFindKitchenPerId() {
		given()
			.pathParam("id", 1)
			.basePath("/kitchens/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnStatus404_WhenNotFoundKitchenPerId() {
		given()
			.pathParam("id", 9)
			.basePath("/kitchens/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void shouldReturnStatus201_WhenCreatedKitchen() {
		Kitchen kitchen = Kitchen.builder()
				.name("Kitchen")
				.build();

		createKitchen(kitchen);

		Kitchen kitchenCreated = findById(3);

		assertEquals(kitchen.getName(), kitchenCreated.getName());
	}

	@Test
	public void shouldReturnStatus200_WhenUpdatedKitchen() {
		Kitchen kitchenCreate = Kitchen.builder()
				.name("Kitchen")
				.build();

		createKitchen(kitchenCreate);

		Kitchen kitchen = Kitchen.builder()
				.name("Kitchen update")
				.build();

		given()
			.pathParam("id", 3)
			.basePath("/kitchens/{id}")
			.port(port)
			.body(kitchen)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.OK.value());

		Kitchen kitchenUpdated = findById(3);

		assertEquals(kitchen.getName(), kitchenUpdated.getName());
	}
	
	@Test
	public void shouldReturnStatus404_WhenUpdatedKitchenWasNotFound() {
		Kitchen kitchen = Kitchen.builder()
				.name("Kitchen update")
				.build();

		given()
			.pathParam("id", 9)
			.basePath("/kitchens/{id}")
			.port(port)
			.body(kitchen)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnStatus204_WhenDeletedKitchen() {
		Kitchen kitchen = Kitchen.builder()
				.name("Kitchen")
				.build();
		
		createKitchen(kitchen);
		
		given()
			.pathParam("id", 4)
			.basePath("/kitchens/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.delete()
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void shouldReturnStatus404_WhenDeletedKitchenNotFound() {
		given()
			.pathParam("id", 4)
			.basePath("/kitchens/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.delete()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnStatus409_WhenDeletedKitchenIsInUse() {
		given()
			.pathParam("id", 1)
			.basePath("/kitchens/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.delete()
		.then()
			.statusCode(HttpStatus.CONFLICT.value());
	}
	
	private Kitchen findById(int id) {
		return given()
				.pathParam("id", id)
				.basePath("/kitchens/{id}")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
				.as(Kitchen.class);
	}
	
	private void createKitchen(Kitchen kitchen) {
		given()
			.basePath("/kitchens")
			.port(port)
			.body(kitchen)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
}
