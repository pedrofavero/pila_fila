public class Fila {
    private class No {
        Cliente valor;
        No prox;
        No(Cliente v) { this.valor = v; }
    }

    private No primeiro;
    private No ultimo;
    private int contador;

    public Fila() {
        primeiro = null;
        ultimo = null;
        contador = 0;
    }

    public boolean vazia() {
        return contador == 0;
    }

    public void adicionar(Cliente c) {
        No novo = new No(c);
        if (vazia()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            ultimo.prox = novo;
            ultimo = novo;
        }
        contador += 1;
    }

    public Cliente atender() {
        if (vazia()) {
            System.out.println("fila vazia");
            return null;
        }
        Cliente cliente = primeiro.valor;
        primeiro = primeiro.prox;
        contador -= 1;
        if (primeiro == null) {
            ultimo = null;
        }
        return cliente;
    }

    public void listar() {
        if (vazia()) {
            System.out.println("fila vazia");
            return;
        }
        for (No n = primeiro; n != null; n = n.prox) {
            System.out.println("[" + n.valor.id + "] " + n.valor.nome + " | " + n.valor.motivo);
        }
    }
}