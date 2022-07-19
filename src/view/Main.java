package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.ProdutosDAO;
import controller.Administrador;
import controller.Cliente;
import model.Categoria;
import model.Compra;
import model.Endereco;
import model.Produto;


public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Administrador adm = new Administrador();
		Cliente cl = new Cliente();
		
		try {
			System.out.println("[1] Entrar como Admin");
			System.out.println("[2] Entrar como Usuario comum");
			System.out.print("Entre com uma das opções\n-> ");
			int opcao = input.nextInt();
			
			if(opcao == 1) {
				String emailAdm = "taldo@gmail.com";
				String senha = "abcdefghij";
				
				adm.setEmail(emailAdm);
				adm.setSenha(senha);
				adm.fazLogin();
				
				administradorOpt(adm, input);
				
			}else {
				String emailCl = "chaletmarcos@gmail.com";
				String senha = "abcdefghij";
				
				cl.setEmail(emailCl);
				cl.setSenha(senha);
				cl.fazLogin();
				
				clienteOpt(cl, input);
			}
		} catch(InputMismatchException err) {
			System.out.println("Entrada inválida");
		}finally {
			input.close();	
		}
	}
	
	public static void administradorOpt(Administrador adm , Scanner input) {
		int opt = 0;
		do {
			opcoesAdministrador();
			System.out.print("\nEntre com uma das opções\n-> ");
			try {
				opt = input.nextInt();
				input.nextLine();
				
				switch (opt) {
				case 0: {
					System.out.println("Saindo do programa");
					break;
				}
				case 1: {
					cadastrarProduto(adm, input);
					break;
				}
				case 2: {
					excluirProduto(adm, input);
					break;
				}
				case 3: {
					excluirUsuario(adm, input);
					break;
				}
				case 4: {
					alterarStatusCompra(adm, input);
					break;
				}
				case 5: {
					alterarEstoque(adm, input);
					break;
				}
				case 6: {
					consultarCompras(adm);
					break;
				}
				default:
					System.out.println("Opção inválida. Entre com valor de 0-9");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Tipo de entrada inválido [entre com um número inteiro]");
			}
		}while(opt != 0);
	}
	
	public static void clienteOpt(Cliente cl, Scanner input) {

		int opt = 0;
		do {
			
			opcoesUsuario();
			System.out.print("\nEntre com uma das opções\n-> ");
			try {
				opt = input.nextInt();
				input.nextLine();

				switch (opt) {
				case 0: {
					System.out.println("Saindo do programa");
					break;
				}
				case 1: {
					listarProdutosCategoria(cl, input);
					break;
				}
				case 2: {
					listarTodosProdutos(cl);
					break;
				}
				case 3: {
					selecionaProduto(cl, input);
					break;
				}
				case 4: {
					adicionaAoCarrinho(cl);
					break;
				}
				case 5: {
					removerProdCarrinho(cl, input);
					break;
				}
				case 6: {
					mostrarCarrinho(cl);
					break;
				}
				case 7: {
					finalizarCompra(cl);
					break;
				}
				case 8: {
					listarComprasFinalizadas(cl);
					break;
				}
				case 9: {
					alterarDadosCliente(cl, input);
					break;
				}
				default:
					System.out.println("Opção inválida. Entre com valor de 0-9");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Tipo de entrada inválido [entre com um número inteiro]");
			}
			
		}while (opt != 0);
	}
	
	public static void opcoesUsuario() {
		System.out.println("*******************************************");
		System.out.println("* [0] Sair do sistema                     *");
		System.out.println("* [1] Listar produtos de uma categoria    *");
		System.out.println("* [2] Listar todos os produtos            *");
		System.out.println("* [3] Escolher um produto                 *");
		System.out.println("* [4] Adicionar produto ao carrinho       *");
		System.out.println("* [5] Remover produto do carrinho         *");
		System.out.println("* [6] Mostrar carrinho                    *");
		System.out.println("* [7] Finalizar compra                    *");
		System.out.println("* [8] Listar compras                      *");
		System.out.println("* [9] Alterar dados                       *");
		System.out.println("*******************************************");
	}
	
	public static void listarProdutosCategoria(Cliente cl, Scanner input) {
		
		try {
			int categoriaSelecionada = 0;
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Categorias =-=-=-=-=-=-=-=-=-=-=-=");
			for (Categoria c : cl.getCategorias()) {
				System.out.println(" Código: #" + c.getCodigo());
				System.out.println(" Categoria: " + c.getTipo() + "\n");
			}
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			System.out.print("Entre com uma das categorias\n-> ");
			
			categoriaSelecionada = input.nextInt();
			input.nextLine();
			
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Produtos =-=-=-=-=-=-=-=-=-=-=-=");
			for (String p : cl.listarProdPorCategoria(categoriaSelecionada)) {
				System.out.println("Descrição-produto: " + p);
			}
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		} catch(InputMismatchException e) {
			System.out.println("Tipo de entrada inválido [entre com um número inteiro]");
		}
	}
	
	public static void listarTodosProdutos(Cliente cl) {
		for (Produto p : cl.listarProdutos()) {
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Produto =-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println(" Cod-produto: " + p.getCodProduto());
			System.out.println(" Cod-categoria: " + p.getCodCategoria());
			System.out.println(" Descrição: " + p.getDescricao());
			System.out.println(" Preço: " + p.getPreco());
			System.out.println(" Estoque: " + p.getEstoque());
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		}
	}
	
	public static void selecionaProduto(Cliente cl, Scanner input) {
		int idProduto = 0;
		try {
			System.out.print("Entre com o id do produto\n-> ");
			idProduto = input.nextInt();
			input.nextLine();
			
			cl.escolheProduto(idProduto);
			System.out.println("O Produto foi selecionado e está Pronto para ser colocado no carrinho.");
		} catch(InputMismatchException e) {
			System.out.println("Tipo de entrada inválido [entre com um número inteiro]");
		}
	}
	
	public static void adicionaAoCarrinho(Cliente cl) {
		if ( cl.addProdutoCarrinho() ) {
			System.out.println("Produto adicionado ao carrinho com sucesso!");
		}else {
			System.out.println("O produto não foi adicionado ao carrinho.");
		}
	}
	
	public static void mostrarCarrinho(Cliente cl) {
		for (Produto p : cl.getProdutosCarrinho()) {
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Produto =-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println(" Cod-produto: " + p.getCodProduto());
			System.out.println(" Cod-categoria: " + p.getCodCategoria());
			System.out.println(" Descrição: " + p.getDescricao());
			System.out.println(" Preço: " + p.getPreco());
			System.out.println(" Qtd: " + p.getQtd());
			System.out.println(" Estoque: " + p.getEstoque());
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		}
	}
	
	public static void removerProdCarrinho(Cliente cl, Scanner input) {
		mostrarCarrinho(cl);
		int codProd = 0;
		
		try {
			System.out.print("Entre com o código do produto a ser removido\n-> ");
			codProd = input.nextInt();
			cl.remProdCarrinho(codProd);
		} catch (InputMismatchException e) {
			System.out.println("Tipo de entrada inválido [entre com um número inteiro]");
		}
	}
	
	public static void finalizarCompra(Cliente cl) {
		cl.finalizarCompra();
	}
	
	public static void listarComprasFinalizadas(Cliente cl) {
		
		for (Compra c : cl.listarCompras()) {
			System.out.println("##################### Compras #####################");
			System.out.println("Quantidade:   " + c.getQtdProdutos());
			System.out.println("Total:        " + c.getTotal());
			System.out.println("" + c.getEnderecoEntrega().toString());

			for (Produto p : c.getCarrinho().getProdutos()) {
				System.out.println("=-=-=-=-=-=-=-=-=-=-= Produto =-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("Descrição: " + p.getDescricao());
				System.out.println("Preço:     " + p.getPreco());
				System.out.println("Categoria: " + p.getCategoria());
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			}
			System.out.println("###################################################\n");
		}
	}
	
	public static void alterarDadosCliente(Cliente cl, Scanner input) {
		Cliente c = new Cliente();
		Endereco e = new Endereco();
		
		System.out.println("=-=-=-=-=-=-=-=-=-=-= Alterando os dados =-=-=-=-=-=-=-=-=-=-=");
		try {
			System.out.print("Nome: ");
			c.setNome(input.nextLine());
			
			System.out.print("Telefone: ");
			c.setTelefone(input.nextLine());
			
			System.out.print("Cidade: ");
			e.setCidade(input.nextLine());
			
			System.out.print("Cep: ");
			e.setCep(input.nextLine());
			
			System.out.print("Rua: ");
			e.setRua(input.nextLine());
			
			System.out.print("Número: ");
			e.setNumero(input.nextInt());
			
			c.setEndereco(e);
			c.setCodigo(cl.getCodigo());
			c.setCpf(cl.getCpf());
			c.setRg(cl.getRg());
			c.setEmail(cl.getEmail());
			c.setSenha(cl.getSenha());

			cl.alterarDadosPessoais(c);
			System.out.println("\nOs dados do cliente foram alterados");
			
		} catch (InputMismatchException err) {
			System.out.println("Tipo de entrada inválido [entre com um número inteiro]");
		}
	}
	
	
	public static void opcoesAdministrador() {
		System.out.println("*******************************************");
		System.out.println("* [0] Sair do sistema                     *");
		System.out.println("* [1] Cadastrar produto                   *");
		System.out.println("* [2] Excluir produto                     *");
		System.out.println("* [3] Excluir usuário                     *");
		System.out.println("* [4] Alterar status de uma compra        *");
		System.out.println("* [5] Alterar estoque                     *");
		System.out.println("* [6] Consultar Compras                   *");
		System.out.println("*******************************************");
	}
	
	public static void cadastrarProduto(Administrador adm, Scanner input) {
		Produto p = new Produto();
		Categoria c = new Categoria();
		
		try {
			System.out.print("Entre com a descrição do produto na forma {\"Nome: descrição\"}\n-> ");
			p.setDescricao(input.nextLine());
			
			System.out.print("Entre com a quantidade de produtos no estoque\n-> ");
			p.setEstoque(input.nextInt());
			input.nextLine();
			
			System.out.print("Entre com o preço do produto\n-> ");
			p.setPreco(input.nextDouble());
			input.nextLine();
			
			// pega as categorias no banco
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Categorias =-=-=-=-=-=-=-=-=-=-=-=");
			for (Categoria categoria : ProdutosDAO.getCategorias()) {
				System.out.println(" Código: #" + categoria.getCodigo());
				System.out.println(" Categoria: " + categoria.getTipo() + "\n");
			}
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
			
			System.out.print("Entre com o código da categoria\n-> ");
			c.setCodigo(input.nextInt());
			
			// pega o tipo da categoria no banco
			for (Categoria categoria : ProdutosDAO.getCategorias()) {
				if(c.getCodigo() == categoria.getCodigo()) {
					c.setTipo(categoria.getTipo());
				}
			}
			
			p.setCategoria(c);
			adm.cadastrarProduto(p);
			System.out.println("Produto cadastrado com sucesso");
		}catch(InputMismatchException e) {
			System.out.println("Produto não cadastrado!");
		}
	}
	
	public static void excluirProduto(Administrador adm, Scanner input) {
		
		System.out.println("##################### Lista de Produtos #####################");
		for (Produto p : ProdutosDAO.getProdutos()) {
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Produto =-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println(" Cod-produto: " + p.getCodProduto());
			System.out.println(" Cod-categoria: " + p.getCodCategoria());
			System.out.println(" Descrição: " + p.getDescricao());
			System.out.println(" Preço: " + p.getPreco());
			System.out.println(" Estoque: " + p.getEstoque());
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		}
		System.out.println("#############################################################");
		
		try {
			System.out.print("Entre com o id do produto que você quer excluir\n-> ");
			int idProduto = input.nextInt();
			input.nextLine();
			
			adm.excluirProduto(idProduto);
			System.out.println("Produto excluido com sucesso");
		} catch(InputMismatchException e) {
			System.out.println("O produto não foi excluido!");
		}
	}
	
	public static void excluirUsuario(Administrador adm, Scanner input) {
		/* Listar usuários */
		try {
			System.out.print("Entre com o id do usuário a ser removido\n-> ");
			int idUsuario = input.nextInt();
			input.nextLine();
			
			adm.excluirUsuario(idUsuario);
			System.out.println("\nUsuário excluido com sucesso.");
		} catch(InputMismatchException e) {
			System.out.println("O usuário não foi excluido!");
		}
	}
	
	public static void alterarStatusCompra(Administrador adm, Scanner input) {
		System.out.print("Entre com o id da compra para ser alterado o status\n-> ");
		int idCompra = input.nextInt();
		input.nextLine();
		
		System.out.print("Entre com o novo status\n-> ");
		String novoStatus = input.nextLine();
		
		adm.alterarStatusCompra(idCompra, novoStatus);
		System.out.println("\nO status da compra foi alterado");
	}
	
	public static void alterarEstoque(Administrador adm, Scanner input) {
		
		System.out.println("##################### Lista de Produtos #####################");
		for (Produto p : ProdutosDAO.getProdutos()) {
			System.out.println("=-=-=-=-=-=-=-=-=-=-= Produto =-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println(" Cod-produto: " + p.getCodProduto());
			System.out.println(" Cod-categoria: " + p.getCodCategoria());
			System.out.println(" Descrição: " + p.getDescricao());
			System.out.println(" Preço: " + p.getPreco());
			System.out.println(" Estoque: " + p.getEstoque());
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		}
		System.out.println("#############################################################");
		
		try {
			System.out.print("Entre com o id do produto que será alterado\n-> ");
			int idProduto = input.nextInt();
			
			System.out.print("Entre com o novo estoque do produto\n-> ");
			double novoEstoque = input.nextDouble();
			
			adm.alterarEstoque(idProduto, novoEstoque);
			System.out.println("Estoque modificado com sucesso");
		} catch(InputMismatchException e) {
			System.out.println("O estoque não foi excluido!");
		}
	}
	
	public static void consultarCompras(Administrador adm) {
		System.out.println("################## Compras Realizadas ##################");
		for(Compra c : adm.consultarCompras()) {
			System.out.println("=-=-=-=-=-=-=-=-=-= Compra =-=-=-=-=-=-=-=-=-=");
			System.out.println("Id:           " + c.getCodCompra());
			System.out.println("Qtd Produtos: " + c.getQtdProdutos());
			System.out.println("Total:        " + c.getTotal());
			System.out.println("Status:       " + c.getStatus());
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		}
		System.out.println("########################################################");
	}
}