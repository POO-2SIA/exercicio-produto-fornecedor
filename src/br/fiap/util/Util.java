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
    private long cnpjFornecedor = 0;

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
                    cnpjFornecedor = parseLong(showInputDialog("Insira o valor do CNPJ do fornecedor"));
                    cadastrarProduto(cnpjFornecedor);
                    break;
                case 2:
                    cnpjFornecedor = parseLong(showInputDialog("Insira o valor do CNPJ do fornecedor")); // Checar com o prof
                    showMessageDialog(null, exibirInfoProduto());
                    break;
                case 3:
                    pesquisarFornecedor();
                    break;
            }
        } while (op != 4);
    }

    private void cadastrarProduto(long cnpjFornecedor) {
        String nomeProduto;
        double valorUnitario;
        int qtdEstoque;
        Fornecedor aux = localizarFornecedor(cnpjFornecedor);

        if(aux == null) {
            aux = cadastrarFornecedor();
        }

        nomeProduto = showInputDialog("Insira o nome do produto");
        valorUnitario = parseDouble(showInputDialog("Insira o valor unitário do produto"));
        qtdEstoque = parseInt(showInputDialog("Insira a quantidade em estoque do produto"));
        produtos[p] = new Produto(nomeProduto, valorUnitario, qtdEstoque, aux);
        p++;
    }

    private Fornecedor cadastrarFornecedor() {
        String nome;
        long cnpj;

        nome = showInputDialog("Insira o nome do fornecedor");
        cnpj = parseLong(showInputDialog("Insira o CNPJ do fornecedor"));

        fornecedores[f] = new Fornecedor(nome, cnpj);
        f++;

        return fornecedores[f-1];
    }

    private Fornecedor localizarFornecedor(long cnpjFornecedor) {
        for(int j = 0; j < f; j++) {
            if (fornecedores[j].getCnpj() == cnpjFornecedor) {
                return fornecedores[j];
            }
        }
        showMessageDialog(null, "CNPJ " + cnpjFornecedor + " não cadastrado!");
        return null;
    }

    private Produto pesquisarProduto() {
        String nomeProduto = showInputDialog("Insira o nome do produto que deseja buscar");
        for(int i = 0; i < p; i++) {
            if (nomeProduto.equalsIgnoreCase(produtos[i].getNome())) {
                return produtos[i];
            }
        }
        showMessageDialog(null, "Produto não encontrado!");
        return null;
    }

    private String exibirInfoProduto() {
        Produto produto = pesquisarProduto();
        String aux = "Produto não encontrado";

        if(produto != null) {
            aux = "";
            aux += "Produto: " + produto.getNome();
            aux += "\nQuantidade em estoque: " + produto.getQtdEstoque();
            aux += "\nValor unitário: R$" + String.format("%.2f", produto.getValorUnitario());
            aux += "\nFornecedor: " + produto.getFornecedor().getNome();
            return aux;
        }
        return aux;
    }

    private void pesquisarFornecedor() {
        Fornecedor fornecedor = localizarFornecedor(cnpjFornecedor);
        String aux = "";
        if (fornecedor != null) {
            aux += "\nFornecedor: " + fornecedor.getNome();
            aux += "\nCNPJ: " + fornecedor.getCnpj();
            showMessageDialog(null, aux);
        } else {
            showMessageDialog(null, "Fornecedor não encontrado!");
        }
    }
}
