package com.example.matchmaker.matchmakerapi;

import com.example.matchmaker.matchmakerapi.entity.repository.*;
import com.example.matchmaker.matchmakerapi.service.GeneroJogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RequiredArgsConstructor
@EnableFeignClients
public class MatchmakerApiApplication implements CommandLineRunner {
	private final GeneroJogoService generoJogoService;

	public static void main(String[] args) {
		SpringApplication.run(MatchmakerApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.generoJogoService.addJogosApi();
	}
}
