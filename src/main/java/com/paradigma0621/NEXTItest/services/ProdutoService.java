package com.paradigma0621.NEXTItest.services; //Camada de serviços

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma0621.NEXTItest.domain.Produto;
import com.paradigma0621.NEXTItest.repositories.ProdutoRepository;
import com.paradigma0621.NEXTItest.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {/*
								 * Responsável por oferecer serviços (operações/consultas) para os controladores
								 * REST (pacote 'resources') relacionados à operações com 'Produto'
								 * 
								 * É uma camada sem contato com as demais tecnologias. Simplemente tem as regras
								 * de negócio que porventura ainda não tenham sido implementadas na camada de
								 * domínio
								 */

	@Autowired // Com essa tag fica especificado que a dependência da
				// linha de baixo será automaticamente instanciada pelo Spring
				// pelo mecanismo de injeção de pendencia ou inversão de cotrole
	private ProdutoRepository repo; // objeto capaz de realizar operações de
									// acesso a dados (buscar, salvar, alterar,
									// deletar) referente ao tipo 'Produto'

	
	public Produto find(Integer id) { // Operação capaz de buscar 'Produto' por
										// código (retornando obj do tipo 'Produto')
		Optional<Produto> obj = repo.findById(id); /*
								 * Retorna um optional (container que carrega o objeto, estando ele instânciado
								 * ou não, evitando ocorrência do erro caso o elemento seja null
								 */
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		
	}
}