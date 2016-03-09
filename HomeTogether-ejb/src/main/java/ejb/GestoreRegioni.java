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
public class GestoreRegioni {

    @EJB
    private RegioneFacadeLocal regioneFacade;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Regione> creaListaRegioni() {
        
        List<Regione> list = regioneFacade.getRegioni();
        return list;
    }
       

}
