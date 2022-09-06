package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	/**
	 * Método responsável por devolver à tela a lista de produtos cadastrados no sistema.
	 * @author Hugo Almeida.
	 * @return retorna a lista de produtos.
	 */
	@GetMapping("/home")
	public ModelAndView home() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("pedidos", pedidos);
		return mv;
	}
	
	/**
	 * Método responsável por devolver à tela a lista de produtos por status do pedido.
	 * @author Hugo Almeida.
	 * @return retorna a lista de produtos por status do pedido.
	 */
	@GetMapping("/home/{status}")
	public ModelAndView byStatus(@PathVariable("status") String status) {
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("pedidos", pedidos);
		mv.addObject("status", status);
		return mv;
	}
	
	/**
	 * Método responsável por tratar exceção caso o usuário digite um path inválido para mapeamento do status
	 * @author Hugo Almeida.
	 * @return redireciona para a página home
	 * @exception IllegalArgumentException
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
