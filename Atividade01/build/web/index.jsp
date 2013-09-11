<%-- 
    Document   : index
    Created on : 04/04/2013, 09:51:36
    Author     : 31117317
--%>
<%@page import="Mackenzie.*"%>
<%
    if(request.getParameter("sair") != null){
        //session.removeAttribute("ctrl_usuario");
        session.invalidate(); //http://stackoverflow.com/questions/13963720/how-to-effectively-destroy-session-in-java-servlet
        response.sendRedirect("index.jsp");
        return;
    }
    String txtUsuario = request.getParameter("txtUsuario");
    String txtSenha = request.getParameter("txtSenha");
    String msg = "";
    Usuario usuarioAutenticado = null;
    try{
        if(txtUsuario != null && txtSenha != null){
            usuarioAutenticado = new Usuario(txtUsuario, txtSenha);
            session.setAttribute("ctrl_usuario", usuarioAutenticado);
        }else{
            txtUsuario = "";
            txtSenha = "";
        }
    }catch(Exception e){
        //msg = e.toString();
        msg = e.getMessage();
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atividade #01: Revisão de TPA2</title>
    </head>
    <body>
        <h1>Atividade #01: Revisão de TPA2 | Index</h1>
        <%
        usuarioAutenticado = (Usuario) session.getAttribute("ctrl_usuario");
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
            <p>Olá <%=usuarioAutenticado.getNome()%>!</p>
            <p><a href="aluno.jsp">Listar alunos</a> | <a href="?sair">Sair</a></p>
            <%
        }
        %>
    </body>
</html>
