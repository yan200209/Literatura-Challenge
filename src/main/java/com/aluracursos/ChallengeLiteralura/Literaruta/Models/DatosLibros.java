package com.aluracursos.ChallengeLiteralura.Literaruta.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibros(
        @JsonAlias("id") Long libroId,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> Datosautor,
        @JsonAlias("languages")List<String> idiomas,
        @JsonAlias("download_count") Double NumerodeDescargas ) {
}
