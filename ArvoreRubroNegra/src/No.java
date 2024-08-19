public class No {
    int valor;
    No esquerda;
    No direita;
    No pai;
    int cor;
    int altura;

    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.cor = 1;
        this.altura = 1;
    }
}
