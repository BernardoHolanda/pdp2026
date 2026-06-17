package Trabalho_Pratico_POO.exceptions;

// Lancada quando o codigo do usuario nao existe
public class UsuarioNaoEncontradoException extends Exception {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
