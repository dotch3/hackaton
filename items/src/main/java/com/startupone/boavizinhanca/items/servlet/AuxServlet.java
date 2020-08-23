package com.startupone.boavizinhanca.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testPublish")
public class AuxServlet extends HttpServlet {

	protected void addItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUserProprietario = request.getParameter("username");
		String nome = request.getParameter("password");
		String quantidadeDisponivel = request.getParameter("quantidadeDisponivel");
		String valor = request.getParameter("valor");
		String tipoValor = request.getParameter("tipoValor");

		String descricao = request.getParameter("descricao");
		String foto = request.getParameter("foto");
		// Optional
		String dataSugestaoEmprestimo = request.getParameter("dataSugestaoEmprestimo");
		String dataSugestaoDevolucao = request.getParameter("dataSugestaoDevolucao");

		// Tags

		String tags[] = request.getParameterValues("tags");
		String tagsHtml = "";

		if (tags != null) {
			System.out.println("Tags are: ");
			for (String tag : tags) {
				tagsHtml += tag + ",";
				System.out.println("\t" + tag);
			}
		}

		System.out.println("Id User is: " + idUserProprietario);
		System.out.println("nome is: " + nome);
		System.out.println("quantidadeDisponivel is: " + quantidadeDisponivel);
		System.out.println("valor  is: " + valor);
		System.out.println("tipoValor  is: " + tipoValor);
		System.out.println("tags  is: " + tagsHtml);
		System.out.println("descricao  is: " + descricao);
		System.out.println("foto  is: " + foto);
		System.out.println("dataSugestaoEmprestimo  is: " + dataSugestaoEmprestimo);
		System.out.println("dataSugestaoDevolucao  is: " + dataSugestaoDevolucao);

		PrintWriter writer = response.getWriter();

		String htmlRespone = "<html><h3>";
		htmlRespone += "user ID is: " + idUserProprietario + "<br/>";
		htmlRespone += "nome is: " + nome + "<br/>";
		htmlRespone += "quantidadeDisponivel is: " + quantidadeDisponivel + "<br/>";
		htmlRespone += "valor is: " + valor + "<br/>";
		htmlRespone += "tipoValor is: " + tipoValor + "<br/>";
		htmlRespone += "descricao is: " + descricao + "<br/>";

		htmlRespone += "foto base64 is: " + foto + "<br/>";
		htmlRespone += "<p 'id=b64'></p>";
		htmlRespone += "<img id='img' height='150'>";
		htmlRespone += "Sugestao Inicio Emprestimo is: " + dataSugestaoEmprestimo + "<br/>";
		htmlRespone += "Sugestao Fim Emprestimo is: " + dataSugestaoDevolucao + "<br/>";
		htmlRespone += "</h3></html>";

		// return response
		writer.println(htmlRespone);
	}

}