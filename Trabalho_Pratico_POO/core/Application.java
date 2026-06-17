package Trabalho_Pratico_POO.core;

import java.util.Scanner;

public class Application {
    private final Scanner scanner = new Scanner(System.in);
    private final BibliotecaManager manager = new BibliotecaManager(scanner);
    private int option;

    public void mainMenu() {
        System.out.println("""

                ========= BIBLIOTECA =========
                | O QUE DESEJA FAZER?        |
                | 1. Cadastrar Livro         |
                | 2. Cadastrar Revista       |
                | 3. Cadastrar DVD           |
                | 4. Cadastrar Aluno         |
                | 5. Cadastrar Professor     |
                | 6. Listar Acervo           |
                | 7. Listar Usuarios         |
                | 8. Realizar Emprestimo     |
                | 9. Realizar Devolucao      |
                | 0. Encerrar Programa       |
                ==============================
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
            case 1 -> manager.cadastrarLivro();
            case 2 -> manager.cadastrarRevista();
            case 3 -> manager.cadastrarDVD();
            case 4 -> manager.cadastrarAluno();
            case 5 -> manager.cadastrarProfessor();
            case 6 -> manager.listarAcervo();
            case 7 -> manager.listarUsuarios();
            case 8 -> manager.realizarEmprestimo();
            case 9 -> manager.realizarDevolucao();
            case 0 -> System.out.println("Encerrando o programa...");
            default -> System.out.println("Comando invalido.");
        }
    }

    private int readMenuOption() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero.");
            }
        }
    }
}
