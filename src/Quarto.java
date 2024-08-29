import java.util.ArrayList;
import java.util.List;

class Quarto {
    private int numero;
    private String tipo;
    private boolean ocupado;
    private Hospedagem hospedagem;

    public Quarto(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ocupado = false;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocupar(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
        this.ocupado = true;
    }

    public void desocupar() {
        this.hospedagem = null;
        this.ocupado = false;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }
}
