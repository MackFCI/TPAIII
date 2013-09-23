/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package provaParcial;

import java.io.Serializable;

/**
 *
 * @author lucasesaito
 */
public class Peca implements Serializable{
    private int posicaoX, posicaoY;
    
    public Peca(int posicaoX, int posicaoY){
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }
    public int getPosicaoX(){
        return posicaoX;
    }
    public int getPosicaoY(){
        return posicaoY;
    }
    public String getPosicao(){
        return ("Linha: " + posicaoX + "\nColuna: " + posicaoY);
    }
}