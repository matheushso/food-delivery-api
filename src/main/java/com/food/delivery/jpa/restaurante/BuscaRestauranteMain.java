package com.food.delivery.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Restaurante;
import com.food.delivery.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		Restaurante restaurante = restauranteRepository.buscar(1L);

		System.out.printf("Nome: %s | Taxa frete: %.2f | Nome cozinha: %s \n", restaurante.getNome(),
				restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
	}

}
