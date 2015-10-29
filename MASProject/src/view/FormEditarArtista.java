package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;  
  
public class FormEditarArtista extends JDialog{  
  
    public FormEditarArtista(FormIntermediarioArtista parent, boolean modal){  
        Edit();  
        this.setLocationRelativeTo(parent);  
    }

	private void Edit() {
		    panelPrincipal = new JPanel();  
	        btnEdit = new JButton();    
	        setTitle("Editar/Exluir ");  
	        setSize(400, 300);  
	        setModal(true);  
	          
	        panelPrincipal.setLayout(null);  
	        panelPrincipal.setBackground(Color.LIGHT_GRAY);  
	          
	      
	 
			
	        
	        
	       
	        
	        btnEdit.setText("Editar");  
	        btnEdit.setBounds(111, 211, 92, 40);  
	        btnEdit.addActionListener(new ActionListener() {  
	            @Override  
	            public void actionPerformed(ActionEvent arg0) {  
	                dispose();  
	                  
	            }  
	  
	        });  

	        getContentPane().add(panelPrincipal);  
	        panelPrincipal.add(btnEdit);  
	        
	        
	        getContentPane().add(panelPrincipal);  
	        panelPrincipal.add(btnEdit);  
	      

	        textField = new JTextField();
	        textField.setBounds(111, 44, 204, 20);
	        panelPrincipal.add(textField);
	        textField.setColumns(10);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(111, 113, 204, 20);
	        panelPrincipal.add(textField_1);
	        textField_1.setEditable(false);
	        textField_1.setColumns(10);
	        
	        JLabel lblNomeDoArtista = new JLabel("Nome Do Artista");
	        lblNomeDoArtista.setBounds(10, 47, 108, 14);
	        panelPrincipal.add(lblNomeDoArtista);
	        
	        JLabel lblIdDoArtista = new JLabel("ID Do Artista");
	        lblIdDoArtista.setBounds(23, 116, 80, 14);
	        panelPrincipal.add(lblIdDoArtista);
	        
	        btnNewButton = new JButton("Excluir");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnNewButton.setBounds(229, 211, 89, 40);
	        panelPrincipal.add(btnNewButton);
	        
	        btnPesquisar = new JButton("Pesquisar");
	        btnPesquisar.setBounds(152, 79, 118, 23);
	        panelPrincipal.add(btnPesquisar);

		
	}
	 private JPanel panelPrincipal;  
	    private JButton btnEdit; 
	    private JTextField textField;
	    private JTextField textField_1;
	    private JButton btnNewButton;
	    private JButton btnPesquisar;
}
       