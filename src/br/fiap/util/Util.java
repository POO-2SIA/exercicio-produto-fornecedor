package br.fiap.util;
import br.fiap.fornecedor.Fornecedor;
import br.fiap.produto.Produto;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Util {
    static final int N = 5;
    private Fornecedor[] fornecedores = new Fornecedor[N];
    private Produto[] produtos = new Produto[N];
    private int p = 0;
    private int f = 0;

    public void menu() {
        String menuText = "1.Cadastrar Produto\n2.Pesquisar Produto por Nome" +
                "\n3.Pesquisar Fornecedor por CNPJ\n4.Finalizar";
        int op;

        do {
            op = parseInt(showInputDialog(menuText));
            if (op < 1 || op > 4) {
                showMessageDialog(null, "Opção Inválida");
            }

            switch (op) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    //pesquisarProduto();
                    break;
                case 3:
                    //pesquisarFornecedor();
                    break;
            }
        } while (op != 4);
    }

    private void cadastrarProduto() {
        String nomeProduto;
        double valorUnitario;
        int qtdEstoque;
        long cnpjFornecedor;

        cnpjFornecedor = parseLong(showInputDialog("Insira o valor do CNPJ do fornecedor"));

        if (localizarFornecedor(cnpjFornecedor) != null) {
            nomeProduto = showInputDialog("Insira o nome do produto");
            valorUnitario = parseDouble(showInputDialog("Insira o valor unitário do produto"));
            qtdEstoque = parseInt(showInputDialog("Insira a quantidade em estoque do produto"));

            produtos[p] = new Produto(nomeProduto, valorUnitario, qtdEstoque, fornecedores[f]);
        } else {

        }
    }

    private void cadastrarFornecedor() {
        String nome;
        long cnpj;

        nome = showInputDialog("Insira o nome do fornecedor");
        cnpj = parseLong("Insira o CNPJ do fornecedor");

        fornecedores[f] = new Fornecedor(nome, cnpj);
        f++;
    }

    private Fornecedor localizarFornecedor(long cnpj) {
        for(int j = 0; j < f; j++) {
            if (fornecedores[j].getCnpj() == cnpj) {
                return fornecedores[j];
            }
        }
        showMessageDialog(null, "CNPJ " + cnpj + " não cadastrado!");
        return null;
    }
}
