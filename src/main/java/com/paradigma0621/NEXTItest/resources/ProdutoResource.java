package com.paradigma0621.NEXTItest.resources; //Pacote de controladores REST

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired // Para instanciar automaticamente o objeto
	private ProdutoService service; // O controlador REST vai acessar o serviço

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Para dizer que o endpoint agora é: /produtos/{id}
	public ResponseEntity<?> find(@PathVariable Integer id) { // Para o Spring saber que esse id informado nessa linha é
																// o "id" da linha de cima colocamos o @PathVariable
		// Esse 'ReponseEntity' é um tipo especial do Spring que encapsula e armazena
		// várias informações de uma resposta http para um serviço REST. Foi colocado
		// esse "?" porque poderá ser de qualquer tipo, pois pode tanto encontrar como
		// não encontrar um 'Produto' com o 'id' especificado

		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj); // ok() para dizer que a operação ocorreu com sucesso,
		// e a resposta terá como corpo o objeto 'obj'
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Produto obj) { //para que o objeto Produto seja construido a partir dos dados json que enviar, temos que inserir a notação '@RequestBody' - faz o json ser convertido para o objeto Java automaticamente
		obj = service.insert(obj);
		
		// Quando inserir com sucesso: é retornado o código HTTP 201, juntamente com a URI do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() //Pega URI do novo recurso inserido = http://localhost:8080/produtos/
			.path("/{id}") //mais o 'id' do objeto que nós criamos
			.buildAndExpand(obj.getId()) //para atribuir o valor do Id na linha de cima (pega o Id do objeto que acabou de inserir).
			.toUri(); // Por fim converte para URI
		return ResponseEntity.created(uri).build(); //método 'created' gera código de HTTP 201
													//o 'build' gera a resposta
	}
	
}
