package ordenacaolista.objetos;

public class No {
    private int num;
    private No prox, ant;

    public No(int num, No prox, No ant) {
        this.num = num;
        this.prox = prox;
        this.ant = ant;
    }

    public No(int num) {
        this.num = num;
        this.prox = null;
        this.ant = null;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}