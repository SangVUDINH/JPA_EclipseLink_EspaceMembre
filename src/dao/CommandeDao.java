package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Commande;

@Stateless
public class CommandeDao {

    @PersistenceContext( unitName = "bdd_sdzee_PC" )
    private EntityManager em;

    public void creer( Commande commande ) throws DAOException {
        try {
            em.persist( commande );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }

    };

    public Commande trouver( long id ) throws DAOException {

        try {
            return em.find( Commande.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void supprimer( Commande commande ) throws DAOException {
        try {
            em.remove( em.merge( commande ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public List<Commande> lister() throws DAOException {

        try {
            TypedQuery<Commande> query = em.createQuery( "SELECT c FROM Commande c ORDER BY c.id", Commande.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /*
     * Commande trouver( long id ) throws DAOException;
     * 
     * List<Commande> lister() throws DAOException;
     * 
     * void supprimer( Commande commande ) throws DAOException;
     */
}
