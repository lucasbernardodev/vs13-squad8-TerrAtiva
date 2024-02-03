package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.mappers.UsuarioMapper;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseEnderecoDTO;
import br.com.dbc.vemser.terrativa.dto.reponses.ResponseUsuarioDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioLoginDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestUsuarioUpdateDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final ContratoService contratoService;
    private final TerrenoService terrenoService;
    private final EnderecoService enderecoService;

    public ResponseUsuarioDTO buscarUsuarioPorId(Integer id) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findByUsuarioIdAndAtivoEquals(id, "S");
        if (usuario == null || usuario.getAtivo().equals("N")) {
            throw new RegraDeNegocioException("Usuário não encontrado");
        }
        return UsuarioMapper.usuarioParaResponseUsuario(usuario);
    }

    public ResponseUsuarioDTO cadastrarUsuario(RequestUsuarioCreateDTO usuario) throws RegraDeNegocioException {
        usuario.setAtivo("S");
        usuario.setUsuarioId(null);
        RequestEnderecoCreateDTO endereco = usuario.getEndereco();
        Usuario usuarioSalvo = usuarioRepository.save(UsuarioMapper.requestUsuarioParaUsuario(usuario));
        endereco.setUsuarioID(usuarioSalvo.getUsuarioId());
        ResponseEnderecoDTO enderecoDTO = enderecoService.adicionarEndereco(endereco);
        //TODO: salvar endereço para o retorno
         ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioSalvo);
         responseUsuario.setEndereco(enderecoDTO);
        //emailService.sendEmailUsuario(responseUsuario, 1);
        return responseUsuario;
    }

    public ResponseUsuarioDTO alterarUsuario(Integer idUsuario, RequestUsuarioUpdateDTO usuario) throws RegraDeNegocioException {
        buscarUsuarioPorId(idUsuario);
        usuario.setUsuarioId(idUsuario);
        Usuario usuarioAtualizado = UsuarioMapper.requestUsuarioParaUsuario(usuario);
        ResponseUsuarioDTO responseUsuario = UsuarioMapper.usuarioParaResponseUsuario(usuarioRepository.save(usuarioAtualizado));
//        emailService.sendEmailUsuario(responseUsuario, 2);
        return responseUsuario;
    }

    public void deletarUsuario(int id) throws RegraDeNegocioException {
        Usuario usuarioRecuperado = usuarioRepository.findByUsuarioIdAndAtivoEquals(id, "S");
        if (usuarioRecuperado == null) {
            throw new RegraDeNegocioException("Usuário não encontrado");
        }
        List<Contrato> listaContratos = contratoService.buscarContratoPorLocatario(usuarioRecuperado.getUsuarioId());
        for (Contrato contrato: listaContratos){
            if(contrato.getAtivo().equals("S")){
                throw new RegraDeNegocioException("Você possui contratos ativos, antes de deletar seu cadastro, finalize todos seus contratos!");
            }
        }
        terrenoService.alterarTerrenosUsuarioDeletado(id);
        usuarioRecuperado.setAtivo("N");
        usuarioRepository.save(usuarioRecuperado);
//        emailService.sendEmailUsuario(responseUsuarioDTO, 3);
    }

    public ResponseUsuarioDTO loginUsuario(RequestUsuarioLoginDTO usuario) throws RegraDeNegocioException {
        Usuario usuarioLogin = usuarioRepository.findByEmailAndSenhaAndAtivoEquals(usuario.getEmail(), usuario.getSenha(), "S");
        if (usuarioLogin == null) {
            throw new RegraDeNegocioException("Usuário não encontrado");
        }
        return UsuarioMapper.usuarioParaResponseUsuario(usuarioLogin);
    }

    public ResponseEnderecoDTO resgatarPorId(Integer id) throws RegraDeNegocioException {
        buscarUsuarioPorId(id);
        return enderecoService.resgatarPorId(id);
    }


    public ResponseEnderecoDTO alterarEndereco(Integer id, RequestEnderecoCreateDTO endereco) throws RegraDeNegocioException {
        buscarUsuarioPorId(id);
        return enderecoService.alterar(id, endereco);
    }
}