package br.com.leroymarcel.store.repository;

import br.com.leroymarcel.store.entity.Ferramenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, String> {
}
