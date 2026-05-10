import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

// janela do conversor com os campos e o resultado
public class TelaConversor extends JFrame {

    private JTextField campoValor;
    private JComboBox<Moeda> comboOrigem;
    private JComboBox<Moeda> comboDestino;
    private JLabel labelResultado;

    // paleta de cores que escolhi pra deixar o visual clean
    private final Color CORFUNDO = new Color(245, 245, 245);
    private final Color CORAZUL = new Color(74, 144, 226);
    private final Color CORTEXTO = new Color(50, 50, 50);
    private final Color CORTEXTOFRACO = new Color(120, 120, 120);
    private final Color CORBORDA = new Color(220, 220, 220);

    public TelaConversor() {
        configurarJanela();
        montarTela();
    }

    private void configurarJanela() {
        setTitle("Conversor de Moedas");
        setSize(420, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela
        setResizable(false);
        getContentPane().setBackground(CORFUNDO);
        setLayout(new BorderLayout());
    }

    private void montarTela() {
        // BoxLayout vertical pra empilhar os componentes de cima pra baixo
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(CORFUNDO);
        painel.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        // titulo
        JLabel titulo = new JLabel("Conversor de Moedas");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(CORTEXTO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(titulo);

        // subtitulo so pra deixar claro que eh simulacao
        JLabel subtitulo = new JLabel("Valores fixos para fins academicos");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitulo.setForeground(CORTEXTOFRACO);
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitulo.setBorder(BorderFactory.createEmptyBorder(5, 0, 25, 0));
        painel.add(subtitulo);

        // campo do valor a converter
        painel.add(criarLabel("Valor"));
        painel.add(Box.createRigidArea(new Dimension(0, 5)));
        campoValor = new JTextField();
        estilizarCampo(campoValor);
        painel.add(campoValor);

        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // combo da moeda de origem
        painel.add(criarLabel("De"));
        painel.add(Box.createRigidArea(new Dimension(0, 5)));
        comboOrigem = criarCombo();
        comboOrigem.setSelectedIndex(0); // comeca selecionando BRL
        painel.add(comboOrigem);

        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // combo da moeda de destino
        painel.add(criarLabel("Para"));
        painel.add(Box.createRigidArea(new Dimension(0, 5)));
        comboDestino = criarCombo();
        comboDestino.setSelectedIndex(1); // comeca selecionando USD
        painel.add(comboDestino);

        painel.add(Box.createRigidArea(new Dimension(0, 25)));

        // botao converter
        JButton botaoConverter = new JButton("Converter");
        botaoConverter.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoConverter.setBackground(CORAZUL);
        botaoConverter.setForeground(Color.WHITE);
        botaoConverter.setFocusPainted(false);
        botaoConverter.setBorderPainted(false);
        botaoConverter.setOpaque(true);
        botaoConverter.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        botaoConverter.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        botaoConverter.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoConverter.addActionListener(e -> converter());
        painel.add(botaoConverter);

        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        // caixa onde o resultado vai aparecer
        labelResultado = new JLabel("O resultado aparecera aqui", SwingConstants.CENTER);
        labelResultado.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelResultado.setForeground(CORTEXTOFRACO);
        labelResultado.setBackground(Color.WHITE);
        labelResultado.setOpaque(true);
        labelResultado.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CORBORDA, 1),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        labelResultado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        labelResultado.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(labelResultado);

        add(painel, BorderLayout.CENTER);
    }

    // metodo auxiliar pra criar os labels dos campos com o mesmo estilo
    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setForeground(CORTEXTO);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // estilo padrao dos campos de texto
    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CORBORDA, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // cria um combo ja com a lista de moedas e o estilo certo
    private JComboBox<Moeda> criarCombo() {
        List<Moeda> moedas = Conversor.getMoedas();
        // converto a lista pra array porque o JComboBox aceita array no construtor
        JComboBox<Moeda> combo = new JComboBox<>(moedas.toArray(new Moeda[0]));
        combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        return combo;
    }

    // metodo chamado quando o usuario clica em converter
    private void converter() {
        String texto = campoValor.getText().trim();

        // primeiro verifico se o campo esta vazio
        if (texto.isEmpty()) {
            mostrarErro("Digite um valor para converter.");
            return;
        }

        // tento transformar o texto em numero
        // se nao conseguir, eh porque o usuario digitou letra ou simbolo
        double valor;
        try {
            // troco virgula por ponto pra aceitar tanto "10,5" quanto "10.5"
            // (no Brasil eh comum usar virgula como separador decimal)
            valor = Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException ex) {
            mostrarErro("Valor invalido. Digite apenas numeros.");
            return;
        }

        // nao deixo converter valor negativo
        if (valor < 0) {
            mostrarErro("O valor nao pode ser negativo.");
            return;
        }

        // pego as moedas selecionadas nos combos
        Moeda origem = (Moeda) comboOrigem.getSelectedItem();
        Moeda destino = (Moeda) comboDestino.getSelectedItem();

        // chamo o metodo da classe Conversor pra fazer a conta
        double resultado = Conversor.converter(valor, origem, destino);

        // formato o resultado com duas casas decimais
        DecimalFormat formato = new DecimalFormat("#,##0.00");

        // monto a frase do resultado e mostro na tela
        String texto1 = formato.format(valor) + " " + origem.getCodigo();
        String texto2 = formato.format(resultado) + " " + destino.getCodigo();

        labelResultado.setForeground(CORTEXTO);
        labelResultado.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelResultado.setText(texto1 + "  =  " + texto2);
    }

    // mostra um erro pro usuario usando JOptionPane
    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
}
