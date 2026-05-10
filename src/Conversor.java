import java.util.ArrayList;
import java.util.List;

// classe que cuida da logica de conversao
// aqui ficam as taxas fixas e a formula de conversao
public class Conversor {

    // lista com as moedas e suas taxas em real
    // essas taxas estao fixas so pra simulacao academica
    // num projeto real eu pegaria de uma API atualizada
    private static final List<Moeda> MOEDAS = new ArrayList<>();

    // bloco static eh executado uma vez quando a classe eh carregada
    // uso ele pra preencher a lista de moedas com os valores iniciais
    static {
        MOEDAS.add(new Moeda("BRL", "Real Brasileiro", 1.00));
        MOEDAS.add(new Moeda("USD", "Dolar Americano", 5.05));
        MOEDAS.add(new Moeda("EUR", "Euro", 5.65));
        MOEDAS.add(new Moeda("GBP", "Libra Esterlina", 6.55));
        MOEDAS.add(new Moeda("JPY", "Iene Japones", 0.034));
        MOEDAS.add(new Moeda("ARS", "Peso Argentino", 0.005));
    }

    // devolve a lista pra a tela montar os combos
    public static List<Moeda> getMoedas() {
        return MOEDAS;
    }

    // converte um valor de uma moeda pra outra
    // a logica eh: primeiro converto pra real, depois pra moeda destino
    // assim qualquer combinacao de moedas funciona com a mesma formula
    public static double converter(double valor, Moeda origem, Moeda destino) {
        // primeiro descubro quanto o valor vale em reais
        double valorEmReal = valor * origem.getTaxaEmReal();

        // depois converto esse valor em reais pra moeda de destino
        return valorEmReal / destino.getTaxaEmReal();
    }
}
