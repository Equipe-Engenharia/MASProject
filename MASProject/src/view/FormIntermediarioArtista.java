package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


  
public class FormIntermediarioArtista extends JFrame{  
    public FormIntermediarioArtista(){  
        Iniciar();  
        setLocationRelativeTo(null);  
    }  
      
    public void Iniciar() {  
        panelPrincipal = new JPanel();  
        btnNovo = new JButton();  
        btnEdit = new JButton();  
        
        btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 new FormEditarArtista(null, true).setVisible(true);  
        	}
        });
        
          
        setTitle("Menu");  
        setSize(400, 400);  
          
        panelPrincipal.setLayout(null);  
        panelPrincipal.setBackground(Color.LIGHT_GRAY);  
          
        btnNovo.setText("Novo");  
        btnNovo.setBounds(45, 50, 300, 40);  
        btnNovo.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent arg0) {  
                new FormNovoArtista(null, true).setVisible(true);  
            }  
  
        });  
          
        btnEdit.setText("Editar/Excluir");  
        btnEdit.setBounds(45, 100, 300, 40);  
       
 
          
          
        getContentPane().add(panelPrincipal);  
        panelPrincipal.add(btnNovo);  
        panelPrincipal.add(btnEdit);  
        
    }  
    public static void main(String[] args) {  
        new FormIntermediarioArtista().setVisible(true);  
    }  
    private JPanel panelPrincipal;  
    private JButton btnNovo;  
    private JButton btnEdit;  
}  