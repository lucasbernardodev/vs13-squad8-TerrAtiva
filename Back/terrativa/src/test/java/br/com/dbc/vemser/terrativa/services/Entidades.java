package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.requests.RequestEnderecoCreateDTO;
import br.com.dbc.vemser.terrativa.dto.responses.relatorios.ResponseContratoRelatorioDTO;
import br.com.dbc.vemser.terrativa.entity.*;

import java.time.LocalDate;

public class Entidades {

    public static Usuario retornaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(2);
        usuario.setNome("João");
        usuario.setSobrenome("Silva");
        usuario.setEmail("joão@email.com");
        usuario.setCpf("12345678910");
        usuario.setDataNascimento(LocalDate.of(2024,02,15));
        usuario.setSexo("M");
        usuario.setAtivo("S");
        usuario.setCelular("123456789");
        usuario.setTelefoneFixo("123456789");

        return usuario;
    }

    public static Terreno retornaTerreno(){
        Terreno terreno = new Terreno();
        terreno.setId(1);
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

    public static Contrato retornaContratoEntity(){
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
        cont.setDataAssinatura(LocalDate.of(2024,02,15));
        cont.setDataFinal(LocalDate.of(2024,02,15));
        cont.setDataInicio(LocalDate.of(2025,02,15));
        cont.setDataVencimentoAluguel(5);
        cont.setNomeLocatario("João");
        cont.setSobrenomeLocatario("Silva");
        cont.setEmailLocatario("joão@email.com");
        cont.setCpfLocatario("12345678910");
        cont.setDataNascimentoLocatario(LocalDate.of(2024,02,15));
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
        cont.setDataNascimentoDono(LocalDate.of(2024,02,15));
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
}
