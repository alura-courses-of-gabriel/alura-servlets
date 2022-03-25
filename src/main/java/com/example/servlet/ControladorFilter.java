package com.example.servlet;

import com.example.acao.Acao;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ControladorFilter")
public class ControladorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String paramAcao = request.getParameter("acao");
        String nomeDaClasse = "com.example.acao." + paramAcao;

        String nome;
        try {
            Class classe = Class.forName(nomeDaClasse);//carrega a classe com o nome
            Acao acao = (Acao) classe.newInstance();
            nome = acao.executa(httpServletRequest,httpServletResponse);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }

        String[] tipoEEndereco = nome.split(":");
        if(tipoEEndereco[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
            rd.forward(request, response);
        } else {
            httpServletResponse.sendRedirect(tipoEEndereco[1]);
        }
    }
}
