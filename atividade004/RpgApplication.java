package atividade004;

import java.util.Scanner;

public class RpgApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final RpgManager manager = new RpgManager(scanner);
    private boolean battleAlreadyStarted;
    private int option;

    public void mainMenu() {
        String title = battleAlreadyStarted ? "JOGAR NOVAMENTE?" : "ESCOLHA SEU PERSONAGEM";
        String formattedTitle = String.format("%-27s", title);
        System.out.println("""

                ------------R P G-------------
                |%s|
                |1. Cavaleiro;              |
                |2. Mago;                   |
                |0. Sair;                   |
                ------------------------------
                """.formatted(formattedTitle));
    }

    public void mainMenuDisplay() {
        do {
            mainMenu();
            option = readMenuOption();
            processMainMenuChoice();
        } while (option != 0);

        scanner.close();
    }

    public void processMainMenuChoice() {
        switch (option) {
            case 1 -> {
                battleAlreadyStarted = true;
                manager.startKnightBattle();
            }
            case 2 -> {
                battleAlreadyStarted = true;
                manager.startMageBattle();
            }
            case 0 -> System.out.println("Saindo.........");
            default -> System.out.println("Comando invalido");
        }
    }

    private int readMenuOption() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("Entrada invalida.");
                scanner.nextLine();
            }
        }
    }
}
