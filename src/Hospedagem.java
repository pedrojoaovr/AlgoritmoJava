import java.util.ArrayList;
import java.util.List;
import Enum.ItemConsumo;


class Hospedagem {
    private String nomeResponsavel;
    private String dataFinal;
    private String dataInicial;
    private List<ItemConsumo> consumos;

    public Hospedagem(String nomeResponsavel, String dataInicial, String dataFinal) {
        this.nomeResponsavel = nomeResponsavel;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.consumos = new ArrayList<>();
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void adicionarConsumo(ItemConsumo item) {
        consumos.add(item);
    }

    public List<ItemConsumo> getConsumos() {
        return consumos;
    }
}