package it.unisa.c07.biblionet.model.entity.utente;

import it.unisa.c07.biblionet.utils.Length;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Column;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Questa classe rappresenta un utente registrato alla piattaforma.
 */
@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class UtenteRegistrato {

    /**
     * Rappresenta l'ID di un utente registrato.
     */
    @Id
    @Column(nullable = false, length = Length.LENGTH_320)
    @NonNull
    private String email;

    /**
     * Rappresenta la password di un utente registrato.
     */
    @Column(nullable = false, length = Length.LENGTH_320)
    @NonNull
    private byte[] password;

    /**
     * Rappresenta il tipo di utente.
     * Utile per essere chiamato sui figli, nella entity UtenteRegistrato
     * non ha senso di essere chiamato.
     */
    @Column(nullable = false, length = Length.LENGTH_10)
    @NonNull
    //@Transient rimosso
    private String tipo;

    /**
     *
     * @param email la mail dell'utente registrato.
     * @param password la password dell'utente registrato.
     * @param provincia la provincia dove vive l'utente.
     * @param citta la città dove vive l'utente.
     * @param via la via dove vive l'utente.
     * @param recapitoTelefonico il recapito telefonico dell'utente.
     */
    public UtenteRegistrato(final String email, final String password, final String tipo) {

        this.email = email;

        this.tipo = tipo;

        /*
        Questo blocco di codice serve per l'hashing della password,
        utilizzando l'algoritmo SHA-256.
        Una volta concluso, setta la password come byte array correttamente
        */
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte[] arr = md.digest(password.getBytes());
            this.password = arr;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementa il set della password effettuando l'hash.
     * @param password la password da settare
     */
    public void setPassword(final String password) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte[] arr = md.digest(password.getBytes());
            this.password = arr;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette l'inserimento di una password già hashata.
     * @param hashPassword la password
     */
    public void setHashedPassword(final byte[] hashPassword) {
        this.password = hashPassword;
    }



}
