package ordenacaolista;

import ordenacaolista.objetos.NoBucket;
import ordenacaolista.objetos.NoFloat;

public class ListaEncadeadaFloat {
    NoFloat inicio;

    public ListaEncadeadaFloat() {
        this.inicio = null;
    }

    public void insereListaFila(float info) {
        if (inicio == null)
            inicio = new NoFloat(info);
        else {
            NoFloat aux = inicio;
            while (aux.getProx() != null)
                aux = aux.getProx();
            aux.setProx(new NoFloat(info));
            aux.getProx().setAnt(aux);
        }
    }

    public void imprimeLista() {
        NoFloat aux = inicio;
        while (aux != null) {
            System.out.print("\t" + aux.getNum());
            aux = aux.getProx();
        }
    }

    public NoBucket insereListaBucket(NoBucket inicio, int info, float numero) {
        NoBucket aux = inicio;
        while (aux != null && aux.getNum() != info)
            aux = aux.getProx();
        if (aux != null) {
            if (aux.getIni() == null)
                aux.setIni(new NoFloat(numero));
            else {
                NoFloat i = aux.getIni();
                while (i.getProx() != null)
                    i = i.getProx();
                i.setProx(new NoFloat(numero));
                i.getProx().setAnt(i);
            }
            return aux;
        }
        return null;
    }

    public void bucketSort() {
        NoBucket iniB = null;  // Inicializado corretamente

        // Criar baldes de 0 a 9
        for (int i = 0; i < 10; i++) {
            if (iniB == null)
                iniB = new NoBucket(i);
            else {
                NoBucket aux2 = iniB;
                while (aux2.getProx() != null)
                    aux2 = aux2.getProx();
                aux2.setProx(new NoBucket(i));
                aux2.getProx().setAnt(aux2);
            }
        }

        // Distribuir elementos nos baldes
        NoFloat i = inicio;
        while (i != null) {
            int num = (int) (i.getNum() * 10); // Melhor conversão
            insereListaBucket(iniB, num, i.getNum());
            i = i.getProx();
        }

        // Ordenação dos baldes com Insertion Sort
        NoBucket aux = iniB;
        while (aux != null) {
            if (aux.getIni() != null) {
                NoFloat atual = aux.getIni().getProx();
                while (atual != null) {
                    float chave = atual.getNum();
                    NoFloat anterior = atual.getAnt();

                    while (anterior != null && anterior.getNum() > chave) {
                        anterior.getProx().setNum(anterior.getNum());
                        anterior = anterior.getAnt();
                    }
                    if (anterior == null) {
                        aux.getIni().setNum(chave);
                    } else {
                        anterior.getProx().setNum(chave);
                    }
                    atual = atual.getProx();
                }
            }
            aux = aux.getProx();
        }

        // Reunir elementos ordenados
        NoBucket aux2 = iniB;
        NoFloat inicioAux = inicio;
        while (aux2 != null) {
            NoFloat j = aux2.getIni();
            while (j != null) {
                inicioAux.setNum(j.getNum());
                inicioAux = inicioAux.getProx();
                j = j.getProx();
            }
            aux2 = aux2.getProx();
        }
    }
}
