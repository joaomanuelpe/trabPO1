package ordenacaolista.objetos;

public class NoFloat {
    private float num;
    private NoFloat prox, ant;

    public NoFloat(float num) {
        this.num = num;
        this.prox = null;
        this.ant = null;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {  // Alterado para float
        this.num = num;
    }

    public NoFloat getAnt() {
        return ant;
    }

    public void setAnt(NoFloat ant) {
        this.ant = ant;
    }

    public NoFloat getProx() {
        return prox;
    }

    public void setProx(NoFloat prox) {
        this.prox = prox;
    }
}
