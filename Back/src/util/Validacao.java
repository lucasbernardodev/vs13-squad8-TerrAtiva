package util;

import infra.exceptions.EmptyDataException;
import models.Usuario;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe com utilitários para formatação de dados
 *
 * @author Pedro Henrique Pereira
 */
public class Validacao {
    static Scanner input = new Scanner(System.in);

    /**
     * Cores que serão usadas para melhor vizualização no Console
     */
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";


    /**
     *
     * Método que valida a entrada de Strings fornecidas para o usuário. Caso o
     * usuário forneça dados vazios ou nulos, o método imprimirá uma mensagem
     * alegando a entrada errônea de dados e irá pedir a inserção de novas
     * informações até que um valor válido seja encontrado
     *
     * @param placeholder mensagem que será mostrada ao usuário antes dele entrar
     *                    com dados
     * @return a String validada pelo método, que poderá ser usada ao longo do
     *         sistema
     */

    public static String validarString(String placeholder) {

        while (true) {
            System.out.print(placeholder);

            try {
                String stringToRead = input.nextLine();
                if (stringToRead.isBlank()) {
                    System.out.printf("%sDIGITE UM VALOR VÁLIDO!%s" + System.lineSeparator(), ANSI_RED, ANSI_RESET);

                } else {
                    return stringToRead.trim();
                }

            } catch (NoSuchElementException e) {
                System.err.println("Comando inválido! Por favor digite um texto válido!");
            }
        }
    }

    public static void validarInfoUsuario(String nomeUsuario,
                                   String email,
                                   String senha,
                                   String nome,
                                   String nascimento) throws EmptyDataException {

        if (nomeUsuario.isBlank()) throw new EmptyDataException("Seu NOME DE USUÁRIO não pode estar vazio!");
        if (email.isBlank()) throw new EmptyDataException("Seu EMAIL não pode estar vazio!");
        if (senha.isBlank()) throw new EmptyDataException("Sua SENHA não pode estar vazio!");
        if (nome.isBlank()) throw new EmptyDataException("Seu NOME não pode estar vazio!");
        if (nascimento.isBlank()) throw new EmptyDataException("Sua DATA DE NASCIMENTO não pode estar vazio!");
    }
}
