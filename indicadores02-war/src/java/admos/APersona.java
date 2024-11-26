/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import manipuladatos.MDPersona;
import modelo.Persona;

/**
 *
 * @author felip
 */
@Named(value = "aPersona")
@SessionScoped
public class APersona implements Serializable {

    @EJB
    private MDPersona mDPersona;
    private Persona perosna;
  

    /**
     * Creates a new instance of APersona
     */
    public APersona() {
        perosna = new Persona();
        
    }

    public MDPersona getmDPersona() {
        return mDPersona;
    }

    public void setmDPersona(MDPersona mDPersona) {
        this.mDPersona = mDPersona;
    }

    public Persona getPerosna() {
        return perosna;
    }

    public void setPerosna(Persona perosna) {
        this.perosna = perosna;
    }
    
    public void registro(){
        mDPersona.registrarPersona(perosna);
    }

}
