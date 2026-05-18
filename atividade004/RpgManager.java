package atividade004;

import java.util.Scanner;

public class RpgManager {
    private final Scanner scanner;
    private final Engine engine = new Engine();

    public RpgManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startKnightBattle() {
        Personagem jogador = new Cavaleiro("Arthur");
        Dragao dragao = new Dragao("Ignis");
        engine.engine(jogador, dragao, scanner);
    }

    public void startMageBattle() {
        Personagem jogador = new Mago("Merlin");
        Dragao dragao = new Dragao("Ignis");
        engine.engine(jogador, dragao, scanner);
    }
}
