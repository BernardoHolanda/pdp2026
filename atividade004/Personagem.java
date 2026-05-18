package atividade004;

public abstract class Personagem {
    private final String nome;
    private final int vidaMaxima;
    private final int manaMaxima;
    private int vida;
    private int mana;

    public Personagem(String nome, int vidaMaxima, int manaMaxima) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.manaMaxima = manaMaxima;
        this.vida = vidaMaxima;
        this.mana = manaMaxima;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    public abstract String getTipo();

    public abstract String getNomeAtaqueNormal();

    public abstract String getNomeAtaqueEspecial();

    public abstract int getDanoAtaqueNormal();

    public abstract int getDanoAtaqueEspecial();

    public abstract int getCustoManaAtaqueEspecial();

    public boolean estaVivo() {
        return vida > 0;
    }

    public boolean podeUsarAtaqueEspecial() {
        return mana >= getCustoManaAtaqueEspecial();
    }

    public String atacarNormal(Personagem alvo) {
        alvo.receberDano(getDanoAtaqueNormal());
        return nome + " usou " + getNomeAtaqueNormal() + " e causou " + getDanoAtaqueNormal() + " de dano.";
    }

    public String atacarEspecial(Personagem alvo) {
        if (!podeUsarAtaqueEspecial()) {
            return nome + " tentou usar " + getNomeAtaqueEspecial() + ", mas nao tinha mana suficiente.";
        }

        mana -= getCustoManaAtaqueEspecial();
        alvo.receberDano(getDanoAtaqueEspecial());
        return nome + " usou " + getNomeAtaqueEspecial() + " e causou " + getDanoAtaqueEspecial()
                + " de dano. Mana gasta: " + getCustoManaAtaqueEspecial() + ".";
    }

    public void receberDano(int dano) {
        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }
    }

    public String getStatus() {
        return getTipo() + " - " + nome
                + "\nVida " + buildBar(vida, vidaMaxima)
                + "\nMana " + buildBar(mana, manaMaxima);
    }

    private String buildBar(int value, int maxValue) {
        int totalBlocks = 20;
        int filledBlocks = (int) Math.round((value / (double) maxValue) * totalBlocks);
        StringBuilder bar = new StringBuilder("[");

        for (int block = 0; block < totalBlocks; block++) {
            if (block < filledBlocks) {
                bar.append("#");
            } else {
                bar.append("-");
            }
        }

        bar.append("] ").append(value).append("/").append(maxValue);
        return bar.toString();
    }

    @Override
    public String toString() {
        return "Tipo: " + getTipo()
                + " | Nome: " + nome
                + " | Vida: " + vida
                + " | Mana: " + mana;
    }
}
