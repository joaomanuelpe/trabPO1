package ordenacaolista.objetos;

public class Resultado {
    String tipoArquivo, algoritmo;
    long comparacoes, movimentacoes, tempoMs;

    public Resultado(String tipoArquivo, String algoritmo, long comparacoes, long movimentacoes, long tempoMs) {
        this.tipoArquivo = tipoArquivo;
        this.algoritmo = algoritmo;
        this.comparacoes = comparacoes;
        this.movimentacoes = movimentacoes;
        this.tempoMs = tempoMs;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-12d | %-14d | %-8d", tipoArquivo, algoritmo, comparacoes, movimentacoes, tempoMs);
    }
}
