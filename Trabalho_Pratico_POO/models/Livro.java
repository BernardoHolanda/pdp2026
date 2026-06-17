package Trabalho_Pratico_POO.models;

// Livro herda de ItemAcervo
public class Livro extends ItemAcervo {
    private final String autor;
    private final int numeroPaginas;

    public Livro(String codigo, String titulo, int anoPublicacao, String autor, int numeroPaginas) {
        super(codigo, titulo, anoPublicacao);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    @Override
    public String exibirDetalhes() {
        return toString() + " | Autor: " + autor + " | Paginas: " + numeroPaginas;
    }
}
