/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mackenzie;

/**
 *
 * @author 31117317
 */
import java.sql.*;

public class Conexao {
    private String servidor = "localhost";
    private String usuario = "root";
    private String senha = "administrador";
    String database = "mackenzie";
    String tabela1 = "usuario";
    String tabela2 = "aluno";
    Connection con;
    Statement s;
    
    public Conexao() throws Exception {
        String retorno = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mySQL://"+servidor+":3306/?useUnicode=true",usuario,senha);
        }catch(ClassNotFoundException e){
            retorno = "Classe MySQL não encontrada!";
        }catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e){
            retorno = "Banco de dados não encontrado!";
        }catch(SQLSyntaxErrorException e){
            switch(e.getErrorCode()){
                case 1049:
                    retorno = "Base de dados não encontrada!";
                    break;
                case 1146:
                    retorno = "Tabela não encontrada!";
                    break;
                default:
                    retorno = e.getErrorCode() + " - " + e.toString();
            }
        }catch(SQLException e){
            switch(e.getErrorCode()){
                case 1045:
                    retorno = "Usuário e/ou senha incorretos!";
                    break;
                default:
                    retorno = e.getErrorCode() + " - " + e.toString();
            }
        }
        
        if(retorno != null){
            throw new Exception(retorno);
        }else{
            this.preparaBanco();
        }
    }
    
    Connection getCon(){
        return con;
    }
    
    void preparaBanco() throws Exception{
        PreparedStatement s;
        
        //CRIANDO O DATABASE
        try{
            s = con.prepareStatement("USE "+database);
            s.executeUpdate();
        }catch(SQLSyntaxErrorException e){
            if(e.getMessage().equals("Unknown database '"+database+"'")){
                s = con.prepareStatement("CREATE DATABASE "+database);
                s.executeUpdate();
                s = con.prepareStatement("USE "+database);
                s.executeUpdate();
            }
        }
        
        //CRIANDO A TABELA usuario
        try{
            s = con.prepareStatement("SELECT drt, nome FROM "+tabela1);
            ResultSet rs = s.executeQuery();
        }catch(SQLSyntaxErrorException e){
            if(e.getMessage().equals("Table '"+database+"."+tabela1+"' doesn't exist")){
                s = con.prepareStatement("CREATE TABLE "+tabela1+" (drt int(11) NOT NULL, nome varchar(45) NOT NULL, usuario varchar(45), senha varchar(45), PRIMARY KEY (drt))");
                s.executeUpdate();
            }
        }
        
        //CRIANDO A TABELA aluno
        try{
            s = con.prepareStatement("SELECT tia, nome FROM "+tabela2);
            ResultSet rs = s.executeQuery();
        }catch(SQLSyntaxErrorException e){
            if(e.getMessage().equals("Table '"+database+"."+tabela2+"' doesn't exist")){
                s = con.prepareStatement("CREATE TABLE "+tabela2+" (tia int(11) NOT NULL, nome varchar(45) NOT NULL, nota1 double, nota2 double, PRIMARY KEY (tia))");
                s.executeUpdate();
            }
        }
        
        //INSERINDO OS REGISTROS
        String[][] registros1 = new String[][] {
            {"31158745", "Aluno 01"},
            {"31198523", "Aluno 02"},
            {"31174513", "Aluno 03"},
            {"31164124", "Aluno 04"}
        };
        for(int i=0; i < registros1.length; i++){
            try{
                s = con.prepareStatement("INSERT INTO "+tabela2+" (tia, nome) VALUES ("+registros1[i][0]+", '"+registros1[i][1]+"')");
                s.executeUpdate();
            }catch(SQLSyntaxErrorException e){
                throw new Exception(e.getErrorCode() + " - " + e.toString());
            }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
                throw new Exception("<p>Registro ["+registros1[i][0]+"] já existe!</p>");
            }
        }
        String[][] registros2 = new String[][] {
            //{DRT, Nome, Usuário, Senha}
            {"1124477", "DANIEL ARNDT ALVES", "1124477", "senha"},
            {"1123636", "VINICIUS MIANA BEZERRA", "1123636", "senha"},
            {"1142412", "LAERCIO CRUVINEL JUNIOR", "1142412", "senha"},
            {"1130664", "CALEBE DE PAULA BIANCHINI", "1130664", "senha"}
        };
        for(int i=0; i < registros2.length; i++){
            try{
                s = con.prepareStatement("INSERT INTO "+tabela2+" (drt, nome, usuario, senha) VALUES ("+registros2[i][0]+", '"+registros2[i][1]+"', '"+registros2[i][2]+"', '"+registros2[i][3]+"')");
                s.executeUpdate();
            }catch(SQLSyntaxErrorException e){
                throw new Exception(e.getErrorCode() + " - " + e.toString());
            }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
                throw new Exception("<p>Registro ["+registros2[i][0]+"] já existe!</p>");
            }
        }
    }
}
