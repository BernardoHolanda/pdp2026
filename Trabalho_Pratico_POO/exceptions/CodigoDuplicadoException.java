package Trabalho_Pratico_POO.exceptions;

// Lancada quando tenta cadastrar com um codigo que ja existe
public class CodigoDuplicadoException extends Exception {
    public CodigoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
