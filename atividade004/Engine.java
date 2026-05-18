package atividade004;

import java.util.Random;
import java.util.Scanner;

public class Engine {
    private final Random random = new Random();

    public void engine(Personagem jogador, Dragao dragao, Scanner scanner) {
        if (jogador == null || dragao == null) {
            System.out.println("Personagem invalido para iniciar a batalha.");
            return;
        }

        System.out.println("=== ENGINE ===");
        System.out.println(jogador.getNome() + " encontrou " + dragao.getNome() + ". A batalha comecou!");

        while (jogador.estaVivo() && dragao.estaVivo()) {
            showBattleStatus(jogador, dragao);
            int option = readBattleOption(scanner);

            if (option == 0) {
                System.out.println("Voce fugiu da batalha.");
                return;
            }

            if (option == 2 && !jogador.podeUsarAtaqueEspecial()) {
                System.out.println("Mana insuficiente para usar ataque especial.");
                continue;
            }

            executePlayerTurn(jogador, dragao, option);
            if (!dragao.estaVivo()) {
                break;
            }

            executeDragonTurn(dragao, jogador);
        }

        showBattleStatus(jogador, dragao);
        if (jogador.estaVivo()) {
            System.out.println("Vitoria! " + jogador.getNome() + " derrotou o dragao.");
            System.out.println("Agora caia nas gracas da princesa.");
        } else {
            System.out.println("Derrota! " + dragao.getNome() + " venceu a batalha.");
        }
    }

    private void executePlayerTurn(Personagem jogador, Dragao dragao, int option) {
        if (option == 1) {
            System.out.println(jogador.atacarNormal(dragao));
            return;
        }

        System.out.println(jogador.atacarEspecial(dragao));
    }

    private void executeDragonTurn(Dragao dragao, Personagem jogador) {
        boolean useSpecialAttack = random.nextBoolean() && dragao.podeUsarAtaqueEspecial();

        if (useSpecialAttack) {
            System.out.println(dragao.atacarEspecial(jogador));
            return;
        }

        System.out.println(dragao.atacarNormal(jogador));
    }

    private void showBattleStatus(Personagem jogador, Dragao dragao) {
        System.out.println();
        System.out.println("=== STATUS DA BATALHA ===");
        System.out.println(jogador.getStatus());
        System.out.println(dragao.getStatus());
        System.out.println();
    }

    private int readBattleOption(Scanner scanner) {
        while (true) {
            System.out.println("Escolha seu ataque:");
            System.out.println("1. Ataque normal");
            System.out.println("2. Ataque especial");
            System.out.println("0. Fugir");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 0 || option == 1 || option == 2) {
                    return option;
                }

                System.out.println("Opcao invalida. Use 0, 1 ou 2.");
            } catch (Exception e) {
                System.out.println("Entrada invalida. Digite um numero.");
                scanner.nextLine();
            }
        }
    }
}
