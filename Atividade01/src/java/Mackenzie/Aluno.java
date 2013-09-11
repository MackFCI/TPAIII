package Mackenzie;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31117317
 */
public class Aluno {
    private String nome;
    private Double nota1;
    private Double nota2;
    
    public String getNome(){
        return this.nome;
    }
    public Double getNota1(){
        return this.nota1;
    }
    public Double getNota2(){
        return this.nota2;
    }
    
    public Aluno(String nome, Double nota1, Double nota2) throws Exception{
         this.nome = nome;
         this.nota1 = nota1;
         this.nota2 = nota2;
    }
    
    public Double retornaMedia(){
        return ((this.nota1+this.nota2)/2);
    }
    public String retornaAprovacao(){
        if(this.retornaMedia() >= 6){
            return "Aprovado";
        }else{
            return "Reprovado";
        }
    }
}
