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
public class RichiestaFacade extends AbstractFacade<Richiesta> implements RichiestaFacadeLocal {

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {

        return em;
    }

    public RichiestaFacade() {
        super(Richiesta.class);
    }
    
    @Override
    public Richiesta getRichiesta(Long idRichiesta) {
        //Query q = em.createNativeQuery(query);
        Query q = em.createQuery("SELECT r FROM Richiesta r WHERE r.id =:custRichiesta");
        
        q.setParameter("custRichiesta", idRichiesta);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()) {
            return null;
        } else {
           Richiesta r = em.find(Richiesta.class, ((Richiesta) l.get(0)).getId());
            return r;
        }

    }
    
}
