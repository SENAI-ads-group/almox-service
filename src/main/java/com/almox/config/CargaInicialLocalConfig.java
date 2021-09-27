package com.almox.config;

import com.almox.model.entidades.Usuario;
import com.almox.model.enums.TipoUsuario;
import com.almox.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Profile("local")
@Configuration
public class CargaInicialLocalConfig implements CommandLineRunner {

    @Value("${executar-carga-inicial:false}")
    private boolean executar;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!executar) return;

    }
}
