package atividade004;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 100, 120);
    }

    @Override
    public String getTipo() {
        return "MAGO";
    }

    @Override
    public String getNomeAtaqueNormal() {
        return "Rajada Arcana";
    }

    @Override
    public String getNomeAtaqueEspecial() {
        return "Meteoro";
    }

    @Override
    public int getDanoAtaqueNormal() {
        return 20;
    }

    @Override
    public int getDanoAtaqueEspecial() {
        return 60;
    }

    @Override
    public int getCustoManaAtaqueEspecial() {
        return 35;
    }
}
