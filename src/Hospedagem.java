import java.util.ArrayList;
import java.util.List;

class Hospedagem {
    private String nomeResponsavel;
    private String dataFinal;
    private List<String> consumos;

    public Hospedagem(String nomeResponsavel, String dataFinal) {
        this.nomeResponsavel = nomeResponsavel;
        this.dataFinal = dataFinal;
        this.consumos = new ArrayList<>();
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void adicionarConsumo(String item) {
        consumos.add(item);
    }

    public List<String> getConsumos() {
        return consumos;
    }
}