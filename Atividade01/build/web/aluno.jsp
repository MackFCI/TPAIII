<%-- 
    Document   : aluno
    Created on : 04/04/2013, 10:37:14
    Author     : 31117317
--%>
<%@page import="java.sql.*"%>
<%@page import="Mackenzie.*"%>
<%
    Usuario usuarioAutenticado = null;
    try{
        usuarioAutenticado = (Usuario) session.getAttribute("ctrl_usuario");
        if(usuarioAutenticado == null){
            throw new Exception("Você deve estar autenticado para acessar esta página.");
        }
//AUTENTICADO
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atividade #01: Revisão de TPA2 | Aluno</title>
    </head>
    <body>
        <h1>Atividade #01: Revisão de TPA2 | Aluno</h1>
        <p>Olá <%=usuarioAutenticado.getNome()%>!</p>
        <%
        try{
            int tia = 0;
            Aluno aluno = null;
            if(request.getParameter("inserir") == null && request.getParameter("tia") != null){
                tia = Integer.valueOf(request.getParameter("tia"));
                //CARREGA OS DADOS DO ALUNO INFORMADO
                aluno = new Aluno(tia);
            }
            
            if(request.getParameter("inserir") != null || (request.getParameter("alterar") != null && aluno != null)){
                String msg = "";
                String botaoSubmit = "";
                
                //INICIALIZANDO AS VARIÁVEIS/CAMPOS
                String[][] campos = new String[][] {
                    //{label, tipo do campo, nome do campo, valor padrão, parâmetros html/input}
                    {"TIA", "number", "tia", "", "min=\"30000000\" max=\"49999999\""},
                    {"Nome", "text", "nome", "", ""},
                    {"Nota 1", "number", "nota1", "", "min=\"0\" max=\"10\""},
                    {"Nota 2", "number", "nota2", "", "min=\"0\" max=\"10\""}
                };
                
                //VERIFICANDO AÇÃO
                if(request.getParameter("inserir") != null){ //INSERIR
                    botaoSubmit = "Inserir";
                }else if(request.getParameter("alterar") != null){ //ALTERAR
                    botaoSubmit = "Alterar";
                }
                
                //VERIFICANDO OS VALORES RECEBIDOS (POST do FORM)
                int qtdParam = 0;
                int qtdParamVazio = 0;
                for(int i=0; i<campos.length; i++){
                    if(request.getParameter(campos[i][2]) != null){
                        campos[i][3] = request.getParameter(campos[i][2]);
                        qtdParam++;
                        if(campos[i][3].equals("")){
                            qtdParamVazio++;
                        }
                    }
                }
                //System.out.print("QTD: "+qtdParam);
                if(qtdParam != campos.length){
                    if(aluno != null){
                        //INICIALIZANDO VALORES PARA O ALTERAR
                        campos[0][3] = String.valueOf(aluno.getTia());
                        campos[0][4] = "readonly=\"readonly\"";
                        campos[1][3] = String.valueOf(aluno.getNome());
                        campos[2][3] = String.valueOf(aluno.getNota1());
                        campos[3][3] = String.valueOf(aluno.getNota2());
                    }
                }else if(qtdParamVazio != 0){
                    msg = "Preencha todos os campos!";
                }else{
                    //RECUPERANDO AS INFORMAÇÕES
                    tia = Integer.valueOf(request.getParameter("tia"));
                    String nome = campos[1][3];
                    double nota1 = Double.valueOf(campos[2][3]);
                    double nota2 = Double.valueOf(campos[3][3]);
                    if((nota1 < 0 || nota1 > 10) || (nota2 < 0 || nota2 > 10)){
                        msg = "Nota inválida! (0-10)";
                    }else{
                        //SALVA INFORMAÇÕES NO BANCO DE DADOS
                        if(request.getParameter("inserir") != null){ //INSERE NOVO
                            Aluno novo = new Aluno(tia, nome, nota1, nota2);
                        }else if(request.getParameter("alterar") != null){ //ATUALIZA AS INFO
                            aluno.setNome(nome);
                            aluno.setNota1(nota1);
                            aluno.setNota2(nota2);
                            aluno.atualizar();
                        }
                        //REDIRECIONA USUÁRIO PARA PÁGINA DO USUÁRIO (DETALHES)
                        response.sendRedirect("aluno.jsp?tia="+tia);
                    }
                }
                %>
                <style>
                    .alinharDireita{
                        text-align: right;
                    }
                </style>
                <form method="post" action="">
                    <table>
                        <% for(int i=0; i<campos.length; i++){ %>
                            <tr>
                                <td class="alinharDireita"><%=campos[i][0]%>:</td>
                                <td><input type="<%=campos[i][1]%>" name="<%=campos[i][2]%>" value="<%=campos[i][3]%>" <%=campos[i][4]%> />
                            </tr>
                        <% } %>
                        <% if(!msg.isEmpty()){ %>
                            <tr>
                                <td colspan="2"><span style="font-weight: bold; color: red;"><%=msg%></span></td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" value="<%=botaoSubmit%>" /></td>
                        </tr>
                    </table>
                </form>
                <%
            }else if(request.getParameter("excluir") != null && aluno != null){//EXCLUIR
                aluno.excluir();
                //REDIRECIONA USUÁRIO PARA PÁGINA DE TODOS OS ALUNOS
                response.sendRedirect("aluno.jsp");
            }else{//NENHUMA AÇÃO
                if(aluno == null){
                    %><p><a href="?inserir">Inserir novo aluno</a><%
                    //MOSTRA TODOS OS ALUNOS
                    ResultSet rs = Aluno.retornaTodos();
                    if(!rs.isBeforeFirst()){
                        out.print("<p>Nenhum aluno foi encontrado!</p>");
                    }else{
                        //MOSTRA OS REGISTROS
                        %><table border="1">
                            <tr>
                                <th>TIA</th>
                                <th>Nome</th>
                                <th>Nota 1</th>
                                <th>Nota 2</th>
                                <th>Média</th>
                                <th>&nbsp;</th>
                            </tr>
                        <%
                        while(rs.next()){
                            %><tr>
                                <td><%=rs.getInt("tia")%></td>
                                <td><%=rs.getString("nome")%></td>
                                <td><%=rs.getDouble("nota1")%></td>
                                <td><%=rs.getDouble("nota2")%></td>
                                <td><%=((rs.getDouble("nota1")+rs.getDouble("nota2"))/2)%></td>
                                <td><a href="?tia=<%=rs.getInt("tia")%>">detalhes</a></td>
                            </tr><%
                        }
                        %></table><%
                    }
                }else{
                    %><p><a href="aluno.jsp">Mostrar todos os alunos cadastrados</a><%
                    //MOSTRA OS DADOS DO ALUNO SELECIONADO
                    %><table border="1">
                        <tr>
                            <th>TIA</th>
                            <td><%=aluno.getTia()%></td>
                        </tr>
                        <tr>
                            <th>Nome</th>
                            <td><%=aluno.getNome()%></td>
                        </tr>
                        <tr>
                            <th>Nota 1</th>
                            <td><%=aluno.getNota1()%></td>
                        </tr>
                        <tr>
                            <th>Nota 2</th>
                            <td><%=aluno.getNota2()%></td>
                        </tr>
                        <tr>
                            <th>Média</th>
                            <td><%=aluno.calculaMedia()%></td>
                        </tr>
                        <tr>
                            <th>Situação</th>
                            <td><%=aluno.retornaSituacao()%></td>
                        </tr>
                        <tr>
                            <th colspan="2"><a href="?alterar&tia=<%=aluno.getTia()%>">editar</a> | <a href="?excluir&tia=<%=aluno.getTia()%>">excluir</a></th>
                        </tr>
                    </table><%
                }
            }
        }catch(Exception e){
            System.out.print(e);
            out.print("<p>"+e.getMessage()+"</p>");
        }
        %>
    </body>
</html>
<%
    }catch(Exception e){
        if(e.getMessage() == "Você deve estar autenticado para acessar esta página."){
            response.sendRedirect("index.jsp");
        }else{
            out.print(e.getMessage());
        }
    }
%>