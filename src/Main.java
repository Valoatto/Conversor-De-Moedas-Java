import javax.swing.SwingUtilities;

// classe principal, eh aqui que o programa comeca
public class Main {

    public static void main(String[] args) {
        // o swing pede pra abrir a janela dentro dessa thread
        // pra evitar comportamento estranho na interface
        SwingUtilities.invokeLater(() -> {
            TelaConversor tela = new TelaConversor();
            tela.setVisible(true);
        });
    }
}
