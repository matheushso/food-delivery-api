package com.food.delivery;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.food.delivery.domain.model.City;
import com.food.delivery.domain.model.State;

import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerTests {

	@LocalServerPort
	private Integer port;

	@Test
	public void shouldReturnStatus200_WhenConsultCities() {
		given()
			.basePath("/cities")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnStatus200_WhenFindCityPerId() {
		given()
			.pathParam("id", 1)
			.basePath("/cities/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnStatus404_WhenNotFoundCityPerId() {
		given()
			.pathParam("id", 9)
			.basePath("/cities/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void shouldReturnStatus201_WhenCreatedCity() {
		City city = City.builder()
				.name("City")
				.state(State.builder().id(1L).build())
				.build();

		createCity(city);

		City cityCreated = findById(5);
		
		assertEquals(city.getName(), cityCreated.getName());
	}
	
	@Test
	public void shouldReturnStatus200_WhenUpdatedCity() {
		City city = City.builder()
				.name("City Updated")
				.state(State.builder().id(1L).build())
				.build();

		given()
			.pathParam("id", 4)
			.basePath("/cities/{id}")
			.port(port)
			.body(city)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.OK.value());

		City cityUpdated = findById(4);

		assertEquals(city.getName(), cityUpdated.getName());
	}
	
	@Test
	public void shouldReturnStatus404_WhenUpdatedCityWasNotFound() {
		City city = City.builder()
				.name("City Updated")
				.state(State.builder().id(1L).build())
				.build();

		given()
			.pathParam("id", 9)
			.basePath("/cities/{id}")
			.port(port)
			.body(city)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnStatus404_WhenUpdatedCityWithStateNotFound() {
		City city = City.builder()
				.name("City Updated")
				.state(State.builder().id(9L).build())
				.build();

		given()
			.pathParam("id", 4)
			.basePath("/cities/{id}")
			.port(port)
			.body(city)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldReturnStatus204_WhenDeletedCity() {
		given()
			.pathParam("id", 2)
			.basePath("/cities/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.delete()
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void shouldReturnStatus404_WhenDeletedCityNotFound() {
		given()
			.pathParam("id", 9)
			.basePath("/kitchens/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.delete()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private City findById(int id) {
		return given()
			.pathParam("id", id)
			.basePath("/cities/{id}")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
			.as(City.class);
	}
	
	private void createCity(City city) {
		given()
			.basePath("/cities")
			.port(port)
			.body(city)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
}
