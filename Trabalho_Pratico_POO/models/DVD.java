package Trabalho_Pratico_POO.models;

// DVD herda de ItemAcervo
public class DVD extends ItemAcervo {
    private final String diretor;
    private final int duracaoMinutos;

    public DVD(String codigo, String titulo, int anoPublicacao, String diretor, int duracaoMinutos) {
        super(codigo, titulo, anoPublicacao);
        this.diretor = diretor;
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String getTipo() {
        return "DVD";
    }

    @Override
    public String exibirDetalhes() {
        return toString() + " | Diretor: " + diretor + " | Duracao: " + duracaoMinutos + " min";
    }
}
