import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import Enum.TipoQuarto;
import Enum.ItemConsumo;


public class Hotel {
    private static final int TOTAL_QUARTOS = 20;
    private static final String NOME_HOTEL = "Hotel Transilvânia";
    private List<Quarto> quartos;

    public Hotel() {
        quartos = new ArrayList<>();
        for (int i = 1; i <= TOTAL_QUARTOS; i++) {
            TipoQuarto tipo = (i <= 10) ? TipoQuarto.INDIVIDUAL : (i <= 15) ? TipoQuarto.ACOMPANHANTE : TipoQuarto.CRIANCA;
            quartos.add(new Quarto(i, tipo));
        }
    }

    public Quarto realizarCheckIn(String nomeResponsavel, TipoQuarto tipoQuarto, String dataInicial, String dataFinal) {
        for (Quarto quarto : quartos) {
            if (!quarto.isOcupado() && quarto.getTipo() == tipoQuarto) {
                Hospedagem hospedagem = new Hospedagem(nomeResponsavel, dataInicial, dataFinal);
                quarto.ocupar(hospedagem);
                gerarVoucher(nomeResponsavel, quarto, hospedagem);
                return quarto;
            }
        }
        throw new IllegalStateException("Não há disponibilidade de quartos para o tipo " + tipoQuarto);
    }



    public void realizarCheckOut(Quarto quarto, String dataCheckOut) {
        if (quarto.isOcupado()) {
            Hospedagem hospedagem = quarto.getHospedagem();

            long diasHospedagem = calcularDiarias(hospedagem, dataCheckOut);
            int valorDiarias = calcularValorDiarias(quarto.getTipo(), diasHospedagem);
            int valorConsumos = calcularValorConsumos(hospedagem);

            int valorTotal = valorDiarias + valorConsumos;

            gerarRecibo(quarto, hospedagem, diasHospedagem, valorTotal);

            quarto.desocupar();
        } else {
            throw new IllegalStateException("Erro: O quarto " + quarto.getNumero() + " não está ocupado.");
        }
    }



    private long calcularDiarias(Hospedagem hospedagem, String dataCheckOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = LocalDate.parse(hospedagem.getDataInicial(), formatter);
        LocalDate dataFimReal = LocalDate.parse(dataCheckOut, formatter);

        return ChronoUnit.DAYS.between(dataInicial, dataFimReal);
    }


    private int calcularValorDiarias(TipoQuarto tipoQuarto, long dias) {
        return (int) dias * tipoQuarto.getValorDiaria();
    }

    private int calcularValorConsumos(Hospedagem hospedagem) {
        int valorConsumo = 0;
        for (ItemConsumo consumo : hospedagem.getConsumos()) {
            valorConsumo += consumo.getValor();
        }
        return valorConsumo;
    }

    private void gerarRecibo(Quarto quarto, Hospedagem hospedagem, long diasHospedagem, int valorTotal) {
        System.out.println("-------------------");
        System.out.println("Recibo:");
        System.out.println("Hotel: " + NOME_HOTEL);
        System.out.println("Quarto: " + quarto.getNumero());
        System.out.println("Tipo de Quarto: " + quarto.getTipo());
        System.out.println("Responsável: " + hospedagem.getNomeResponsavel());
        System.out.println("Período: De " + hospedagem.getDataInicial() + " até " + hospedagem.getDataFinal());
        System.out.println("Dias Hospedados: " + diasHospedagem);
        System.out.println("Consumos: " + hospedagem.getConsumos());
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("-------------------");
    }



    private void gerarVoucher(String nomeResponsavel, Quarto quarto, Hospedagem hospedagem) {
        System.out.println("-------------------");
        System.out.println("Voucher:");
        System.out.println("Hotel: " + NOME_HOTEL);
        System.out.println("Quarto: " + quarto.getNumero());
        System.out.println("Tipo de Quarto: " + quarto.getTipo());
        System.out.println("Responsável: " + nomeResponsavel);
        System.out.println("Período: De " + hospedagem.getDataInicial() + " até " + hospedagem.getDataFinal());
        System.out.println("-------------------");
    }


    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        try {
            Quarto quarto1 = hotel.realizarCheckIn("João Pedro Vilar", TipoQuarto.INDIVIDUAL, "25/08/2024", "31/08/2024");
            Quarto quarto2 = hotel.realizarCheckIn("Helena Cortes", TipoQuarto.ACOMPANHANTE, "27/08/2024", "01/09/2024");

            if (quarto1 != null) {
                quarto1.getHospedagem().adicionarConsumo(ItemConsumo.AGUA);
                quarto1.getHospedagem().adicionarConsumo(ItemConsumo.CERVEJA);
            }

            if (quarto2 != null) {
                quarto2.getHospedagem().adicionarConsumo(ItemConsumo.REFRIGERANTE);
            }

            if (quarto1 != null) {
                hotel.realizarCheckOut(quarto1, "31/08/2024");
            }

            if (quarto2 != null) {
                hotel.realizarCheckOut(quarto2, "01/09/2024");
            }

        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }




}
