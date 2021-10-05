package com.paradigma0621.NEXTItest.services; //Camada de serviços

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma0621.NEXTItest.domain.Pedido;
import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.repositories.PedidoRepository;
import com.paradigma0621.NEXTItest.repositories.ProdutoRepository;
import com.paradigma0621.NEXTItest.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {/*
							 * Responsável por oferecer serviços (operações/consultas) para os controladores
							 * REST (pacote 'resources') relacionados à operações com 'Pedido'
							 * 
							 * É uma camada sem contato com as demais tecnologias. Simplemente tem as regras
							 * de negócio que porventura ainda não tenham sido implementadas na camada de
							 * domínio
							 */

	@Autowired // Com essa tag fica especificado que a dependência da
				// linha de baixo será automaticamente instanciada pelo Spring
				// pelo mecanismo de injeção de pendencia ou inversão de cotrole
	private PedidoRepository repo; // objeto capaz de realizar operações de
									// acesso a dados (buscar, salvar, alterar,
									// deletar) referente ao tipo 'Pedido'
	@Autowired
	private ProdutoRepository repoProdutos;

	public Pedido find(Integer id) { // Operação capaz de buscar 'Pedido' por
										// código (retornando obj do tipo 'Pedido')
		Optional<Pedido> obj = repo
				.findById(id); /*
								 * Retorna um optional (container que carrega o objeto, estando ele instânciado
								 * ou não, evitando ocorrência do erro caso o elemento seja null
								 */
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido update(Pedido obj) {
		Pedido newObj = find(obj.getId()); // Instância objeto a partir do banco de dados -
			//ele estará monitorado pelo JPA
		updateData(newObj, obj); // Atualiza o objeto com os dados que foram enviados na requisição
		return repo.save(newObj); // Salva objeto no banco de dados
	}

	private void updateData(Pedido newObj, Pedido obj) {
		newObj.setCliente(obj.getCliente());
		newObj.setTotalDaCompra(obj.getTotalDaCompra());
		newObj.setProdutos(obj.getProdutos());
		newObj.setDataCompra(obj.getDataCompra());
	}

	public void delete(Integer id) {
		find(id); // Antes de atualizar verifica se o objeto existe. Se não existir é lançada a
					// exceção ObjectNotFoundException
		repo.deleteById(id);
	}

	public List<Pedido> findAll() {
		return repo.findAll();
	}
}