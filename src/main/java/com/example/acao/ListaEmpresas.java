package com.example.acao;

import com.example.modelo.Banco;
import com.example.modelo.Empresa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListaEmpresas implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession sessao = request.getSession();
//		if(sessao.getAttribute("usuarioLogado") == null) {
//			return "redirect:entrada?acao=LoginForm";
//		}
		
		System.out.println("listando empresas");
		
		Banco banco = new Banco();
		List<Empresa> lista = banco.getEmpresas();
		
		request.setAttribute("empresas", lista);
		
		return "forward:listaEmpresas.jsp";
	}

}
