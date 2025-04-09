package ordenacaolista.objetos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TabelaResultados {
    List<Resultado> resultados = new ArrayList<>();

    public void adicionar(Resultado r) {
        resultados.add(r);
    }

    public void salvarEmArquivo(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("Tipo do Arquivo  | Algoritmo       | Comparacoes  | Movimentacoes  | Tempo(ms)");
            writer.println("--------------------------------------------------------------------------------");
            for (Resultado r : resultados) {
                writer.println(r.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
