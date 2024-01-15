package util.formatter;

public class ShowMenu {
    public static void line(int size) {
        System.out.println("=".repeat(size));
    }

    /**
     * Imprimir cabeçalho
     *
     * @author Darllinson Azevedo
     *
     * @param text Título do cabeçalho
     * @param sizeOfLine Tamanho da linha separadora
     */
    public static void header(String text, int sizeOfLine) {
        StringAlign textCenter = new StringAlign(sizeOfLine, StringAlign.Alignment.CENTER);

        System.out.println("\n" + "-".repeat(sizeOfLine));
        System.out.print(textCenter.format(text));
        System.out.print("-".repeat(sizeOfLine) + "\n");
    }
}
