package atividade004;

public class Cavaleiro extends Personagem {
    public Cavaleiro(String nome) {
        super(nome, 150, 60);
    }

    @Override
    public String getTipo() {
        return "CAVALEIRO";
    }

    @Override
    public String getNomeAtaqueNormal() {
        return "Golpe de Espada";
    }

    @Override
    public String getNomeAtaqueEspecial() {
        return "Investida Heroica";
    }

    @Override
    public int getDanoAtaqueNormal() {
        return 25;
    }

    @Override
    public int getDanoAtaqueEspecial() {
        return 45;
    }

    @Override
    public int getCustoManaAtaqueEspecial() {
        return 20;
    }
}
