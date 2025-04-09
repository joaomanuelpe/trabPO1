package ordenacaolista.objetos;

public class NoBucket {
    private int num;
    private NoBucket prox, ant;
    private NoFloat ini;

    public NoBucket(int num) {
        this.num = num;
        this.prox = null;
        this.ant = null;
        this.ini = null;
    }

    public int getNum() {
        return num;
    }

    public NoBucket getProx() {
        return prox;
    }

    public void setProx(NoBucket prox) {
        this.prox = prox;
    }

    public NoBucket getAnt() {
        return ant;
    }

    public void setAnt(NoBucket ant) {
        this.ant = ant;
    }

    public NoFloat getIni() {
        return ini;
    }

    public void setIni(NoFloat ini) {
        this.ini = ini;
    }
}
