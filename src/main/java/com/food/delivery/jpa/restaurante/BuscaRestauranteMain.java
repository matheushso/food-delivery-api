package com.food.delivery.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.RestaurantRepository;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestaurantRepository restauranteRepository = applicationContext.getBean(RestaurantRepository.class);

		Restaurant restaurante = restauranteRepository.buscar(1L);

		System.out.printf("Nome: %s | Taxa frete: %.2f | Nome cozinha: %s \n", restaurante.getNome(),
				restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
	}

}
