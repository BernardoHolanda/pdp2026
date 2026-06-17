package Trabalho_Pratico_POO.models;

// Revista herda de ItemAcervo
public class Revista extends ItemAcervo {
    private final int edicao;
    private final String editora;

    public Revista(String codigo, String titulo, int anoPublicacao, int edicao, String editora) {
        super(codigo, titulo, anoPublicacao);
        this.edicao = edicao;
        this.editora = editora;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String exibirDetalhes() {
        return toString() + " | Edicao: " + edicao + " | Editora: " + editora;
    }
}
