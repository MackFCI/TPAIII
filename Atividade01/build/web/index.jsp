<%-- 
    Document   : index
    Created on : 16/08/2013, 11:19:12
    Author     : 31117317
--%>

<%
    String nome = "", strNota1 = "", strNota2 = "", mensagem = "";
    try{
        if(request.getParameter("nome") != null){
            nome = request.getParameter("nome").trim();
            strNota1 = request.  getParameter("nota1").trim();
            strNota2 = request.getParameter("nota2").trim();
            if(nome.isEmpty()){
                mensagem = "Preencha o campo nome!";
            }else if(strNota1.isEmpty()){
                mensagem = "Preencha o campo nota 1!";
            }else if(strNota2.isEmpty()){
                mensagem = "Preencha o campo nota 2!";
            }else{
                Double nota1 = Double.valueOf(strNota1);
                Double nota2 = Double.valueOf(strNota2);
                if((nota1 < 0 || nota1 > 10) || (nota2 < 0 || nota2 > 10)){
                    mensagem = "O campo nota está incorreto. Preencha com valores entre 0 e 10.";
                }
            }
        }
    }catch(NumberFormatException e){
        mensagem = "Você inseriu texto em um campo de números!";
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atividade #01: Revisão de TPA2</title>
        <style>
            table th{
                text-align: right;
            }
        </style>
    </head>
    <body>
        <h1>Atividade #01: Revisão de TPA2</h1>
        <form method="post">
            <table>
                <tr>
                    <th>Nome:</th>
                    <td><input type="text" name="nome" value="<%=nome%>" /></td>
                </tr>
                <tr>
                    <th>Nota 1:</th>
                    <td><input type="text" name="nota1" value="<%=strNota1%>" /></td>
                </tr>
                <tr>
                    <th>Nota 2:</th>
                    <td><input type="number" name="nota2" min="0" max="10" value="<%=strNota2%>" /></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" value="Enviar" /></td>
                </tr>
                <%
                    if(!mensagem.isEmpty()){
                        %>
                        <tr>
                            <td colspan="2"><%=mensagem%></td>
                        </tr>
                        <%
                    }
                %>
            </table>
        </form>
    </body>
</html>
