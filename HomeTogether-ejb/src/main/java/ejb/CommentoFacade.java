/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
public class CommentoFacade extends AbstractFacade<Commento> implements CommentoFacadeLocal {

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {

        return em;
    }

    public CommentoFacade() {
        super(Commento.class);
    }

    @Override
    public Commento getCommento(Long idCommento) {
        Query q = em.createQuery("SELECT c FROM Commento c WHERE c.id=:custId");
        q.setParameter("custId", idCommento);
        Commento c = (Commento)q.getResultList().get(0);
        return c;
    }
    
}
