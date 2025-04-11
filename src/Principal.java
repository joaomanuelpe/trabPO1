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
                    "InserçãoDireta", "InsercaoBinaria", "Seleção", "Bolha", "Shake", "Shell",
                    "Heap", "QuickSemPivo", "QuickComPivo", "Merge1", "Merge2",
                    "Counting", "Bucket", "Radix", "Comb", "Gnome", "Tim"
            };

            TabelaArquivo table = new TabelaArquivo();
            long compEq = -1, movEq = -1;
            for (String metodo : metodos) {
                table.escreveNomeMetodo(metodo);

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
                        case "InserçãoDireta" -> {
                            arq.insertionSortArquivo();
                            if(i == 0) {
                                compEq = arq.getComp() - 1;
                                movEq = 3*(arq.getMov()-1);
                            } else if(i == 1) {
                                compEq = (long)((Math.pow(arq.getComp(),2) + arq.getComp() - 4)/4);
                                movEq = (long)((Math.pow(arq.getMov(),2) + 3*arq.getComp() - 4)/2);
                            } else {
                                compEq = (long)((Math.pow(arq.getComp(),2) + arq.getComp() - 2)/4);
                                movEq = (long)((Math.pow(arq.getMov(),2) + 9*arq.getComp() - 10)/4);
                            }

                        }
                        case "InsercaoBinaria" -> {
                            arq.insercaoBinariaArq();
                            compEq = (long)(arq.getComp() * (Math.log(arq.getComp()) - Math.log(2.71828) + 0.5));
                            if(i == 0) {
                                movEq = 3*(arq.getMov()-1);
                            } else if(i == 1) {
                                movEq = (long)((Math.pow(arq.getMov(),2) + 3*arq.getComp() - 4)/2);
                            } else {
                                movEq = (long)((Math.pow(arq.getMov(),2) + 9*arq.getComp() - 10)/4);
                            }
                        }
                        case "Seleção" -> {
                            arq.selectionSortArquivo();
                            compEq = (long)((Math.pow(arq.getComp(),2) - arq.getComp())/2);
                            if(i == 0) {
                                movEq = 3*(arq.getMov()-1);
                            } else if(i == 1) {
                                movEq = (long)(Math.pow(arq.getMov(),2)/4 + 3*(arq.getMov()-1));
                            } else {
                                movEq = (long)(arq.getMov()*(Math.log(arq.getMov()) + 0.577216));
                            }
                        }
                        case "Bolha" -> {
                            arq.bolhaArquivo();
                            compEq =  (long)((Math.pow(arq.getComp(),2) - arq.getComp())/2);
                            if(i == 0) {
                                movEq = 0;
                            } else if(i == 1) {
                                movEq = (long)(3 * (Math.pow(arq.getMov(),2) - arq.getMov())/4);
                            } else {
                                movEq = (long)(3*(Math.pow(arq.getMov(),2) - arq.getMov())/2);
                            }
                        }
                        case "Shake" -> {
                            arq.shakeArquivo();
                            compEq =  (long)((Math.pow(arq.getComp(),2) - arq.getComp())/2);
                            if(i == 0) {
                                movEq = 0;
                            } else if(i == 1) {
                                movEq = (long)(3 * (Math.pow(arq.getMov(),2) - arq.getMov())/4);
                            } else {
                                movEq = (long)(3*(Math.pow(arq.getMov(),2) - arq.getMov())/2);
                            }
                        }
                        case "Shell" -> {
                            arq.shellArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "Heap" -> {
                            arq.heapSortArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "QuickSemPivo" -> {
                            arq.QuickSemPivoArq();
                            compEq = -1; movEq = -1;

                        }
                        case "QuickComPivo" -> {
                            arq.QuickComPivoArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "Merge1" -> {
                            arq.merge1Arq();
                            compEq = -1; movEq = -1;
                        }
                        case "Merge2" -> {
                            arq.mergeSort2ImpArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "Counting" -> {
                            arq.countingSort();
                            compEq = -1; movEq = -1;
                        }
                        case "Bucket" -> {
                            arq.bucketSortArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "Radix" -> {
                            arq.radixSort();
                            compEq = -1; movEq = -1;
                        }
                        case "Comb" -> {
                            arq.combSortArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "Gnome" -> {
                            arq.gnomeSortArquivo();
                            compEq = -1; movEq = -1;
                        }
                        case "Tim" -> {
                            arq.timSortArquivo();
                            compEq = -1; movEq = -1;
                        }
                    }
                    long fim = System.currentTimeMillis();
                    int tempo = (int)(fim - inicio);
                    table.escreverLinhaTabela(
                            arq.getComp(),  // Comparações programadas
                            (int)compEq,              // Comparações equação (placeholder)
                            arq.getMov(),   // Movimentações programadas
                            (int)movEq,              // Movimentações equação (placeholder)
                            tempo,          // Tempo em milissegundos
                            i == 2          // True if it's the last column (random file)
                    );
                    System.out.printf(
                            "%-15s | Comparações: %-8d | Comp. Eq.: %d | Movimentações: %-8d | Mov.Eq.: %d | Ordenado: %-5b | Tempo: %dms%n" ,
                            metodo,
                            arq.getComp(),
                            compEq,
                            arq.getMov(),
                            movEq,
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