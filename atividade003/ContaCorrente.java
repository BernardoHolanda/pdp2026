public class ContaCorrente extends ContaBancaria {
    private static final double LIMITE_CHEQUE_ESPECIAL = 1000;

    @Override
    public boolean depositar(double valor) {
        if (!valorValido(valor)) {
            return false;
        }

        saldo += valor;
        return true;
    }

    @Override
    public boolean sacar(double valor) {
        if (!valorValido(valor) || valor > saldo + LIMITE_CHEQUE_ESPECIAL) {
            return false;
        }

        saldo -= valor;
        return true;
    }

    public double getLimiteChequeEspecial() {
        return LIMITE_CHEQUE_ESPECIAL;
    }

    @Override
    public String getTipoConta() {
        return "CONTA CORRENTE";
    }

    @Override
    public String toString() {
        return super.toString() + " | Limite: R$ " + String.format("%.2f", LIMITE_CHEQUE_ESPECIAL);
    }
}
