package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDao;
import dao.CommandeDao;
import entities.Client;
import entities.Commande;
import forms.CreationCommandeForm;

/**
 * Servlet implementation class CreationCommande
 */
@WebServlet( "/CreationCommande" )
public class CreationCommande extends HttpServlet {
    private static final long  serialVersionUID  = 1L;
    public static final String VUE_FORM          = "/WEB-INF/creerCommande.jsp";

    public static final String ATT_COMMANDE      = "commande";
    public static final String ATT_FORM          = "form";
    public static final String SESSION_CLIENTS   = "clients";
    public static final String SESSION_COMMANDES = "commandes";

    public static final String VUE_SUCCESS       = "/WEB-INF/afficherCommande.jsp";

    @EJB
    private ClientDao          clientDao;

    @EJB
    private CommandeDao        commandeDao;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        CreationCommandeForm formCommande = new CreationCommandeForm( commandeDao, clientDao );
        Commande commande = formCommande.creerCommande( request );

        request.setAttribute( ATT_FORM, formCommande );
        request.setAttribute( ATT_COMMANDE, commande );

        if ( formCommande.getErreurs().isEmpty() ) {

            HttpSession session = request.getSession();
            Map<Long, Client> clients = (HashMap<Long, Client>) session.getAttribute( SESSION_CLIENTS );

            if ( clients == null ) {
                clients = new HashMap<Long, Client>();
            }

            session.setAttribute( SESSION_CLIENTS, clients );

            /* Ensuite r�cup�ration de la map des commandes dans la session */
            Map<String, Commande> commandes = (HashMap<String, Commande>) session.getAttribute( SESSION_COMMANDES );
            /*
             * Si aucune map n'existe, alors initialisation d'une nouvelle map
             */
            if ( commandes == null ) {
                commandes = new HashMap<String, Commande>();
            }
            /* Puis ajout de la commande courante dans la map */

            // commandes.put( commande.getDateCommande(), commande );

            /* Et enfin (r�)enregistrement de la map en session */
            session.setAttribute( SESSION_COMMANDES, commandes );

            /* Affichage de la fiche r�capitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );

            System.out.println( formCommande.getErreurs() );

        }
    }

}
