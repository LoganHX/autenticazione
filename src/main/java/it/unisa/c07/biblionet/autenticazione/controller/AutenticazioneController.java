package it.unisa.c07.biblionet.autenticazione.controller;

import it.unisa.c07.biblionet.autenticazione.service.AutenticazioneService;
import it.unisa.c07.biblionet.config.JwtGeneratorInterface;
import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Implementa il controller per il sottosistema
 * Autenticazione.
 * @author Ciro Maiorino , Giulio Triggiani
 */
@Controller
//@SessionAttributes("loggedUser")
@RequiredArgsConstructor
@RequestMapping("/autenticazione")
public class AutenticazioneController {
    /**
     * Il service per effettuare le operazioni di persistenza.
     */
    private final AutenticazioneService autenticazioneService;
    private final JwtGeneratorInterface jwtGenerator;

    /**
     * Implementa la funzionalità che permette
     * di visualizzare la view del login.
     * @param model il Model
     * @return la pagina dove è visualizzato
     */
    /*
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String visualizzaLogin(final Model model) {
        model.addAttribute("loggedUser", null);
        return "autenticazione/login";
    }
    */


    /**
     * Implementa la funzionalità di login come utente.
     * @param email dell'utente.
     * @param password password dell'utente.
     * @return rimanda alla pagina di home.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam final String email,
                                   @RequestParam final String password) {

        //todo one secret key for each user? che significa
        UtenteRegistrato utente = autenticazioneService.login(email, password);

            if (utente == null) {
                //System.out.println("Forbidden");
                return null;
            } else {
                return new ResponseEntity<>(jwtGenerator.generateToken(utente), HttpStatus.OK);

            }


    }



    /**
     * Implenta la funzionalità che permette
     * di effettuare il logout dell'utente
     * togliendolo dalla sessione.
     * @param status contiene i dati della sessione.
     * @return Rimanda alla pagina di index.
     */
    /*
    @RequestMapping(value = "/logout", method = RequestMethod.GET)

    public String logout(final SessionStatus status) {
        status.setComplete();
        return "index";
    }
    todo la si fa lato client: https://stackoverflow.com/questions/61473907/how-can-logout-using-spring-boot-jwt
    */



    /**
     * Implementa la funzionalità che permette
     * di aggiungere un utente alla sessione.
     * @return dell'utente in sessione.
     */
    //@ModelAttribute("loggedUser")
    //public UtenteRegistrato utenteRegistrato() {return new UtenteRegistrato();}

}
