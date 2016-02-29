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
public class UtenteFacebookFacade extends AbstractFacade<UtenteFacebook> implements UtenteFacebookFacadeLocal {
    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public UtenteFacebookFacade() {
        super(UtenteFacebook.class);
    }
    
    @Override
    public UtenteFacebook getUtente(String email, String idFacebook){
        //Query q = em.createNativeQuery(query);
        System.out.println("entro in getutente Facebook");
        Query q = em.createQuery("SELECT u FROM UtenteFacebook u WHERE u.email =:custEmail AND u.idFacebook =:custIdFacebook");
        q.setParameter("custEmail", email);
        q.setParameter("custIdFacebook", idFacebook);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            UtenteFacebook u = em.find(UtenteFacebook.class, ((UtenteFacebook)l.get(0)).getId());
            return u;
        }
        
    }
    
}
