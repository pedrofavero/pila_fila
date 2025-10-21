import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Pilha historico = new Pilha();
        Fila fila = new Fila();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean executando = true;

        System.out.println("=== Sistema de Atendimento (Lista Encadeada) ===");
        while (executando) {
            try {
                System.out.println();
                System.out.println("1) Adicionar SOLICITACAO ao historico (PILHA)");
                System.out.println("2) Remover ultima SOLICITACAO do historico (PILHA)");
                System.out.println("3) Listar historico (PILHA)");
                System.out.println("4) Adicionar CLIENTE a fila (FILA)");
                System.out.println("5) Atender proximo CLIENTE (FILA)");
                System.out.println("6) Listar fila (FILA)");
                System.out.println("7) Historico esta vazio?");
                System.out.println("8) Fila esta vazia?");
                System.out.println("9) Sair");
                System.out.print("Opcao: ");

                String opStr = br.readLine();
                int op = 0;
                try { op = Integer.parseInt(opStr); } catch (Exception e) { op = 0; }

                if (op == 1) {
                    System.out.print("ID da solicitacao: ");
                    String id = br.readLine();
                    System.out.print("Descricao: ");
                    String desc = br.readLine();
                    System.out.print("Data e hora (ex: 2025-10-14 09:30): ");
                    String dh = br.readLine();
                    historico.adicionar(new Solicitacao(id, desc, dh));
                    System.out.println("OK: adicionada ao historico.");
                } else if (op == 2) {
                    Solicitacao removida = historico.remover();
                    if (removida != null) {
                        System.out.println("Removida: [" + removida.id + "] " + removida.descricao + " | " + removida.dataHora);
                    }
                } else if (op == 3) {
                    System.out.println("Historico");
                    historico.listar();
                } else if (op == 4) {
                    System.out.print("ID do cliente: ");
                    String id = br.readLine();
                    System.out.print("Nome: ");
                    String nome = br.readLine();
                    System.out.print("Motivo do atendimento: ");
                    String motivo = br.readLine();
                    fila.adicionar(new Cliente(id, nome, motivo));
                    System.out.println("OKcliente entrou na fila.");
                } else if (op == 5) {
                    Cliente atendido = fila.atender();
                    if (atendido != null) {
                        System.out.println("Atendido: [" + atendido.id + "] " + atendido.nome + " | " + atendido.motivo);
                    }
                } else if (op == 6) {
                    System.out.println("fila");
                    fila.listar();
                } else if (op == 7) {
                    System.out.println("historico vazio? " + (historico.vazia() ? "Sim" : "Nao"));
                } else if (op == 8) {
                    System.out.println("Fila vazia? " + (fila.vazia() ? "Sim" : "Nao"));
                } else if (op == 9) {
                    executando = false;
                } else {
                    System.out.println("opcao invalida");
                }

            } catch (Exception e) {
                System.out.println("Deu ruim " + e.getMessage());
            }
        }

        System.out.println("Tchau Brigado!");
    }
}
