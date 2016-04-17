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
public class ProvinciaFacade extends AbstractFacade<Provincia> implements ProvinciaFacadeLocal{

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public ProvinciaFacade() {
        super(Provincia.class);
    }
    
    public List<Provincia> getProvincie() {
        
        Query q = em.createQuery("SELECT p FROM Provincia p");
        List l = q.getResultList();
        if (l.isEmpty()){
            return null;
        } else {
            return l;
        }
    }
}
