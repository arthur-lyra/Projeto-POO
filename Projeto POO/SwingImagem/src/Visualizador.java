package SwingImagem.src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Visualizador {

	private JFrame frame;
	private JLabel label;
	private JLabel label_1;
	private String[] arquivos = {"1.jpg", "2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg"};
	private JPanel panel;
	private int Estacionamento = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visualizador window = new Visualizador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Visualizador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Visualizar imagem");
		frame.setBounds(100, 100, 321, 183);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Imagem do aplicativo
		ImageIcon icon = new ImageIcon(Visualizador.class.getResource("/imagens/1.jpg"));
		frame.setIconImage(icon.getImage()); 

		label = new JLabel("imagem");
		label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		label.setBounds(198, 32, 76, 81);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("arquivo");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(194, 115, 90, 14);
		frame.getContentPane().add(label_1);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Exibir a imagem", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 22, 174, 95);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton button = new JButton("Sortear arquivo");
		button.setBounds(10, 21, 138, 23);
		panel.add(button);

		JButton button_1 = new JButton("Abrir arquivo externo");
		button_1.setBounds(10, 62, 154, 23);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File local = new File("./src/imagens");  //new File("c:/")
					JFileChooser chooser = new JFileChooser(local);   
					//listar somente arquivos jpg, gif, png
					chooser.setFileFilter(new FileNameExtensionFilter("Arquivo de imagem","jpg","gif", "png"));
					chooser.showOpenDialog(frame);			//abrir a janela por cima do frame
					File file = chooser.getSelectedFile();	//obter arquivo selecionado pelo usuario
					if (file==null) 
						return;
					else {
						ImageIcon icon = new ImageIcon(file.getPath());
						icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT) );
						label.setIcon(icon);
						label_1.setText(file.getName()); //exibir nome do arquivo
					}
				} 
				catch (Exception e) {
					label_1.setText(e.getMessage());
				}
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random sorteio = new Random();
				int index = sorteio.nextInt(8);  //0 a 7
				ImageIcon icon =new ImageIcon(Visualizador.class.getResource("/imagens/"+ arquivos[index]));
				icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), 1) );
				label.setIcon(icon);
				label_1.setText(arquivos[index]); //exibir nome do arquivo
			}
		});

	
	}
}
