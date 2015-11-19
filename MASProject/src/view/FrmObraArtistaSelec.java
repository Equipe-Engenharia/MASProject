package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;

import controller.ArtistaCtrl;
import controller.ObraArtistaCtrl;
import java.awt.ScrollPane;

public class FrmObraArtistaSelec extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<Object> listObras;
	private JList<Object> listObrasSelecionadas;
	private JButton btnMoveObj, btnUndoMove;
	private JLabel lblObrasParaSelecionar;
	private JLabel lblObrasSelecionadas;
	private JButton btnEnviarObras;
	private String nomeArtista;
	private ArtistaCtrl aController;
	private final JScrollPane scrollSelecionada = new JScrollPane();
	private JScrollPane scrollSelecionar;
	/**
	 * Create the dialog.
	 */
	public FrmObraArtistaSelec(String nomeArtista) {
		this.nomeArtista = nomeArtista;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Obras do artista: " + this.nomeArtista);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		aController = new ArtistaCtrl();
		String artistas[] = aController.preencherComboBoxArtista();
		
		btnMoveObj = new JButton(">>");
		btnMoveObj.setBounds(190, 71, 62, 36);
		contentPanel.add(btnMoveObj);
		
		btnUndoMove = new JButton("<<");
		btnUndoMove.setBounds(190, 119, 62, 36);
		contentPanel.add(btnUndoMove);
		
		lblObrasParaSelecionar = new JLabel("Obras para selecionar:");
		lblObrasParaSelecionar.setBounds(12, 44, 169, 15);
		contentPanel.add(lblObrasParaSelecionar);
		
		lblObrasSelecionadas = new JLabel("Obras selecionadas:");
		lblObrasSelecionadas.setBounds(279, 44, 153, 15);
		contentPanel.add(lblObrasSelecionadas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEnviarObras = new JButton("OK");
				btnEnviarObras.setActionCommand("OK");
				buttonPane.add(btnEnviarObras);
				getRootPane().setDefaultButton(btnEnviarObras);
			}
		}
		scrollSelecionada.setBounds(278, 71, 146, 98);
		contentPanel.add(scrollSelecionada);
		
//		Object name[] = {"R2", "Darth Vader"}; //teste do JList
		listObrasSelecionadas = new JList<Object>();
		scrollSelecionada.setViewportView(listObrasSelecionadas);
		
		scrollSelecionar = new JScrollPane();
		scrollSelecionar.setBounds(12, 71, 146, 98);
		contentPanel.add(scrollSelecionar);
		listObras = new JList<Object>(artistas);
		scrollSelecionar.setViewportView(listObras);
		
		ObraArtistaCtrl oAController = new ObraArtistaCtrl(listObras,
				listObrasSelecionadas, btnEnviarObras, btnMoveObj,
				btnUndoMove, this.nomeArtista);
		
	}
}