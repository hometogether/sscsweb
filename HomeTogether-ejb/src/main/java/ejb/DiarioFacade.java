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
public class DiarioFacade extends AbstractFacade<Diario> implements DiarioFacadeLocal {

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
     public EntityManager getEntityManager() {

        return em;
    }

    public DiarioFacade() {
        super(Diario.class);
    }

    @Override
    public Diario getDiario(Long idDiario) {
        Query q = em.createQuery("SELECT d FROM Diario d LEFT JOIN d.post p WHERE d.id=:custId");
        q.setParameter("custId", idDiario);
        
        if (q.getResultList().isEmpty()){
            return null;
        } else {
            Diario d = (Diario)q.getResultList().get(0);
            return d;
        }
    }

    
}
