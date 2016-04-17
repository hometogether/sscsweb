/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.Constants;

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class ProfiloFacade extends AbstractFacade<Profilo> implements ProfiloFacadeLocal {

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {

        return em;
    }

    public ProfiloFacade() {
        super(Profilo.class);
    }

    @Override
    public Profilo getProfilo(String email) {
        Query q = em.createQuery("SELECT p FROM Profilo p WHERE p.email =:custEmail");
        q.setParameter("custEmail", email);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            Profilo p = em.find(Profilo.class, ((Profilo) l.get(0)).getId());
            return p;
        }

    }

    @Override
    public Profilo getProfilo(Long idProfilo) {
        //Query q = em.createNativeQuery(query);
        Query q = em.createQuery("SELECT p FROM Profilo p WHERE p.id =:custProfilo");
        q.setParameter("custProfilo", idProfilo);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            Profilo p = em.find(Profilo.class, ((Profilo) l.get(0)).getId());
            return p;
        }

    }

    @Override
    public List getProfiloUtente(String nomeDigitato, Long idp, int i) {
        Query q = em.createQuery("SELECT p FROM Profilo p  WHERE ((LOWER(CONCAT(p.cognome,' ',p.nome)) LIKE :searchString) OR"
                + "(LOWER(CONCAT(p.nome,' ',p.cognome)) LIKE :searchString )) "
                + "ORDER BY CASE WHEN (p.id = :custId) THEN 0 "
                + "WHEN (p.id = ANY (SELECT ppp.id FROM Profilo pp LEFT JOIN pp.following ppp WHERE pp.id = :custId) )  THEN 1 "
                + "ELSE 2 END");
        q.setFirstResult(i);
        q.setMaxResults(Constants.LIMIT);
        q.setParameter("searchString", nomeDigitato + "%");
        q.setParameter("custId", idp);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l;
        }
    }

    @Override
    public int checkEmailEsistente(String email) {
        Query q = em.createQuery("SELECT p FROM Profilo p WHERE p.email =:custEmail");
        q.setParameter("custEmail", email);
        List l = q.getResultList();
        if (l.isEmpty()) {
            return 0;
        } else {
            return -1;
        }

    }
    
    @Override
    public List<Profilo> getMatchComune(Long comune, Long idprofilo) {
        Query q = em.createQuery("SELECT p FROM Profilo p WHERE p.comune.id =:custId AND p.id !=:custProf");
        q.setParameter("custId", comune);
        q.setParameter("custProf", idprofilo);

        List l = q.getResultList();
        return l;

    }
    
    @Override
    public List<Profilo> getMatchProvincia(Long provincia, Long idprofilo) {
        Query q = em.createQuery("SELECT p FROM Profilo p WHERE p.comune.provincia.id =:custId AND p.id !=:custProf");
        q.setParameter("custId", provincia);
        q.setParameter("custProf", idprofilo);

        List l = q.getResultList();
        return l;

    }
    
    @Override
    public List<Profilo> getMatchRegione(Long regione, Long idprofilo) {
        Query q = em.createQuery("SELECT p FROM Profilo p WHERE p.comune.provincia.regione.id =:custId AND p.id !=:custProf");
        q.setParameter("custId", regione);
        q.setParameter("custProf", idprofilo);

        List l = q.getResultList();
        return l;

    }
    
    
}
