package com.aluracursos.ChallengeLiteralura.Literaruta.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long libroId;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Asegura que el autor se guarde automáticamente
    @JoinColumn(name = "DatosAutor_id")
    private Autor DatosAutor;
    private String idioma;
    private Double NumerodeDescargas;

    public Libro() {
    }

    public Libro(DatosLibros libros) {
        this.libroId = libros.libroId();
        this.titulo = libros.titulo();
        // Si autor es una lista de autores (como parece en tu registro DatosLibro)
        if (libros.Datosautor() != null && !libros.Datosautor().isEmpty()) {
            this.DatosAutor = new Autor(libros.Datosautor().get(0)); // Toma el primer autor de la lista
        } else {
            this.DatosAutor = null; // o maneja el caso de que no haya autor
        }
        this.idioma = idiomaModificado(libros.idiomas());
        this.NumerodeDescargas = libros.NumerodeDescargas();
    }


    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";
        }
        return idiomas.get(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getDatosAutor() {
        return DatosAutor;
    }

    public void setDatosAutor(Autor datosAutor) {
        DatosAutor = datosAutor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumerodeDescargas() {
        return NumerodeDescargas;
    }

    public void setNumerodeDescargas(Double numerodeDescargas) {
        NumerodeDescargas = numerodeDescargas;
    }

    @Override
    public String toString() {
        return " \nid=" + id +
                ", \nlibroId=" + libroId +
                ", \ntitulo='" + titulo+
                ", \nauthors=" + (DatosAutor != null ? DatosAutor.getNombre() : "N/A")+
                ", \nidioma='" + idioma +
                ", \nNumerodeDescargas=" + NumerodeDescargas ;
    }
}
