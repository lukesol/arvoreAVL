import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArvoreBalanceada sistema = new ArvoreBalanceada();

        // Inserções
        sistema.inserirReserva(new Reserva(1001, "Alice Silva", "V001", "2024-08-20T15:30:00"));
        sistema.inserirReserva(new Reserva(1002, "Joao Pereira", "V001", "2024-08-20T14:00:00"));
        sistema.inserirReserva(new Reserva(1003, "Maria Costa", "V001", "2024-08-20T15:00:00"));
        sistema.inserirReserva(new Reserva(1004, "Carlos Souza", "V002", "2024-08-21T09:00:00"));
        sistema.inserirReserva(new Reserva(1005, "Beatriz Lima", "V002", "2024-08-21T10:30:00"));
        sistema.inserirReserva(new Reserva(1006, "Fernanda Oliveira", "V001", "2024-08-20T16:00:00"));
        sistema.inserirReserva(new Reserva(1007, "Joaquim Ferreira", "V002", "2024-08-21T10:30:00"));
        sistema.inserirReserva(new Reserva(1008, "Manoel Leao", "V001", "2024-08-20T16:00:00"));
        sistema.inserirReserva(new Reserva(1009, "Jose Carlos", "V002", "2024-08-21T10:30:00"));
        sistema.inserirReserva(new Reserva(1010, "Andre Mateus", "V001", "2024-08-20T16:00:00"));

        sistema.exibirReservas();

        // Remoções
        sistema.removerReserva(1009);
        sistema.removerReserva(1010);
        sistema.removerReserva(1004);
        System.err.println();

        // Impressão em pré-ordem
        sistema.imprimirEmPreOrdem();
        System.err.println();

        Reserva reserva = sistema.buscarReserva(1002);
        System.out.println("Reserva encontrada: " + reserva);
        System.out.println();

        List<Reserva> reservasVooV001 = sistema.listarReservasPorVoo("V001");
        System.out.println("Reservas para o voo V001:");
        for (Reserva r : reservasVooV001) {
            System.out.println(r);
        }
        System.out.println();

        sistema.imprimirEmPreOrdem();
    }
}