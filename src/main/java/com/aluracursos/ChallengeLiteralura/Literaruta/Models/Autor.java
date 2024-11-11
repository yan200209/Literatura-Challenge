package com.aluracursos.ChallengeLiteralura.Literaruta.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechadenacimiento;
    @OneToMany(mappedBy = "DatosAutor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@Transient
    private List<Libro> libros;

    public Autor(){
    }
    public Autor(DatosAutor autor){
        this.nombre = autor.nombre();
        this.fechadenacimiento = autor.fechadenacimiento();
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", fechadenacimiento=" + fechadenacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(String fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
