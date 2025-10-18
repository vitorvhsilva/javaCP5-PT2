package br.com.leroymarcel.store.repository;

import br.com.leroymarcel.store.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
    boolean existsByEmailUsuario(String emailUsuario);
}