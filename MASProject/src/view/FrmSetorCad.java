package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.SetorCtrl;

public class FrmSetorCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDigitado;
	private JTextField id_setor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSetorCad frame = new FrmSetorCad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSetorCad() {
		setTitle("Registro de Setor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdDoSetor = new JLabel("ID. Setor:");
		lblIdDoSetor.setBounds(10, 23, 67, 14);
		contentPane.add(lblIdDoSetor);

		JLabel lblNomeDoSetor = new JLabel("Setor:");
		lblNomeDoSetor.setBounds(10, 51, 76, 14);
		contentPane.add(lblNomeDoSetor);

		txtDigitado = new JTextField();
		txtDigitado.setText("Digite o nome do setor...");
		txtDigitado.setBounds(69, 48, 169, 20);
		contentPane.add(txtDigitado);
		txtDigitado.setColumns(10);
		
		id_setor = new JTextField();
		id_setor.setEditable(false);
		id_setor.setBounds(69, 20, 169, 20);
		id_setor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(id_setor);
		id_setor.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(342, 96, 89, 23);
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		JLabel mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(10, 96, 228, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		JLabel mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(10, 96, 192, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);
		
		
		SetorCtrl RsContrl = new SetorCtrl(id_setor, txtDigitado, mensagemGravado, mensagemVazio, btnGravar);
		
		//Essa linha abaixo ser� excluida quando o menu estiver pronto pois ser� um evento do bot�o que chama este Form***
        RsContrl.gerarIdSetor();
        
		txtDigitado.addMouseListener(RsContrl.limpaCampo);
		txtDigitado.addActionListener(RsContrl.gravarSetor);
		btnGravar.addActionListener(RsContrl.gravarSetor);

	}
}
