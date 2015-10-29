package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Operacoes;
import model.Artista;  
  
public class FormNovoArtista extends JDialog{  

	JTextField nomeDoArtista;
	JTextField paraPesquisar;
	JTextField idArtista;
    Operacoes op =new Operacoes();
    private JPanel panelPrincipal;  
    private JButton btnGravar; 
    private JTextField textField;
    private JTextField textField_1;
    
    public FormNovoArtista(FormIntermediarioArtista parent, boolean modal){  
        Iniciar();  
        this.setLocationRelativeTo(parent);  
    }  
    
   
	public void Iniciar() {  
        panelPrincipal = new JPanel();  
        btnGravar = new JButton();  
        setTitle("Novo Artista");  
        setSize(400, 300);  
        setModal(true);  
        panelPrincipal.setLayout(null);  
        panelPrincipal.setBackground(Color.LIGHT_GRAY);  
        getContentPane().add(panelPrincipal);  
        panelPrincipal.add(btnGravar);  

        textField = new JTextField();
        textField.setBounds(142, 44, 204, 20);
        panelPrincipal.add(textField);
        //////////////////////////////
        nomeDoArtista=textField;
        ////////////////////////////////
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(142, 87, 204, 20);
        panelPrincipal.add(textField_1);
        textField_1.setEditable(false);
        ///////////////////////////
        idArtista=textField_1;
        ////////////////////////////////////
        textField_1.setColumns(10);
        
        btnGravar.setText("Gravar");  
        btnGravar.setBounds(142, 211, 80, 40);  
        btnGravar.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent arg0) {  
            	try {
					op.escreverArquivo("D:\\Documentos", "ListaDeArtistas", nomeDoArtista, idArtista);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }  
        });  
        
        JLabel lblNomeDoArtista = new JLabel("Nome Do Artista");
        lblNomeDoArtista.setBounds(34, 47, 108, 14);
        panelPrincipal.add(lblNomeDoArtista);
        
        JLabel lblIdDoArtista = new JLabel("ID Do Artista");
        lblIdDoArtista.setBounds(34, 93, 80, 14);
        panelPrincipal.add(lblIdDoArtista);
    }  
}  

