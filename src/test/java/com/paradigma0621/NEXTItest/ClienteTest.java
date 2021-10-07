package com.paradigma0621.NEXTItest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.Date;

import org.junit.jupiter.api.Test;
//import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.paradigma0621.NEXTItest.domain.Cliente;
import com.paradigma0621.NEXTItest.repositories.ClienteRepository;
import com.paradigma0621.NEXTItest.services.ClienteService;

@SpringBootTest
@Sql("/cleanAndCreateTestDb.sql") // Deleta e cria o banco de dados 'testNEXTIdb' para garantir que
									// não tenha nenhum item ao iniciar.
									// Esse banco de dados será usado para apenas Tests

@ActiveProfiles("test") // usando arquivo de configuração src/test/resources/application-test.properties
						// para definir banco de dados separado de nome 'testNEXTIdb'
class ClienteTest  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired // Vai ser instanciado automaticamente
	private ClienteRepository clienteRepository;

	@Autowired // Para instanciar automaticamente o objeto
	private ClienteService clienteService;

	@Test
	void dadosCliente() {
			Date data = new Date();
			Cliente c1 = new Cliente(null, "Fábio Santos", "040.630.489-20", data);
			clienteService.insert(c1);            // Testará a implementação do insert de ClienteService
			Cliente obj = clienteService.find(1); // Testará a implementação do find de ClienteService
			
			assertTrue(obj.getId().equals(1));
			assertTrue(obj.getNome().equals(c1.getNome()));
			assertTrue(obj.getCpf().equals(c1.getCpf()));
	}

	@Test
	void adicionarClientes() {
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
		
		assertEquals(clienteRepository.count(), 11); // Testa o número de linhas na tabela 'cliente' 
													// do banco de dados - se é igual ao número 
													// adicionado (=11)
	
		clienteService.delete(5);	// Testará a implementação do delete de ClienteService
		clienteService.delete(4);
		assertEquals(clienteRepository.count(), 9); // Removeu 2 elementos da base de dados; ficaram 9
		 											// Confere com o limero de registros da tabela?
		
	}
}

