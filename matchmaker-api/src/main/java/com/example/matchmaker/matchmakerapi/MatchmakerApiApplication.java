package com.example.matchmaker.matchmakerapi;

import com.example.matchmaker.matchmakerapi.entity.repository.*;
import com.example.matchmaker.matchmakerapi.service.GeneroJogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RequiredArgsConstructor
@EnableFeignClients
public class MatchmakerApiApplication  {

	private final UsuarioRepository usuarioRepository;
	private final PlanoRepository planoRepository;
	private final PerfilRepository perfilRepository;
	private final GeneroJogoRepository generoJogoRepository;
	private final GeneroJogoPerfilRepository generoJogoPerfilRepository;
	private final InteresseRepository interesseRepository;
	private final InteressePerfilRepository interessePerfilRepository;
	private final ConsoleRepository consoleRepository;
	private final ConsolePerfilRepository consolePerfilRepository;
	private final MidiaRepository midiaRepository;
	private final GeneroJogoService generoJogoService;

	public static void main(String[] args) {
		SpringApplication.run(MatchmakerApiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Plano plano = new Plano(null, "free", "gratuito", 15.50F);
//		planoRepository.save(plano);
//
//		Usuario usuario = new Usuario();
//		usuario.setNome("Adam");
//		usuario.setSobrenome("Sandler");
//		usuario.setSenha("123456");
//		usuario.setIdentidadeGenero("Homem");
//		usuario.setEmail("adam@sandler.com");
//		usuario.setCelular("11234456789");
//		usuario.setDtNascimento(LocalDate.of(2000,01,01));
//		usuarioRepository.save(usuario);
//
////		Perfil perfil = new Perfil(null,usuario, "adamSandMan", "ola sou o famoso ator de Bollywood", 10.0F, "Hetero", false, true, false, true, plano,null,null, null, null);
////		perfilRepository.save(perfil);
//
////		GeneroJogo generoJogo = new GeneroJogo(null, "Esportes", "Jogos como EaFC24, F1 23, NBA 2K");
////		GeneroJogo generoJogo1 = new GeneroJogo(null, "FPS", "Jogos como CoD, Battlefield, Cs:Go");
////		generoJogoRepository.saveAll(Arrays.asList(generoJogo,generoJogo1));
//
////		GeneroJogoPerfilId generoJogoPerfilId = new GeneroJogoPerfilId(perfil.getIdPerfil(), generoJogo.getIdGeneroJogos());
////		GeneroJogoPerfilId generoJogoPerfilId1 = new GeneroJogoPerfilId(perfil.getIdPerfil(), generoJogo1.getIdGeneroJogos());
////		GeneroJogoPerfil generoJogoPerfil = new GeneroJogoPerfil(generoJogoPerfilId, perfil, generoJogo, true);
////		GeneroJogoPerfil generoJogoPerfil1 = new GeneroJogoPerfil(generoJogoPerfilId1, perfil, generoJogo1, true);
////		generoJogoPerfilRepository.saveAll(Arrays.asList(generoJogoPerfil,generoJogoPerfil1));
//
//		Interesse interesse = new Interesse(null, "Musica", "Interesse em musicas");
//		Interesse interesse1 = new Interesse(null, "Artes", "Interesse em artes");
//		interesseRepository.saveAll(Arrays.asList(interesse,interesse1));
//
////		InteressePerfilId interessePerfilId = new InteressePerfilId(perfil.getIdPerfil(), interesse.getIdInteresse());
////		InteressePerfilId interessePerfilId1 = new InteressePerfilId(perfil.getIdPerfil(), interesse1.getIdInteresse());
////		InteressePerfil interessePerfil = new InteressePerfil(interessePerfilId,perfil,interesse,true);
////		InteressePerfil interessePerfil1 = new InteressePerfil(interessePerfilId1,perfil,interesse1,true);
////		interessePerfilRepository.saveAll(Arrays.asList(interessePerfil1,interessePerfil));
//
//		Console console = new Console(null, "PS4");
//		Console console1 = new Console(null, "XBOX One");
//		consoleRepository.saveAll(Arrays.asList(console1,console));
//
////		ConsolePerfilId consolePerfilId = new ConsolePerfilId(perfil.getIdPerfil(), console.getId());
////		ConsolePerfilId consolePerfilId1 = new ConsolePerfilId(perfil.getIdPerfil(), console1.getId());
////		ConsolePerfil consolePerfil = new ConsolePerfil(consolePerfilId,perfil,console,true);
////		ConsolePerfil consolePerfil1 = new ConsolePerfil(consolePerfilId1,perfil,console1,true);
////		consolePerfilRepository.saveAll(Arrays.asList(consolePerfil,consolePerfil1));
//
////		Midia midia = new Midia(null, perfil, "ubesfbdu",false);
////		Midia midia1 = new Midia(null, perfil, "ubsdojbnefesfbdu",true);
////		midiaRepository.saveAll(Arrays.asList(midia,midia1));
//
//		this.generoJogoService.addJogosApi();
//
//	}
}
