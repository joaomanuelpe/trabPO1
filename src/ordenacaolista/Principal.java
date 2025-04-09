package ordenacaolista;

import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        try {
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
        }
    }
}
