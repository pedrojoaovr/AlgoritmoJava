package Enum;

public enum ItemConsumo {
    AGUA(5), REFRIGERANTE(8), CERVEJA(10);

    private final int valor;

    ItemConsumo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
