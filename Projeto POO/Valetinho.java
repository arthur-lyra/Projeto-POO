import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Valetinho extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estacionamento estacionamento;
    private JTextField entradaVagaField, entradaPlacaField, saidaVagaField, consultaPlacaField;
    private JLabel vagaLabel, placaLabel, resultadoLabel;
    private JButton entradaButton, saidaButton, consultaButton;

    public Valetinho() throws Throwable {
        super("Valetinho - Sistema de Estacionamento");

        // Inicializa o estacionamento com 10 vagas
        estacionamento = new Estacionamento(10);

        // Configura a interface gráfica
        entradaVagaField = new JTextField(5);
        entradaPlacaField = new JTextField(10);
        saidaVagaField = new JTextField(5);
        consultaPlacaField = new JTextField(10);
        vagaLabel = new JLabel("Vaga:");
        placaLabel = new JLabel("Placa:");
        resultadoLabel = new JLabel();
        entradaButton = new JButton("Entrada");
        saidaButton = new JButton("Saída");
        consultaButton = new JButton("Consulta");
        entradaButton.addActionListener(this);
        saidaButton.addActionListener(this);
        consultaButton.addActionListener(this);

        JPanel entradaPanel = new JPanel(new FlowLayout());
        entradaPanel.add(vagaLabel);
        entradaPanel.add(entradaVagaField);
        entradaPanel.add(placaLabel);
        entradaPanel.add(entradaPlacaField);
        entradaPanel.add(entradaButton);

        JPanel saidaPanel = new JPanel(new FlowLayout());
        saidaPanel.add(vagaLabel);
        saidaPanel.add(saidaVagaField);
        saidaPanel.add(saidaButton);

        JPanel consultaPanel = new JPanel(new FlowLayout());
        consultaPanel.add(placaLabel);
        consultaPanel.add(consultaPlacaField);
        consultaPanel.add(consultaButton);

        JPanel resultadoPanel = new JPanel(new FlowLayout());
        resultadoPanel.add(resultadoLabel);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.add(entradaPanel);
        mainPanel.add(saidaPanel);
        mainPanel.add(consultaPanel);
        mainPanel.add(resultadoPanel);

        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == entradaButton) {
            int vaga = Integer.parseInt(entradaVagaField.getText());
            String placa = entradaPlacaField.getText();
            try {
				if (estacionamento.entrar(placa, vaga)) {
				    resultadoLabel.setText("Entrada registrada na vaga " + vaga);
				} else {
				    resultadoLabel.setText("A vaga " + vaga + " já está ocupada.");
				}
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
        } else if (e.getSource() == saidaButton) {
            int vaga = Integer.parseInt(saidaVagaField.getText());
            try {
				if (estacionamento.sair(vaga)) {
				    resultadoLabel.setText("Saída registrada na vaga " + vaga);
				} else {
				    resultadoLabel.setText("A vaga " + vaga + " já está livre.");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
        } else if (e.getSource() == consultaButton) {
            String placa = consultaPlacaField.getText();
            int vaga = estacionamento.consultarPlaca(placa);
            if (vaga == -1) {
                resultadoLabel.setText("Veículo com placa " + placa + " não encontrado.");
            } else {
            	resultadoLabel.setText("Placa Encontrada");
}
        }
    
}
}
