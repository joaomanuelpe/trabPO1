import java.io.IOException;
import java.io.RandomAccessFile;

public class TableGenerator {
    private RandomAccessFile file;

    public TableGenerator() {
        try {
            file = new RandomAccessFile("src/arquivos/TabelaOrdenacao.txt", "rw");
            file.setLength(0); // Clear file contents
            writeHeader();
        } catch (IOException e) {
            throw new RuntimeException("Error creating file", e);
        }
    }

    private void writeHeader() {
        String header =
                "+=====================+===========================================+===========================================+===========================================+\n" +
                        "║  Métodos Ordenação    ║           Arquivo Ordenado                ║       Arquivo em Ordem Reversa            ║           Arquivo Randômico               ║\n" +
                        "+=====================+===========================================+===========================================+===========================================+\n" +
                        "║                     ║ Comp. │ Comp. │ Mov.  │ Mov.  │ Tempo   ║ Comp. │ Comp. │ Mov.  │ Mov.  │ Tempo   ║ Comp. │ Comp. │ Mov.  │ Mov.  │ Tempo   ║\n" +
                        "║                     ║ Prog. │ Equa. │ Prog. │ Equa. │ (Seg)   ║ Prog. │ Equa. │ Prog. │ Equa. │ (Seg)   ║ Prog. │ Equa. │ Prog. │ Equa. │ (Seg)   ║\n" +
                        "+=====================+===========================================+===========================================+===========================================+\n";
        writeToFile(header);
    }

    private void writeToFile(String content) {
        try {
            file.writeBytes(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    private String centerText(String text, int width) {
        int padding = width - text.length();
        int leftPad = padding / 2;
        int rightPad = padding - leftPad;
        return " ".repeat(leftPad) + text + " ".repeat(rightPad);
    }

    public void writeMethodName(String name) {
        String centered = centerText(name, 19);
        writeToFile("║" + centered + "  ║");
    }

    private String centerNumber(int num, int width) {
        return centerText(String.valueOf(num), width);
    }

    public void writeTableRow(int comp, int compCalc, int mov, int movCalc, int time, boolean isLastColumn) {
        StringBuilder row = new StringBuilder();
        row.append(centerNumber(comp, 7)).append(" │");
        row.append(centerNumber(compCalc, 7)).append(" │");
        row.append(centerNumber(mov, 7)).append(" │");
        row.append(centerNumber(movCalc, 7)).append("  │");
        row.append(centerNumber(time, 8)).append("║");

        writeToFile(row.toString());

        if (isLastColumn) {
            writeToFile("\n");
            writeDividerLine();
        }
    }

    private void writeDividerLine() {
        String line = "+=====================+===========================================+===========================================+===========================================+\n";
        writeToFile(line);
    }

    public static void main(String[] args) {
        TableGenerator table = new TableGenerator();
        String[] methods = {
                "Inserção Direta",
                "Inserção Binária",
                "Seleção",
                "Bolha",
                "Shake",
                "Shell",
                "Heap",
                "Quick s/ pivô",
                "Quick c/ pivô",
                "Merge 1ª Implement",
                "Merge 2ª Implement",
                "Counting",
                "Bucket",
                "Radix",
                "Comb",
                "Gnome",
                "Tim"
        };

        for (String method : methods) {
            table.writeMethodName(method);
            table.writeTableRow(0, 0, 0, 0, 0, false);
            table.writeTableRow(0, 0, 0, 0, 0, false);
            table.writeTableRow(0, 0, 0, 0, 0, true);
        }

        System.out.println("Table has been generated in archive.txt");
    }
}