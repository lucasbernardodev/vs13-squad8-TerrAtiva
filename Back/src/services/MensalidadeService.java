package services;

import models.Endereco;
import models.Mensalidade;
import repository.MensalidadeRepository;
import util.validar.ValidarModel;

public class MensalidadeService {

    MensalidadeRepository mensalidade = new MensalidadeRepository();

    public void alterarMensalidade(Integer mensalidadeID, Integer contratoID, Double valorMensal, Integer anoExercicio) {
        ValidarModel.MENSALIDADES(valorMensal, anoExercicio);
        mensalidade.alterar(mensalidadeID, new Mensalidade(contratoID, valorMensal, anoExercicio));
    }

    public void deletarMensalidade(Integer id) {
        mensalidade.deletar(id);
    }

    public Mensalidade resgatarMensalidadePorId(Integer id) {
        return mensalidade.resgatarDadosPorId(id);
    }
}
