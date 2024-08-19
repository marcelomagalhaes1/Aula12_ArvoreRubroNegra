public class ArvoreRubroNegra {

    private No raiz;
    private final int PRETO = 0;
    private final int VERMELHO = 1;

    public ArvoreRubroNegra() {
        this.raiz = null;
    }

    private void rotacaoEsquerda(No no) {
        No direita = no.direita;
        no.direita = direita.esquerda;
        if (direita.esquerda != null) {
            direita.esquerda.pai = no;
        }
        direita.pai = no.pai;
        if (no.pai == null) {
            raiz = direita;
        } else if (no == no.pai.esquerda) {
            no.pai.esquerda = direita;
        } else {
            no.pai.direita = direita;
        }
        direita.esquerda = no;
        no.pai = direita;
    }

    private void rotacaoDireita(No no) {
        No esquerda = no.esquerda;
        no.esquerda = esquerda.direita;
        if (esquerda.direita != null) {
            esquerda.direita.pai = no;
        }
        esquerda.pai = no.pai;
        if (no.pai == null) {
            raiz = esquerda;
        } else if (no == no.pai.direita) {
            no.pai.direita = esquerda;
        } else {
            no.pai.esquerda = esquerda;
        }
        esquerda.direita = no;
        no.pai = esquerda;
    }

    private void corrigirInsercao(No no) {
        No pai = null;
        No avo = null;

        while (no != raiz && no.pai != null && no.pai.cor == VERMELHO) {
            pai = no.pai;
            avo = no.pai.pai;

            if (pai == avo.esquerda) {
                No tio = avo.direita;

                if (tio != null && tio.cor == VERMELHO) {
                    avo.cor = VERMELHO;
                    pai.cor = PRETO;
                    tio.cor = PRETO;
                    no = avo;
                } else {
                    if (no == pai.direita) {
                        rotacaoEsquerda(pai);
                        no = pai;
                        pai = no.pai;
                    }
                    rotacaoDireita(avo);
                    int cor = pai.cor;
                    pai.cor = avo.cor;
                    avo.cor = cor;
                    no = pai;
                }
            } else {
                No tio = avo.esquerda;

                if (tio != null && tio.cor == VERMELHO) {
                    avo.cor = VERMELHO;
                    pai.cor = PRETO;
                    tio.cor = PRETO;
                    no = avo;
                } else {
                    if (no == pai.esquerda) {
                        rotacaoDireita(pai);
                        no = pai;
                        pai = no.pai;
                    }
                    rotacaoEsquerda(avo);
                    int cor = pai.cor;
                    pai.cor = avo.cor;
                    avo.cor = cor;
                    no = pai;
                }
            }
        }
        raiz.cor = PRETO;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        raiz = inserirRecursivo(raiz, novoNo);
        corrigirInsercao(novoNo);
    }

    private No inserirRecursivo(No raiz, No novoNo) {
        if (raiz == null) {
            return novoNo;
        }

        if (novoNo.valor < raiz.valor) {
            raiz.esquerda = inserirRecursivo(raiz.esquerda, novoNo);
            raiz.esquerda.pai = raiz;
        } else if (novoNo.valor > raiz.valor) {
            raiz.direita = inserirRecursivo(raiz.direita, novoNo);
            raiz.direita.pai = raiz;
        }

        return raiz;
    }
}