package com.aluracursos.ChallengeLiteralura.Literaruta;

import com.aluracursos.ChallengeLiteralura.Literaruta.Principal.Principal;
import com.aluracursos.ChallengeLiteralura.Literaruta.Repository.iAutorRepository;
import com.aluracursos.ChallengeLiteralura.Literaruta.Repository.iLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterarutaApplication implements CommandLineRunner {

	@Autowired
	private iLibroRepository libroRepository;
	@Autowired
	private iAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterarutaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(libroRepository, autorRepository);
		principal.muestraMenu();
	}

}
