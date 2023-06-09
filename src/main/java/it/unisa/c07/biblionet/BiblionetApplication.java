package it.unisa.c07.biblionet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.NoSuchAlgorithmException;


/**
 * Questa è la main class del progetto, che fa partire l'applicazione e popola
 * il database.
 */
@SpringBootApplication
public class BiblionetApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(BiblionetApplication.class, args);
    }

}
