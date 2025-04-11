package ordenacaolista;

import ordenacaolista.objetos.No;

public class ListaEncadeada {
    private No inicio, fim;

    public ListaEncadeada() {
        this.inicio = null;
        this.fim = null;
    }

    public ListaEncadeada(No inicio, No fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public void insereListaFila(int info) {
        if (inicio == null)
            inicio = new No(info);
        else {
            No aux = inicio;
            while (aux.getProx() != null)
                aux = aux.getProx();
            aux.setProx(new No(info));
            aux.getProx().setAnt(aux);
        }
    }

    public void exibir() {
        No aux = inicio;
        int i = 1;
        while(aux!=null) {
            System.out.print(aux.getNum()+" ");
            aux = aux.getProx();
            if(i%75 == 0) {
                System.out.println();
            }
            i++;
        }
    }

    public void inserirNoIni(int info) {
        No nova = new No(info, inicio, null);
        if(inicio == null) {
            inicio = fim = nova;
        }
        else {

            inicio.setAnt(nova);
            inicio = nova;
        }
    }


    public void imprimeLista() {
        No aux = inicio;
        while (aux != null) {
            System.out.print("\t" + aux.getNum());
            aux = aux.getProx();
        }
    }

    public void gnomeSort(){
        No i = inicio.getProx();
        No j = i.getProx(); //

        while (i != null) {
            if (i.getAnt().getNum() <= i.getNum()) {
                i = j;
                if(j!=null){
                    j = j.getProx();
                }
            } else {
                int num = i.getAnt().getNum();
                i.getAnt().setNum(i.getNum());
                i.setNum(num);

                i = i.getAnt();
                if (i.getAnt() == null) {
                    i = j;
                    if(j!=null){
                        j = j.getProx();
                    }
                }
            }
        }
    }

    public void quickSortSemPivo(No ini, No fim) {
        No i = ini, j = fim, aux = new No(0);

        while(i != j) {
            while (i != j && i.getNum() <= j.getNum())
                i = i.getProx();
            aux.setNum(i.getNum());
            i.setNum(j.getNum());
            j.setNum(aux.getNum());
            while (i != j && i.getNum() <= j.getNum())
                j = j.getAnt();
            aux.setNum(i.getNum());
            i.setNum(j.getNum());
            j.setNum(aux.getNum());
        }
        if(ini != i && ini != i.getAnt())
            quickSortSemPivo(ini, i.getAnt());
        if(j != fim && j.getProx() != fim)
            quickSortSemPivo(j.getProx(), fim);

    }

    public void insertionSort(No ini, No fim){
        int num;
        No auxi = ini, posmenor, auxj, auxf = fim;

        while(auxi.getProx() != auxf.getProx()) {
            posmenor = auxi;
            auxj = auxi.getProx();
            while(auxj != auxf.getProx()) {
                if(auxj.getNum() < posmenor.getNum())
                    posmenor = auxj;
                auxj = auxj.getProx();
            }
            num = auxi.getNum();
            auxi.setNum(posmenor.getNum());
            posmenor.setNum(num);

            auxi = auxi.getProx();
        }
    }

    public ListaEncadeada countingSort() {
        int maior = 0;
        No aux = inicio;
        while(aux != null){
            if(aux.getNum() > maior)
                maior = aux.getNum();
            aux = aux.getProx();
        }

        ListaEncadeada lista = new ListaEncadeada();
        for(int i = 0; i < maior; i++)
            lista.insereListaFila(0);

        for(aux = inicio; aux != null; aux = aux.getProx()) {
            int indice = aux.getNum();
            No j = lista.getInicio(); //andando até a posição "indice" para somar 1
            for(int k = 0; k <= indice - 1; k++) {
                if(k == indice - 1)
                    j.setNum(j.getNum() + 1);
                j = j.getProx();
            }
        }

        for(aux = lista.getInicio().getProx(); aux != null; aux = aux.getProx())
            aux.setNum(aux.getNum() + aux.getAnt().getNum());

        ListaEncadeada listaOrd = new ListaEncadeada(); //lista final ordenada
        for(aux = inicio; aux.getProx() != null; aux = aux.getProx())
            listaOrd.insereListaFila(0);
        listaOrd.insereListaFila(0); //preciso que o aux pare no ultimo elemento para fazer a ordenação e que a lista ordenada tenha o mesmo tamanho da primeira lista passada

        while(aux != null) {
            int indice = aux.getNum();
            No j = lista.getInicio(), auxListaOrd = new No(0);
            int pos = 0;
            for (int k = 0; k <= indice - 1; k++) {
                if(k == indice - 1) {
                    pos = j.getNum();
                    j.setNum(j.getNum() - 1);
                }
                j = j.getProx();
            }
            auxListaOrd = buscaPos(listaOrd, pos - 1);
            auxListaOrd.setNum(indice);
            aux = aux.getAnt();
        }
        return listaOrd;
    }

    public No posiciona(No no) {
        No aux = no;
        while(aux.getAnt()!=null) {

            aux = aux.getAnt();
        }
        return aux;
    }

    public No geraMeio(No ini, No fim) {
        No aux = ini;
        int auxI = 1, i = 0;
        while(aux != fim.getProx()) {
            aux = aux.getProx();
            auxI++;
        }
        aux = ini;
        while (i < auxI/2-1) {
            aux = aux.getProx();
            i++;
        }
        return aux;
    }

    public void fusao(No ini1, No fim1, No ini2, No fim2, No aux) {
        No i = ini1, j = ini2;
        while(i!=fim1.getProx() && j!=fim2.getProx())

            if(i.getNum()< j.getNum()) {
                if(aux == null)
                    aux = new No(i.getNum());
                else {
                    aux.setProx(new No(i.getNum(), null, aux));
                    aux = aux.getProx();
                }
                i = i.getProx();
            }
            else {
                if(aux == null)
                    aux = new No(j.getNum(), null, null);
                else {
                    aux.setProx(new No(j.getNum(), null, aux));
                    aux = aux.getProx();
                }
                j = j.getProx();
            }
        while(i!=fim1.getProx()) {
            aux.setProx(new No(i.getNum(), null, aux));
            aux = aux.getProx();
            i = i.getProx();
        }
        while(j!=fim2.getProx()) {
            aux.setProx(new No(j.getNum(), null, aux));
            aux = aux.getProx();
            j = j.getProx();
        }
        aux = posiciona(aux);
        for(i=ini1; aux!=null; i=i.getProx(), aux=aux.getProx()) {
            i.setNum(aux.getNum());
        }

    }

    public void merge(No esq, No dir, No aux) {
        if(esq!=dir) {
            No meio = geraMeio(esq, dir);
            merge(esq, meio, aux);
            merge(meio.getProx(), dir, aux);
            fusao(esq, meio, meio.getProx(), dir, aux);
        }
    }

    public void mergeSort() {
        No aux = null;
        merge(inicio, fim, aux);
    }

    public void timSort() {
        int RUN = 32;
        int TL = geraTL();
        No aux = null;
        No ini1, fim1;
        for (int i = 0; i < TL; i++) {
            int fim = i + RUN;
            if(fim>=TL)
                fim = TL;
            ini1 = pegaNo(i);
            fim1 = pegaNo(fim);
            insertionSort(ini1, fim1);
        }

        for (int tam = RUN; tam < TL; tam =  tam * 2) {
            for (int ini = 0; ini < TL; ini += 2 * tam) {
                No ini2 = pegaNo(ini);
                int meio = ini + tam;
                No fim;
                int fimI = ini + 2 * tam;
                if(fimI >= TL) {
                    fim = pegaNo(TL);
                    meio = (ini+TL)/2;
                }
                else
                    fim = pegaNo(fimI);
                if(meio < fimI) {
                    No meio2 = pegaNo(meio);
                    fusao(ini2, meio2, meio2.getProx(), fim, aux);
                }
            }
        }
    }



    public int geraTL() {
        No aux = inicio;
        int i = 0;
        while(aux != null) {
            i++;
            aux = aux.getProx();
        }
        return i;
    }

    public No pegaNo(int i) {
        No aux = inicio;
        int j = 1;
        while(j < i) {
            aux = aux.getProx();
            j++;
        }
        return aux;
    }

    public void shellSort() {
        int d = 2, pos, auxI, TL = geraTL()+1;
        No aux, aux2;
        while(d<TL+1)
            d = d*2+1;
        d = d/2;
        while(d>1) {
            for (int i = d; i < TL; i++) {
                pos = i;
                aux = pegaNo(pos);
                aux2 = pegaNo(pos-d+1);
                auxI = aux.getNum();
                while(pos>=d && auxI < aux2.getNum()) {
                    aux.setNum(aux2.getNum());
                    pos = pos-d+1;
                    aux = pegaNo(pos);
                    aux2 = pegaNo(pos-d+1);
                }
                aux.setNum(auxI);
            }
            d = d/2;
        }
    }

    public int buscaPivo(No ini, No fim) {
        No aux = ini;
        int i = 0;
        while(aux != fim.getProx()) {
            i++;
            aux = aux.getProx();
        }
        aux = ini;
        for (int j = 0; j < i/2; j++) {
            aux = aux.getProx();
        }
        return aux.getNum();
    }

    public void quickCP(No ini2, No fim2) {
        No i = ini2, j = fim2;
        int aux, pivo = buscaPivo(ini2, fim2);

        while(i!=j && i.getAnt()!=j) {
            while(i.getNum() < pivo && i!=j)
                i = i.getProx();
            while(j.getNum() > pivo && i!=j)
                j = j.getAnt();
            if (i != j) {
                aux = i.getNum();
                i.setNum(j.getNum());
                j.setNum(aux);
                i = i.getProx();
                j = j.getAnt();
            }
        }

        if(j.getAnt()!=ini2 && ini2!=j) {
            quickCP(ini2, j);
        }
        if(i.getProx()!=fim2 && fim2!=i) {
            quickCP(i, fim2);
        }
    }

    public void quickSortComPivo() {
        quickCP(inicio, fim);
    }

    public void bolha() {
        int aux;
        boolean flag = true;
        No tl = fim, i;
        while(tl.getAnt()!=null && flag) {
            flag = false;
            for (i = inicio; i.getProx()!=tl.getProx(); i = i.getProx()) {
                if(i.getNum() > i.getProx().getNum()) {
                    aux = i.getNum();
                    i.setNum(i.getProx().getNum());
                    i.getProx().setNum(aux);
                    flag = true;
                }
            }
            tl = tl.getAnt();
        }
    }

    public void ShakeSort() {
        int aux;
        No ini = inicio, aux2 = fim, i;
        boolean flag = true;
        while(ini != aux2 && flag) {
            flag = false;
            for (i = ini; i != aux2; i = i.getProx()) {
                if(i.getNum() > i.getProx().getNum()) {
                    aux = i.getNum();
                    i.setNum(i.getProx().getNum());
                    i.getProx().setNum(aux);
                    flag = true;
                }
            }
            aux2 = aux2.getAnt();

            if(flag) {
                flag = false;
                for(i = aux2; i != ini; i = i.getAnt()) {
                    if(i.getNum() < i.getAnt().getNum()) {
                        aux = i.getNum();
                        i.setNum(i.getAnt().getNum());
                        i.getAnt().setNum(aux);
                        flag = true;
                    }
                }
                ini = ini.getProx();
            }
        }
    }

    public void selecaoDireta() {
        No i = inicio, j = inicio.getProx(), posM;
        int aux;
        while(i.getProx()!=null) {
            posM = i;
            while(j!=null) {
                if(posM.getNum() > j.getNum()) {
                    posM = j;
                }
                j = j.getProx();
            }
            aux = i.getNum();
            i.setNum(posM.getNum());
            posM.setNum(aux);
            i = i.getProx();
            j = i.getProx();
        }
    }

    public void combSort() {
        int TL = geraTL();
        int gap = TL, aux;
        No aux1, aux2;
        boolean flag = true;
        while(gap>1 || flag) {
            if(gap > 1) {
                gap = (gap*10) / 13;
            }
            flag = false;
            for (int i = 1; i + gap <= TL; i++) {
                aux1 = pegaNo(i);
                aux2 = pegaNo(i+gap);
                if(aux1.getNum() > aux2.getNum()) {
                    aux = aux1.getNum();
                    aux1.setNum(aux2.getNum());
                    aux2.setNum(aux);
                    flag = true;
                }
            }
        }
    }

    public No reseta (No no) {
        while(no.getAnt()!=null) {
            no = no.getAnt();
        }
        return no;
    }

    public void fusao1 (No ini1, No ini2, int TL, int seq) {
        ini1 = reseta(ini1);
        ini2 = reseta(ini2);
        int i = 0, j = 0, k = 0, aux_seq = seq;
        No aux = inicio;
        while(k < TL) {
            while(i < seq && j < seq) {
                if(ini1.getNum() < ini2.getNum()) {
                    aux.setNum(ini1.getNum());
                    aux = aux.getProx();
                    ini1 = ini1.getProx();
                    i++;
                }
                else {
                    aux.setNum(ini2.getNum());
                    aux = aux.getProx();
                    ini2 = ini2.getProx();
                    j++;
                }
                k++;
            }
            while(i < seq && ini1!=null) {
                aux.setNum(ini1.getNum());
                aux = aux.getProx();
                i++;
                ini1 = ini1.getProx();
                k++;
            }
            while(j < seq && ini2!=null) {
                aux.setNum(ini2.getNum());
                aux = aux.getProx();
                j++;
                ini2 = ini2.getProx();
                k++;
            }
            seq = seq + aux_seq;
        }
    }

    public No[] particao(int TL) {
        No[] res = new No[2];
        No aux = pegaNo(1);

        No ini1 = new No(aux.getNum(), null, null);
        No atual1 = ini1;
        for (int i = 2; i <= TL / 2; i++) {
            aux = pegaNo(i);
            atual1.setProx(new No(aux.getNum(), null, atual1));
            atual1 = atual1.getProx();
        }

        aux = pegaNo((TL / 2) + 1);
        No ini2 = new No(aux.getNum(), null, null);
        No atual2 = ini2;
        for (int i = (TL / 2) + 2; i <= TL; i++) {
            aux = pegaNo(i);
            atual2.setProx(new No(aux.getNum(), null, atual2));
            atual2 = atual2.getProx();
        }

        res[0] = ini1;
        res[1] = ini2;
        return res;
    }

    public void merge1() {
        int TL = geraTL();
        No aux1 = null, aux2 = null;
        int seq = 1;
        while(seq < TL) {
            No[] aux = particao(TL);
            aux1 = aux[0];
            aux2 = aux[1];
            fusao1(aux1, aux2, TL, seq);
            seq = seq * 2;
        }
    }

    public No buscaPos(ListaEncadeada inicio, int indice) {
        No aux = inicio.getInicio();
        for (int i = 0; i < indice; i++)
            aux = aux.getProx();
        return aux;
    }

    public No getInicio() {
        return inicio;
    }
    public No getFim() {
        return fim;
    }

}