package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;

    @Column( name = "prenom" )
    private String prenomClient;

    @Column( name = "nom" )
    private String nomClient;

    @Column( name = "adresse" )
    private String adresseClient;

    @Column( name = "telephone" )
    private String telephoneClient;

    @Column( name = "email" )
    private String emailClient;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient( String prenomClient ) {
        this.prenomClient = prenomClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient( String nomClient ) {
        this.nomClient = nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient( String adresseClient ) {
        this.adresseClient = adresseClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient( String telephoneClient ) {
        this.telephoneClient = telephoneClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient( String emailClient ) {
        this.emailClient = emailClient;
    }

}
