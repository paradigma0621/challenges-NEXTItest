package com.paradigma0621.NEXTItest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paradigma0621.NEXTItest.domain.Produto;

/* Camada de acesso a dados. Sua responsabilidade é conversar com o banco de
dados (operações de salvar, alterar, excluir, consultar os dados). A camada de
serviço ('services') por sua vez utilizará essa camada para realizar regras de
negócio.
*/

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
// Tipo especial do Spring capaz de acessar os dados com base no tipo passado (objetos do tipo 'Produto')
// Integer é o tipo do atributo identificador do objeto (que nesse caso é 'Id', dentro da classe Produto.java

	  
}