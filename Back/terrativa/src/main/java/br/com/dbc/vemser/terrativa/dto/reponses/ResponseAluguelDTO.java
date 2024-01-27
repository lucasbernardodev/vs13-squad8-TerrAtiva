package br.com.dbc.vemser.terrativa.dto.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAluguelDTO {

    @NotBlank(message = "O ID do pagamento não pode ser nulo")
    private Integer pagamentoID;
    private Integer mensalidadeID;
    private Integer mesReferencia;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double taxas;
    private String codigoBarras;
    private LocalDate dataPagamento;
    private String pago;

}
