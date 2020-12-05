package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import tools.JodaDateTimeConverter;

@Entity
public class Commande {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long     id;

    @ManyToOne
    @JoinColumn( name = "id_client" )
    private Client   client;

    @Column( columnDefinition = "TIMESTAMP" )
    @Converter( name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class )
    @Convert( "dateTimeConverter" )
    private DateTime dateCommande;

    @Column( name = "montant" )
    private Double   montantCommande;

    @Column( name = "mode_paiement" )
    private String   modePaiementCommande;

    @Column( name = "statut_paiment" )
    private String   statutPaiementCommande;

    @Column( name = "mode_livraison" )
    private String   modeLivraisonCommande;

    @Column( name = "statut_livraison" )
    private String   statutLivraisonCommande;

    public Client getClient() {
        return client;
    }

    public void setClient( Client client ) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public DateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande( DateTime dateCommande ) {
        this.dateCommande = dateCommande;
    }

    public Double getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande( Double montantCommande ) {
        this.montantCommande = montantCommande;
    }

    public String getModePaiementCommande() {
        return modePaiementCommande;
    }

    public void setModePaiementCommande( String modePaiementCommande ) {
        this.modePaiementCommande = modePaiementCommande;
    }

    public String getStatutPaiementCommande() {
        return statutPaiementCommande;
    }

    public void setStatutPaiementCommande( String statutPaiementCommande ) {
        this.statutPaiementCommande = statutPaiementCommande;
    }

    public String getStatutLivraisonCommande() {
        return statutLivraisonCommande;
    }

    public void setStatutLivraisonCommande( String statutLivraisonCommande ) {
        this.statutLivraisonCommande = statutLivraisonCommande;
    }

    public String getModeLivraisonCommande() {
        return modeLivraisonCommande;
    }

    public void setModeLivraisonCommande( String modeLivraisonCommande ) {
        this.modeLivraisonCommande = modeLivraisonCommande;
    }
}
