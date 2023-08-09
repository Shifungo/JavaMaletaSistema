package armario.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import armario.dao.MaletaDAO;
import armario.model.Itens;

import javax.swing.JTextField;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ConsultarMaleta extends JFrame {

	private JPanel contentPane;
	private JTextField textMaleta;
	private JButton btnPesquisar;
	
	private static final String CONFIG_FILE_PATH = "configText.ini";
    private static final String LAST_TEXTBOX_KEY = "lastComboBoxSelection";

	 private static void saveLastTextBox(String string) throws IOException {
	        Properties properties = new Properties();
	        properties.setProperty(LAST_TEXTBOX_KEY, string);

	        try (OutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
	            properties.store(output, "Last selected item in the ComboBox");
	        }
	    }
	 
	 private static String loadLastTextBox() throws IOException {
		    Properties properties = new Properties();
		    try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
		        properties.load(input);
		    }
		    return properties.getProperty(LAST_TEXTBOX_KEY);
		}
	
	public Itens[] pesquisarItem() {
		String maletaName = textMaleta.getText();
		MaletaDAO maletaDAO = new MaletaDAO();
		Itens[] listaDeItens = new Itens[0];
		try {
			int maletaid = maletaDAO.getMaletaIdByName(maletaName);
			
		listaDeItens = maletaDAO.getItensNaMaleta(maletaid);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return listaDeItens;
	}
	
	public ConsultarMaleta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		textMaleta = new JTextField();
		
		JList<Itens> ListaItemsMaleta = new JList<Itens>();

		textMaleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Itens[]	resultadoItens = pesquisarItem();
				ListaItemsMaleta.setListData(resultadoItens);	
			}
		});

		textMaleta.setColumns(10);

		JLabel lblNewLabel = new JLabel("Digite o nome da maleta");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

		
		ListaItemsMaleta.setToolTipText("teste");

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Itens[]	resultadoItens = pesquisarItem();
			ListaItemsMaleta.setListData(resultadoItens);	
			}
		});

		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(textMaleta, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPesquisar,
										GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(
								ListaItemsMaleta, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(137, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(41)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textMaleta, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(ListaItemsMaleta, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
		
		try {
			String lastText = loadLastTextBox();
			if(lastText != null) {
				textMaleta.setText(lastText);
				Itens[] resultadoItens = pesquisarItem();
				ListaItemsMaleta.setListData(resultadoItens);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					saveLastTextBox(textMaleta.getText());
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
	}
}
