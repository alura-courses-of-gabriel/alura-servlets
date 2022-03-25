package com.example.servlet;

import com.example.modelo.Banco;
import com.example.modelo.Empresa;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/empresas")
public class ListaEmpresasService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empresa> empresas = new Banco().getEmpresas();

        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().print(gson.toJson(empresas));


        XStream xStream = new XStream();
        xStream.alias("empresa", Empresa.class);
        String xml = xStream.toXML(empresas);
        response.setContentType("application/json");
        response.getWriter().print(xml);

    }
}
