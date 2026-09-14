import java.util.Scanner;

public class calculadora {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        executar();
        sc.close();
    }

    public static void executar(Scanner scannerCompartilhado) {
        sc = scannerCompartilhado;
        executar();
    }

    static void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1: soma(); break;
                case 2: subtracao(); break;
                case 3: multiplicacao(); break;
                case 4: divisao(); break;
                case 5: potenciacao(); break;
                case 6: restoDivisao(); break;
                case 7: System.out.println("Encerrando..."); break;
                default: System.out.println("Opcao invalida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 7);
    }

    static void exibirMenu() {
        System.out.println("------ CALCULADORA ------");
        System.out.println("1. Soma");
        System.out.println("2. Subtracao");
        System.out.println("3. Multiplicacao");
        System.out.println("4. Divisao");
        System.out.println("5. Potenciacao");
        System.out.println("6. Resto da divisao");
        System.out.println("7. Encerrar");
    }

    // Le um inteiro validando a entrada para nao quebrar o programa
    static int lerInteiro(String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                return valor;
            } else {
                System.out.println("Entrada invalida! Digite um numero inteiro.");
                sc.next();
            }
        }
    }

    static double lerDouble(String mensagem) {
        double valor;
        while (true) {
            System.out.print(mensagem);
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                return valor;
            } else {
                System.out.println("Entrada invalida! Digite um numero.");
                sc.next();
            }
        }
    }

    static void soma() {
        double a = lerDouble("Digite o primeiro valor: ");
        double b = lerDouble("Digite o segundo valor: ");
        System.out.println("Resultado: " + (a + b));
    }

    static void subtracao() {
        double a = lerDouble("Digite o primeiro valor: ");
        double b = lerDouble("Digite o segundo valor: ");
        System.out.println("Resultado: " + (a - b));
    }

    static void multiplicacao() {
        double a = lerDouble("Digite o primeiro valor: ");
        double b = lerDouble("Digite o segundo valor: ");
        System.out.println("Resultado: " + (a * b));
    }

    static void divisao() {
        double a = lerDouble("Digite o dividendo: ");
        double b = lerDouble("Digite o divisor: ");
        if (b == 0) {
            System.out.println("Erro algo de errado na divisao por zero nao e permitida.");
        } else {
            System.out.println("Resultado: " + (a / b));
        }
    }

    static void potenciacao() {
        double base = lerDouble("Digite a base: ");
        double expoente = lerDouble("Digite o expoente: ");
        System.out.println("Resultado: " + Math.pow(base, expoente));
    }

    static void restoDivisao() {
        int a = lerInteiro("Digite o dividendo (inteiro): ");
        int b = lerInteiro("Digite o divisor (inteiro): ");
        if (b == 0) {
            System.out.println("Erro algo de errado na divisao por zero nao e permitida.");
        } else {
            System.out.println("Resto: " + (a % b));
        }
    }
}