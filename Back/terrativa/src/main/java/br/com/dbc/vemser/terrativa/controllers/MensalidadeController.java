package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.services.MensalidadeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensalidadeController {

    public final MensalidadeService mensalidadeService;

    public MensalidadeController(MensalidadeService mensalidadeService) {
        this.mensalidadeService = mensalidadeService;
    }

    public String atualizarMensalidade(Integer mensalidadeID, Integer contratoID, Double valorMensal, Integer anoExercicio) {
            mensalidadeService.alterarMensalidade(mensalidadeID, contratoID, valorMensal, anoExercicio);
            return "Mensalidade Atualizada com Sucesso!";
    }

    public String resgatarEnderecoPorID(Integer id){
            return mensalidadeService.resgatarMensalidadePorId(id).toString();
    }

    public String deletarMensalidade(Integer id) {
            mensalidadeService.deletarMensalidade(id);
            return "Mensalidade deletada com Sucesso";
    }
}
