import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static final int TOTAL_QUARTOS = 20;
    private static final String NOME_HOTEL = "Hotel Transilvânia";
    private List<Quarto> quartos;

    public Hotel() {
        quartos = new ArrayList<>();
        for (int i = 1; i <= TOTAL_QUARTOS; i++) {
            String tipo = (i <= 10) ? "individual" : (i <= 15) ? "acompanhante" : "criança";
            quartos.add(new Quarto(i, tipo));
        }
    }

    public Quarto realizarCheckIn(String nomeResponsavel, String tipoQuarto, String dataFinal) {
        for (Quarto quarto : quartos) {
            if (!quarto.isOcupado() && quarto.getTipo().equals(tipoQuarto)) {
                Hospedagem hospedagem = new Hospedagem(nomeResponsavel, dataFinal);
                quarto.ocupar(hospedagem);
                System.out.println("-------------------");
                System.out.println("Voucher:");
                System.out.println("Hotel: " + NOME_HOTEL);
                System.out.println("Quarto: " + quarto.getNumero());
                System.out.println("Tipo de Quarto: " + quarto.getTipo());
                System.out.println("Responsável: " + nomeResponsavel);
                System.out.println("Período: Até " + hospedagem.getDataFinal());
                System.out.println("-------------------");
                return quarto;
            }
        }
        System.out.println("Desculpe, não há disponibilidade de quartos para o tipo solicitado.");
        return null;
    }

    public void realizarCheckOut(Quarto quarto, String dataFinal) {
        if (quarto.isOcupado()) {
            Hospedagem hospedagem = quarto.getHospedagem();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataInicial = LocalDate.now();
            LocalDate dataFim = LocalDate.parse(hospedagem.getDataFinal(), formatter);
            long diasHospedagem = ChronoUnit.DAYS.between(dataInicial, dataFim);

            int valorDiaria = 0;

            switch (quarto.getTipo()) {
                case "individual":
                    valorDiaria = 30;
                    break;
                case "acompanhante":
                    valorDiaria = 50;
                    break;
                case "criança":
                    valorDiaria = 80;
                    break;
            }

            int valorTotalHospedagem = (int) diasHospedagem * valorDiaria;
            int valorConsumo = 0;

            for (String consumo : hospedagem.getConsumos()) {
                switch (consumo) {
                    case "água":
                        valorConsumo += 5;
                        break;
                    case "refrigerante":
                        valorConsumo += 8;
                        break;
                    case "cerveja":
                        valorConsumo += 10;
                        break;
                }
            }

            valorTotalHospedagem += valorConsumo;

            System.out.println("-------------------");
            System.out.println("Recibo:");
            System.out.println("Hotel: " + NOME_HOTEL);
            System.out.println("Quarto: " + quarto.getNumero());
            System.out.println("Tipo de Quarto: " + quarto.getTipo());
            System.out.println("Responsável: " + hospedagem.getNomeResponsavel());
            System.out.println("Período: Até " + hospedagem.getDataFinal());
            System.out.println("Quantidade de Pernoites: " + diasHospedagem);
            System.out.println("Consumos: " + hospedagem.getConsumos());
            System.out.println("Valor Total: R$" + valorTotalHospedagem);
            System.out.println("-------------------");

            quarto.desocupar();
        } else {
            System.out.println("O quarto não está ocupado.");
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Quarto quarto1 = hotel.realizarCheckIn("João Pedro Vilar", "individual", "31/08/2024");
        Quarto quarto2 = hotel.realizarCheckIn("Helena Cortes", "acompanhante", "01/09/2024");

        if (quarto1 != null) {
            quarto1.getHospedagem().adicionarConsumo("água");
            quarto1.getHospedagem().adicionarConsumo("cerveja");
        }

        if (quarto2 != null) {
            quarto2.getHospedagem().adicionarConsumo("refrigerante");
        }

        if (quarto1 != null) {
            hotel.realizarCheckOut(quarto1, "31/08/2024");
        }

        if (quarto2 != null) {
            hotel.realizarCheckOut(quarto2, "01/09/2024");
        }
    }
}
