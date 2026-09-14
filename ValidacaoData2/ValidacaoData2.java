import java.util.Scanner;

public class Main {

    public static boolean isBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    public static int diasNoMes(int mes, int ano) {
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (mes < 1 || mes > 12) {
            return -1;
        }

        if (mes == 2 && isBissexto(ano)) {
            return 29;
        }

        return dias[mes - 1];
    }

    public static boolean validarData(int dia, int mes, int ano) {
        if (ano <= 0) {
            return false;
        }

        if (mes < 1 || mes > 12) {
            return false;
        }

        int maxDias = diasNoMes(mes, ano);

        if (dia < 1 || dia > maxDias) {
            return false;
        }

        return true;
    }

    public static int calcularTrimestre(int mes) {
        return (mes - 1) / 3 + 1;
    }

    public static int calcularPosicaoNoAno(int dia, int mes, int ano) {
        int posicao = dia;

        for (int i = 1; i < mes; i++) {
            posicao += diasNoMes(i, ano);
        }

        return posicao;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o dia: ");
        int dia = scanner.nextInt();

        System.out.print("Digite o mês: ");
        int mes = scanner.nextInt();

        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();

        boolean valida = validarData(dia, mes, ano);

        System.out.println("Resultado");

        if (valida) {
            int trimestre = calcularTrimestre(mes);
            int posicao = calcularPosicaoNoAno(dia, mes, ano);

            System.out.println("Data válida: " + dia + "/" + mes + "/" + ano);
            System.out.println("Ano bissexto: " + (isBissexto(ano) ? "Sim" : "Não"));
            System.out.println("Trimestre: " + trimestre + "º trimestre");
            System.out.println("Posição no ano: " + posicao + "º dia do ano");
        } else {
            System.out.println("Data inválida: " + dia + "/" + mes + "/" + ano);
        }

        scanner.close();
    }
}