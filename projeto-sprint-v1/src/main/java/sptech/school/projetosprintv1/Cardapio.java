package sptech.school.projetosprintv1;

public class Cardapio {
    private String nome;
    private String descricao;
    private double preco;
    private String imagem;

    public Cardapio(String nome, String descricao, double preco, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }
}
