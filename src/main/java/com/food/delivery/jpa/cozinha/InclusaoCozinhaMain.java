package com.food.delivery.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.food.delivery.FoodDeliveryApiApplication;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.repository.KitchenRepository;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodDeliveryApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		KitchenRepository cozinhaRepository = applicationContext.getBean(KitchenRepository.class);

		Kitchen cozinha1 = new Kitchen();
		cozinha1.setNome("Brasileira");

		Kitchen cozinha2 = new Kitchen();
		cozinha2.setNome("Japonesa");

		cozinha1 = cozinhaRepository.salvar(cozinha1);
		cozinha2 = cozinhaRepository.salvar(cozinha2);

		System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
		System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
	}

}
