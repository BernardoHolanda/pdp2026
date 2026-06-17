package Trabalho_Pratico_POO.models;

// Professor herda de Usuario
public class Professor extends Usuario {
    private final String departamento;

    public Professor(String codigo, String nome, String departamento) {
        super(codigo, nome);
        this.departamento = departamento;
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    // Professor tem 14 dias pra devolver
    @Override
    public int getPrazoDevolucaoDias() {
        return 14;
    }

    @Override
    public String toString() {
        return super.toString() + " | Departamento: " + departamento;
    }
}
