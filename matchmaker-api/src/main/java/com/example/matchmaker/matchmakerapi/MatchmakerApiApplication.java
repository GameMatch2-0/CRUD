package com.example.matchmaker.matchmakerapi;

import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.entity.repository.UsuarioRepository;
import com.example.matchmaker.matchmakerapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class MatchmakerApiApplication implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(MatchmakerApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Adam");
		usuario.setSobrenome("Sandler");
		usuario.setSenha("12345");
		usuario.setIdentidadeGenero("Homem");
		usuario.setEmail("adam@sandler.com");
		usuario.setContato("11234456789");
		usuario.setDtNascimento(LocalDate.of(2000,01,01));

		usuarioRepository.save(usuario);

	}
}
