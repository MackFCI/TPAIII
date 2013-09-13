/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade02;

/**
 *
 * @author lucasesaito
 */
public class Complexo {
    private double real, imaginario;
    
    public Complexo(Double real, Double imaginario){
        this.real = real;
        this.imaginario = imaginario;
    }
    public double getReal(){
        return real;
    }
    public double getImaginario(){
        return imaginario;
    }
    public String getComplexo(){
        return ("(" + real + " + " + imaginario + "i)");
    }
}