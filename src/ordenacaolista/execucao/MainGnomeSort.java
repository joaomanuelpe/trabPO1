package ordenacaolista.execucao;

import ordenacaolista.ListaEncadeada;

import java.util.Scanner;

public class MainGnomeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEncadeada inicio = new ListaEncadeada();
        System.out.println("---GNOME SORT---");
        for (int i = 0; i < 10; i++)
            inicio.insereListaFila(scanner.nextInt());
        inicio.imprimeLista();
        System.out.println("\n---ORDENANDO COM GNOME SORT---");
        inicio.gnomeSort();
        System.out.println("---LISTA ORDENADA APÓS O GNOME SORT---");
        inicio.imprimeLista();
    }
}
