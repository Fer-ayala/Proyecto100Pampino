package com.cibertec.models.service.impl;

import com.cibertec.models.entity.Usuario;
import com.cibertec.models.repository.UsuarioRepository;
import com.cibertec.models.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerUsuarioXnombre(String nomUsuario) {
        return usuarioRepository.findByNomusuario(nomUsuario);
    }
}
