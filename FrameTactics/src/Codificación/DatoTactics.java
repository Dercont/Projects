/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codificación;

/**
 *
 * @author Armando
 */
public class DatoTactics {
    private int variable;
    private DatoTactics izq,der;
    public DatoTactics(int dato){
        this.variable = dato;
        izq = null;
        der = null;
    }

    public int getDato() {
        return variable;
    }

    public DatoTactics getIzq() {
        return izq;
    }

    public DatoTactics getDer() {
        return der;
    }

    public void setDato(int dato) {
        this.variable = dato;
    }

    public void setIzq(DatoTactics izq) {
        this.izq = izq;
    }

    public void setDer(DatoTactics der) {
        this.der = der;
    }
}
