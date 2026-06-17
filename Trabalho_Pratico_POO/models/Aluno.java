package Trabalho_Pratico_POO.models;

// Aluno herda de Usuario
public class Aluno extends Usuario {
    private final String curso;

    public Aluno(String codigo, String nome, String curso) {
        super(codigo, nome);
        this.curso = curso;
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    // Aluno tem 7 dias pra devolver
    @Override
    public int getPrazoDevolucaoDias() {
        return 7;
    }

    @Override
    public String toString() {
        return super.toString() + " | Curso: " + curso;
    }
}
