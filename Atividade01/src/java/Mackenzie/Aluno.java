package Mackenzie;

import java.sql.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31117317
 */
public class Aluno {
    int tia;
    String nome;
    double nota1;
    double nota2;

    //CONSTRUTORES
    public Aluno(int tia) throws Exception {
        this.tia = tia;
        
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM aluno WHERE tia = ?");
        ps.setInt(1, tia);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            throw new Exception("Aluno não encontrado!");
        }else{
            this.tia = rs.getInt("tia");
            this.nome = rs.getString("nome");
            this.nota1 = rs.getDouble("nota1");
            this.nota2 = rs.getDouble("nota2");
        }
    }
    public Aluno(int tia, String nome, double nota1, double nota2) throws Exception {
        this.tia = tia;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        PreparedStatement ps;
        ps = con.prepareStatement("SELECT * FROM aluno WHERE tia = ?");
        ps.setInt(1, tia);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            //INSERIR
            ps = con.prepareStatement("INSERT INTO aluno (tia, nome, nota1, nota2) VALUES (?, ?, ?, ?)");
            ps.setInt(1, tia);
            ps.setString(2, nome);
            ps.setDouble(3, nota1);
            ps.setDouble(4, nota2);
            ps.executeUpdate();
        }else{
            //ALUNO JÁ EXISTE
            throw new Exception("O aluno informado (TIA: "+tia+" | Nome: "+rs.getString("nome")+") já existe!");
        }
    }
    
    //GETTERS
    public int getTia(){
        return tia;
    }
    public String getNome(){
        return nome;
    }
    public double getNota1(){
        return nota1;
    }
    public double getNota2(){
        return nota2;
    }
    //SETTERS
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNota1(double nota1){
        this.nota1 = nota1;
    }
    public void setNota2(double nota2){
        this.nota2 = nota2;
    }
    
    public double calculaMedia(){
        return (nota1+nota2)/2;
    }
    public String retornaSituacao(){
        if(calculaMedia() >= 7){
            return "Aprovado";
        }else{
            return "Reprovado";
        }
    }
    
    public void atualizar() throws Exception{
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        //INSERIR
        PreparedStatement ps = con.prepareStatement("UPDATE aluno SET nome = ?, nota1 = ?, nota2 = ? WHERE tia = ?");
        ps.setString(1, nome);
        ps.setDouble(2, nota1);
        ps.setDouble(3, nota2);
        ps.setInt(4, tia);
        ps.executeUpdate();
    }
    public void excluir() throws Exception{
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        //EXCLUIR
        PreparedStatement ps = con.prepareStatement("DELETE FROM aluno WHERE tia = ?");
        ps.setInt(1, tia);
        ps.executeUpdate();
    }
    
    static public ResultSet retornaTodos() throws Exception{
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM aluno");
        
        return rs;
    }
}
