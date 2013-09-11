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
public class Usuario {
    int drt;
    String nome;
    String usuario;
    String senha;

    //CONSTRUTORES
    public Usuario(int drt) throws Exception {
        this.drt = drt;
        
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE drt = ?");
        ps.setInt(1, drt);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            throw new Exception("Usuário não encontrado!");
        }else{
            this.drt = rs.getInt("drt");
            this.nome = rs.getString("nome");
            this.usuario = rs.getString("usuario");
            this.senha = rs.getString("senha");
        }
    }
    public Usuario(int drt, String nome, String usuario, String senha) throws Exception {
        this.drt = drt;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        PreparedStatement ps;
        ps = con.prepareStatement("SELECT * FROM usuario WHERE drt = ?");
        ps.setInt(1, drt);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            //INSERIR
            ps = con.prepareStatement("INSERT INTO usuario (drt, nome, usuario, senha) VALUES (?, ?, ?, ?)");
            ps.setInt(1, drt);
            ps.setString(2, nome);
            ps.setString(3, usuario);
            ps.setString(4, senha);
            ps.executeUpdate();
        }else{
            //ALUNO JÁ EXISTE
            throw new Exception("O usuário informado (DRT: "+drt+" | Nome: "+rs.getString("nome")+") já existe!");
        }
    }
    public Usuario(String usuario, String senha) throws Exception {
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND senha = ?");
        ps.setString(1, usuario);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        if(!rs.next()){
            throw new Exception("Usuário e/ou Senha inválidos!");
        }else{
            this.drt = rs.getInt("drt");
            this.nome = rs.getString("nome");
            this.usuario = rs.getString("usuario");
            this.senha = rs.getString("senha");
        }
    }
    
    //GETTERS
    public int getDrt(){
        return drt;
    }
    public String getNome(){
        return nome;
    }
    public String getUsuario(){
        return usuario;
    }
    public String getSenha(){
        return senha;
    }
    //SETTERS
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public void setNota2(String senha){
        this.senha = senha;
    }
    
    public void atualizar() throws Exception{
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        //INSERIR
        PreparedStatement ps = con.prepareStatement("UPDATE usuario SET nome = ?, usuario = ?, senha = ? WHERE drt = ?");
        ps.setString(1, nome);
        ps.setString(2, usuario);
        ps.setString(3, senha);
        ps.setInt(4, drt);
        ps.executeUpdate();
    }
    public void excluir() throws Exception{
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        //EXCLUIR
        PreparedStatement ps = con.prepareStatement("DELETE FROM usuario WHERE drt = ?");
        ps.setInt(1, drt);
        ps.executeUpdate();
    }
    
    static public ResultSet retornaTodos() throws Exception{
        Conexao banco = new Conexao();
        Connection con = banco.getCon();
        
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM usuario");
        
        return rs;
    }
}
