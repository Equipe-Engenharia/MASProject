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
	private JLabel lblIdDoSetor, lblNomeDoSetor, mensagemGravado, mensagemVazio;
	private JButton btnGravar, btnFechar;

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
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIdDoSetor = new JLabel("ID");
		lblIdDoSetor.setBounds(148, 35, 20, 16);
		contentPane.add(lblIdDoSetor);

		lblNomeDoSetor = new JLabel("Novo Setor");
		lblNomeDoSetor.setBounds(92, 87, 89, 16);
		contentPane.add(lblNomeDoSetor);

		txtDigitado = new JTextField();
		txtDigitado.setToolTipText("Digite aqui o novo setor!");
		txtDigitado.setBounds(180, 81, 178, 28);
		contentPane.add(txtDigitado);
		txtDigitado.setColumns(10);

		id_setor = new JTextField();
		id_setor.setEnabled(false);
		id_setor.setEditable(false);
		id_setor.setBounds(180, 33, 178, 20);
		id_setor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(id_setor);
		id_setor.setColumns(10);

		mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(43, 177, 230, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(43, 177, 230, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);

		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(288, 166, 97, 34);
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon("../MASProject/icons/out.png"));
		btnFechar.setBounds(397, 166, 97, 34);
		contentPane.add(btnFechar);

		SetorCtrl RsContrl = new SetorCtrl(id_setor, txtDigitado, mensagemGravado, mensagemVazio, btnGravar);

		// Essa linha abaixo será excluida quando o menu estiver pronto pois
		// será um evento do botão que chama este Form***
		RsContrl.gerarIdSetor();

		txtDigitado.addMouseListener(RsContrl.limpaCampo);
		txtDigitado.addActionListener(RsContrl.gravarSetor);
		txtDigitado.addKeyListener(RsContrl.ativaGravar);
		btnGravar.addActionListener(RsContrl.gravarSetor);
		btnFechar.addActionListener(RsContrl.fecharTela);

	}
}
