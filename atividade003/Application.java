package atividade003;

import java.util.Scanner;

public class Application {
    private final Scanner scanner = new Scanner(System.in);
    private final BankManager manager = new BankManager(scanner);
    private int option;

    public void mainMenu() {
        System.out.println("""

                ------------M E N U------------
                |O QUE DESEJA FAZER?          |
                |1. Cadastrar Conta Corrente; |
                |2. Cadastrar Conta Poupanca; |
                |3. Fazer Deposito;           |
                |4. Fazer Saque;              |
                |5. Fazer Transferencia;      |
                |6. Listar Contas;            |
                |0. Sair;                     |
                -------------------------------
                """);
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
            case 1 -> manager.addCheckingAccount();
            case 2 -> manager.addSavingsAccount();
            case 3 -> manager.deposit();
            case 4 -> manager.withdraw();
            case 5 -> manager.transfer();
            case 6 -> manager.listAccounts();
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
