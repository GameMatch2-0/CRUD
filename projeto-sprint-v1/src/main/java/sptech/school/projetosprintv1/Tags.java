package sptech.school.projetosprintv1;

public class Tags {

    private String nome;
    private String imagem;

    public Tags(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
