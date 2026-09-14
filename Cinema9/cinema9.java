import java.util.Scanner;

public class Main {

    static boolean[][] assentos;
    static int NUM_FILEIRAS;
    static int NUM_COLUNAS;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Sistema de Reservas de Assentos ===");
        System.out.print("Informe o numero de fileiras: ");
        NUM_FILEIRAS = lerInteiroPositivo(sc);
        System.out.print("Informe o numero de assentos por fileira: ");
        NUM_COLUNAS = lerInteiroPositivo(sc);

        assentos = new boolean[NUM_FILEIRAS][NUM_COLUNAS];

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiroPositivo(sc);

            switch (opcao) {
                case 1:
                    exibirMapa();
                    break;
                case 2:
                    reservarAssento(sc);
                    break;
                case 3:
                    cancelarReserva(sc);
                    break;
                case 4:
                    informarOcupacao();
                    break;
                case 5:
                    fileiraMaiorOcupacao();
                    break;
                case 6:
                    procurarConsecutivos(sc);
                    break;
                case 7:
                    System.out.println("Encerrando o sistema. Ate mais!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

            System.out.println();
        } while (opcao != 7);

        sc.close();
    }

    static void exibirMenu() {
        System.out.println("----- MENU -----");
        System.out.println("1. Exibir mapa de assentos");
        System.out.println("2. Reservar um assento");
        System.out.println("3. Cancelar uma reserva");
        System.out.println("4. Informar quantidade e percentual de ocupados");
        System.out.println("5. Identificar fileira com maior ocupacao");
        System.out.println("6. Procurar assentos consecutivos");
        System.out.println("7. Encerrar");
        System.out.print("Escolha uma opcao: ");
    }

    // Operacao 1: exibe o mapa de assentos
    static void exibirMapa() {
        System.out.println("\nMapa de assentos (O = ocupado, . = livre)");
        System.out.print("     ");
        for (int c = 0; c < NUM_COLUNAS; c++) {
            System.out.printf("%3d", c + 1);
        }
        System.out.println();

        for (int f = 0; f < NUM_FILEIRAS; f++) {
            System.out.printf("F%-3d ", f + 1);
            for (int c = 0; c < NUM_COLUNAS; c++) {
                System.out.print(assentos[f][c] ? "  O" : "  .");
            }
            System.out.println();
        }
    }

    static void reservarAssento(Scanner sc) {
        System.out.print("Informe a fileira (1 a " + NUM_FILEIRAS + "): ");
        int fileira = lerInteiroPositivo(sc) - 1;
        System.out.print("Informe o assento (1 a " + NUM_COLUNAS + "): ");
        int coluna = lerInteiroPositivo(sc) - 1;

        if (!posicaoValida(fileira, coluna)) {
            System.out.println("Posicao fora dos limites da matriz.");
            return;
        }

        if (assentos[fileira][coluna]) {
            System.out.println("Esse assento ja esta ocupado.");
        } else {
            assentos[fileira][coluna] = true;
            System.out.println("Assento reservado com sucesso.");
        }
    }

    // Operacao 3: cancela uma reserva
    static void cancelarReserva(Scanner sc) {
        System.out.print("Informe a fileira (1 a " + NUM_FILEIRAS + "): ");
        int fileira = lerInteiroPositivo(sc) - 1;
        System.out.print("Informe o assento (1 a " + NUM_COLUNAS + "): ");
        int coluna = lerInteiroPositivo(sc) - 1;

        if (!posicaoValida(fileira, coluna)) {
            System.out.println("Posicao fora dos limites da matriz.");
            return;
        }

        if (!assentos[fileira][coluna]) {
            System.out.println("Esse assento ja esta livre.");
        } else {
            assentos[fileira][coluna] = false;
            System.out.println("Reserva cancelada com sucesso.");
        }
    }

    static void informarOcupacao() {
        int total = NUM_FILEIRAS * NUM_COLUNAS;
        int ocupados = contarOcupados();
        double percentual = (ocupados * 100.0) / total;

        System.out.println("Assentos ocupados: " + ocupados + " de " + total);
        System.out.printf("Percentual de ocupacao: %.2f%%%n", percentual);
    }

    static void fileiraMaiorOcupacao() {
        int melhorFileira = -1;
        int maiorQuantidade = -1;

        for (int f = 0; f < NUM_FILEIRAS; f++) {
            int quantidade = 0;
            for (int c = 0; c < NUM_COLUNAS; c++) {
                if (assentos[f][c]) {
                    quantidade++;
                }
            }
            if (quantidade > maiorQuantidade) {
                maiorQuantidade = quantidade;
                melhorFileira = f;
            }
        }

        if (maiorQuantidade <= 0) {
            System.out.println("Nenhum assento ocupado no momento.");
        } else {
            System.out.println("Fileira com maior ocupacao: F" + (melhorFileira + 1)
                    + " (" + maiorQuantidade + " assento(s) ocupado(s))");
        }
    }

    static void procurarConsecutivos(Scanner sc) {
        System.out.print("Quantos assentos consecutivos deseja? ");
        int quantidade = lerInteiroPositivo(sc);

        if (quantidade <= 0 || quantidade > NUM_COLUNAS) {
            System.out.println("Quantidade invalida para o tamanho da fileira.");
            return;
        }

        for (int f = 0; f < NUM_FILEIRAS; f++) {
            int inicio = buscarSequenciaLivre(f, quantidade);
            if (inicio != -1) {
                System.out.println("Encontrado na fileira F" + (f + 1)
                        + ", assentos " + (inicio + 1) + " a " + (inicio + quantidade));
                return;
            }
        }

        System.out.println("Nao foi encontrado um conjunto de " + quantidade
                + " assentos consecutivos livres.");
    }

    static int buscarSequenciaLivre(int fileira, int quantidade) {
        int consecutivos = 0;
        for (int c = 0; c < NUM_COLUNAS; c++) {
            if (!assentos[fileira][c]) {
                consecutivos++;
                if (consecutivos == quantidade) {
                    return c - quantidade + 1;
                }
            } else {
                consecutivos = 0;
            }
        }
        return -1;
    }

    static int contarOcupados() {
        int ocupados = 0;
        for (int f = 0; f < NUM_FILEIRAS; f++) {
            for (int c = 0; c < NUM_COLUNAS; c++) {
                if (assentos[f][c]) {
                    ocupados++;
                }
            }
        }
        return ocupados;
    }

    static boolean posicaoValida(int fileira, int coluna) {
        return fileira >= 0 && fileira < NUM_FILEIRAS && coluna >= 0 && coluna < NUM_COLUNAS;
    }

    static int lerInteiroPositivo(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Entrada invalida. Digite um numero: ");
            sc.next();
        }
        return sc.nextInt();
    }
}