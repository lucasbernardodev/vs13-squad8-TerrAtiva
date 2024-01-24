package br.com.dbc.vemser.terrativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAluguel {


    private Integer pagamentoID;

    private Integer mensalidadeID;
    private Integer mesReferencia;

    @PastOrPresent(message = "A data de emissão deve ser no passado ou presente")
    private LocalDate dataEmissao;

    @FutureOrPresent(message = "A data de vencimento deve ser no presente ou futuro")
    private LocalDate dataVencimento;

    @NotNull(message = "As taxas não podem ser nulas")
    private Double taxas;

    private String codigoBarras;

    @FutureOrPresent(message = "A data de pagamento deve ser no presente ou futuro")
    private LocalDate dataPagamento;

    @NotBlank(message = "O campo 'pago' não pode estar em branco")
    private String pago;
}
