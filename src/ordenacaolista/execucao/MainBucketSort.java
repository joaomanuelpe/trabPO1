package ordenacaolista.execucao;

import ordenacaolista.ListaEncadeadaFloat;

import java.util.Scanner;

public class MainBucketSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEncadeadaFloat inicio = new ListaEncadeadaFloat();
        System.out.println("---BUCKET SORT---");
        for (int i = 0; i < 10; i++)
            inicio.insereListaFila(scanner.nextFloat());
        inicio.imprimeLista();
        System.out.println("\n---ORDENANDO COM BUCKET SORT---");
        inicio.bucketSort();
        System.out.println("---LISTA ORDENADA COM BUCKET SORT---");
        inicio.imprimeLista();
    }
}
