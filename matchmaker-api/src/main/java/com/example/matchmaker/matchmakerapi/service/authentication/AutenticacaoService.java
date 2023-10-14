package com.example.matchmaker.matchmakerapi.service.authentication;

import com.example.matchmaker.matchmakerapi.domain.Usuario;
import com.example.matchmaker.matchmakerapi.service.authentication.dto.UsuarioDetalhesDto;
import com.example.matchmaker.matchmakerapi.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmailAndDeletedFalse(username);

        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario: %s não encontrado", username));
        }

        return new UsuarioDetalhesDto(usuarioOptional.get());
    }
}
