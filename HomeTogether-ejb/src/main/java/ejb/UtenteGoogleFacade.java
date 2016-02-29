/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giovanna
 */
@Stateless
public class UtenteGoogleFacade extends AbstractFacade<UtenteGoogle> implements UtenteGoogleFacadeLocal {
    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public UtenteGoogleFacade() {
        super(UtenteGoogle.class);
    }
    
    @Override
    public UtenteGoogle getUtente(String email, String idGoogle){
        //Query q = em.createNativeQuery(query);
        System.out.println("entro in getutente Google");
        System.out.println("email"+email);
        System.out.println("idGoogle"+idGoogle);
        Query q = em.createQuery("SELECT u FROM UtenteGoogle u WHERE u.email =:custEmail AND u.idGoogle =:custIdGoogle");
        q.setParameter("custEmail", email);
        q.setParameter("custIdGoogle", idGoogle);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            UtenteGoogle u = em.find(UtenteGoogle.class, ((UtenteGoogle)l.get(0)).getId());
            return u;
        }
        
    }
    
    
    
}
