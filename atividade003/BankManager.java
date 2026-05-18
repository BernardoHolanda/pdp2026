package atividade003;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankManager {
    private final Scanner scanner;
    private final Map<String, ContaBancaria> accounts = new HashMap<>();

    public BankManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addCheckingAccount() {
        ContaCorrente account = new ContaCorrente();
        fillAccountData(account);

        accounts.put(account.getNumero(), account);
        System.out.println("Conta corrente cadastrada com sucesso.");
    }

    public void addSavingsAccount() {
        ContaPoupanca account = new ContaPoupanca();
        fillAccountData(account);

        accounts.put(account.getNumero(), account);
        System.out.println("Conta poupanca cadastrada com sucesso.");
    }

    public void deposit() {
        ContaBancaria account = findAccountByNumber();
        if (account == null) {
            return;
        }

        double value = readMoney("Valor do deposito: ");
        if (account.depositar(value)) {
            System.out.println("Deposito realizado com sucesso.");
            System.out.println(account);
            return;
        }

        System.out.println("Nao foi possivel realizar o deposito.");
    }

    public void withdraw() {
        ContaBancaria account = findAccountByNumber();
        if (account == null) {
            return;
        }

        double value = readMoney("Valor do saque: ");
        if (account.sacar(value)) {
            System.out.println("Saque realizado com sucesso.");
            System.out.println(account);
            return;
        }

        System.out.println("Saldo insuficiente para realizar o saque.");
    }

    public void transfer() {
        if (accounts.size() < 2) {
            System.out.println("Cadastre pelo menos duas contas para transferir.");
            return;
        }

        ContaBancaria sourceAccount = findAccountByNumber("Informe a conta de origem ou 0 para cancelar:");
        if (sourceAccount == null) {
            return;
        }

        ContaBancaria targetAccount = findAccountByNumber("Informe a conta de destino ou 0 para cancelar:");
        if (targetAccount == null) {
            return;
        }

        if (sourceAccount == targetAccount) {
            System.out.println("A conta de origem deve ser diferente da conta de destino.");
            return;
        }

        double value = readMoney("Valor da transferencia: ");
        if (sourceAccount.transferir(targetAccount, value)) {
            System.out.println("Transferencia realizada com sucesso.");
            System.out.println("Origem: " + sourceAccount);
            System.out.println("Destino: " + targetAccount);
            return;
        }

        System.out.println("Saldo insuficiente para realizar a transferencia.");
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        System.out.println("=== LISTA DE CONTAS ===");
        for (ContaBancaria account : accounts.values()) {
            System.out.println(account);
        }
    }

    private void fillAccountData(ContaBancaria account) {
        account.setTitular(readName("Nome do titular: "));
        account.setNumero(readUniqueAccountNumber("Numero da conta: "));
    }

    private ContaBancaria findAccountByNumber() {
        return findAccountByNumber("Informe o numero da conta ou 0 para cancelar:");
    }

    private ContaBancaria findAccountByNumber(String prompt) {
        if (accounts.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return null;
        }

        while (true) {
            String number = readLookupCode(prompt, "Numero da conta nao pode estar vazio.");
            if (number == null) {
                return null;
            }

            ContaBancaria account = accounts.get(number);
            if (account != null) {
                return account;
            }

            System.out.println("Conta nao encontrada.");
        }
    }

    private String readName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String name = scanner.nextLine().trim().replaceAll("\\s+", " ");

            if (name.isBlank()) {
                System.out.println("Nome nao pode estar vazio.");
                continue;
            }

            if (!name.matches("^[\\p{L}]+(?: [\\p{L}]+)*$")) {
                System.out.println("Nome invalido. Use apenas letras e espacos.");
                continue;
            }

            return name.toUpperCase(java.util.Locale.ROOT);
        }
    }

    private String readUniqueAccountNumber(String prompt) {
        while (true) {
            System.out.println(prompt);
            String number = normalizeCode(scanner.nextLine());

            if (number.isBlank()) {
                System.out.println("Numero da conta nao pode estar vazio.");
                continue;
            }

            if (!number.matches("^[A-Za-z0-9]+$")) {
                System.out.println("Numero invalido. Use apenas letras e numeros.");
                continue;
            }

            if (accounts.containsKey(number)) {
                System.out.println("Conta ja cadastrada.");
                continue;
            }

            return number;
        }
    }

    private String readLookupCode(String prompt, String emptyMessage) {
        while (true) {
            System.out.println(prompt);
            String code = normalizeCode(scanner.nextLine());

            if (code.equals("0")) {
                System.out.println("Operacao cancelada.");
                return null;
            }

            if (code.isBlank()) {
                System.out.println(emptyMessage);
                continue;
            }

            return code;
        }
    }

    private double readMoney(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();

                if (value <= 0) {
                    System.out.println("Valor deve ser maior que zero.");
                    continue;
                }

                return value;
            } catch (Exception e) {
                System.out.println("Entrada invalida. Digite um numero.");
                scanner.nextLine();
            }
        }
    }

    private String normalizeCode(String raw) {
        return raw.replaceAll("\\s+", "").toUpperCase(java.util.Locale.ROOT);
    }
}
