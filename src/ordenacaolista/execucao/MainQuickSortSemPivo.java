package ordenacaolista.execucao;

import ordenacaolista.ListaEncadeada;
import ordenacaolista.objetos.No;

import java.util.Scanner;

public class MainQuickSortSemPivo
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaEncadeada lista = new ListaEncadeada();
        System.out.println("---QUICK SORT SEM PIVÔ---");
        for (int i = 0; i < 10; i++) {
            lista.insereListaFila(sc.nextInt());
        }
        System.out.print("CONTEÚDO DA LISTA: ");
        lista.imprimeLista();
        System.out.println("\nLISTA APÓS O QUICK SORT SEM PIVÔ:");
        No fim = lista.getInicio();
        while(fim.getProx() != null)
            fim = fim.getProx();
        lista.quickSortSemPivo(lista.getInicio(), fim);
        lista.imprimeLista();
    }
}
