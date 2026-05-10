// classe que representa uma moeda
// cada moeda tem o codigo (USD, EUR...), o nome completo e
// quanto vale 1 unidade dela em reais
public class Moeda {

    private String codigo;
    private String nome;
    private double taxaEmReal;

    public Moeda(String codigo, String nome, double taxaEmReal) {
        this.codigo = codigo;
        this.nome = nome;
        this.taxaEmReal = taxaEmReal;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getTaxaEmReal() {
        return taxaEmReal;
    }

    // sobrescrevo o toString pra a moeda aparecer formatada nos combos
    // exemplo: "USD - Dolar Americano"
    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}
