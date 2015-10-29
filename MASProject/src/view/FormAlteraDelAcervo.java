package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.AcervoController;

public class FormAlteraDelAcervo extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField nome_artist;
	private JTextField nome_obra;
	private JTextField data_obra;
	private JComboBox<String> cbCategoria;
	private JComboBox<String> cbMaterial;
	private JComboBox<String> cbSetor;
	private JComboBox<String> comboStatus;
	private JTextField textField_valor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAlteraDelAcervo frame = new FormAlteraDelAcervo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormAlteraDelAcervo() {
		setResizable(false);
		setTitle("Excluir/Alterar Acervo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 674);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// JLabel lblId = new JLabel("ID.Obra");
		// lblId.setBounds(103, 27, 62, 14);
		// contentPane.add(lblId);

		// textField = new JTextField();
		// textField.setEnabled(false);
		// textField.setBounds(166, 27, 86, 17);
		// textField.setEditable(false);
		// contentPane.add(textField);
		// textField.setColumns(10);

		JLabel lblArtista = new JLabel("Artista");
		lblArtista.setBounds(113, 26, 52, 14);
		contentPane.add(lblArtista);

		JLabel msgGravado = new JLabel("Dados Salvos com sucesso");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(43, 600, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 600, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		// nome_artist = new JTextField();
		// nome_artist.setBounds(166, 56, 352, 20);
		// contentPane.add(nome_artist);
		// nome_artist.setColumns(10);

		JComboBox cbNomeArtistas = new JComboBox<String>();
		cbNomeArtistas.setBounds(166, 26, 352, 20);
		contentPane.add(cbNomeArtistas);

		JButton btnPesquisaArtist = new JButton("");
		btnPesquisaArtist.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaArtist.setBounds(522, 22, 29, 28);
		contentPane.add(btnPesquisaArtist);

		// JButton btnPesquisaId = new JButton("");
		// btnPesquisaId.setIcon(new
		// ImageIcon("../MASProject/icons/search.png"));
		// btnPesquisaId.setBounds(255, 18, 29, 28);
		// contentPane.add(btnPesquisaId);

		JButton btnPesquisaNome = new JButton("");
		btnPesquisaNome.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaNome.setBounds(522, 55, 29, 28);
		contentPane.add(btnPesquisaNome);

		JLabel lblNomeDaObra = new JLabel("Nome da Obra");
		lblNomeDaObra.setBounds(67, 58, 98, 14);
		contentPane.add(lblNomeDaObra);

		JTextField txtNovaObra = new JTextField("Nome da Obra Atualizada");
		txtNovaObra.setBounds(166, 88, 352, 20);
		contentPane.add(txtNovaObra);

		// nome_obra = new JTextField();
		// nome_obra.setBounds(206, 110, 352, 20);
		// contentPane.add(nome_obra);
		// nome_obra.setColumns(10);

		JComboBox cbObras = new JComboBox<String>();
		cbObras.setBounds(166, 58, 352, 20);
		contentPane.add(cbObras);

		JLabel lblNewLabel = new JLabel("Data de Composição");
		lblNewLabel.setBounds(28, 120, 139, 14);
		contentPane.add(lblNewLabel);

		data_obra = new JTextField();
		data_obra.setBounds(168, 120, 86, 20);
		contentPane.add(data_obra);
		data_obra.setColumns(10);

		JLabel lblCategoriaDaObra = new JLabel("Categoria da Obra");
		lblCategoriaDaObra.setBounds(44, 152, 122, 14);
		contentPane.add(lblCategoriaDaObra);

		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(167, 152, 133, 20);
		contentPane.add(cbCategoria);

		JLabel lblMaterial = new JLabel("Material da Obra");
		lblMaterial.setBounds(49, 219, 122, 14);
		contentPane.add(lblMaterial);

		cbMaterial = new JComboBox<String>();
		cbMaterial.setBounds(166, 219, 133, 20);
		contentPane.add(cbMaterial);

		JLabel lblNewLabel_1 = new JLabel("Descrição da Obra");
		lblNewLabel_1.setBounds(28, 330, 139, 14);
		contentPane.add(lblNewLabel_1);

		JEditorPane editor_descricao = new JEditorPane();
		editor_descricao.setBounds(28, 346, 558, 81);
		contentPane.add(editor_descricao);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(115, 195, 576, 144);
		contentPane.add(lblStatus);

		comboStatus = new JComboBox<String>();
		comboStatus.setBounds(167, 258, 110, 20);
		contentPane.add(comboStatus);

		JLabel lblValor = new JLabel("Valor :");

		lblValor.setBounds(28, 388, 576, 144);
		contentPane.add(lblValor);
		textField_valor = new JTextField();
		textField_valor.setBounds(68, 448, 110, 20);
		contentPane.add(textField_valor);

		JLabel Setor = new JLabel("Setor");
		Setor.setBounds(127, 188, 46, 14);
		contentPane.add(Setor);

		cbSetor = new JComboBox<String>();
		cbSetor.setBounds(167, 185, 110, 20);
		contentPane.add(cbSetor);

		JButton btnGravar = new JButton("Gravar Altera��es");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(200, 594, 177, 34);
		contentPane.add(btnGravar);

		JLabel lblSelecImagem = new JLabel("");
		lblSelecImagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		lblSelecImagem.setBackground(SystemColor.inactiveCaption);
		lblSelecImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecImagem.setBounds(397, 137, 189, 147);
		lblSelecImagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lblSelecImagem);

		JButton btnPesquisarImagem = new JButton("");
		btnPesquisarImagem.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnPesquisarImagem.setBounds(440, 299, 46, 35);
		contentPane.add(btnPesquisarImagem);

		JButton btnExcluirImagem = new JButton("");
		btnExcluirImagem.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluirImagem.setBounds(497, 299, 46, 35);
		contentPane.add(btnExcluirImagem);

		AcervoController Acontroller = new AcervoController(cbNomeArtistas, cbObras, txtNovaObra, cbSetor, cbMaterial,
				cbCategoria, comboStatus, data_obra, editor_descricao, msgGravado, msgVazio, textField_valor);

		btnPesquisarImagem.addActionListener(Acontroller.inserir_imagem);

		btnExcluirImagem.addActionListener(Acontroller.remover_imagem);
		// btnGravar.addActionListener(Acontroller.gravarAcervo);

		JButton btnExcluir = new JButton("Excluir Obra");
		btnExcluir.setIcon(new ImageIcon("../MASProject/icons/delete.png"));

		// btnExcluir.addActionListener(Acontroller.excluirObras);
		Acontroller.preencherComboBoxAtista();
		Acontroller.preencherComboBoxObras();
		
		btnExcluir.setBounds(402, 594, 177, 34);
		contentPane.add(btnExcluir);

	}
}
