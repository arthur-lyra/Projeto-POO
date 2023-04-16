import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Valetinho {

	private JFrame frame;
	private JLabel label;
	private JPanel panel;
	private JButton button_entrada;
	private JButton button_sair;
	private JButton button_consultar;
	private JButton button_transferir;
	private JButton button_lista_geral;
	private JButton button_lista_livre;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Valetinho window = new Valetinho();
					window.frame.setVisible(true);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Throwable 
	 */
	public Valetinho() throws Throwable {
		initialize(new Estacionamento(10));
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Throwable 
	 */
	private void initialize(Estacionamento estacionamento) throws Throwable {
		frame = new JFrame();
		frame.setTitle("Valetinho");
		frame.setBounds(100, 100, 639, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(70, 12, 454, 84);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("Estacionamento");
		label.setBounds(6, 16, 442, 62);
		panel.add(label);
		label.setBackground(new Color(0, 0, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		button_entrada = new JButton("Entrar");
		button_entrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String placa;
				int vaga;
			placa = JOptionPane.showInputDialog("Digite a Placa:");
			vaga = Integer.parseInt(JOptionPane.showInputDialog("Digite a Vaga de 1-10:"));
			try {
				estacionamento.entrar(placa,vaga);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());

			}
		    
			}
		});
		button_entrada.setBounds(10, 138, 103, 34);
		frame.getContentPane().add(button_entrada);
		
		button_sair = new JButton("Sair");
		button_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vaga;
			vaga = Integer.parseInt(JOptionPane.showInputDialog("Digite a Vaga de 1-10:"));
			try {
				estacionamento.sair(vaga);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				
			}
			}
		});
		button_sair.setBounds(10, 211, 103, 34);
		frame.getContentPane().add(button_sair);
		
		button_consultar = new JButton("Consultar Placa");
		button_consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String placa;
				placa = JOptionPane.showInputDialog("Digite a placa:");
				try {
					
					JOptionPane.showMessageDialog(null,estacionamento.consultarPlaca(placa));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				}
		
			}
		);
		button_consultar.setBounds(10, 280, 103, 34);
		frame.getContentPane().add(button_consultar);
		
		button_transferir = new JButton("Transferir");
		button_transferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vaga1;
				int vaga2;
				vaga1 = Integer.parseInt(JOptionPane.showInputDialog("Digite a Vaga origem de 1-10:"));
				vaga2 = Integer.parseInt(JOptionPane.showInputDialog("Digite a Vaga Destino de 1-10:"));
				try {
					estacionamento.transferir(vaga1,vaga2);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		button_transferir.setBounds(10, 348, 103, 34);
		frame.getContentPane().add(button_transferir);
		
		button_lista_geral = new JButton("Listagem Geral");
		button_lista_geral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					JOptionPane.showMessageDialog(null,estacionamento.listarGeral());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
			}
		});
		button_lista_geral.setBounds(10, 418, 103, 34);
		frame.getContentPane().add(button_lista_geral);
		
		button_lista_livre = new JButton("Listagem Livre");
		button_lista_livre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					JOptionPane.showMessageDialog(null,estacionamento.listarLivres());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
			}
		});
		button_lista_livre.setBounds(10, 491, 103, 34);
		frame.getContentPane().add(button_lista_livre);
	}
}
