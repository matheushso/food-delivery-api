package com.food.delivery.jpa.restaurante;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.RestaurantRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestaurantRepository restauranteRepository = applicationContext.getBean(RestaurantRepository.class);

		Restaurant restaurante = new Restaurant();
		restaurante.setId(1L);
		restaurante.setNome("Caseirinho");
		restaurante.setTaxaFrete(BigDecimal.valueOf(9.0));

		restauranteRepository.salvar(restaurante);

	}

}
