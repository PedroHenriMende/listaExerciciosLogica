import java.util.Scanner;

public class Main {

    public static boolean isPositivo(int numero) {
        return numero > 0;
    }

    public static boolean isNegativo(int numero) {
        return numero < 0;
    }

    public static boolean isPar(int numero) {
        return numero % 2 == 0;
    }

    public static boolean isMultiploDeTres(int numero) {
        return numero % 3 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantidade = 0;
        int soma = 0;
        int maior = Integer.MIN_VALUE;
        int menor = Integer.MAX_VALUE;
        int positivos = 0;
        int negativos = 0;
        int pares = 0;
        int impares = 0;
        int multiplosDeTres = 0;

        System.out.print("Digite um número ou 0 para encerrar: ");
        int numero = scanner.nextInt();

        if (numero == 0) {
            System.out.println("Nenhum valor foi informado, Encerrando sem cálculos.");
            scanner.close();
            return;
        }

        while (numero != 0) {
            quantidade++;
            soma += numero;

            if (numero > maior) {
                maior = numero;
            }

            if (numero < menor) {
                menor = numero;
            }

            if (isPositivo(numero)) {
                positivos++;
            } else if (isNegativo(numero)) {
                negativos++;
            }

            if (isPar(numero)) {
                pares++;
            } else {
                impares++;
            }

            if (isMultiploDeTres(numero)) {
                multiplosDeTres++;
            }

            System.out.print("Digite um número ou 0 para encerrar: ");
            numero = scanner.nextInt();
        }

        double media = (double) soma / quantidade;

        System.out.println("Resultado");
        System.out.println("Quantidade de números digitados: " + quantidade);
        System.out.println("Soma dos valores: " + soma);
        System.out.printf("Média geral: %.2f%n", media);
        System.out.println("Maior valor: " + maior);
        System.out.println("Menor valor: " + menor);
        System.out.println("Quantidade de positivos: " + positivos);
        System.out.println("Quantidade de negativos: " + negativos);
        System.out.println("Quantidade de pares: " + pares);
        System.out.println("Quantidade de ímpares: " + impares);
        System.out.println("Quantidade de múltiplos de três: " + multiplosDeTres);

        scanner.close();
    }
}