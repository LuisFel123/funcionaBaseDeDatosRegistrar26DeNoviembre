/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
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
    private List<Persona> personas;

    public List<Persona> getPersonas() {
        return mDPersona.personas();
    }

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

    public void registro() {
        System.out.println(registrado());
        if (registrado() == false) {
            mDPersona.registrarPersona(perosna);
            perosna = new Persona();
            personas = getPersonas();

        }

    }

    public void eliminarPersona(Persona p) {
        mDPersona.eliminarP(p);
    }

    public boolean registrado() {
        personas = getPersonas();
        boolean esta = false;
        for (Persona p : personas) {
            esta = perosna.getUsuario().equals(p.getUsuario());
           // esta = perosna.getUsuario().equals(p.getUsuario()) && perosna.getPassword().equals(p.getPassword());

            if (esta) {
                return esta;
            }
        }
        return esta;
    }

    public void registroA() {
        if (!registrado()) {
            mDPersona.registrarPersona(perosna);
        }
    }
}
