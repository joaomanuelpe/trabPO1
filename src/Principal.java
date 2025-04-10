public class Principal {
    public static void main(String[] args) {
        try {
            Arquivo aleatorioArquivo = new Arquivo("dadosBase.bin");
            aleatorioArquivo.geraArquivoRandomico();
            Arquivo ordArquivo = new Arquivo("dadosOrdenados.bin");
            ordArquivo.geraArquivoOrdenado();
            Arquivo reverseArquivo = new Arquivo("dadosReversos.bin");
            reverseArquivo.geraArquivoReverso();

            String[] metodos = {
                    "Radix", "Shake", "Seleção", "Inserção", "Shell", "QuickSemPivo",
                    "QuickComPivo", "InsercaoBinaria", "Merge1", "Tim", "Counting",
                    "Merge2", "Gnome", "Bucket", "Comb", "Bolha", "Heap"
            };

            // Create table generator
            TableGenerator table = new TableGenerator();

            // Process each method
            for (String metodo : metodos) {
                table.writeMethodName(metodo);

                // Process for each type of file (ordered, reverse, random)
                Arquivo[] arquivos = {ordArquivo, reverseArquivo, aleatorioArquivo};

                for (int i = 0; i < arquivos.length; i++) {
                    Arquivo arq = new Arquivo("temp.bin");
                    arq.truncate(0);
                    arq.copiaArquivo(arquivos[i].getFile());
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
                        case "Merge2" -> arq.mergeSort2ImpArquivo();
                        case "Counting" -> arq.countingSort();
                        case "Radix" -> arq.radixSort();
                        case "Gnome" -> arq.gnomeSortArquivo();
                        case "Tim" -> arq.timSortArquivo();
                        case "Comb" -> arq.combSortArquivo();
                        case "Bucket" -> arq.bucketSortArquivo();
                        case "Heap" -> arq.heapSortArquivo();
                    }

                    long fim = System.currentTimeMillis();
                    int tempo = (int)(fim - inicio);

                    // Write to table (last parameter is true if it's the last column)
                    table.writeTableRow(
                            arq.getComp(),  // Comparações programadas
                            0,              // Comparações equação (placeholder)
                            arq.getMov(),   // Movimentações programadas
                            0,              // Movimentações equação (placeholder)
                            tempo,          // Tempo em milissegundos
                            i == 2          // True if it's the last column (random file)
                    );
                    System.out.printf(
                            "%-15s | Comparações: %-8d | Movimentações: %-8d | Ordenado: %-5b | Tempo: %dms%n",
                            metodo,
                            arq.getComp(),
                            arq.getMov(),
                            arq.estaOrdenado(),
                            tempo
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}