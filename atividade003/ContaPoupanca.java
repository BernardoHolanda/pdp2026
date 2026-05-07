public class ContaPoupanca extends ContaBancaria {
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
        if (!valorValido(valor) || valor > saldo) {
            return false;
        }

        saldo -= valor;
        return true;
    }

    @Override
    public String getTipoConta() {
        return "CONTA POUPANCA";
    }
}
