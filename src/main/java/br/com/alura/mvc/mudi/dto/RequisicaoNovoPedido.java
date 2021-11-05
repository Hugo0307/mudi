package br.com.alura.mvc.mudi.dto;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe responsável por realizar as validações. Para devolver ao browser validado, é necessário
 * anotar com @Valid na classe controller no método responsável por receber o formulário na requisição.
 * @author Hugo Almeida.
 *
 */
@Getter
@Setter
public class RequisicaoNovoPedido {
	
	@NotBlank
	private String nomeProduto;
	
	@NotBlank
	private String urlImagem;
	
	@NotBlank
	private String urlProduto;
	
	private String descricaoProduto;
	
	private StatusPedido status;
	
	/**
	 * Método responsável por devolver ao controller o pedido após as validações necessárias.
	 * @return pedido validado com ou sem erros de validação.
	 */
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setNomeProduto(nomeProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setUrlProduto(urlProduto);
		pedido.setDescricao(descricaoProduto);
		pedido.setStatus(status.AGUARDANDO);
		return pedido;
	}
	
}
