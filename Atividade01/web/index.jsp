<%-- 
    Document   : index
    Created on : 04/04/2013, 09:51:36
    Author     : 31117317
--%>
<%
    String txtUsuario = request.getParameter("txtUsuario");
    String txtSenha = request.getParameter("txtSenha");
    String msg = "";
    
    if(txtUsuario != null && txtSenha != null){
        if(txtUsuario.equals("teste") && txtSenha.equals("teste")){
            session.setAttribute("ctrl_usuario", txtUsuario);
        }else{
            msg = "Usuário e/ou senha incorretos!";
        }
    }else{
        txtUsuario = "";
        txtSenha = "";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab 7 - Micro TIA</title>
    </head>
    <body>
        <h1>Lab 7 - Micro TIA</h1>
        <%
        String usuarioAutenticado = (String) session.getAttribute("ctrl_usuario");
        if(usuarioAutenticado == null){
            %>
            <style>
                .alinharDireita{
                    text-align: right;
                }
            </style>
            <form action="" method="post">
                <table>
                    <tr>
                        <td class="alinharDireita">Usuário:</td>
                        <td><input type="text" name="txtUsuario" id="txtUsuario" value="<%=txtUsuario%>" /></td>
                    </tr>
                    <tr>
                        <td class="alinharDireita">Senha:</td>
                        <td><input type="password" name="txtSenha" /></td>
                    </tr>
                    <% if(!msg.isEmpty()){ %>
                        <tr>
                            <td colspan="2"><span style="font-weight: bold; color: red;"><%=msg%></span></td>
                        </tr>
                    <% } %>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="Login" /></td>
                    </tr>
                </table>
            </form>
            <script>document.getElementById('txtUsuario').focus();</script>
            <%
        }else{ //USUÁRIO AUTENTICADO
            %>
            <p><a href="aluno.jsp">Listar alunos</a></p>
        <%
        }
        %>
    </body>
</html>
