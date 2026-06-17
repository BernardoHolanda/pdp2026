package Trabalho_Pratico_POO.models;

// Superclasse abstrata dos usuarios
public abstract class Usuario {
    private final String codigo;
    private final String nome;

    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public abstract String getTipo();

    // Prazo diferente pra aluno e professor
    public abstract int getPrazoDevolucaoDias();

    @Override
    public String toString() {
        return "[" + getTipo() + "] Cod: " + codigo
                + " | Nome: " + nome
                + " | Prazo de devolucao: " + getPrazoDevolucaoDias() + " dias";
    }
}
