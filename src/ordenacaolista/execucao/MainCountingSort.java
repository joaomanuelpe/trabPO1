package ordenacaolista.execucao;

import ordenacaolista.ListaEncadeada;

import java.util.Scanner;

public class MainCountingSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaEncadeada lista = new ListaEncadeada();
        System.out.println("---COUNTING SORT---");
        for (int i = 0; i < 10; i++) {
            lista.insereListaFila(sc.nextInt());
        }
        System.out.println("\n---LISTA ANTES DO COUNTING SORT---");
        lista.imprimeLista();
        System.out.println("\n---LISTA APÓS O COUNTING SORT---");
        ListaEncadeada listaOrd = lista.countingSort();
        listaOrd.imprimeLista();
    }
}
