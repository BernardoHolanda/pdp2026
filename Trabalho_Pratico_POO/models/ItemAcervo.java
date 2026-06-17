package Trabalho_Pratico_POO.models;

import Trabalho_Pratico_POO.exceptions.ItemIndisponivelException;
import Trabalho_Pratico_POO.interfaces.Emprestavel;

// Superclasse abstrata dos itens do acervo, implementa Emprestavel
public abstract class ItemAcervo implements Emprestavel {
    private final String codigo;
    private final String titulo;
    private final int anoPublicacao;
    private boolean disponivel;
    private String codigoUsuarioEmprestimo;

    public ItemAcervo(String codigo, String titulo, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getCodigoUsuarioEmprestimo() {
        return codigoUsuarioEmprestimo;
    }

    // Cada subclasse mostra seus proprios detalhes
    public abstract String exibirDetalhes();

    public abstract String getTipo();

    @Override
    public void emprestar(String codigoUsuario) throws ItemIndisponivelException {
        if (!disponivel) {
            throw new ItemIndisponivelException("O item '" + titulo + "' (cod: " + codigo + ") ja esta emprestado.");
        }
        this.disponivel = false;
        this.codigoUsuarioEmprestimo = codigoUsuario;
    }

    @Override
    public void devolver() throws ItemIndisponivelException {
        if (disponivel) {
            throw new ItemIndisponivelException("O item '" + titulo + "' (cod: " + codigo + ") nao esta emprestado.");
        }
        this.disponivel = true;
        this.codigoUsuarioEmprestimo = null;
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        String status = disponivel ? "Disponivel" : "Emprestado";
        return "[" + getTipo() + "] Cod: " + codigo
                + " | Titulo: " + titulo
                + " | Ano: " + anoPublicacao
                + " | Status: " + status;
    }
}
