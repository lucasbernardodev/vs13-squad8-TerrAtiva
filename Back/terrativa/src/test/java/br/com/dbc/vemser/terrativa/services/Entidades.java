package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.*;
import br.com.dbc.vemser.terrativa.dto.responses.*;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Entidades {

    // USUARIO
    public static Usuario retornaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(2);
        usuario.setNome("João");
        usuario.setSobrenome("Silva");
        usuario.setEmail("joão@email.com");
        usuario.setCpf("12345678910");
        usuario.setDataNascimento(LocalDate.of(2024,2,15));
        usuario.setSexo("M");
        usuario.setAtivo("S");
        usuario.setCelular("123456789");
        usuario.setTelefoneFixo("123456789");

        return usuario;
    }

    public static ResponseUsuarioDTO retornaResponseUsuarioDTO(){
        ResponseUsuarioDTO usuario = new ResponseUsuarioDTO();
        usuario.setUsuarioId(2);
        usuario.setNome("João");
        usuario.setSobrenome("Silva");
        usuario.setEmail("joão@email.com");
        usuario.setCpf("12345678910");
        usuario.setDataNascimento(LocalDate.of(2024,2,15));
        usuario.setSexo("M");
        usuario.setCelular("123456789");
        usuario.setTelefoneFixo("123456789");

        return usuario;
    }

    public static RequestUsuarioUpdateDTO retornaRequestUsuarioUpdateDTO() {
        Usuario usuario = retornaUsuario();
        RequestUsuarioUpdateDTO dto = new RequestUsuarioUpdateDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNome(usuario.getNome());
        dto.setSobrenome(usuario.getSobrenome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setSexo(usuario.getSexo());
        dto.setCelular(usuario.getCelular());
        dto.setTelefoneFixo(usuario.getTelefoneFixo());
        return dto;
    }

    public static RequestUsuarioCreateDTO retornaRequestUsuarioCreateDTO() {
        Usuario usuario = retornaUsuario();
        RequestUsuarioCreateDTO dto = new RequestUsuarioCreateDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNome(usuario.getNome());
        dto.setSobrenome(usuario.getSobrenome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha("senha123");
        dto.setCpf(usuario.getCpf());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setSexo(usuario.getSexo());
        dto.setCelular(usuario.getCelular());
        dto.setTelefoneFixo(usuario.getTelefoneFixo());
        dto.setEndereco(retornaRequestEnderecoCreateDTO());
        return dto;
    }

    public static RequestAdminDTO retornaRequestAdminDTO() {
        Usuario usuario = retornaUsuario();
        RequestAdminDTO dto = new RequestAdminDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNome(usuario.getNome());
        dto.setSobrenome(usuario.getSobrenome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha("senha123");
        dto.setCpf(usuario.getCpf());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setSexo(usuario.getSexo());
        dto.setCelular(usuario.getCelular());
        dto.setTelefoneFixo(usuario.getTelefoneFixo());
        return dto;
    }

    public static ResponseAdminDTO retornaResponseAdminDTO() {
        Usuario usuario = retornaUsuario();
        ResponseAdminDTO dto = new ResponseAdminDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNome(usuario.getNome());
        dto.setSobrenome(usuario.getSobrenome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setSexo(usuario.getSexo());
        dto.setCelular(usuario.getCelular());
        dto.setTelefoneFixo(usuario.getTelefoneFixo());

        return dto;
    }

    // CARGOS
    public static Set<Cargo> retornaCargos(){
        Set<Cargo> cargos = new HashSet<>();
        Cargo cargo = new Cargo();
        cargo.setIdCargo(2);
        cargo.setNome("ROLE_USUARIOS");
        cargos.add(cargo);

        Cargo cargo2 = new Cargo();
        cargo2.setIdCargo(1);
        cargo2.setNome("ROLE_ADMIN");
        cargos.add(cargo2);

        return cargos;
    }

    // TERRENO

    public static Terreno retornaTerreno(){
        Terreno terreno = new Terreno();
        terreno.setId(1);
        terreno.setEnderecoID(1);
        terreno.setTitulo("Terreno para Compra");
        terreno.setDescricao("Descrição do Terreno");
        terreno.setProprietarioID(2);
        terreno.setPreco(2000);
        terreno.setTamanho("1500");
        terreno.setDisponivel("S");
        terreno.setDono(retornaUsuario());
        terreno.setEnderecoTerrenoID(retornaEnderecoTerreno());

        return terreno;
    }

    public static ResponseTerrenoDTO retronaResponseTerrenoDTO(){
        ResponseTerrenoDTO res = new ResponseTerrenoDTO();
        res.setId(1);
        res.setTitulo("Terreno para Compra");
        res.setDescricao("Descrição do Terreno");
        res.setProprietarioID(2);
        res.setPreco(2000);
        res.setTamanho("1500");
        res.setDisponivel("S");
        res.setEndereco(responseEnderecoTerrenosDTO());

        return res;
    }

    public static Contrato retornaContratoEntity(){
        Contrato cont = new Contrato();
        cont.setId(1);
        cont.setLocatarioID(2);
        cont.setTerrenoID(3);
        cont.setAtivo("S");
        cont.setDataAssinatura(LocalDate.of(2024,2,15));
        cont.setDataFinal(LocalDate.of(2024,2,15));
        cont.setDataInicio(LocalDate.of(2025,2,15));
        cont.setDataVencimentoAluguel(5);
        cont.setCriado("15/02/2024");
        cont.setEditado("15/02/2024");
        cont.setTerreno(retornaTerreno());

        return cont;
    }

    public static EnderecoTerrenos retornaEnderecoTerreno(){
        EnderecoTerrenos end = new EnderecoTerrenos();
        end.setId(1);
        end.setLogradouro("Avenida Paulista");
        end.setNumero(123);
        end.setComplemento(null);
        end.setBairro("Jardins");
        end.setCodMunicipioIBGE(12345);
        end.setCep(12345678);
        end.setLocalizacao("12345");
        end.setCodIBGE(retornaEstadosMunicipios());

        return end;
    }

    public static RequestEnderecoCreateDTO retornaRequestEnderecoCreateDTO(){
        RequestEnderecoCreateDTO end = new RequestEnderecoCreateDTO(1, 1, "Avenida Paulista", 1500,
                "Jardins", "Jardins", 12345, 12345678);
        end.setUsuarioID(1);
        return end;
    }

    public static ResponseEnderecoTerrenosDTO responseEnderecoTerrenosDTO(){
        ResponseEnderecoTerrenosDTO res = new ResponseEnderecoTerrenosDTO();
        res.setId(1);
        res.setLogradouro("Avenida Paulista");
        res.setNumero(123);
        res.setComplemento(null);
        res.setBairro("Jardins");
        res.setCodigoMunicipioIBGE(12345);
        res.setCep(12345678);
        res.setLocalizacao("12345");

        return res;
    }



    public static Endereco retornaEnderecoEntityMock(){
        Endereco end = new Endereco();
        end.setId(1);
        end.setLogradouro("Avenida Paulista");
        end.setNumero(123);
        end.setComplemento(null);
        end.setBairro("Jardins");
        end.setCodMunIBGE(12345);
        end.setCep(12345678);
        end.setCodIBGE(retornaEstadosMunicipios());
        end.setUsuario(retornaUsuario());
        end.setUsuarioID(1);
        end.getUsuario().setUsuarioId(1);

        return end;
    }

    public static EstadosMunicipios retornaEstadosMunicipios(){
        EstadosMunicipios est = new EstadosMunicipios();
        est.setMunicipioCodIBGE(12345);
        est.setNomeMunicipio("Porto Alegre");
        est.setEstadoCod(123456);
        est.setNomeEstado("Rio Grande Do Sul");

        return est;
    }

    public static Mensalidade retornaMensalidade(){
        Mensalidade men = new Mensalidade();
        men.setMensalidadeID(1);
        men.setContratoID(1);
        men.setValorMensal(2000);
        men.setAnoExercicio(2024);
        men.setAtivo("S");

        return men;
    }

    public static ResponseContratoRelatorioDTO retornaRelatorioContrato(){
        ResponseContratoRelatorioDTO cont = new ResponseContratoRelatorioDTO();
        cont.setIdContrato(1);
        cont.setAtivo("S");
        cont.setDataAssinatura(LocalDate.of(2024,2,15));
        cont.setDataFinal(LocalDate.of(2024,2,15));
        cont.setDataInicio(LocalDate.of(2025,2,15));
        cont.setDataVencimentoAluguel(5);
        cont.setNomeLocatario("João");
        cont.setSobrenomeLocatario("Silva");
        cont.setEmailLocatario("joão@email.com");
        cont.setCpfLocatario("12345678910");
        cont.setDataNascimentoLocatario(LocalDate.of(2024,2,15));
        cont.setSexoLocatario("M");
        cont.setCelularLocatario("123456789");
        cont.setTelefoneFixoLocatario("123456789");
        cont.setIdTerreno(1);
        cont.setPreco(2000.0);
        cont.setTamanho("1500");
        cont.setNomeDono("João");
        cont.setSobrenomeDono("Silva");
        cont.setEmailDono("joão@email.com");
        cont.setCpfDono("12345678910");
        cont.setDataNascimentoDono(LocalDate.of(2024,2,15));
        cont.setSexoDono("M");
        cont.setCelularDono("123456789");
        cont.setTelefoneFixoDono("123456789");
        cont.setLogradouro("Avenida Paulista");
        cont.setNumero(123);
        cont.setComplemento(null);
        cont.setBairro("Jardins");
        cont.setCep(12345678);
        cont.setLocalizacao("12345");
        cont.setNomeMunicipio("Porto Alegre");
        cont.setNomeEstado("Rio Grande Do Sul");


        return cont;
    }

    public static RequestNotificacoesDTO retornaRequestNotificacoesDTO(){
        RequestNotificacoesDTO res = new RequestNotificacoesDTO();
        res.setTitulo("Novo Terreno");
        res.setUsuarios(List.of(1, 2 ,3));

        return res;
    }

    public static ResponseNotificacoesDTO retornaResponseNotificacoesDTO(){
        ResponseNotificacoesDTO res = new ResponseNotificacoesDTO();
        res.setId("1");
        res.setTitulo("Novo Terreno");
        res.setUsuarios(List.of(1, 2 ,3));

        return res;
    }

    public static Notificacoes retornaNotificacoes(){
        Notificacoes res = new Notificacoes();
        res.setId("1");
        res.setTitulo("Novo Terreno");
        res.setUsuarios(List.of(1, 2 ,3));
        res.setData(LocalDate.now());

        return res;
    }

}
