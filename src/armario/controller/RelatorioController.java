package armario.controller;



import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.fill.ReportTemplateSource;
import net.sf.jasperreports.view.JasperViewer;
import armario.dao.ItemsDAO;
import armario.model.Itens;

import java.sql.Connection;
import java.sql.SQLException;

import armario.dao.ConnectionMVC;


public class RelatorioController {

	public void relatorioController() {
		ItemsDAO itemsDAO = new ItemsDAO();
		
		
		
		try {
			Itens[] itens = itemsDAO.getItemName();
			JasperReport jasperReport = JasperCompileManager.compileReport("relatorio_tabela_itens.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanArrayDataSource(itens));
			JasperViewer.viewReport(jasperPrint,false);
			 
			 
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
	}
	public void showMasterReport(String reportTemplatePath) throws JRException, SQLException {
		try {
			Connection conn = new ConnectionMVC().getConnection();
			String jasperFilePathString = reportTemplatePath;
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePathString, null,conn);
			
			JasperViewer.viewReport(jasperPrint,false);
		} catch (JRException |SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
