package com.paradigma0621.NEXTItest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Date;

import org.junit.jupiter.api.Test;
//import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.paradigma0621.NEXTItest.domain.Cliente;
import com.paradigma0621.NEXTItest.domain.Pedido;
import com.paradigma0621.NEXTItest.repositories.PedidoRepository;
import com.paradigma0621.NEXTItest.services.ClienteService;
import com.paradigma0621.NEXTItest.services.PedidoService;

@SpringBootTest
@Sql("/cleanAndCreateTestDb.sql") // Deleta e cria o banco de dados 'testNEXTIdb' para garantir que
									// não tenha nenhum item ao iniciar.
									// Esse banco de dados será usado para apenas Tests

@ActiveProfiles("test") // usando arquivo de configuração src/test/resources/application-test.properties
						// para definir banco de dados separado de nome 'testNEXTIdb'
class PedidoTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired // Vai ser instanciado automaticamente
	private PedidoRepository pedidoRepository;
	
	@Autowired // Para instanciar automaticamente o objeto
	private PedidoService pedidoService;

	@Autowired // Para instanciar automaticamente o objeto
	private ClienteService clienteService;

	@Test
	void adicionarPedidos() {
		Cliente c1 = new Cliente(null, "Cliente 1", "123.456.789-00", new Date());
		Cliente c2 = new Cliente(null, "Cliente 2", "223.456.789-00", new Date());
		Cliente c3 = new Cliente(null, "Cliente 3", "323.456.789-00", new Date());
		Cliente c4 = new Cliente(null, "Cliente 4", "423.456.789-00", new Date());
		Cliente c5 = new Cliente(null, "Cliente 5", "523.456.789-00", new Date());
		Cliente c6 = new Cliente(null, "Cliente 6", "623.456.789-00", new Date());
		Cliente c7 = new Cliente(null, "Cliente 7", "723.456.789-00", new Date());
		Cliente c8 = new Cliente(null, "Cliente 8", "823.456.789-00", new Date());
		Cliente c9 = new Cliente(null, "Cliente 9", "923.456.789-00", new Date());
		Cliente c10 = new Cliente(null, "Cliente 10", "103.456.789-00", new Date());
		Cliente c11 = new Cliente(null, "Cliente 11", "113.456.789-00", new Date());
		
		clienteService.insert(c1); 
		clienteService.insert(c2); 
		clienteService.insert(c3); 
		clienteService.insert(c4); 
		clienteService.insert(c5); 
		clienteService.insert(c6); 
		clienteService.insert(c7); 
		clienteService.insert(c8); 
		clienteService.insert(c9); 
		clienteService.insert(c10); 
		clienteService.insert(c11); 
		
		Pedido pedido1 = new Pedido(null, c1, new Date());
		Pedido pedido2 = new Pedido(null, c2, new Date());
		Pedido pedido3 = new Pedido(null, c3, new Date());
		Pedido pedido4 = new Pedido(null, c4, new Date());
		Pedido pedido5 = new Pedido(null, c5, new Date());
		Pedido pedido6 = new Pedido(null, c6, new Date());
		Pedido pedido7 = new Pedido(null, c7, new Date());
		Pedido pedido8 = new Pedido(null, c8, new Date());
		Pedido pedido9 = new Pedido(null, c9, new Date());
		Pedido pedido10 = new Pedido(null, c10, new Date());
		Pedido pedido11 = new Pedido(null, c11, new Date());
				
		pedidoService.insert(pedido1);
		pedidoService.insert(pedido2);
		pedidoService.insert(pedido3);
		pedidoService.insert(pedido4);
		pedidoService.insert(pedido5);
		pedidoService.insert(pedido6);
		pedidoService.insert(pedido7);
		pedidoService.insert(pedido8);
		pedidoService.insert(pedido9);
		pedidoService.insert(pedido10);
		pedidoService.insert(pedido11);
		
		System.out.println("aaa");
		assertEquals(pedidoRepository.count(), 11); // Testa o número de linhas na tabela 'pedido' 
													// do banco de dados - se é igual ao número 
													// adicionado (=11)
	
		pedidoService.delete(5);	// Testará a implementação do delete de PedidoService
		pedidoService.delete(4);
		System.out.println("aaa");
		assertEquals(pedidoRepository.count(), 9); // Removeu 2 elementos da base de dados; ficaram 9
		 											// Confere com o limero de registros da tabela?
		
	}
}

