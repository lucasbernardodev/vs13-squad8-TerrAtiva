package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.RequestContratoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.requests.RequestMensalidadeCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.ResponseContratoDTO;
import br.com.dbc.vemser.terrativa.entity.Contrato;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.repository.ContratoRepository;
import br.com.dbc.vemser.terrativa.repository.UsuarioRepository;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ContratoService - Test")
class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private SessaoUsuarioService sessaoUsuarioService;

    @InjectMocks
    private ContratoService contratoService;

    @Test
    @DisplayName("Criar contrato com sucesso")
    public void criarContratoComSucesso(){
        //GIVEN
        RequestContratoCreateDTO requestContratoCreateDTOMock = retornaContratCreate();
        Contrato contratoMock = retornaContratoEntity();
        ResponseContratoDTO responseContratoDTOMock = retornaResponseContratoDTO();

        //WHEN
        when(contratoRepository.save(anyObject())).thenReturn(contratoMock);

        ResponseContratoDTO responseContratoDTO = contratoService.createContrato(requestContratoCreateDTOMock);

        //THEN
        assertNotNull(responseContratoDTO);
        assertTrue(new ReflectionEquals(responseContratoDTO).matches(responseContratoDTOMock));

    }

    @Test
    @DisplayName("Deve deletar um contrato com sucesso")
    public void deletarContratoComSucesso() throws RegraDeNegocioException {
        //GIVEN
        Optional<Contrato> contrato = Optional.of(retornaContratoEntity());

        //WHEN
        when(contratoService.verificaUsuario(anyInt())).thenReturn(null);
        when(contratoRepository.findById(anyInt())).thenReturn(contrato);

        contratoService.deletar(1);

        //THEN
        verify(contratoRepository, times(1)).delete(contrato.get());
    }


    public Contrato retornaContratoEntity(){
        Contrato cont = new Contrato();
        cont.setId(1);
        cont.setLocatarioID(2);
        cont.setTerrenoID(3);
        cont.setAtivo("S");
        cont.setDataAssinatura(LocalDate.of(2024,02,15));
        cont.setDataFinal(LocalDate.of(2024,02,15));
        cont.setDataInicio(LocalDate.of(2025,02,15));
        cont.setDataVencimentoAluguel(5);
        cont.setCriado("15/02/2024");
        cont.setEditado("15/02/2024");

        return cont;
    }

    public RequestContratoCreateDTO retornaContratCreate(){
        RequestContratoCreateDTO cont = new RequestContratoCreateDTO();
        cont.setDataAssinatura(LocalDate.of(2024,02,15));
        cont.setDataFinal(LocalDate.of(2024,02,15));
        cont.setDataInicio(LocalDate.of(2025,02,15));
        cont.setDataVencimentoAluguel(new Random().nextInt());
        cont.setMensalidade(retornaCreateMensalidade());

        return cont;
    }

    public RequestMensalidadeCreateDTO retornaCreateMensalidade(){
        RequestMensalidadeCreateDTO men = new RequestMensalidadeCreateDTO();
        men.setAnoExercicio(2024);
        men.setValorMensal(2000);

        return men;
    }

    public ResponseContratoDTO retornaResponseContratoDTO(){
        ResponseContratoDTO cont = new ResponseContratoDTO();
        cont.setId(1);
        cont.setAtivo("S");
        cont.setDataAssinatura(LocalDate.of(2024,02,15));
        cont.setDataFinal(LocalDate.of(2024,02,15));
        cont.setDataInicio(LocalDate.of(2025,02,15));
        cont.setDataVencimentoAluguel(5);

        return cont;
    }
}