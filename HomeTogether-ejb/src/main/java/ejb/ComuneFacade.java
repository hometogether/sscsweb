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

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class ComuneFacade extends AbstractFacade<Comune> implements ComuneFacadeLocal{

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public ComuneFacade() {
        super(Comune.class);
    }

    @Override
    public List<Comune> getComuni() {
        //Query q = em.createNativeQuery(query);
        System.out.println("entro in getutente Google");
        Query q = em.createQuery("SELECT c FROM Comune c");
        List l = q.getResultList();
        //System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            return l;
        }
    }
    
}
