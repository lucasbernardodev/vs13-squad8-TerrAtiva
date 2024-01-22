package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.entity.Mensalidade;
import br.com.dbc.vemser.terrativa.repository.MensalidadeRepository;
import br.com.dbc.vemser.terrativa.util.validar.ValidarModel;
import org.springframework.stereotype.Service;

@Service
public class MensalidadeService {

    public final MensalidadeRepository mensalidade;

    public MensalidadeService(MensalidadeRepository mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void alterarMensalidade(Integer mensalidadeID, Integer contratoID, Double valorMensal, Integer anoExercicio) {
        ValidarModel.MENSALIDADES(valorMensal, anoExercicio);
        mensalidade.alterar(new Mensalidade(mensalidadeID, contratoID, valorMensal, anoExercicio));
    }

    public void deletarMensalidade(Integer id) {
        mensalidade.deletar(id);
    }

    public Mensalidade resgatarMensalidadePorId(Integer id) {
        return mensalidade.resgatarDadosPorId(id);
    }
}