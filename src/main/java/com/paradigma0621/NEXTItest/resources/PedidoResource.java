package com.paradigma0621.NEXTItest.resources; //Pacote de controladores REST

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paradigma0621.NEXTItest.domain.Pedido;
import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.services.PedidoService;
import com.paradigma0621.NEXTItest.services.ProdutoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired // Para instanciar automaticamente o objeto
	private PedidoService service; // O controlador REST vai acessar o serviço

	@Autowired // Para instanciar automaticamente o objeto
	private ProdutoService prodService; // O controlador REST vai acessar o serviço

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Para dizer que o endpoint
																	// agora é: /Pedidos/{id}
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		// Para o Spring saber que esse id informado nessa linha é o "id" da linha de
		// cima colocamos o @PathVariable. Esse 'ReponseEntity' é um tipo especial do Spring 
		// que encapsula e armazena várias informações de uma resposta http para um serviço REST.

		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj); // ok() para dizer que a operação ocorreu com sucesso,
		// e a resposta terá como corpo o objeto 'obj'
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Pedido obj) {
		// para que o objeto Pedido seja construido a partir dos dados json que enviar,
		// temos que
		// inserir a notação '@RequestBody' - faz o json ser convertido para o objeto
		// Java
		// automaticamente.
		obj = service.insert(obj);

		// Quando inserir com sucesso: é retornado o código HTTP 201, juntamente com a
		// URI do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			// Pega URI do novo recurso inserido = http://localhost:8080/Pedidos/
				.path("/{id}") // mais o 'id' do objeto que nós criamos
				.buildAndExpand(obj.getId()) // para atribuir o valor do Id na linha de cima 
											//(pega o Id do objeto que acabou de inserir).
				.toUri(); // Por fim converte para URI
		return ResponseEntity.created(uri).build(); // método 'created' gera código de HTTP 201
													// o 'build' gera a resposta
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Pedido obj, @PathVariable Integer id) { 
		// Para que o objeto Pedido seja construido a partir dos dados json que enviar, temos que
		// inserir a notação '@RequestBody' - faz o json ser convertido para o objeto Java
		// automaticamente.
		// O @PathVariable é para o Spring saber que esse id informado nessa linha é o
		// "id" da linha de cima.

		obj.setId(id); // Por precaução de que o update vai acontecer o objeto com esse 'id' que
					   //foi passado como parâmetro do método
		obj = service.update(obj);
		return ResponseEntity.noContent().build(); // Retorno de resposta HTTP ok
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) { // 'Void': pois quando apagar
			//vai retornar uma resposta com corpo vazio. O @PathVariable é para
			// o Spring saber que esse id informado nessa linha é o "id" da linha de cima
		service.delete(id);
		return ResponseEntity.noContent().build(); // Retorno de resposta HTTP ok
	}

	@RequestMapping(method = RequestMethod.GET) // endpoint = "http://localhost:8080/pedidos") 
												//(Busque todos Pedidos)
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}/adicionaProduto/{pid}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable("id") Integer id, @PathVariable("pid") Integer pid) {
		// Para o Spring saber que esse id informado é o 'id', e que o pid é o 'pid',
		// colocamos o @PathVariable na linha de cima. 
		// Esse 'ReponseEntity' é um tipo especial do Spring que encapsula e armazena
		// várias informações de uma resposta http para um serviço REST.

		Pedido obj = service.find(id);
		Produto produtoObj = prodService.find(pid);
		obj.getProdutos().add(produtoObj); // Adiciona o produto na lista de produtos de Pedido
		produtoObj.setPedido(obj); // O atributo 'pedido' do Produto produtoObj passará a ser o
								   //Pedido 'obj'
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj); // ok() para dizer que a operação ocorreu com sucesso,
		// e a resposta terá como corpo o objeto 'obj'
	}

}
