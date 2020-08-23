package com.startupone.boavizinhanca.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startupone.boavizinhanca.items.entity.Item;
import com.startupone.boavizinhanca.items.service.ItemService;

//test
import java.io.PrintWriter;

@RestController
@RequestMapping("/items") 
public class ItemController {

	@Autowired
	private ItemService service;
	
	@PostMapping("/publish")
	public Item addItem(@RequestBody Item item) {
		return service.saveItem(item);
	}
	
	@GetMapping("/findAll")
	public List<Item> findAllItems(){
		return service.getItems();
	}
	
	@GetMapping("/findItem/{id}")
	public Item findById(@PathVariable int id){
		return service.getItemById(id);
	}
	
	@GetMapping("/findItemProprietario/{id}")
	public List<Item> findByIdProprietario(@PathVariable int id){
		return service.getItemsByIdProprietario(id);
	}
	
	@PutMapping("/update")
	public Item updateItem(@RequestBody Item item) {
		return service.updateItem(item);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable int id) {
		return service.deleteItem(id);
	}
	
	//Testing for addItem
	@PostMapping("/testPublish")
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
	
	@GetMapping("/error")
	public void erroring (){
		htmlRespone += "<html><h3>Error!!</h3></html>";

		// return response
		writer.println(htmlRespone);
		return service.getItems();
	}

}
