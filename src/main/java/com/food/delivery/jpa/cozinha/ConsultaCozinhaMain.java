package com.food.delivery.jpa.cozinha;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.repository.KitchenRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		KitchenRepository cozinhaRepository = applicationContext.getBean(KitchenRepository.class);

		List<Kitchen> todasCozinhas = cozinhaRepository.listar();

		for (Kitchen cozinha : todasCozinhas) {
			System.out.println(cozinha.getNome());
		}
	}

}
