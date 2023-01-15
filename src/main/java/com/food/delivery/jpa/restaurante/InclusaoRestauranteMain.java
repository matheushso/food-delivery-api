package com.food.delivery.jpa.restaurante;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Restaurante;
import com.food.delivery.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("King Esfiharia");
		restaurante1.setTaxaFrete(BigDecimal.valueOf(9.0));

		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Taqueria Mexicana");
		restaurante2.setTaxaFrete(BigDecimal.valueOf(7.0));

		restaurante1 = restauranteRepository.salvar(restaurante1);
		restaurante2 = restauranteRepository.salvar(restaurante2);

		System.out.printf("%d - Nome: %s | Taxa frete: %.2f \n", restaurante1.getId(), restaurante1.getNome(),
				restaurante1.getTaxaFrete());
		System.out.printf("%d - Nome: %s | Taxa frete: %.2f \n", restaurante2.getId(), restaurante2.getNome(),
				restaurante2.getTaxaFrete());
	}

}
