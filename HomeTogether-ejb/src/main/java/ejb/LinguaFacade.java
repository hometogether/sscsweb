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
public class LinguaFacade extends AbstractFacade<Lingua> implements LinguaFacadeLocal{

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public LinguaFacade() {
        super(Lingua.class);
    }
    
    @Override
    public Lingua getLingua(String nomelingua) {
        Query q = em.createQuery("SELECT l FROM Lingua l WHERE l.nome =:custNome");
        q.setParameter("custNome", nomelingua);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            Lingua lingua = em.find(Lingua.class, ((Lingua)l.get(0)).getId());
            return lingua;
        }
    }
    
    @Override
    public Lingua getLingua(Long idlingua) {
         Query q = em.createQuery("SELECT l FROM Lingua l WHERE l.id =:custId");
        q.setParameter("custId", idlingua);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            Lingua lingua = em.find(Lingua.class, ((Lingua)l.get(0)).getId());
            return lingua;
        }
    }
    
    @Override
    public List<Lingua> getLingue() {
        //Query q = em.createNativeQuery(query);
        Query q = em.createQuery("SELECT l FROM Lingua l");
        List l = q.getResultList();
        if (l.isEmpty()){
            return null;
        } else {
            return l;
        }
    }
}
