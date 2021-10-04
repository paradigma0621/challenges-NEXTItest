package com.paradigma0621.NEXTItest.services; //Camada de serviços

import java.util.List;
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

	public Produto insert(Produto obj) {
		obj.setId(null); //Para garantir que estará salvando um objeto novo, pois se ele tiver algu, setId(#NumId) o método 'save' vai considerar que é uma atualização, e não uma inserção (ver update() logo abaixo)
		return repo.save(obj); 
	}
	
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId()); //Instância objeto a partir do banco de dados - ele estará monitorado pelo JPA
		updateData(newObj, obj); // Atualiza o objeto com os dados que foram enviados na requisição
		return repo.save(newObj); // Salva objeto no banco de dados
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
	}
	
	
	public void delete(Integer id) {
		find(id); //Antes de atualizar verifica se o objeto existe. Se não existir é lançada a exceção ObjectNotFoundException
		repo.deleteById(id);
	}
		
	public List<Produto> findAll() {
		return repo.findAll();
	}
}