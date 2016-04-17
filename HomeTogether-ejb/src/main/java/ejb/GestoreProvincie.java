/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Antonio
 */

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class GestoreProvincie {

    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Provincia> creaListaProvincie() {
        List<Provincia> list = provinciaFacade.getProvincie();
        return list;
    }
       

}
