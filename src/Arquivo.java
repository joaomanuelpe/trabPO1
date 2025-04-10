import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public Arquivo(String nomearquivo) {
        try {
            this.nomearquivo = nomearquivo;
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem) {
        try {
            arquivo.seek(0);
            arquivoOrigem.seek(0);
            byte[] buffer = new byte[Registro.length()];
            while (arquivoOrigem.getFilePointer() < arquivoOrigem.length()) {
                arquivoOrigem.read(buffer);
                arquivo.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RandomAccessFile getFile() {
        return arquivo;
    }

    public void truncate(long pos) {
        try {
            arquivo.setLength(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean eof() {
        try {
            return arquivo.getFilePointer() >= arquivo.length();
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int filesize() {
        try {
            return (int) (arquivo.length() / Registro.length());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void initComp() {
        comp = 0;
    }

    public void initMov() {
        mov = 0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public boolean estaOrdenado() {
        Registro atual = new Registro(0);
        Registro anterior = new Registro(0);
        int TL = filesize();
        boolean ordenado = true;
        if (TL <= 1) {
            return true;
        }
        seekArq(0);
        anterior.leDoArq(arquivo);

        for (int i = 1; i < TL && ordenado; i++) {
            atual.leDoArq(arquivo);
            if (anterior.getNumero() > atual.getNumero()) {
                ordenado = false;
            }
            anterior.setNumero(atual.getNumero());
        }
        return ordenado;
    }

    public int busca_binaria(int chave, int TL) {
        int ini = 0, fim = TL - 1, meio;
        Registro reg1 = new Registro(0);

        while (ini <= fim) {
            meio = (ini + fim) / 2;
            seekArq(meio);
            reg1.leDoArq(arquivo);
            comp++;
            if (chave < reg1.getNumero()) {
                fim = meio - 1;
            } else {
                ini = meio + 1;
            }
        }
        return ini;
    }

    public void insercaoBinariaArq() {
        int aux, pos;
        Registro reg1 = new Registro(0);
        Registro reg2 = new Registro(0);
        for (int i = 1; i < filesize(); i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            aux = reg1.getNumero();
            pos = busca_binaria(aux, i);
            for (int j = i; j > pos; j--) {
                seekArq(j - 1);
                reg2.leDoArq(arquivo);
                seekArq(j);
                reg2.gravaNoArq(arquivo);
                mov++;
            }
            seekArq(pos);
            reg1.setNumero(aux);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }


    public void selectionSortArquivo() {
        Registro reg1 = new Registro(0);
        Registro reg2 = new Registro(0);
        int i = 0, j, posMenor = 0, TL = filesize(), aux;

        while (i < TL - 1) {
            j = i;
            posMenor = j + 1;
            while (j < TL) {
                seekArq(j);
                reg1.leDoArq(arquivo);
                seekArq(posMenor);
                reg2.leDoArq(arquivo);
                comp++;
                if (reg1.getNumero() < reg2.getNumero()) {
                    posMenor = j;
                }
                j++;
            }
            seekArq(i);
            reg1.leDoArq(arquivo);
            aux = reg1.getNumero();
            seekArq(posMenor);
            reg2.leDoArq(arquivo);
            seekArq(i);
            reg2.gravaNoArq(arquivo); mov++;
            seekArq(posMenor);
            reg1.setNumero(aux);
            reg1.gravaNoArq(arquivo); mov++;
            i++;
        }
    }

    public void insertionSortArquivo() {
        int TL = filesize();
        Registro atual = new Registro(0);
        Registro anterior = new Registro(0);

        for (int i = 1; i < TL; i++) {
            seekArq(i);
            atual.leDoArq(arquivo);
            int chave = atual.getNumero();
            int j = i - 1;
            boolean parar = false;

            while (j >= 0 && !parar) {
                seekArq(j);
                anterior.leDoArq(arquivo);
                comp++;
                if (anterior.getNumero() > chave) {
                    seekArq(j + 1);
                    anterior.gravaNoArq(arquivo); mov++;
                    j--;
                } else {
                    parar = true;
                }
            }

            seekArq(j + 1);
            atual.setNumero(chave);
            atual.gravaNoArq(arquivo); mov++;
        }
    }

    public void bolhaArquivo() {
        boolean flag = false;
        int TL2 = filesize(), i;
        Registro regi = new Registro(0), regj = new Registro(0);
        while (TL2 > 1 && !flag) {
            flag = true;
            for (i = 0; i < TL2 - 1; i++) {
                seekArq(i);
                regi.leDoArq(arquivo);
                seekArq(i + 1);
                regj.leDoArq(arquivo);
                comp++;
                if (regi.getNumero() > regj.getNumero()) {
                    flag = false;
                    seekArq(i);
                    regj.gravaNoArq(arquivo); mov++;
                    seekArq(i + 1);
                    regi.gravaNoArq(arquivo); mov++;
                }
            }
            TL2--;
        }
    }

    public void shakeArquivo() {
        int inicio = 0, fim = filesize() - 1;
        boolean trocou = true;
        Registro r1 = new Registro(0), r2 = new Registro(0);

        while (inicio < fim && trocou) {
            trocou = false;
            for (int i = inicio; i < fim; i++) {
                seekArq(i);
                r1.leDoArq(arquivo);
                seekArq(i + 1);
                r2.leDoArq(arquivo);
                comp++;
                if (r1.getNumero() > r2.getNumero()) {
                    seekArq(i);
                    r2.gravaNoArq(arquivo); mov++;
                    seekArq(i + 1);
                    r1.gravaNoArq(arquivo); mov++;
                    trocou = true;
                }
            }
            fim--;
            for (int i = fim; i > inicio; i--) {
                seekArq(i - 1);
                r1.leDoArq(arquivo);
                seekArq(i);
                r2.leDoArq(arquivo);
                comp++;
                if (r1.getNumero() > r2.getNumero()) {
                    seekArq(i - 1);
                    r2.gravaNoArq(arquivo); mov++;
                    seekArq(i);
                    r1.gravaNoArq(arquivo); mov++;
                    trocou = true;
                }
            }
            inicio++;
        }
    }

    public void shellArquivo() {
        int TL = filesize();
        Registro atual = new Registro(0), anterior = new Registro(0);
        int dist = 1;

        while (dist < TL / 2) {
            dist = dist * 2 + 1;
        }

        while (dist > 0) {
            for (int i = dist; i < TL; i++) {
                seekArq(i);
                atual.leDoArq(arquivo);
                int valor = atual.getNumero();
                int j = i;

                seekArq(j - dist);
                anterior.leDoArq(arquivo);
                comp++;
                while (j >= dist && valor < anterior.getNumero()) {
                    seekArq(j);
                    anterior.gravaNoArq(arquivo); mov++;
                    j -= dist;
                    if (j >= dist) {
                        seekArq(j - dist);
                        anterior.leDoArq(arquivo);
                        comp++;
                    }
                }

                seekArq(j);
                atual.setNumero(valor);
                atual.gravaNoArq(arquivo); mov++;
            }
            dist /= 2;
        }
    }


    public void QuickSemPivoArq() {
        quickSemPivoArquivo(0, filesize() - 1);
    }

    public void quickSemPivoArquivo(int ini, int fim) {
        int i = ini, j = fim;
        boolean flag = true;
        Registro regi = new Registro(0);
        Registro regj = new Registro(0);

        while (i < j) {
            seekArq(j);
            regj.leDoArq(arquivo);
            seekArq(i);
            regi.leDoArq(arquivo);
            comp++;

            if (flag) {
                while (i < j && regi.getNumero() <= regj.getNumero()) {
                    i++;
                    seekArq(i);
                    regi.leDoArq(arquivo);
                    comp++;
                }
            } else {
                while (i < j && regj.getNumero() >= regi.getNumero()) {
                    j--;
                    seekArq(j);
                    regj.leDoArq(arquivo);
                    comp++;
                }
            }

            if (i < j) {
                seekArq(i);
                regj.gravaNoArq(arquivo); mov++;
                seekArq(j);
                regi.gravaNoArq(arquivo); mov++;
                flag = !flag;
            }
        }

        if (ini < i - 1)
            quickSemPivoArquivo(ini, i - 1);
        if (j + 1 < fim)
            quickSemPivoArquivo(j + 1, fim);
    }


    public void QuickComPivoArquivo() {
        quickComPivoArq(0, filesize() - 1);
    }

    public void quickComPivoArq(int ini, int fim) {
        int i = ini, j = fim, pivo;
        Registro regi = new Registro(0);
        Registro regj = new Registro(0);

        seekArq(ini);
        regi.leDoArq(arquivo);
        seekArq(fim);
        regj.leDoArq(arquivo);
        pivo = (regi.getNumero() + regj.getNumero()) / 2;

        while (i <= j) {
            seekArq(i);
            regi.leDoArq(arquivo);
            comp++;
            while (regi.getNumero() < pivo && i <= fim) {
                i++;
                if (i <= fim) {
                    seekArq(i);
                    regi.leDoArq(arquivo);
                    comp++;
                }
            }

            seekArq(j);
            regj.leDoArq(arquivo);
            comp++;
            while (regj.getNumero() > pivo && j >= ini) {
                j--;
                if (j >= ini) {
                    seekArq(j);
                    regj.leDoArq(arquivo);
                    comp++;
                }
            }

            if (i <= j) {
                seekArq(i);
                regi.leDoArq(arquivo);
                seekArq(j);
                regj.leDoArq(arquivo);

                seekArq(i);
                regj.gravaNoArq(arquivo); mov++;
                seekArq(j);
                regi.gravaNoArq(arquivo); mov++;
                i++;
                j--;
            }
        }

        if (ini < j)
            quickComPivoArq(ini, j);
        if (i < fim)
            quickComPivoArq(i, fim);
    }

    public void particao(Arquivo arq1, Arquivo arq2) {
        Registro reg = new Registro(0);
        int meio = filesize()/2;
        seekArq(0);
        for (int i = 0; i < meio; i++) {
            reg.leDoArq(arquivo);
            reg.gravaNoArq(arq1.getFile()); mov++;
            seekArq(i + meio);
            reg.leDoArq(arquivo);
            reg.gravaNoArq(arq2.getFile()); mov++;
            seekArq(i);
        }
    }

    public void fusao(Arquivo arq1, Arquivo arq2, int seq) {
        int aux_seq = seq, i = 0, j = 0, k = 0, TL = filesize();

        Registro reg1 = new Registro(0);
        Registro reg2 = new Registro(0);

        while(k < TL) {
            while(i < seq && j < seq) {
                seekArq(i);
                reg1.leDoArq(arq1.getFile());
                seekArq(j);
                reg2.leDoArq(arq2.getFile());
                comp++;
                if(reg1.getNumero() < reg2.getNumero()) {
                    seekArq(k);
                    reg1.gravaNoArq(arquivo); mov++;
                    k++;i++;
                }
                else {
                    seekArq(k);
                    reg2.gravaNoArq(arquivo); mov++;
                    k++;j++;
                }
            }
            while (i < seq) {
                seekArq(i);
                reg1.leDoArq(arq1.getFile());
                seekArq(k);
                reg1.gravaNoArq(arquivo); mov++;
                k++;i++;
            }
            while(j < seq) {
                seekArq(j);
                reg2.leDoArq(arq2.getFile());
                seekArq(k);
                reg2.gravaNoArq(arquivo); mov++;
                k++;j++;
            }
            seq+=aux_seq;
        }
    }

    public void merge1Arq() {

        Arquivo arq1 = new Arquivo("src/arquivos/arquivo1.dat");
        Arquivo arq2 = new Arquivo("src/arquivos/arquivo2.dat");
        int seq = 1;

        while(seq < filesize()) {
            particao(arq1, arq2);
            fusao(arq1, arq2, seq);
            seq*=2;
        }
        try {
            arq1.getFile().close();
            File file1 = new File("src/arquivos/arquivo1.dat");
            file1.delete();
            arq2.getFile().close();
            file1 = new File("src/arquivos/arquivo2.dat");
            file1.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void fusaoArquivo(int ini1, int fim1, int ini2, int fim2, Arquivo aux, Arquivo vet) {
        int i = ini1, j = ini2, k = 0;
        Registro regI = new Registro(0), regJ = new Registro(0);
        while (i <= fim1 && j <= fim2) {
            vet.seekArq(i); regI.leDoArq(vet.getFile());
            vet.seekArq(j); regJ.leDoArq(vet.getFile());
            comp++;
            if (regI.getNumero() < regJ.getNumero()) {
                aux.seekArq(k++);
                regI.gravaNoArq(aux.getFile()); mov++;
                i++;
            } else {
                aux.seekArq(k++);
                regJ.gravaNoArq(aux.getFile()); mov++;
                j++;
            }
        }
        while (i <= fim1) {
            vet.seekArq(i++);
            regI.leDoArq(vet.getFile());
            aux.seekArq(k++);
            regI.gravaNoArq(aux.getFile()); mov++;
        }
        while (j <= fim2) {
            vet.seekArq(j++);
            regJ.leDoArq(vet.getFile());
            aux.seekArq(k++);
            regJ.gravaNoArq(aux.getFile()); mov++;
        }
        for (int l = 0; l < k; l++) {
            aux.seekArq(l);
            regI.leDoArq(aux.getFile());
            vet.seekArq(ini1 + l);
            regI.gravaNoArq(vet.getFile()); mov++;
        }
    }

    public void merge2ImpArquivo(int esq, int dir, Arquivo aux, Arquivo vet) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            merge2ImpArquivo(esq, meio, aux, vet);
            merge2ImpArquivo(meio + 1, dir, aux, vet);
            fusaoArquivo(esq, meio, meio + 1, dir, aux, vet);
        }
    }

    public void mergeSort2ImpArquivo() {
        int TL = filesize();
        Registro reg = new Registro(0);

        Arquivo vet = new Arquivo("src/arquivos/mergeVet.dat");
        Arquivo aux = new Arquivo("src/arquivos/mergeAux.dat");

        vet.truncate(0);
        aux.truncate(0);
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet.seekArq(i);
            reg.gravaNoArq(vet.getFile());
        }
        merge2ImpArquivo(0, TL - 1, aux, vet);
        for (int i = 0; i < TL; i++) {
            vet.seekArq(i);
            reg.leDoArq(vet.getFile());
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }

        try {
            vet.getFile().close();
            File file1 = new File("src/arquivos/mergeVet.dat");
            file1.delete();
            aux.getFile().close();
            file1 = new File("src/arquivos/mergeAux.dat");
            file1.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void countingSort() {
        int TL = filesize();
        int[] vet = new int[TL];
        Registro reg = new Registro(0);
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet[i] = reg.getNumero();
        }
        int max = vet[0];
        for (int i = 1; i < TL; i++) {
            if (vet[i] > max) {
                max = vet[i];
            }
        }
        int[] count = new int[max + 1];
        for (int i = 0; i < TL; i++) {
            count[vet[i]]++;
            comp++;
        }
        int k = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                vet[k++] = i;
                count[i]--;
                mov++;
            }
        }
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.setNumero(vet[i]);
            reg.gravaNoArq(arquivo);
        }
    }

    public void countingSortPeloDigito(int[] vet, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int index = (vet[i] / exp) % 10;
            count[index]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int index = (vet[i] / exp) % 10;
            output[count[index] - 1] = vet[i];
            count[index]--;
            mov++;
        }
        for (int i = 0; i < n; i++) {
            vet[i] = output[i];
            mov++;
        }
    }

    public void radixSort() {
        int TL = filesize();
        int[] vet = new int[TL];
        Registro reg = new Registro(0);

        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet[i] = reg.getNumero(); mov++;
        }
        int maior = vet[0];
        for (int i = 1; i < TL; i++) {
            comp++;
            if (vet[i] > maior) {
                maior = vet[i];
            }
        }
        for (int exp = 1; maior / exp > 0; exp *= 10) {
            countingSortPeloDigito(vet, TL, exp);
        }
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.setNumero(vet[i]);
            reg.gravaNoArq(arquivo); mov++;
        }
    }

    public void gnomeSortArquivo() {
        int TL = filesize(), i = 1;

        Registro regAtual = new Registro(0);
        Registro regAnterior = new Registro(0);

        while (i < TL) {
            seekArq(i);
            regAtual.leDoArq(arquivo);
            seekArq(i - 1);
            regAnterior.leDoArq(arquivo);
            comp++;
            if (regAnterior.getNumero() <= regAtual.getNumero()) {
                i++;
            } else {
                int aux = regAnterior.getNumero();
                regAnterior.setNumero(regAtual.getNumero());
                regAtual.setNumero(aux);
                seekArq(i - 1);
                regAnterior.gravaNoArq(arquivo); mov++;
                seekArq(i);
                regAtual.gravaNoArq(arquivo); mov++;
                i--;
                if (i == 0) i = 1;
            }
        }
    }

    public void insertionSortArquivo(int ini, int fim) {
        Registro atual = new Registro(0);
        Registro anterior = new Registro(0);

        for (int i = ini + 1; i <= fim; i++) {
            seekArq(i);
            atual.leDoArq(arquivo);
            int chave = atual.getNumero();
            int j = i - 1;

            while (j >= ini) {
                seekArq(j);
                anterior.leDoArq(arquivo);
                comp++;
                if (anterior.getNumero() > chave) {
                    seekArq(j + 1);
                    anterior.gravaNoArq(arquivo); mov++;
                    j--;
                } else {
                    break;
                }
            }

            seekArq(j + 1);
            atual.setNumero(chave);
            atual.gravaNoArq(arquivo); mov++;
        }
    }

    public void fusaoTimSortArquivo(int ini1, int fim1, int ini2, int fim2, Arquivo aux, Arquivo vet) {
        int i = ini1, j = ini2, k = 0;
        Registro regI = new Registro(0), regJ = new Registro(0);

        while (i <= fim1 && j <= fim2) {
            vet.seekArq(i); regI.leDoArq(vet.getFile());
            vet.seekArq(j); regJ.leDoArq(vet.getFile());
            comp++;
            if (regI.getNumero() < regJ.getNumero()) {
                aux.seekArq(k++);
                regI.gravaNoArq(aux.getFile()); mov++;
                i++;
            } else {
                aux.seekArq(k++);
                regJ.gravaNoArq(aux.getFile()); mov++;
                j++;
            }
        }

        while (i <= fim1) {
            vet.seekArq(i++);
            regI.leDoArq(vet.getFile());
            aux.seekArq(k++);
            regI.gravaNoArq(aux.getFile()); mov++;
        }

        while (j <= fim2) {
            vet.seekArq(j++);
            regJ.leDoArq(vet.getFile());
            aux.seekArq(k++);
            regJ.gravaNoArq(aux.getFile()); mov++;
        }

        for (int l = 0; l < k; l++) {
            aux.seekArq(l);
            regI.leDoArq(aux.getFile());
            vet.seekArq(ini1 + l);
            regI.gravaNoArq(vet.getFile()); mov++;
        }
    }


    public void timSortArquivo() {
        int TL = filesize();
        int RUN = 32;
        Registro reg = new Registro(0);

        Arquivo vet = new Arquivo("src/arquivos/timSortVet.dat");
        Arquivo aux = new Arquivo("src/arquivos/timSortAux.dat");

        vet.truncate(0);
        aux.truncate(0);
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet.seekArq(i);
            reg.gravaNoArq(vet.getFile());
        }
        for (int i = 0; i < TL; i += RUN) {
            int fim = i + RUN - 1;
            if (fim >= TL) fim = TL - 1;
            vet.insertionSortArquivo(i, fim);
        }
        for (int tam = RUN; tam < TL; tam = tam * 2) {
            for (int ini = 0; ini < TL; ini += 2 * tam) {
                int meio = ini + tam - 1;
                int fim = ini + 2 * tam - 1;
                if (fim >= TL) fim = TL - 1;
                if (meio < fim)
                    fusaoTimSortArquivo(ini, meio, meio + 1, fim, aux, vet);
            }
        }

        for (int i = 0; i < TL; i++) {
            vet.seekArq(i);
            reg.leDoArq(vet.getFile());
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }

        try {
            aux.getFile().close();
            File file1 = new File("src/arquivos/timSortAux.dat");
            file1.delete();
            aux.getFile().close();
            file1 = new File("src/arquivos/timSortVet.dat");
            file1.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void combSortArquivo() {
        int TL = filesize();
        int gap = TL;
        boolean houveTroca = true;

        Registro reg1 = new Registro(0);
        Registro reg2 = new Registro(0);

        while (gap > 1 || houveTroca) {
            if (gap > 1)
                gap = (gap * 10) / 13;
            houveTroca = false;
            for (int i = 0; i + gap < TL; i++) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i + gap);
                reg2.leDoArq(arquivo);

                comp++;
                if (reg1.getNumero() > reg2.getNumero()) {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo); mov++;
                    seekArq(i + gap);
                    reg1.gravaNoArq(arquivo); mov++;

                    houveTroca = true;
                }
            }
        }
    }

    public void fecharBaldes(Arquivo[] baldes, int nBaldes) {
        try {
            for (int i = 0; i < nBaldes; i++) {
                baldes[i].getFile().close();
                File file = new File("src/arquivos/bucket" + i + ".dat");
                file.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bucketSortArquivo() {
        int TL = filesize();
        Registro reg = new Registro(0);
        int max = 0;
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            if (reg.getNumero() > max)
                max = reg.getNumero();
        }

        int qtdBuckets = (int) Math.sqrt(TL);
        if (qtdBuckets == 0) qtdBuckets = 1;

        int range = (max + 1);
        int intervalo = (int) Math.ceil((double) range / qtdBuckets);

        Arquivo[] buckets = new Arquivo[qtdBuckets];
        for (int i = 0; i < qtdBuckets; i++) {
            buckets[i] = new Arquivo("src/arquivos/bucket" + i + ".dat");
            buckets[i].truncate(0);
        }
        for (int i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            int valor = reg.getNumero();
            int indice = valor / intervalo;
            if (indice >= qtdBuckets) indice = qtdBuckets - 1;
            buckets[indice].seekArq(buckets[indice].filesize());
            reg.gravaNoArq(buckets[indice].getFile());
            mov++;
        }
        for (int i = 0; i < qtdBuckets; i++) {
            buckets[i].initComp();
            buckets[i].initMov();
            buckets[i].insertionSortArquivo();
            comp += buckets[i].getComp();
            mov += buckets[i].getMov();
        }
        int pos = 0;
        for (int i = 0; i < qtdBuckets; i++) {
            int tam = buckets[i].filesize();
            for (int j = 0; j < tam; j++) {
                buckets[i].seekArq(j);
                reg.leDoArq(buckets[i].getFile());
                seekArq(pos++);
                reg.gravaNoArq(arquivo);
                mov++;
            }
        }
        fecharBaldes(buckets, qtdBuckets);
    }




    public void heapSortArquivo() {
        int pai, TL2 = filesize(), FE, FD, maiorF;
        Registro reg1 = new Registro(0);
        Registro reg2 = new Registro(0);

        while (TL2 > 1) {
            pai = TL2 / 2 - 1;
            while (pai >= 0) {
                FE = 2 * pai + 1;
                FD = FE + 1;
                maiorF = FE;
                seekArq(FE);
                reg1.leDoArq(arquivo);
                seekArq(FD);
                reg2.leDoArq(arquivo);
                if (FD < TL2) {
                    comp++;
                    if (reg2.getNumero() > reg1.getNumero())
                        maiorF = FD;
                }
                seekArq(pai);
                reg1.leDoArq(arquivo);
                seekArq(maiorF);
                reg2.leDoArq(arquivo);
                comp++;
                if (reg1.getNumero() < reg2.getNumero()) {
                    seekArq(pai);
                    reg2.gravaNoArq(arquivo); mov++;
                    seekArq(maiorF);
                    reg1.gravaNoArq(arquivo); mov++;
                }

                pai--;
            }
            seekArq(0);
            reg1.leDoArq(arquivo);
            seekArq(TL2 - 1);
            reg2.leDoArq(arquivo);
            seekArq(0);
            reg2.gravaNoArq(arquivo); mov++;
            seekArq(TL2 - 1);
            reg1.gravaNoArq(arquivo); mov++;
            TL2--;
        }
    }


    public void geraArquivoOrdenado() {
        try {
            truncate(0);
            for (int i = 0; i < 128; i++) {
                Registro r = new Registro(i);
                r.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void geraArquivoReverso() {
        try {
            truncate(0);
            for (int i = 128; i > 0; i--) {
                Registro r = new Registro(i);
                r.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void geraArquivoRandomico() {
        try {
            truncate(0);
            java.util.Random rand = new java.util.Random();
            for (int i = 0; i < 128; i++) {
                Registro r = new Registro(rand.nextInt(1024));
                r.gravaNoArq(arquivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
