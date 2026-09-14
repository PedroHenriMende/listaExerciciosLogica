import java.util.Scanner;

public class Main {

    static int[] dLinha = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dColuna = {1, -1, 0, 0, 1, -1, 1, -1};
    static String[] nomesDirecoes = {
            "Horizontal (esquerda para direita)",
            "Horizontal (direita para esquerda)",
            "Vertical (cima para baixo)",
            "Vertical (baixo para cima)",
            "Diagonal (superior esquerda para inferior direita)",
            "Diagonal (inferior direita para superior esquerda)",
            "Diagonal (superior direita para inferior esquerda)",
            "Diagonal (inferior esquerda para superior direita)"
    };

    // Verifica se uma palavra existe a partir de uma posição, em uma direção específica
    public static boolean verificarPalavra(char[][] matriz, String palavra, int linha, int coluna, int direcao) {
        int linhas = matriz.length;
        int colunas = matriz[0].length;

        int linhaAtual = linha;
        int colunaAtual = coluna;

        for (int i = 0; i < palavra.length(); i++) {
            if (linhaAtual < 0 || linhaAtual >= linhas || colunaAtual < 0 || colunaAtual >= colunas) {
                return false;
            }

            if (matriz[linhaAtual][colunaAtual] != palavra.charAt(i)) {
                return false;
            }

            linhaAtual += dLinha[direcao];
            colunaAtual += dColuna[direcao];
        }

        return true;
    }

    // Busca uma palavra em todas as posições e direções da matriz
    public static void buscarPalavra(char[][] matriz, String palavra) {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        String palavraUpper = palavra.toUpperCase();

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                for (int direcao = 0; direcao < 8; direcao++) {
                    if (verificarPalavra(matriz, palavraUpper, linha, coluna, direcao)) {
                        int linhaFinal = linha + dLinha[direcao] * (palavraUpper.length() - 1);
                        int colunaFinal = coluna + dColuna[direcao] * (palavraUpper.length() - 1);

                        System.out.println("Palavra \"" + palavra + "\" encontrada!");
                        System.out.println("  Posição inicial: (" + linha + ", " + coluna + ")");
                        System.out.println("  Posição final: (" + linhaFinal + ", " + colunaFinal + ")");
                        System.out.println("  Direção: " + nomesDirecoes[direcao]);
                        return;
                    }
                }
            }
        }

        System.out.println("Palavra \"" + palavra + "\" não encontrada");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de linhas da matriz: ");
        int linhas = scanner.nextInt();

        System.out.print("Digite o número de colunas da matriz: ");
        int colunas = scanner.nextInt();

        char[][] matriz = new char[linhas][colunas];

        System.out.println("Digite as letras da matriz, linha por linha sem espaços:");
        for (int i = 0; i < linhas; i++) {
            System.out.print("Linha " + i + ": ");
            String linhaTexto = scanner.next();
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = Character.toUpperCase(linhaTexto.charAt(j));
            }
        }

        System.out.print("Digite a quantidade de palavras a procurar: ");
        int quantidadePalavras = scanner.nextInt();

        String[] palavras = new String[quantidadePalavras];
        System.out.println("Digite as palavras:");
        for (int i = 0; i < quantidadePalavras; i++) {
            palavras[i] = scanner.next();
        }

        System.out.println("Resultado da busca");
        for (String palavra : palavras) {
            buscarPalavra(matriz, palavra);
            System.out.println();
        }

        scanner.close();
    }
}
