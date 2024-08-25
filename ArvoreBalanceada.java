import java.util.ArrayList;
import java.util.List;

class ArvoreBalanceada {
    //Classe interna para representar o nó da árvore AVL
    class Nodo {
        Reserva reserva;
        int altura;
        Nodo esquerda, direita;

        public Nodo(Reserva reserva) {
            this.reserva = reserva;
            this.altura = 1;
        }
    }

    private Nodo raiz;

    // Função para obter a altura do nó
    private int altura(Nodo nodo) {
        if (nodo == null)
            return 0;
        return nodo.altura;
    }

    // Função para obter o fator de balanceamento do nó
    private int getBalanceamento(Nodo nodo) {
        if (nodo == null)
            return 0;
        return altura(nodo.esquerda) - altura(nodo.direita);
    }

    // Função para fazer uma rotação a direita
    private Nodo rotacaoDireita(Nodo y) {
        Nodo x = y.esquerda;
        Nodo T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    // Função para fazer uma rotação a esquerda
    private Nodo rotacaoEsquerda(Nodo x) {
        Nodo y = x.direita;
        Nodo T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    // Função para inserir uma nova reserva na árvore AVL
    public void inserirReserva(Reserva reserva) {
        raiz = inserir(raiz, reserva);
    }

    private Nodo inserir(Nodo nodo, Reserva reserva) {
        if (nodo == null)
            return new Nodo(reserva);

        if (reserva.getCodigo() < nodo.reserva.getCodigo())
            nodo.esquerda = inserir(nodo.esquerda, reserva);
        else if (reserva.getCodigo() > nodo.reserva.getCodigo())
            nodo.direita = inserir(nodo.direita, reserva);
        else
            return nodo;

        nodo.altura = 1 + Math.max(altura(nodo.esquerda), altura(nodo.direita));

        int balanceamento = getBalanceamento(nodo);

        if (balanceamento > 1 && reserva.getCodigo() < nodo.esquerda.reserva.getCodigo())
            return rotacaoDireita(nodo);

        if (balanceamento < -1 && reserva.getCodigo() > nodo.direita.reserva.getCodigo())
            return rotacaoEsquerda(nodo);

        if (balanceamento > 1 && reserva.getCodigo() > nodo.esquerda.reserva.getCodigo()) {
            nodo.esquerda = rotacaoEsquerda(nodo.esquerda);
            return rotacaoDireita(nodo);
        }

        if (balanceamento < -1 && reserva.getCodigo() < nodo.direita.reserva.getCodigo()) {
            nodo.direita = rotacaoDireita(nodo.direita);
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

    // Função para remover uma reserva da árvore AVL
    public void removerReserva(int codigo) {
        raiz = remover(raiz, codigo);
    }

    private Nodo remover(Nodo nodo, int codigo) {
        if (nodo == null)
            return nodo;

        if (codigo < nodo.reserva.getCodigo())
            nodo.esquerda = remover(nodo.esquerda, codigo);
        else if (codigo > nodo.reserva.getCodigo())
            nodo.direita = remover(nodo.direita, codigo);
        else {
            if ((nodo.esquerda == null) || (nodo.direita == null)) {
                Nodo temp = (nodo.esquerda != null) ? nodo.esquerda : nodo.direita;

                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else
                    nodo = temp;
            } else {
                Nodo temp = getMinimo(nodo.direita);

                nodo.reserva = temp.reserva;

                nodo.direita = remover(nodo.direita, temp.reserva.getCodigo());
            }
        }

        if (nodo == null)
            return nodo;

        nodo.altura = Math.max(altura(nodo.esquerda), altura(nodo.direita)) + 1;

        int balanceamento = getBalanceamento(nodo);

        if (balanceamento > 1 && getBalanceamento(nodo.esquerda) >= 0)
            return rotacaoDireita(nodo);

        if (balanceamento > 1 && getBalanceamento(nodo.esquerda) < 0) {
            nodo.esquerda = rotacaoEsquerda(nodo.esquerda);
            return rotacaoDireita(nodo);
        }

        if (balanceamento < -1 && getBalanceamento(nodo.direita) <= 0)
            return rotacaoEsquerda(nodo);

        if (balanceamento < -1 && getBalanceamento(nodo.direita) > 0) {
            nodo.direita = rotacaoDireita(nodo.direita);
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

    private Nodo getMinimo(Nodo nodo) {
        Nodo atual = nodo;

        while (atual.esquerda != null)
            atual = atual.esquerda;

        return atual;
    }

    // Função para buscar uma reserva pelo código
    public Reserva buscarReserva(int codigo) {
        Nodo resultado = buscar(raiz, codigo);
        return resultado != null ? resultado.reserva : null;
    }

    private Nodo buscar(Nodo nodo, int codigo) {
        if (nodo == null || nodo.reserva.getCodigo() == codigo)
            return nodo;

        if (codigo < nodo.reserva.getCodigo())
            return buscar(nodo.esquerda, codigo);

        return buscar(nodo.direita, codigo);
    }

    // Função para listar reservas por voo
    public List<Reserva> listarReservasPorVoo(String voo) {
        List<Reserva> reservas = new ArrayList<>();
        listarPorVoo(raiz, voo, reservas);
        return reservas;
    }

    private void listarPorVoo(Nodo nodo, String voo, List<Reserva> reservas) {
        if (nodo != null) {
            listarPorVoo(nodo.esquerda, voo, reservas);
            if (nodo.reserva.getVoo().equals(voo))
                reservas.add(nodo.reserva);
            listarPorVoo(nodo.direita, voo, reservas);
        }
    }

    // Função para imprimir a árvore em pré-ordem
    public void imprimirEmPreOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.reserva.getCodigo() + " ");
            preOrdem(nodo.esquerda);
            preOrdem(nodo.direita);
        }
    }

    // Função para exibir todas as reservas (em ordem)
    public void exibirReservas() {
        inOrder(raiz);
        System.out.println();
    }

    private void inOrder(Nodo nodo) {
        if (nodo != null) {
            inOrder(nodo.esquerda);
            System.out.println(nodo.reserva);
            inOrder(nodo.direita);
        }
    }
}