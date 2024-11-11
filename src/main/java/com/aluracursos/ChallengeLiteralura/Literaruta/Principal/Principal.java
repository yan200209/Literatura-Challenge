package com.aluracursos.ChallengeLiteralura.Literaruta.Principal;

import com.aluracursos.ChallengeLiteralura.Literaruta.Configuration.ConsumoApi;
import com.aluracursos.ChallengeLiteralura.Literaruta.Configuration.ConvierteDatos;
import com.aluracursos.ChallengeLiteralura.Literaruta.Models.Autor;
import com.aluracursos.ChallengeLiteralura.Literaruta.Models.Datos;
import com.aluracursos.ChallengeLiteralura.Literaruta.Models.DatosLibros;
import com.aluracursos.ChallengeLiteralura.Literaruta.Models.Libro;
import com.aluracursos.ChallengeLiteralura.Literaruta.Repository.iAutorRepository;
import com.aluracursos.ChallengeLiteralura.Literaruta.Repository.iLibroRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class Principal {
    private static final String URLBASE = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi=new ConsumoApi();
    private ConvierteDatos convierteDatos=new ConvierteDatos();
    private Scanner teclado=new Scanner(System.in);
    private List<Libro> datosLibro = new ArrayList<>();
    private iLibroRepository libroRepository;
    private iAutorRepository autorRepository;

    public Principal(iLibroRepository libroRepository, iAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }
    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n|------------------------------|
                    |---------Libros Web ----------|
                    |------------------------------|
                    1 - Agregar Libro por Nombre
                    2 - Libros buscados
                    3 - Buscar libro por Nombre
                    4 - Buscar todos los Autores de libros buscados
                    5 - Buscar Autores por año
                    6 - Buscar Libros por Idioma
                    7 - Top 10 Libros mas Descargados
                    8 - Buscar Autor por Nombre
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    librosBuscados();
                    break;
                case 3:
                    buscarLibroPorNombre();
                    break;
                case 4:
                    BuscarAutores();
                    break;
                case 5:
                    buscarAutoresPorAño();
                    break;
                case 6:
                    buscarLibrosPorIdioma();
                    break;
                case 7:
                    top10LibrosMasDescargados();
                    break;
                case 8:
                    buscarAutorPorNombre();
                    break;
                case 0:
                    System.out.println("|----Cerrando la aplicación...----|");
                    break;
                default:
                    System.out.println("|-------Opción inválida--------|");
            }
        }

    }
    private DatosLibros getDatosLibro() {
        System.out.println("Escribe el nombre del Libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URLBASE + nombreLibro.replace(" ", "+"));
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
        DatosLibros primerLibro=datos.libros().get(0);
        return primerLibro;
    }
    private void buscarLibroWeb() {
        DatosLibros libros = getDatosLibro();
        if (libros == null){
            System.out.println("Libro no encontrado. el valor es null");
            return;
        }
        Libro libro =new Libro(libros);
        libroRepository.save(libro);
        System.out.println(libro.toString());

    }

    private void librosBuscados() {
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("Libros encontrados en la base de datos: ");
            libros.forEach(System.out::println);
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Escribe el nombre del Libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        Libro libroBuscado = libroRepository.findBytituloContainsIgnoreCase(nombreLibro);
        if (libroBuscado != null) {
            System.out.println("El libro buscado fue: " + libroBuscado);
        } else {
            System.out.println("El libro con el titulo '" + nombreLibro + "' no se encontró.");
        }
    }

    private void BuscarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos. \n");
        } else {
            System.out.println("Libros encontrados en la base de datos: \n");
            autores.forEach(System.out::println);
        }
    }

    private void buscarAutoresPorAño() {
        System.out.println("Indica el año para buscar los autores: \n");
        var añoBuscado = teclado.nextLine();
        List<Autor> autoresAño = autorRepository.findByfechadenacimientoGreaterThanEqual(añoBuscado);

        if (autoresAño.isEmpty()) {
            System.out.println("No se encontraron autores del año " + añoBuscado + ".");
        } else {
            System.out.println("Los autores del año " + añoBuscado + " son:");
            autoresAño.forEach(System.out::println);
        }
    }

    private void buscarLibrosPorIdioma(){
        var menuIdioma= """
                Ingrese el idioma a buscar:\n
                |---------------------------------------|
                |----Opcion: es =>Libros en español.----|
                |----Opcion: en =>Libros en Ingles.-----|
                |---------------------------------------|
                """;
        System.out.println(menuIdioma);
        var idioma=teclado.nextLine();
        List<Libro> librosporidioma=libroRepository.findByIdioma(idioma);
        if (librosporidioma.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("Libros según el idioma: " +
                    (idioma.equalsIgnoreCase("es") ? "Español" : "Inglés")
                    + " encontrados en la base de datos:");
            librosporidioma.forEach(System.out::println);
        }
    }

    private void top10LibrosMasDescargados(){
        List<Libro> top10Libros=libroRepository.findTop10BytituloByNumerodeDescargas();
        top10Libros.forEach(s-> System.out.println("Titulo: "+s.getTitulo()+
                ", Numero De Descargas: "+s.getNumerodeDescargas()));
    }

    private void buscarAutorPorNombre(){
        System.out.println("Ingrese nombre del autor que quiere buscar: ");
        var autor = teclado.nextLine();
        Optional<Autor> autorbuscado = autorRepository.findFirstByNombreContainsIgnoreCase(autor);
        if (autorbuscado != null) {
            System.out.println("\nEl escritor buscado fue: " + autorbuscado.get().getNombre());
        } else {
            System.out.println("\nEl escritor con el titulo '" + autor + "' no se encontró.");
        }

    }

}
