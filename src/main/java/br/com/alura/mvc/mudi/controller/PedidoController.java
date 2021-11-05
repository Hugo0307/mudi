package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	/**
	 * Método responsável por redirecionar para a página de formulário
	 * @author Hugo Almeida
	 * @param requisicao
	 * @return retorna a página de formulário para requisição de novo pedido
	 */
	@GetMapping("/formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}
	/**
	 * Método que recebe o formulário com novo pedido, realiza as validações através da classe dto RequisicaoNovoPedido
	 * e usa o objeto do tipo BindingResult para saber se há erros de validação
	 * @author Hugo Almeida
	 * @param requisicao
	 * @param result
	 * @return retorna a página do formulário limpa caso tenha sucesso na validação ou retorna mensagens de erro contidas no
	 * messases.properties caso contenha erros de validação
	 */
	@PostMapping("/novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		
		Pedido pedido = requisicao.toPedido();
		pedidoRepository.save(pedido);
		return "redirect:/home";
	}
}
