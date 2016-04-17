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
public class InteresseFacade extends AbstractFacade<Interesse> implements InteresseFacadeLocal{

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public InteresseFacade() {
        super(Interesse.class);
    }
    
    @Override
    public Interesse getInteresse(String nomeinteresse) {
        Query q = em.createQuery("SELECT i FROM Interesse i WHERE i.nome =:custNome");
        q.setParameter("custNome", nomeinteresse);
        List l = q.getResultList();
        if (l.isEmpty()){
            return null;
        } else {
            Interesse i = em.find(Interesse.class, ((Interesse)l.get(0)).getId());
            return i;
        }
    }
    
    @Override
    public Interesse getInteresse(Long idinteresse) {
        Query q = em.createQuery("SELECT i FROM Interesse i WHERE i.id =:custId");
        q.setParameter("custId", idinteresse);
        List l = q.getResultList();
        if (l.isEmpty()){
            return null;
        } else {
            Interesse i = em.find(Interesse.class, ((Interesse)l.get(0)).getId());
            return i;
        }
    }
    
}
