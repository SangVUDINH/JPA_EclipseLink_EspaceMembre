package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Client;

@Stateless
public class ClientDao {
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT c FROM Client c WHERE c.email=:email";
    private static final String PARAM_EMAIL           = "email";

    @PersistenceContext( unitName = "bdd_sdzee_PC" )
    private EntityManager       em;

    public void creer( Client client ) throws DAOException {
        try {
            em.persist( client );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }

    };

    public Client trouver( String email ) throws DAOException {
        Client client = null;
        Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
        requete.setParameter( PARAM_EMAIL, email );

        try {
            client = (Client) requete.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }

        return client;
    };

    // Client trouver( long id ) throws DAOException;

    public List<Client> lister() throws DAOException {

        try {
            TypedQuery<Client> query = em.createQuery( "SELECT c FROM Client c ORDER BY c.id", Client.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void supprimer( Client client ) throws DAOException {
        try {
            em.remove( em.merge( client ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

}
