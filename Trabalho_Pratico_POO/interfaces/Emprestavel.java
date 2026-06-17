package Trabalho_Pratico_POO.interfaces;

import Trabalho_Pratico_POO.exceptions.ItemIndisponivelException;

// Interface pro emprestimo e devolucao dos itens
public interface Emprestavel {
    void emprestar(String codigoUsuario) throws ItemIndisponivelException;
    void devolver() throws ItemIndisponivelException;
    boolean estaDisponivel();
}
