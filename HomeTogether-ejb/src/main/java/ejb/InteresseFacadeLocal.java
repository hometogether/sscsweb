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
public interface InteresseFacadeLocal {

    void create(Interesse interesse);

    void edit(Interesse interesse);

    void remove(Interesse interesse);

    Interesse find(Object id);

    List<Interesse> findAll();

    List<Interesse> findRange(int[] range);

   /* Profilo getInteresse(String email);

    int checkEmailEsistente(String email);*/
     
    int count();

    public Interesse getInteresse(String nomeinteresse);

    public Interesse getInteresse(Long idinteresse);

    
}
