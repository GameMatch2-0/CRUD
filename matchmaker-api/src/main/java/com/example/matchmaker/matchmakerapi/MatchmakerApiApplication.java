package com.example.matchmaker.matchmakerapi;

import com.example.matchmaker.matchmakerapi.entity.Perfil;
import com.example.matchmaker.matchmakerapi.entity.Plano;
import com.example.matchmaker.matchmakerapi.entity.Usuario;
import com.example.matchmaker.matchmakerapi.entity.repository.PerfilRepository;
import com.example.matchmaker.matchmakerapi.entity.repository.PlanoRepository;
import com.example.matchmaker.matchmakerapi.entity.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class MatchmakerApiApplication implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;
	private final PlanoRepository planoRepository;
	private final PerfilRepository perfilRepository;

	public static void main(String[] args) {
		SpringApplication.run(MatchmakerApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Plano plano = new Plano(10L, "free", "gratuito", 15.50F);
		planoRepository.save(plano);

		Usuario usuario = new Usuario();
		usuario.setNome("Adam");
		usuario.setSobrenome("Sandler");
		usuario.setSenha("12345");
		usuario.setIdentidadeGenero("Homem");
		usuario.setEmail("adam@sandler.com");
		usuario.setContato("11234456789");
		usuario.setDtNascimento(LocalDate.of(2000,01,01));

		usuarioRepository.save(usuario);

		Perfil perfil = new Perfil(10L,usuario, "adamSandMan", "ola sou o famoso ator de Bollywood", 10.0F, "Hetero", false, true, false, true, plano);
		perfilRepository.save(perfil);

	}
}
