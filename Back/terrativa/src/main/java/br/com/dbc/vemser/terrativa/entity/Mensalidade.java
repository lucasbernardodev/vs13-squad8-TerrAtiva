package br.com.dbc.vemser.terrativa.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "MENSALIDADES")
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENSALIDADE")
    @SequenceGenerator(name = "SEQ_MENSALIDADE", sequenceName = "SEQ_MENSALIDADE", allocationSize = 1)
    @Column(name = "mensalidade_id")
    private Integer mensalidadeID;

    @Column(name = "contrato_id")
    private Integer contratoID;

    @Column(name = "valor_mensal")
    private double valorMensal;

    @Column(name = "ano_exercicio")
    private Integer anoExercicio;

    @Column(name = "ativo")
    private String ativo;

}
