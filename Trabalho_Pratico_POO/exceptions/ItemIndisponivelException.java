package Trabalho_Pratico_POO.exceptions;

// Lancada quando tenta emprestar item ja emprestado ou devolver item que nao foi emprestado
public class ItemIndisponivelException extends Exception {
    public ItemIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
