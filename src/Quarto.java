import java.util.ArrayList;
import java.util.List;
import Enum.TipoQuarto;


class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private Hospedagem hospedagem;

    public Quarto(int numero, TipoQuarto tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.hospedagem = null;
    }

    public int getNumero() {
        return numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public boolean isOcupado() {
        return hospedagem != null;
    }

    public void ocupar(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public void desocupar() {
        this.hospedagem = null;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }
}


