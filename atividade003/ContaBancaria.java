package atividade003;

public abstract class ContaBancaria {
    private String titular;
    private String numero;
    protected double saldo;

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract boolean depositar(double valor);

    public abstract boolean sacar(double valor);

    public boolean transferir(ContaBancaria contaDestino, double valor) {
        if (contaDestino == null || !valorValido(valor)) {
            return false;
        }

        if (!sacar(valor)) {
            return false;
        }

        contaDestino.depositar(valor);
        return true;
    }

    protected boolean valorValido(double valor) {
        return valor > 0;
    }

    public abstract String getTipoConta();

    @Override
    public String toString() {
        return "Tipo: " + getTipoConta()
                + " | Titular: " + titular
                + " | Conta: " + numero
                + " | Saldo: R$ " + String.format("%.2f", saldo);
    }
}
