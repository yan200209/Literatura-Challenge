package com.aluracursos.ChallengeLiteralura.Literaruta.Repository;

import com.aluracursos.ChallengeLiteralura.Literaruta.Models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iAutorRepository extends JpaRepository<Autor,Long> {
    List<Autor> findAll();
    List<Autor> findByfechadenacimientoGreaterThanEqual(String añoBuscado);
    Optional<Autor> findFirstByNombreContainsIgnoreCase(String escritor);
}
