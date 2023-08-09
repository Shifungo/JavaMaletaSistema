
package armario.view;
import armario.controller.MaletaController;
import armario.controller.RelatorioController;

import java.io.OutputStream;
import armario.controller.VinculoItemMaleta;
import armario.model.Itens;
import armario.model.Maletas;
import net.sf.jasperreports.engine.JRException;
import save.SaveIniFile;
import armario.controller.ItemController;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


import javax.swing.GroupLayout.Alignment;

import java.lang.invoke.VarHandle;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.Properties;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class AddItemsMaleta extends javax.swing.JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private static final String CONFIG_FILE_PATH = "config.ini";
    private static final String LAST_COMBOBOX_KEY = "lastComboBoxSelection";

	
	ItemController itemController = new ItemController();
	MaletaController maletaController = new MaletaController();
	VinculoItemMaleta vinculoItemMaleta = new VinculoItemMaleta();
	
	 private static void saveLastComboBoxSelection(String string) throws IOException {
	        Properties properties = new Properties();
	        properties.setProperty(LAST_COMBOBOX_KEY, string);

	        try (OutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
	            properties.store(output, "Last selected item in the ComboBox");
	        }
	    }
	 
	 private static String loadLastComboBoxSelection() throws IOException {
		    Properties properties = new Properties();
		    try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
		        properties.load(input);
		    }
		    return properties.getProperty(LAST_COMBOBOX_KEY);
		}

    
    public AddItemsMaleta() {
    	setResizable(false);
    	getContentPane().setBackground(new Color(128, 128, 128));
        initComponents();
    }
  
    @SuppressWarnings("unchecked")
    
    private void initComponents()  {
    	
    	setTitle("AddItem");
    	
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(new Color(0, 255, 0));
        jLabel1 = new javax.swing.JLabel();
        selectMaleta = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(new Color(255, 128, 128));
        jLabel2 = new javax.swing.JLabel();
        textoPesquisa = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemList = new javax.swing.JList<Itens>();
        openCadastroItem = new javax.swing.JButton();
        OpenAddMaleta = new javax.swing.JButton();
        OpenAddMaleta.setFont(new Font("Tahoma", Font.PLAIN, 16));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome Maleta");

        
        
        selectMaleta.setModel(new javax.swing.DefaultComboBoxModel<Maletas> (maletaController.getMaletaList()));        
        selectMaleta.addActionListener(e -> {
            Maletas selectedItem = (Maletas) selectMaleta.getSelectedItem();
            if (selectedItem != null) {
                try {
                    saveLastComboBoxSelection(selectedItem.getNome_maleta());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        try {
            String lastSelectedItemName = loadLastComboBoxSelection();
            
            if (lastSelectedItemName != null) {
                Maletas lastSelectedItem = maletaController.findMaletasByName(lastSelectedItemName);
                if (lastSelectedItem != null) {
                	
                	
                    selectMaleta.setSelectedItem(lastSelectedItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
        				.addComponent(selectMaleta, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(selectMaleta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);
        JList<Itens> itemList = new JList<Itens>(); 
        jLabel2.setText("Pesquisa Item");

        Itens[] itensParaLista = itemController.getItemNames();
        
        
        textoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	
            	String filterTextString = textoPesquisa.getText().trim().toLowerCase();
            	DefaultListModel<Itens> listaFiltrada = new DefaultListModel<>();
            	if (filterTextString.isEmpty() || filterTextString.isBlank()) {
        		
        			
        			itemList.setListData(itensParaLista);}
            	else {
            		for (int i = 0; i<itemList.getModel().getSize();i++) {
                		Itens itemString =itemList.getModel().getElementAt(i) ;
                		
                		if(itemString.getNome_itens().toLowerCase().contains(filterTextString)) {
                			listaFiltrada.addElement(itemString);
                			itemList.setModel(listaFiltrada);
                		
                		}/*if(!itemString.getNome_itens().toLowerCase().contains(filterTextString)) {
                			System.out.println("teste3");
                			listaFiltrada.clear();
                			itemList.setModel(listaFiltrada);
                		}*/else {
                			
                			listaFiltrada.clear();
                			itemList.setModel(listaFiltrada);
    					}
                	}
				}
            	
            	
                jTextField1ActionPerformed(evt);
            }
        });
        
        jButton2.setText("Pesquisar");
        
        
        

    
        
       
        
        DefaultListModel<Itens> model = new DefaultListModel<Itens>();
        
        itemList.setModel(model);  
        itemList.setListData(itensParaLista);
        
        
        jScrollPane1.setViewportView(itemList);
        openCadastroItem.setText("cadastrar novo item");
        openCadastroItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openCadastroItemActionPerformed(evt);
               
            }
        });
        addButton = new javax.swing.JButton();
        
                addButton.setText("ADD ITEM");
                addButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                        	Itens item = itemList.getSelectedValue();
                            Maletas maleta = (Maletas) selectMaleta.getSelectedItem();                         
                            try {
                            	
                            	vinculoItemMaleta.VincularItemMaleta(maleta, item);
                    		} catch (Exception e) {
                    			throw new Exception("erro na addButton" + e);
                    		}
						} catch (Exception e) {
							
							e.printStackTrace();
						}
                    }
                });
        
        JButton gerarRelatorio = new JButton("relatorioItems");
        gerarRelatorio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RelatorioController relatiorioController = new RelatorioController();
        		relatiorioController.relatorioController();
        	}
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(16)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(jLabel2)
        					.addGap(18)
        					.addComponent(textoPesquisa, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(jButton2)
        					.addGap(0, 125, Short.MAX_VALUE))
        				.addComponent(openCadastroItem, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        				.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gerarRelatorio))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel2)
        						.addComponent(textoPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton2))
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addContainerGap(22, Short.MAX_VALUE))
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addGap(24)
        							.addComponent(openCadastroItem)
        							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
        							.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        							.addGap(24))))
        				.addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        					.addComponent(gerarRelatorio)
        					.addContainerGap())))
        );
        jPanel2.setLayout(jPanel2Layout);

        OpenAddMaleta.setText("+ AddMaleta");
        OpenAddMaleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenAddMaletaActionPerformed(evt);
            }
        });
        
        btnMostrarMaleta = new JButton("Consultar Maletas");
        btnMostrarMaleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMostrarMaleta.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		ConsultarMaleta consultarMaleta = new ConsultarMaleta();
        		consultarMaleta.setVisible(true);
        	}
        });
        
        JButton relatorioMaletas = new JButton("Relatorio de todas as maletas");
        relatorioMaletas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RelatorioController relatorioController = new RelatorioController();
        		try {
					relatorioController.showMasterReport("RelatorioMaletas.jasper");
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jPanel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(OpenAddMaleta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnMostrarMaleta, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
        					.addGap(111))
        				.addComponent(relatorioMaletas, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(OpenAddMaleta, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnMostrarMaleta, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(relatorioMaletas, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
       
    	
    }

    private void selectMaletaActionPerformed(java.awt.event.ActionEvent evt) {
    }


        
        
    


    private void OpenAddMaletaActionPerformed(java.awt.event.ActionEvent evt) {
        AddMaleta addMaleta = new AddMaleta();
        AddItemsMaleta addItemsMaleta = new AddItemsMaleta();
        addItemsMaleta.setVisible(false);
        addMaleta.setVisible(true);
        
    }

    private void openCadastroItemActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroItem cadastroItem = new CadastroItem();
        cadastroItem.setVisible(true);
    }
    
    public static void main(String args[]) {
    	
    	
    	
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddItemsMaleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItemsMaleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItemsMaleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItemsMaleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItemsMaleta().setVisible(true);
            }
        });
    }

    
    private javax.swing.JButton OpenAddMaleta;
    private javax.swing.JButton addButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<Itens> itemList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textoPesquisa;
    private javax.swing.JButton openCadastroItem;
    private javax.swing.JComboBox<Maletas> selectMaleta;
    private JButton btnMostrarMaleta;
}
