package com.aluracursos.ChallengeLiteralura.Literaruta.Repository;

import com.aluracursos.ChallengeLiteralura.Literaruta.Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface iLibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);

    Libro findBytituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdioma(String idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.NumerodeDescargas DESC LIMIT 10")
    List<Libro> findTop10BytituloByNumerodeDescargas();


}
