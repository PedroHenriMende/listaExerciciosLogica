import java.util.Scanner;

public class Main {

    public static double calcularMedia(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 3.0;
    }

    public static String determinarSituacao(double media, double frequencia) {
        String situacao;

        if (frequencia < 75) {
            situacao = "Reprovado por frequência";
        } else if (media >= 7) {
            situacao = "Aprovado";
        } else if (media >= 5) {
            situacao = "Recuperação";
        } else {
            situacao = "Reprovado por nota";
        }

        return situacao;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do estudante: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a primeira nota (0 a 10): ");
        double nota1 = scanner.nextDouble();

        System.out.print("Digite a segunda nota (0 a 10): ");
        double nota2 = scanner.nextDouble();

        System.out.print("Digite a terceira nota (0 a 10): ");
        double nota3 = scanner.nextDouble();

        System.out.print("Digite o percentual de frequência (0 a 100): ");
        double frequencia = scanner.nextDouble();

        double media = calcularMedia(nota1, nota2, nota3);
        String situacao = determinarSituacao(media, frequencia);

        System.out.println("Resultado");
        System.out.println("Nome: " + nome);
        System.out.printf("Média: %.2f%n", media);
        System.out.println("Frequência: " + frequencia + "%");
        System.out.println("Situação: " + situacao);

        scanner.close();
    }
}
