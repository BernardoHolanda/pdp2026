package Trabalho_Pratico_POO.exceptions;

// Lancada quando o codigo do item nao existe no acervo
public class ItemNaoEncontradoException extends Exception {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
