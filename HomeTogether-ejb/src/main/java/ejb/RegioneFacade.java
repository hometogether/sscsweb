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

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class RegioneFacade extends AbstractFacade<Regione> implements RegioneFacadeLocal{

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public RegioneFacade() {
        super(Regione.class);
    }
    
}
