import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        ArvoreRubroNegra arvoreRubroNegra = new ArvoreRubroNegra();

        try (BufferedReader br = new BufferedReader(new FileReader("dados100_mil.txt"))) {
            String linha;
            long inicioAVL = System.currentTimeMillis();
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    try {
                        int numero = Integer.parseInt(linha);
                        arvoreAVL.inserir(numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Linha inválida ignorada: " + linha);
                    }
                }
            }
            long fimAVL = System.currentTimeMillis();

            BufferedReader br2 = new BufferedReader(new FileReader("dados100_mil.txt"));

            long inicioRubroNegra = System.currentTimeMillis();
            while ((linha = br2.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    try {
                        int numero = Integer.parseInt(linha);
                        arvoreRubroNegra.inserir(numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Linha inválida ignorada: " + linha);
                    }
                }
            }
            long fimRubroNegra = System.currentTimeMillis();
            br2.close();

            System.out.println("Tempo para preencher a Árvore AVL: " + (fimAVL - inicioAVL) + " ms");
            System.out.println("Tempo para preencher a Árvore Rubro-Negra: " + (fimRubroNegra - inicioRubroNegra) + " ms");

            Random random = new Random();
            for (int i = 0; i < 50000; i++) {
                int sorteado = random.nextInt(19999) - 9999;
                if (sorteado % 3 == 0) {
                    arvoreAVL.inserir(sorteado);
                    arvoreRubroNegra.inserir(sorteado);
                } else if (sorteado % 5 == 0) {

                } else {

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}