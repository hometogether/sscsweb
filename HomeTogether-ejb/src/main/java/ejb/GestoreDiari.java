/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class GestoreDiari {

    @EJB
    private DiarioFacadeLocal comuneFacade;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Diario getDiario(Long idDiario) {
        System.out.println("entro in crea listaComuni");
        Diario d = comuneFacade.getDiario(idDiario);
        return d;
    }
       

}
