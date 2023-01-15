package com.food.delivery.jpa.restaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Restaurante;
import com.food.delivery.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteaRepository = applicationContext.getBean(RestauranteRepository.class);

		List<Restaurante> todosRestaurantes = restauranteaRepository.listar();

		for (Restaurante restaurante : todosRestaurantes) {
			System.out.printf("Nome: %s | Taxa frete: %.2f | Nome cozinha: %s \n", restaurante.getNome(),
					restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
		}
	}

}
