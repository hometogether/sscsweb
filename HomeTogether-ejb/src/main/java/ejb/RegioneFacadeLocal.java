/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Andrea22
 */
import java.util.List;
import javax.ejb.Local;

@Local
public interface RegioneFacadeLocal {

    void create(Regione regione);

    void edit(Regione regione);

    void remove(Regione regione);

    Regione find(Object id);

    List<Regione> findAll();

    List<Regione> findRange(int[] range);

    int count();

    public List<Regione> getRegioni();


}
