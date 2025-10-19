package br.com.leroymarcel.store.repository;

import br.com.leroymarcel.store.entity.Ferramenta;
import br.com.leroymarcel.store.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, String> {
    List<Ferramenta> findByUsuario(Usuario usuario);
}
