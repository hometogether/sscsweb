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
public class CommentoFacade extends AbstractFacade<Commento> implements CommentoFacadeLocal {

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    public CommentoFacade() {
        super(Commento.class);
    }

    
}
