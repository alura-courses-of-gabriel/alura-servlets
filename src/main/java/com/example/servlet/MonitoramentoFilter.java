package com.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/entrada")
public class MonitoramentoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Long antes = System.currentTimeMillis();

        String acao = request.getParameter("acao");

        filterChain.doFilter(request, response);

        Long depois = System.currentTimeMillis();

        System.out.println("Ação: " + acao + " gastou: " + (depois - antes) + "ms para ser executada");
    }
}
