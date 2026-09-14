import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> nomes = new ArrayList<>();
    static ArrayList<Integer> quantidades = new ArrayList<>();
    static ArrayList<Double> precos = new ArrayList<>();

    public static void adicionarProduto(String nome, int quantidade, double preco) {
        nomes.add(nome);
        quantidades.add(quantidade);
        precos.add(preco);
        System.out.println("Produto adicionado com sucesso!");
    }

    public static void alterarQuantidade(int indice, int novaQuantidade) {
        if (indiceValido(indice)) {
            quantidades.set(indice, novaQuantidade);
            System.out.println("Quantidade atualizada com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void alterarPreco(int indice, double novoPreco) {
        if (indiceValido(indice)) {
            precos.set(indice, novoPreco);
            System.out.println("Preço atualizado com sucesso");
        } else {
            System.out.println("Produto não encontrado");
        }
    }

    // Remove um produto pelo índice
    public static void removerProduto(int indice) {
        if (indiceValido(indice)) {
            System.out.println("Produto removido: " + nomes.get(indice));
            nomes.remove(indice);
            quantidades.remove(indice);
            precos.remove(indice);
        } else {
            System.out.println("Produto não encontrado");
        }
    }

    public static void pesquisarProdutos(String termo) {
        boolean encontrou = false;
        String termoMinusculo = termo.toLowerCase();

        for (int i = 0; i < nomes.size(); i++) {
            if (nomes.get(i).toLowerCase().contains(termoMinusculo)) {
                exibirProduto(i);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum produto encontrado com o termo: " + termo);
        }
    }

    // Lista todos os produtos cadastrados
    public static void listarProdutos() {
        if (nomes.isEmpty()) {
            System.out.println("Lista de compras vazia");
            return;
        }

        for (int i = 0; i < nomes.size(); i++) {
            exibirProduto(i);
        }
    }

    public static void exibirProduto(int indice) {
        double subtotal = calcularSubtotal(indice);
        System.out.printf("[%d] %s | Qtd: %d | Preço unit.: R$ %.2f | Subtotal: R$ %.2f%n",
                indice, nomes.get(indice), quantidades.get(indice), precos.get(indice), subtotal);
    }

    public static double calcularSubtotal(int indice) {
        return quantidades.get(indice) * precos.get(indice);
    }

    public static double calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < nomes.size(); i++) {
            total += calcularSubtotal(i);
        }
        return total;
    }

    // Identifica o índice do produto com maior subtotal
    public static int indiceMaiorSubtotal() {
        if (nomes.isEmpty()) {
            return -1;
        }

        int indiceMaior = 0;
        double maiorSubtotal = calcularSubtotal(0);

        for (int i = 1; i < nomes.size(); i++) {
            double subtotalAtual = calcularSubtotal(i);
            if (subtotalAtual > maiorSubtotal) {
                maiorSubtotal = subtotalAtual;
                indiceMaior = i;
            }
        }

        return indiceMaior;
    }

    public static boolean indiceValido(int indice) {
        return indice >= 0 && indice < nomes.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Gerenciador de Lista de Compras");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Alterar quantidade de um produto");
            System.out.println("3. Alterar preço de um produto");
            System.out.println("4. Remover produto");
            System.out.println("5. Pesquisar produtos pelo nome");
            System.out.println("6. Listar todos os produtos");
            System.out.println("7. Calcular valor total da compra");
            System.out.println("8. Identificar produto com maior subtotal");
            System.out.println("9. Encerrar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();
                    adicionarProduto(nome, quantidade, preco);
                    break;

                case 2:
                    listarProdutos();
                    System.out.print("Digite o índice do produto: ");
                    int indiceQtd = scanner.nextInt();
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    alterarQuantidade(indiceQtd, novaQuantidade);
                    break;

                case 3:
                    listarProdutos();
                    System.out.print("Digite o índice do produto: ");
                    int indicePreco = scanner.nextInt();
                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();
                    alterarPreco(indicePreco, novoPreco);
                    break;

                case 4:
                    listarProdutos();
                    System.out.print("Digite o índice do produto a remover: ");
                    int indiceRemover = scanner.nextInt();
                    removerProduto(indiceRemover);
                    break;

                case 5:
                    System.out.print("Digite o nome ou parte do nome: ");
                    String termo = scanner.nextLine();
                    pesquisarProdutos(termo);
                    break;

                case 6:
                    listarProdutos();
                    break;

                case 7:
                    System.out.printf("Valor total da compra: R$ %.2f%n", calcularValorTotal());
                    break;

                case 8:
                    int indiceMaior = indiceMaiorSubtotal();
                    if (indiceMaior == -1) {
                        System.out.println("Lista de compras vazia");
                    } else {
                        System.out.println("Produto com maior subtotal:");
                        exibirProduto(indiceMaior);
                    }
                    break;

                case 9:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida, Tente novamente");
            }

        } while (opcao != 9);

        scanner.close();
    }
}