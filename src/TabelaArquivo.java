import java.io.IOException;
import java.io.RandomAccessFile;

public class TabelaArquivo {
    private RandomAccessFile file;

    public TabelaArquivo() {
        try {
            file = new RandomAccessFile("src/arquivos/TabelaOrdenacao.txt", "rw");
            file.setLength(0); // Clear file contents
            criarCabecalho();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo", e);
        }
    }

    private void criarCabecalho() {
        String header =
                "+=======================+================================================+================================================+================================================+\n" +
                        "║    Métodos Ordenação  ║              Arquivo Ordenado                  ║         Arquivo em Ordem Reversa               ║            Arquivo Randômico                   ║\n" +
                        "+=======================+================================================+================================================+================================================+\n" +
                        "║                       ║  Comp. \u0002   Comp. \u0002    Mov.  \u0002  Mov.  \u0002   Tempo  ║  Comp. \u0002    Comp. \u0002  Mov.  \u0002    Mov.  \u0002  Tempo  ║  Comp. \u0002    Comp. \u0002  Mov.  \u0002   Mov.  \u0002   Tempo  ║\n" +
                        "║                       ║  Prog. \u0002   Equa. \u0002    Prog. \u0002  Equa. \u0002   (Seg)  ║  Prog. \u0002    Equa. \u0002  Prog. \u0002    Equa. \u0002  (Seg)  ║  Prog. \u0002    Equa. \u0002  Prog. \u0002   Equa. \u0002   (Seg)  ║\n" +
                        "+=======================+================================================+================================================+================================================+\n";
        escreverNoArquivo(header);
    }

    private void escreverNoArquivo(String content) {
        try {
            file.writeBytes(content);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo", e);
        }
    }

    private String centralizaTexto(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = width - text.length();
        int leftPad = padding / 2;
        int rightPad = padding - leftPad;
        return " ".repeat(leftPad) + text + " ".repeat(rightPad);
    }

    public void escreveNomeMetodo(String name) {
        String centered = centralizaTexto(name, 21); // Increased width to 21
        escreverNoArquivo("║" + centered + "  ║");
    }

    private String centralizaNumero(int num, int width) {
        String numStr = String.valueOf(num);
        return centralizaTexto(numStr, width);
    }

    public void escreverLinhaTabela(int comp, int compCalc, int mov, int movCalc, int time, boolean isLastColumn) {
        StringBuilder row = new StringBuilder();
        row.append(" ").append(centralizaNumero(comp, 7)).append(" │");
        row.append(" ").append(centralizaNumero(compCalc, 7)).append(" │");
        row.append(" ").append(centralizaNumero(mov, 7)).append(" │");
        row.append(" ").append(centralizaNumero(movCalc, 7)).append(" │");
        row.append(" ").append(centralizaNumero(time, 7)).append(" ║");

        escreverNoArquivo(row.toString());

        if (isLastColumn) {
            escreverNoArquivo("\n");
            escreverLinhaDivisória();
        }
    }

    private void escreverLinhaDivisória() {
        String line = "+=======================+================================================+================================================+================================================+\n";
        escreverNoArquivo(line);
    }
}