
public class Pilha {

    private class No {
        Solicitacao valor;
        No prox;
        No(Solicitacao v) { this.valor = v; }
    }

    private No topo;

    public Pilha() {
        topo = null;
    }

    public boolean vazia() {
        return topo == null;
    }

    public void adicionar(Solicitacao s) {
        No novo = new No(s);
        novo.prox = topo;
        topo = novo;
    }

    public Solicitacao remover() {
        if (vazia()) {
            System.out.println("Histórico vazio.");
            return null;
        }
        Solicitacao s = topo.valor;
        topo = topo.prox;
        return s;
    }

    public void listar() {
        if (vazia()) {
            System.out.println("Histórico vazio.");
            return;
        }
        for (No n = topo; n != null; n = n.prox) {
            System.out.println("[" + n.valor.id + "] " + n.valor.descricao + " | " + n.valor.dataHora);
        }
    }
}
