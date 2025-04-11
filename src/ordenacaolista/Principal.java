package ordenacaolista;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Principal {
    public static void main(String[] args) {

        ListaEncadeada lista = new ListaEncadeada(null, null);
        // Criar uma lista de números de 1 a 1000
        List<Integer> numeros = new ArrayList<>();
        Instant ini = Instant.now();
        for (int i = 1; i <= 700; i++) {
            numeros.add(i);
        }

        // Embaralhar os números para ficarem desordenados
        Collections.shuffle(numeros);

        // Inserir os números na lista personalizada
        for (int num : numeros) {
            lista.inserirNoIni(num);
        }

        lista.exibir();
        lista.combSort();
        System.out.println();
        System.out.println();
        lista.exibir();

        /*try {
            Arquivo baseArquivo = new Arquivo("dadosBase.bin");
            baseArquivo.geraArquivoRandomico();

            String[] metodos = {
                    "Bolha", "Shake", "Seleção", "Inserção", "Shell", "QuickSemPivo",
                    "QuickComPivo", "InsercaoBinaria", "Merge1", "Merge2", "Counting",
                    "Radix", "Gnome", "Tim", "Comb", "Bucket", "Heap"
            };

            for (String metodo : metodos) {
                Arquivo arq = new Arquivo("temp.bin");
                arq.truncate(0);
                arq.copiaArquivo(baseArquivo.getFile());

                arq.initComp();
                arq.initMov();

                long inicio = System.currentTimeMillis();

                switch (metodo) {
                    case "Bolha" -> arq.bolhaArquivo();
                    case "Shake" -> arq.shakeArquivo();
                    case "Seleção" -> arq.selectionSortArquivo();
                    case "Inserção" -> arq.insertionSortArquivo();
                    case "Shell" -> arq.shellArquivo();
                    case "QuickSemPivo" -> arq.QuickSemPivoArq();
                    case "QuickComPivo" -> arq.QuickComPivoArquivo();
                    case "InsercaoBinaria" -> arq.insercaoBinariaArq();
                    case "Merge1" -> arq.merge1Arq();
                    case "Merge2" -> arq.mergeSort2Imp();
                    case "Counting" -> arq.countingSort();
                    case "Radix" -> arq.radixSort();
                    case "Gnome" -> arq.gnomeSortArquivo();
                    case "Tim" -> arq.timSortArquivo();
                    case "Comb" -> arq.combSortArquivo();
                    case "Bucket" -> arq.bucketSortArquivo();
                    case "Heap" -> arq.heapSortArquivo();
                }

                long fim = System.currentTimeMillis();

                System.out.printf(
                        "%-15s | Comparações: %-6d | Movimentações: %-6d | Ordenado: %-5b | Tempo: %dms%n",
                        metodo,
                        arq.getComp(),
                        arq.getMov(),
                        arq.estaOrdenado(),
                        (fim - inicio)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
