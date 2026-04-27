package biblioteca.core;

import biblioteca.models.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        // Criando Autor
        Autor autor1 = new Autor("Machado de Assis", "Brasileiro", LocalDate.of(1839, Month.JUNE, 21));
        Autor autor2 = new Autor("Aluísio Azevedo", "Brasileiro", LocalDate.of(1857, Month.APRIL, 14));
        Autor autor3 = new Autor("José de Alencar", "Brasileiro", LocalDate.of(1829, Month.MAY, 1));
        Autor autor4 = new Autor("Jorge Amado", "Brasileiro", LocalDate.of(1912, Month.AUGUST, 10));

        // Criando Leitor
        Leitor leitor = new Leitor("João da Silva", "123.456.789-00", "joao.silva@email.com");

        // Criando Livros
        Livro livro1 = new Livro("Dom Casmurro", "978-85-359-1424-8", 1899, 5, autor1);
        livro1.adicionarCapitulo(1, "Do Título", 2);
        livro1.adicionarCapitulo(2, "Do Autor", 3);

        Livro livro2 = new Livro("O Cortiço", "978-85-359-0277-1", 1890, 3, autor2);
        Livro livro3 = new Livro("Iracema", "978-85-08-06732-2", 1865, 2, autor3);
        Livro livro4 = new Livro("Capitães da Areia", "978-85-359-1425-5", 1937, 4, autor4);

        // Simulação de Empréstimos com Atraso
        LocalDate hoje = LocalDate.now();

        // Empréstimo 1 (atraso de 5 dias)
        LocalDate dataDevolucaoPrevista1 = hoje.minusDays(5);
        new Emprestimo(leitor, livro1, dataDevolucaoPrevista1);

        // Empréstimo 2 (atraso de 3 dias)
        LocalDate dataDevolucaoPrevista2 = hoje.minusDays(3);
        new Emprestimo(leitor, livro2, dataDevolucaoPrevista2);

        // Empréstimo 3 (atraso de 8 dias)
        LocalDate dataDevolucaoPrevista3 = hoje.minusDays(8);
        new Emprestimo(leitor, livro3, dataDevolucaoPrevista3);

        // Empréstimo 4 (atraso de 2 dias)
        LocalDate dataDevolucaoPrevista4 = hoje.minusDays(2);
        new Emprestimo(leitor, livro4, dataDevolucaoPrevista4);

        // Geração do Relatório de Multas
        System.out.println("--- Relatório de Multas ---");
        System.out.println("Leitor: " + leitor.getNome());

        double subtotal = 0;
        long atrasos = 0;

        for (Emprestimo emprestimo : leitor.getEmprestimos()) {
            double multa = emprestimo.calcularMulta(hoje);
            if (multa > 0) {
                atrasos++;
                long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje);
                System.out.printf("Empréstimo %d - %s: %d dias de atraso -> R$ %.2f\n",
                        atrasos, emprestimo.getLivro().getTitulo(), diasAtraso, multa);
                subtotal += multa;
            }
        }

        System.out.printf("Subtotal: R$ %.2f\n", subtotal);

        double totalAPagar = leitor.calcularMultaTotal(hoje);

        if (atrasos > 3) {
            System.out.println("Penalidade: mais de 3 atrasos -> multa DOBRADA");
            System.out.printf("TOTAL A PAGAR: R$ %.2f\n", totalAPagar);
        } else {
            System.out.printf("TOTAL A PAGAR: R$ %.2f\n", totalAPagar);
        }
    }
}
