import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Valetinho {

    private Estacionamento estacionamento;

    public Valetinho(int n) throws Exception {
        if (n <= 0) {
            throw new Exception("O número de vagas deve ser maior que 0");
        }
        this.estacionamento = new Estacionamento(n);
        this.estacionamento.lerDados();
    }

    public void entrada() {
        try {
            int vaga = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a vaga livre:"));
            String placa = JOptionPane.showInputDialog(null, "Digite a placa do veículo:");
            this.estacionamento.entrar(placa, vaga);
            JOptionPane.showMessageDialog(null, "Veículo estacionado na vaga " + vaga);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saida() {
        try {
            int vaga = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a vaga do veículo:"));
            this.estacionamento.sair(vaga);
            JOptionPane.showMessageDialog(null, "Veículo retirado da vaga " + vaga);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void consultaPlaca() {
        String placa = JOptionPane.showInputDialog(null, "Digite a placa do veículo:");
        int vaga = this.estacionamento.consultarPlaca(placa);
        if (vaga == -1) {
            JOptionPane.showMessageDialog(null, "Veículo não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Veículo está na vaga " + vaga);
        }
    }

    public void transferenciaPlaca() {
        try {
            int vagaOrigem = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a vaga de origem:"));
            int vagaDestino = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a vaga de destino:"));
            this.estacionamento.transferir(vagaOrigem, vagaDestino);
            JOptionPane.showMessageDialog(null, "Veículo transferido da vaga " + vagaOrigem + " para a vaga " + vagaDestino);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listagemGeral() {
        String[] vagas = this.estacionamento.listarGeral();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vagas.length; i++) {
            sb.append("Vaga ").append(i + 1).append(": ").append(vagas[i]).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void listagemVagasLivres() {
        ArrayList<Integer> vagasLivres = this.estacionamento.listarLivres();
        StringBuilder sb = new StringBuilder();
        for (int vaga : vagasLivres) {
            sb.append(vaga).append(", ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
            JOptionPane.showMessageDialog(null, "Vagas livres: " + sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Não há vagas livres");
        }
    }
}