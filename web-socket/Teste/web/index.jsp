<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<%
    // Recupera os parâmetros enviados pelo cliente
    String usuario = request.getParameter("usuario");
    String senha = request.getParameter("senha");

    // Verifica se os parâmetros estão vazios ou nulos
        if (usuario.equals("admin") && senha.equals("admin")) {
            // Usuário e senha válidos, faça algo (redirecione para página de sucesso, etc.)
            out.write("Sucesso");
        } else {
            // Usuário ou senha inválidos, faça algo (redirecione, exiba uma mensagem de erro, etc.)
            out.write("Senha ou Usuario invalidos!!!!");
        }
%>