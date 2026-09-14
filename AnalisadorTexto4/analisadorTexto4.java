import java.util.Scanner;

public class AnalisadorTexto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma frase:");
        String frase = scanner.nextLine();

        System.out.println("Caracteres (total): " + contarCaracteres(frase));
        System.out.println("Letras: " + contarLetras(frase));
        System.out.println("Vogais: " + contarVogais(frase));
        System.out.println("Consoantes: " + contarConsoantes(frase));
        System.out.println("Algarismos: " + contarAlgarismos(frase));
        System.out.println("Espacos: " + contarEspacos(frase));
        System.out.println("Outros: " + contarOutros(frase));
        System.out.println("Palavras: " + contarPalavras(frase));
        System.out.println("Maior palavra: " + maiorPalavra(frase));
        System.out.println("Frequencia de 'a': " + frequenciaLetra(frase, 'a'));
        System.out.println("E palindromo? " + ehPalindromo(frase));

        scanner.close();
    }

    public static int contarCaracteres(String frase) {
        return frase.length();
    }

    public static int contarLetras(String frase) {
        int letrasContadas = 0;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (Character.isLetter(c)) {
                letrasContadas++;
            }
        }
        return letrasContadas;
    }

    public static int contarVogais(String frase) {
        int vogaisContadas = 0;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (Character.toLowerCase(c) == 'a' || Character.toLowerCase(c) == 'e'
                    || Character.toLowerCase(c) == 'i' || Character.toLowerCase(c) == 'o'
                    || Character.toLowerCase(c) == 'u') {
                vogaisContadas++;
            }
        }
        return vogaisContadas;
    }

    public static int contarConsoantes(String frase) {
        int consoantesContadas = 0;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (Character.isLetter(c) && !(Character.toLowerCase(c) == 'a' || Character.toLowerCase(c) == 'e'
                    || Character.toLowerCase(c) == 'i' || Character.toLowerCase(c) == 'o'
                    || Character.toLowerCase(c) == 'u')) {
                consoantesContadas++;
            }
        }
        return consoantesContadas;
    }

    public static int contarAlgarismos(String frase) {
        int algarismosContados = 0;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (Character.isDigit(c)) {
                algarismosContados++;
            }
        }
        return algarismosContados;
    }

    public static int contarEspacos(String frase) {
        int espacosContados = 0;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (Character.isWhitespace(c)) {
                espacosContados++;
            }
        }
        return espacosContados;
    }

    public static int contarOutros(String frase) {
        int outrosContados = 0;
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && !Character.isWhitespace(c)) {
                outrosContados++;
            }
        }
        return outrosContados;
    }

    public static int contarPalavras(String frase) {
        int palavrasContadas = 0;
        boolean dentroDeUmaPalavra = false;

        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);

            if (!Character.isWhitespace(c) && !dentroDeUmaPalavra) {
                palavrasContadas++;
                dentroDeUmaPalavra = true;
            } else if (Character.isWhitespace(c)) {
                dentroDeUmaPalavra = false;
            }
        }
        return palavrasContadas;
    }

    public static String maiorPalavra(String frase) {
        String palavraAtual = "";
        String maior = "";
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (!Character.isWhitespace(c)) {
                palavraAtual += c;
                if (palavraAtual.length() > maior.length()) {
                    maior = palavraAtual;
                }
            } else {
                palavraAtual = "";
            }
        }
        return maior;
    }

    public static int frequenciaLetra(String frase, char letra) {
        int contagem = 0;
        for (int i = 0; i < frase.length(); i++) {
            if (Character.toLowerCase(frase.charAt(i)) == Character.toLowerCase(letra)) {
                contagem++;
            }
        }
        return contagem;
    }

    public static boolean ehPalindromo(String frase) {
        String limpa = frase.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return limpa.equals(new StringBuilder(limpa).reverse().toString());
    }
}