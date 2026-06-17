package Trabalho_Pratico_POO.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Trabalho_Pratico_POO.exceptions.CodigoDuplicadoException;
import Trabalho_Pratico_POO.exceptions.ItemIndisponivelException;
import Trabalho_Pratico_POO.exceptions.ItemNaoEncontradoException;
import Trabalho_Pratico_POO.exceptions.UsuarioNaoEncontradoException;
import Trabalho_Pratico_POO.models.Aluno;
import Trabalho_Pratico_POO.models.DVD;
import Trabalho_Pratico_POO.models.ItemAcervo;
import Trabalho_Pratico_POO.models.Livro;
import Trabalho_Pratico_POO.models.Professor;
import Trabalho_Pratico_POO.models.Revista;
import Trabalho_Pratico_POO.models.Usuario;

public class BibliotecaManager {
    private final Scanner scanner;
    private final List<ItemAcervo> acervo = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();

    public BibliotecaManager(Scanner scanner) {
        this.scanner = scanner;
    }

    // ==================== CADASTRO DE ITENS ====================

    public void cadastrarLivro() {
        try {
            String codigo = readCodigoUnicoItem("Codigo do livro: ");
            String titulo = readTexto("Titulo: ");
            int ano = readInteiro("Ano de publicacao: ");
            String autor = readTexto("Autor: ");
            int paginas = readInteiroPositivo("Numero de paginas: ");

            acervo.add(new Livro(codigo, titulo, ano, autor, paginas));
            System.out.println("Livro cadastrado com sucesso.");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cadastrarRevista() {
        try {
            String codigo = readCodigoUnicoItem("Codigo da revista: ");
            String titulo = readTexto("Titulo: ");
            int ano = readInteiro("Ano de publicacao: ");
            int edicao = readInteiroPositivo("Edicao: ");
            String editora = readTexto("Editora: ");

            acervo.add(new Revista(codigo, titulo, ano, edicao, editora));
            System.out.println("Revista cadastrada com sucesso.");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cadastrarDVD() {
        try {
            String codigo = readCodigoUnicoItem("Codigo do DVD: ");
            String titulo = readTexto("Titulo: ");
            int ano = readInteiro("Ano de publicacao: ");
            String diretor = readTexto("Diretor: ");
            int duracao = readInteiroPositivo("Duracao (minutos): ");

            acervo.add(new DVD(codigo, titulo, ano, diretor, duracao));
            System.out.println("DVD cadastrado com sucesso.");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ==================== CADASTRO DE USUARIOS ====================

    public void cadastrarAluno() {
        try {
            String codigo = readCodigoUnicoUsuario("Codigo do aluno: ");
            String nome = readTexto("Nome: ");
            String curso = readTexto("Curso: ");

            usuarios.add(new Aluno(codigo, nome, curso));
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cadastrarProfessor() {
        try {
            String codigo = readCodigoUnicoUsuario("Codigo do professor: ");
            String nome = readTexto("Nome: ");
            String departamento = readTexto("Departamento: ");

            usuarios.add(new Professor(codigo, nome, departamento));
            System.out.println("Professor cadastrado com sucesso.");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ==================== LISTAGENS ====================

    public void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("Nenhum item cadastrado no acervo.");
            return;
        }

        System.out.println("=== ACERVO DA BIBLIOTECA ===");
        for (ItemAcervo item : acervo) {
            System.out.println(item.exibirDetalhes());
        }
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }

        System.out.println("=== USUARIOS CADASTRADOS ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    // ==================== EMPRESTIMO E DEVOLUCAO ====================

    public void realizarEmprestimo() {
        try {
            if (acervo.isEmpty()) {
                System.out.println("Nenhum item cadastrado no acervo.");
                return;
            }
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuario cadastrado.");
                return;
            }

            String codigoItem = readTexto("Codigo do item: ");
            ItemAcervo item = buscarItem(codigoItem);

            String codigoUsuario = readTexto("Codigo do usuario: ");
            Usuario usuario = buscarUsuario(codigoUsuario);


            item.emprestar(usuario.getCodigo());

            System.out.println("Emprestimo realizado com sucesso!");
            System.out.println("Item: " + item.getTitulo());
            System.out.println("Usuario: " + usuario.getNome() + " (" + usuario.getTipo() + ")");
            System.out.println("Prazo de devolucao: " + usuario.getPrazoDevolucaoDias() + " dias");
        } catch (ItemNaoEncontradoException | UsuarioNaoEncontradoException | ItemIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void realizarDevolucao() {
        try {
            if (acervo.isEmpty()) {
                System.out.println("Nenhum item cadastrado no acervo.");
                return;
            }

            String codigoItem = readTexto("Codigo do item a devolver: ");
            ItemAcervo item = buscarItem(codigoItem);


            item.devolver();

            System.out.println("Devolucao realizada com sucesso!");
            System.out.println("Item: " + item.getTitulo());
        } catch (ItemNaoEncontradoException | ItemIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ==================== BUSCA ====================

    private ItemAcervo buscarItem(String codigo) throws ItemNaoEncontradoException {
        String codigoNormalizado = normalizarCodigo(codigo);
        for (ItemAcervo item : acervo) {
            if (item.getCodigo().equals(codigoNormalizado)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("Item com codigo '" + codigo + "' nao encontrado.");
    }

    private Usuario buscarUsuario(String codigo) throws UsuarioNaoEncontradoException {
        String codigoNormalizado = normalizarCodigo(codigo);
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo().equals(codigoNormalizado)) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuario com codigo '" + codigo + "' nao encontrado.");
    }

    // ==================== LEITURA DE DADOS ====================

    private String readCodigoUnicoItem(String prompt) throws CodigoDuplicadoException {
        System.out.println(prompt);
        String codigo = normalizarCodigo(scanner.nextLine());

        if (codigo.isBlank()) {
            throw new CodigoDuplicadoException("Codigo nao pode estar vazio.");
        }

        for (ItemAcervo item : acervo) {
            if (item.getCodigo().equals(codigo)) {
                throw new CodigoDuplicadoException("Ja existe um item com o codigo '" + codigo + "'.");
            }
        }

        return codigo;
    }

    private String readCodigoUnicoUsuario(String prompt) throws CodigoDuplicadoException {
        System.out.println(prompt);
        String codigo = normalizarCodigo(scanner.nextLine());

        if (codigo.isBlank()) {
            throw new CodigoDuplicadoException("Codigo nao pode estar vazio.");
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo().equals(codigo)) {
                throw new CodigoDuplicadoException("Ja existe um usuario com o codigo '" + codigo + "'.");
            }
        }

        return codigo;
    }

    private String readTexto(String prompt) {
        while (true) {
            System.out.println(prompt);
            String texto = scanner.nextLine().trim();

            if (!texto.isBlank()) {
                return texto;
            }

            System.out.println("Entrada nao pode estar vazia.");
        }
    }

    private int readInteiro(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }

    private int readInteiroPositivo(String prompt) {
        while (true) {
            int valor = readInteiro(prompt);
            if (valor > 0) {
                return valor;
            }
            System.out.println("Valor deve ser maior que zero.");
        }
    }

    private String normalizarCodigo(String raw) {
        return raw.replaceAll("\\s+", "").toUpperCase(java.util.Locale.ROOT);
    }
}
