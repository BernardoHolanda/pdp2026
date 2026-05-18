package atividade004;

public class Dragao extends Personagem {
    public Dragao(String nome) {
        super(nome, 180, 70);
    }

    @Override
    public String getTipo() {
        return "DRAGAO";
    }

    @Override
    public String getNomeAtaqueNormal() {
        return "Garra Flamejante";
    }

    @Override
    public String getNomeAtaqueEspecial() {
        return "Sopro Infernal";
    }

    @Override
    public int getDanoAtaqueNormal() {
        return 18;
    }

    @Override
    public int getDanoAtaqueEspecial() {
        return 35;
    }

    @Override
    public int getCustoManaAtaqueEspecial() {
        return 30;
    }
}
