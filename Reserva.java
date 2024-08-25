class Reserva {
    private int codigo;
    private String nome;
    private String voo;
    private String data;

    public Reserva(int codigo, String nome, String voo, String data) {
        this.codigo = codigo;
        this.nome = nome;
        this.voo = voo;
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getVoo() {
        return voo;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nome: " + nome + ", Voo: " + voo + ", Data: " + data;
    }
}