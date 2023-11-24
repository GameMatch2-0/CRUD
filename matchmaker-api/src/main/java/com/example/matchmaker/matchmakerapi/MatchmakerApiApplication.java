package com.example.matchmaker.matchmakerapi;

import com.example.matchmaker.matchmakerapi.entity.*;
import com.example.matchmaker.matchmakerapi.entity.embeddable.GeneroJogoPerfilId;
import com.example.matchmaker.matchmakerapi.entity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class MatchmakerApiApplication implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;
	private final PlanoRepository planoRepository;
	private final PerfilRepository perfilRepository;
	private final GeneroJogoRepository generoJogoRepository;
	private final GeneroJogoPerfilRepository generoJogoPerfilRepository;

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
		usuario.setSenha("123456");
		usuario.setIdentidadeGenero("Homem");
		usuario.setEmail("adam@sandler.com");
		usuario.setContato("11234456789");
		usuario.setDtNascimento(LocalDate.of(2000,01,01));
		usuarioRepository.save(usuario);

		Perfil perfil = new Perfil(1L,usuario, "adamSandMan", "ola sou o famoso ator de Bollywood", 10.0F, "Hetero", false, true, false, true, plano,null);
		perfilRepository.save(perfil);

		GeneroJogo generoJogo = new GeneroJogo(1L, "Esportes", "Jogos como EaFC24, F1 23, NBA 2K");
		GeneroJogo generoJogo1 = new GeneroJogo(2L, "FPS", "Jogos como CoD, Battlefield, Cs:Go");
		generoJogoRepository.saveAll(Arrays.asList(generoJogo,generoJogo1));

		GeneroJogoPerfilId generoJogoPerfilId = new GeneroJogoPerfilId(perfil.getIdPerfil(), generoJogo.getIdGeneroJogos());
		GeneroJogoPerfilId generoJogoPerfilId1 = new GeneroJogoPerfilId(perfil.getIdPerfil(), generoJogo1.getIdGeneroJogos());
		GeneroJogoPerfil generoJogoPerfil = new GeneroJogoPerfil(generoJogoPerfilId, perfil, generoJogo, true);
		GeneroJogoPerfil generoJogoPerfil1 = new GeneroJogoPerfil(generoJogoPerfilId1, perfil, generoJogo1, true);
		generoJogoPerfilRepository.saveAll(Arrays.asList(generoJogoPerfil,generoJogoPerfil1));
	}
}
