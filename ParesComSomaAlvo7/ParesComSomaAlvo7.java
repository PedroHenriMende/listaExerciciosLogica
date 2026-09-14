import java.util.Scanner;

public class Main {

    public static int buscarPares(int[] vetor, int alvo) {
        int quantidadeEncontrada = 0;

        for (int i = 0; i < vetor.length; i++) {
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[i] + vetor[j] == alvo) {
                    quantidadeEncontrada++;
                    System.out.println("Par " + quantidadeEncontrada + ": "
                            + "valores (" + vetor[i] + ", " + vetor[j] + ") "
                            + "nos índices (" + i + ", " + j + ") "
                            + "soma = " + (vetor[i] + vetor[j]));
                }
            }
        }

        return quantidadeEncontrada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de elementos do vetor: ");
        int tamanho = scanner.nextInt();

        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            System.out.print("Digite o valor do índice " + i + ": ");
            vetor[i] = scanner.nextInt();
        }

        System.out.print("Digite o valor alvo da soma: ");
        int alvo = scanner.nextInt();

        System.out.println("Resultado");

        int totalPares = buscarPares(vetor, alvo);

        if (totalPares == 0) {
            System.out.println("Nenhum par com soma igual a " + alvo + " foi encontrado");
        } else {
            System.out.println("Quantidade total de pares encontrados: " + totalPares);
        }

        scanner.close();
    }
}
