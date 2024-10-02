package Enum;

public enum TipoQuarto {
    INDIVIDUAL(30), ACOMPANHANTE(50), CRIANCA(80);

    private final int valorDiaria;

    TipoQuarto(int valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getValorDiaria() {
        return valorDiaria;
    }
}
