package br.com.eliel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliel.model.Book;
import br.com.eliel.proxy.CambioProxy;
import br.com.eliel.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired 
	Environment environment;
	
	@Autowired 
	BookRepository repository;
	
	@Autowired 
	CambioProxy proxy;
	
	@GetMapping(value = "{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		var book = repository.getById(id);
		if (book == null) throw new RuntimeException("Book not found");
				
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment(port + " Feign");
		book.setPrice(cambio.getConvertedValue());
		
		return book;
	}
	
	/* versao antiga
	 * @GetMapping(value = "{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		var book = repository.getById(id);
		if (book == null) throw new RuntimeException("Book not found");
		
		HashMap<String, String> params = new HashMap();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var response = new RestTemplate()
			.getForEntity(
				"http://localhost:8000/cambio-service/{amount}/{from}/{to}", 
				Cambio.class, 
				params
			);
		
		var cambio = response.getBody();
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment(port);
		book.setPrice(cambio.getConvertedValue());
		
		return book;
	}*/
}
