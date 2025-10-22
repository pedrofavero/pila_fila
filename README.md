# Pilha e Fila

## Como rodar:

- Clone o repositório
- Abra com um editor de código que preferir
- Entre no diretório da pasta Atendimento
  ```java
  cd Atendimento
  ```
- Compile o Main.java
  ```java
  javac Main.java
  ```
- Rode o Main.java
    ```java
  java Main.java
  ```

## Explicação do código

### Cliente.java

```java


// Classe de domínio simples que representa um cliente na fila de atendimento.
// Não possui lógica de estrutura de dados: apenas carrega informações para a Fila.

public class Cliente {
    // Identificador textual do cliente (ex.: "1")
    public String id;     

    // Nome do cliente (usado nas listagens e mensagens)
    public String nome;

    // Motivo/assunto do atendimento (descrição curta)
    public String motivo;  

    // Construtor: garante que o objeto nasce completo para ser enfileirado.
    public Cliente(String id, String nome, String motivo) {
        this.id = id;          // define o identificador
        this.nome = nome;      // define o nome
        this.motivo = motivo;  // define o motivo do atendimento
    }
}



```

## Solicitacao.java

```java

// Classe de domínio simples que representa uma solicitação do histórico (usada na Pilha)
// Não tem lógica de estrutura de dados: apenas carrega informações.

public class Solicitacao {
    // Identificador textual da solicitação (ex.: "1").
    public String id;

    // Descrição breve do que foi solicitado
    public String descricao;

    // Registro do momento em que a solicitação foi criada
    public String dataHora;  

    // Construtor: garante que a solicitação nasce completa para ser empilhada no histórico.
    public Solicitacao(String id, String descricao, String dataHora) {
        this.id = id;               // define o identificador
        this.descricao = descricao; // define a descrição
        this.dataHora = dataHora;   // define o carimbo de data/hora
    }
}

```

## Pilha.java 


```java

// Implementação de uma PILHA usando lista encadeada simples (LIFO: último a entrar, primeiro a sair).
// Cada nó guarda uma Solicitacao e a referência para o próximo nó (encadeamento).

public class Pilha {

    // Classe interna que representa um elo (nó) da lista encadeada.
    private class No {
        Solicitacao valor; // dado armazenado no nó (uma solicitação do histórico)
        No prox;           // ponteiro para o próximo nó na pilha
        No(Solicitacao v) { this.valor = v; } // constrói o nó com o valor
    }

    // Ponteiro para o topo da pilha (primeiro nó da lista). Se for null, a pilha está vazia.
    private No topo;

    // Construtor: inicia a pilha vazia.
    public Pilha() {
        topo = null;
    }

    // Retorna true se não há nenhum elemento (topo == null).
    public boolean vazia() {
        return topo == null;
    }

    // Empilha (push): insere uma solicitação no TOPO.
    // Passos:
    // 1) cria um novo nó com a solicitação;
    // 2) faz o novo nó apontar para o topo atual;
    // 3) move o ponteiro topo para o novo nó.
    // Custo O(1).
    public void adicionar(Solicitacao s) {
        No novo = new No(s);
        novo.prox = topo; // encadeia novo nó antes do antigo topo
        topo = novo;      // atualiza o topo para o novo nó
    }

    // Desempilha (pop): remove e retorna a solicitação do TOPO.
    // Se vazio, avisa e retorna null.
    // Passos:
    // 1) guarda o valor do topo (para retornar);
    // 2) move o topo para o próximo nó (desencadeia o primeiro elo);
    // 3) retorna o valor removido.
    // Custo O(1).
    public Solicitacao remover() {
        if (vazia()) {
            System.out.println("Histórico vazio.");
            return null;
        }
        Solicitacao s = topo.valor; // pega o dado do topo
        topo = topo.prox;           // "pula" o primeiro nó; agora o topo é o próximo
        return s;                   // devolve a solicitação removida
    }

    // Percorre e imprime o histórico do mais recente (topo) ao mais antigo (base).
    // Se vazio, informa ao usuário.
    public void listar() {
        if (vazia()) {
            System.out.println("Histórico vazio.");
            return;
        }
        // Itera pelos nós encadeados começando do topo
        for (No n = topo; n != null; n = n.prox) {
            System.out.println("[" + n.valor.id + "] " + n.valor.descricao + " | " + n.valor.dataHora);
        }
    }
}

```


## Fila.java

```java

// Implementação de uma FILA usando lista encadeada simples (FIFO: primeiro que entra é o primeiro que sai).
// Cada nó guarda um Cliente e a referência para o próximo nó (encadeamento).
// Operações principais:
//  - adicionar (enqueue) insere NO FIM em O(1)
//  - atender (dequeue) remove DO INÍCIO em O(1)
//  - listar percorre da frente até o fim em O(n)

public class Fila {
    // Nó (elo) da lista encadeada para a Fila.
    private class No {
        Cliente valor; // dado armazenado no nó (um cliente da fila)
        No prox;       // ponteiro para o próximo nó
        No(Cliente v) { this.valor = v; }
    }

    // Ponteiros de controle da fila:
    private No primeiro; // cabeça: de onde sai (frente da fila)
    private No ultimo;   // cauda: onde entra (fim da fila)
    private int contador; // quantidade de elementos (facilita checagem de vazio)

    // Constrói uma fila vazia (sem nós).
    public Fila() {
        primeiro = null;
        ultimo = null;
        contador = 0;
    }

    // Retorna true se não há elementos na fila.
    public boolean vazia() {
        return contador == 0;
    }

    // Enfileira (enqueue): insere o cliente NO FIM da fila em O(1).
    // Casos:
    //  - se estiver vazia: primeiro e ultimo apontam para o novo nó
    //  - caso contrário: liga o antigo 'ultimo' ao novo e atualiza 'ultimo'
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

    // Desenfileira (dequeue): remove e retorna o cliente DO INÍCIO da fila em O(1).
    // Passos:
    //  1) se vazia, informa e retorna null
    //  2) guarda o valor do 'primeiro' para retornar
    //  3) move 'primeiro' para o próximo elo
    //  4) decrementa o contador
    //  5) se 'primeiro' virou null, a fila esvaziou -> zera também 'ultimo'
    public Cliente atender() {
        if (vazia()) {
            System.out.println("fila vazia");
            return null;
        }
        Cliente cliente = primeiro.valor; // dado do início da fila
        primeiro = primeiro.prox;         // avança a cabeça para o próximo nó
        contador -= 1;
        if (primeiro == null) {           // se esvaziou, ajusta a cauda
            ultimo = null;
        }
        return cliente;
    }

    // Percorre e imprime do primeiro até o último cliente (ordem de atendimento).
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

```


## Main.java

```java

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Classe principal: fornece o menu de console e gerencia as operações
// sobre a Pilha (histórico de solicitações) e a Fila (ordem de atendimento).
public class Main {
    public static void main(String[] args) {
        // Estruturas de dados encadeadas criadas vazias
        Pilha historico = new Pilha();
        Fila fila = new Fila();

        // Leitor simples de linhas do console (I/O permitido pelo enunciado)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean executando = true;

        System.out.println("=== Sistema de Atendimento (Lista Encadeada) ===");
        while (executando) { // loop do menu até o usuário escolher sair
            try {
                // --- Menu de opções ---
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

                // Lê a opção como String e tenta converter para int
                String opStr = br.readLine();
                int op = 0;
                try { op = Integer.parseInt(opStr); } catch (Exception e) { op = 0; }

                // --- Desvio de fluxo conforme a opção digitada ---
                if (op == 1) { // Empilhar solicitação no histórico
                    System.out.print("ID da solicitacao: ");
                    String id = br.readLine();
                    System.out.print("Descricao: ");
                    String desc = br.readLine();
                    System.out.print("Data e hora (ex: 2025-10-14 09:30): ");
                    String dh = br.readLine();

                    // Cria o objeto de domínio e adiciona no topo da pilha
                    historico.adicionar(new Solicitacao(id, desc, dh));
                    System.out.println("OK: adicionada ao historico.");

                } else if (op == 2) { // Desempilhar (remover do topo)
                    Solicitacao removida = historico.remover();
                    if (removida != null) {
                        System.out.println("Removida: [" + removida.id + "] " + removida.descricao + " | " + removida.dataHora);
                    }

                } else if (op == 3) { // Listar histórico (do topo à base)
                    System.out.println("Historico");
                    historico.listar();

                } else if (op == 4) { // Enfileirar cliente (entra no fim)
                    System.out.print("ID do cliente: ");
                    String id = br.readLine();
                    System.out.print("Nome: ");
                    String nome = br.readLine();
                    System.out.print("Motivo do atendimento: ");
                    String motivo = br.readLine();

                    // Cria o objeto de domínio e adiciona ao final da fila
                    fila.adicionar(new Cliente(id, nome, motivo));
                    System.out.println("OKcliente entrou na fila."); // mensagem informativa

                } else if (op == 5) { // Atender cliente (sai da frente)
                    Cliente atendido = fila.atender();
                    if (atendido != null) {
                        System.out.println("Atendido: [" + atendido.id + "] " + atendido.nome + " | " + atendido.motivo);
                    }

                } else if (op == 6) { // Listar fila (da frente ao fim)
                    System.out.println("fila");
                    fila.listar();

                } else if (op == 7) { // Verifica se a pilha está vazia
                    System.out.println("historico vazio? " + (historico.vazia() ? "Sim" : "Nao"));

                } else if (op == 8) { // Verifica se a fila está vazia
                    System.out.println("Fila vazia? " + (fila.vazia() ? "Sim" : "Nao"));

                } else if (op == 9) { // Encerrar a aplicação
                    executando = false;

                } else { // Qualquer outra entrada inválida
                    System.out.println("opcao invalida");
                }

            } catch (Exception e) {
                // Tratamento genérico: evita que o programa quebre por entradas inesperadas
                System.out.println("Deu ruim " + e.getMessage());
            }
        }

        // Mensagem final ao sair do loop principal
        System.out.println("Tchau Brigado!");
    }
}


```
