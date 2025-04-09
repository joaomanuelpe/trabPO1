package ordenacaolista.execucao;

import ordenacaolista.ListaEncadeada;

import java.util.Scanner;

public class MainInsertionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEncadeada lista = new ListaEncadeada();

        System.out.println("---INSERTION SORT---");
        for (int i = 0; i < 10; i++) {
            lista.insereListaFila(scanner.nextInt());
        }
        System.out.println("---LISTA ANTES DO INSERTION SORT---");
        lista.imprimeLista();
        System.out.println("\n---LISTA APÓS O INSERTION SORT---");
        lista.insertionSort();
        lista.imprimeLista();
    }
}
