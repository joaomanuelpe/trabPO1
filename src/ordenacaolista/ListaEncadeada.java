package ordenacaolista;

import ordenacaolista.objetos.No;

public class ListaEncadeada {
    private No inicio;

    public ListaEncadeada() {
        this.inicio = null;
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

    public void insertionSort(){
        int num;
        No auxi = inicio, posmenor, auxj;

        while(auxi.getProx() != null) {
            posmenor = auxi;
            auxj = auxi.getProx();
            while(auxj != null) {
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

    public No buscaPos(ListaEncadeada inicio, int indice) {
        No aux = inicio.getInicio();
        for (int i = 0; i < indice; i++)
            aux = aux.getProx();
        return aux;
    }

    public No getInicio() {
        return inicio;
    }

}
