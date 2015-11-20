package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JLabel;

import controller.ObraArtistaCtrl;

public class FrmObraArtistaSelec extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JList<String> listObras;
	private JList<String> listObrasSelecionadas;
	
	//modelo para as listas
	private DefaultListModel<String> listModelObras = new DefaultListModel<>();
	private DefaultListModel<String> listModelObrasSelecionadas = new DefaultListModel<>();
	
	private JButton btnMoveObra, btnUndoMoveObra;
	private JButton btnEnviarObras;
	private JButton btnAdicionarTodos, btnRemoverTodos;
	
	private JLabel lblObrasParaSelecionar;
	private JLabel lblObrasSelecionadas;
	private JLabel lblStatus;
	
	private String nomeArtista;
	
	private final JScrollPane scrollSelecionada = new JScrollPane();
	private JScrollPane scrollSelecionar;
	
	private DefaultTableModel tableModel;
	private JTable tableObras;
	/**
	 * Create the dialog.
	 */
	public FrmObraArtistaSelec(String nomeArtista, DefaultTableModel tableModel, JTable tableObras) {
		this.nomeArtista = nomeArtista;
		this.tableModel = tableModel;
		this.tableObras = tableObras;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Obras do artista: " + this.nomeArtista);
		setBounds(100, 100, 496, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblObrasParaSelecionar = new JLabel("Obras para selecionar:");
		lblObrasParaSelecionar.setBounds(12, 44, 169, 15);
		contentPanel.add(lblObrasParaSelecionar);
		
		lblObrasSelecionadas = new JLabel("Obras selecionadas:");
		lblObrasSelecionadas.setBounds(279, 44, 153, 15);
		contentPanel.add(lblObrasSelecionadas);

		btnMoveObra = new JButton(">>");
		btnMoveObra.setBounds(170, 71, 106, 36);
		contentPanel.add(btnMoveObra);
		
		btnUndoMoveObra = new JButton("<<");
		btnUndoMoveObra.setBounds(170, 113, 106, 36);
		contentPanel.add(btnUndoMoveObra);

		btnAdicionarTodos = new JButton(">> Todos");
		btnAdicionarTodos.setBounds(170, 161, 109, 25);
		contentPanel.add(btnAdicionarTodos);
		
		btnRemoverTodos = new JButton("<< Todos");
		btnRemoverTodos.setBounds(170, 198, 106, 25);
		contentPanel.add(btnRemoverTodos);
		
		//Posicionando botao enviarObras de uma forma diferente
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEnviarObras = new JButton("Enviar Obras");
				btnEnviarObras.setActionCommand("OK");
				buttonPane.add(btnEnviarObras);
				getRootPane().setDefaultButton(btnEnviarObras);
			}
		}
		
		scrollSelecionada.setBounds(278, 71, 146, 98);
		contentPanel.add(scrollSelecionada);
		
		//Barra de rolagem
		scrollSelecionar = new JScrollPane();
		scrollSelecionar.setBounds(12, 71, 146, 98);
		contentPanel.add(scrollSelecionar);
		
		listObras = new JList<>(listModelObras);
		scrollSelecionar.setViewportView(listObras);
		listObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listObrasSelecionadas = new JList<>(listModelObrasSelecionadas);
		scrollSelecionada.setViewportView(listObrasSelecionadas);
		listObrasSelecionadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lblStatus = new JLabel("Status: Tudo OK!");
		lblStatus.setBounds(12, 12, 443, 15);
		contentPanel.add(lblStatus);
		
		//CONTROLLER
		ObraArtistaCtrl oAController = new ObraArtistaCtrl(listObras, listObrasSelecionadas, 
				btnEnviarObras, btnMoveObra, btnUndoMoveObra, nomeArtista, 
				btnAdicionarTodos, btnRemoverTodos, listModelObras, 
				listModelObrasSelecionadas, lblStatus, contentPanel, this.tableModel, this.tableObras);
		
		//AÇÕES DOS BOTÕES
		btnMoveObra.addActionListener(oAController);
		btnUndoMoveObra.addActionListener(oAController);
		btnAdicionarTodos.addActionListener(oAController);
		btnRemoverTodos.addActionListener(oAController);
		btnEnviarObras.addActionListener(oAController);
		//REALIZA A AÇÃO ANTERIOR E DEPOIS FECHA - SOMENTE PRA TESTE
		btnEnviarObras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		//AÇÕES DAS JLISTS
		listObras.addListSelectionListener(oAController);
		listObrasSelecionadas.addListSelectionListener(oAController);
	}
}