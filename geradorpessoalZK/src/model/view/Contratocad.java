 
 
package model.view; 
 import java.util.Date; 
 import java.util.HashMap; 
 import java.util.Map; 
 import org.zkoss.zul.ListModelList; 
 import org.zkoss.zul.Messagebox; 
 import pmcg.framework.ui.WindowCrud; 
 import pmcg.framework.util.WinUtils; 
 import org.zkoss.zk.ui.event.Events; 
 import org.zkoss.zk.ui.Executions; 
 import model.exceptions.DaoException; 
 
import model.dao.impl.hibernate.ContratoDAO;
import model.entity.hibernate.Contrato;
import model.entity.hibernate.Imovel;
import model.dao.impl.hibernate.ImovelDAO;
import model.entity.hibernate.Inquilino;
import model.dao.impl.hibernate.InquilinoDAO; 
 public class Contratocad extends WindowCrud { 
 	
public Contrato contrato = new Contrato();
 private Imovel imovel= new Imovel();
 private Inquilino inquilino= new Inquilino(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Contratocad() { 
 		super("contratocad.zul"); 
 		Map<String, String> map = Executions.getCurrent().getArg(); 
 		if (map.get("pageName") != null) { 
 			abertoPeloMenu = Boolean.FALSE; 
 		} 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void incluir() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& !this.getCrdBar().getBotao(0).isDisabled()) { 
 				 
ContratoDAO contratoDAO = new ContratoDAO(); 
contrato = contratoDAO.insertReg(this.contrato);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.contrato);
	}
				this.getCrdBar().getBotao(0).setDisabled(true);
				this.getCrdBar().getBotao(1).setDisabled(false);
				this.vincular(); 
 			} 
 		} catch (Exception e) { 
 			try { 
 				Messagebox.show("O registro não pode ser incluído: " 
 						+ e.getMessage(), "Erro:", Messagebox.OK, 
 						Messagebox.ERROR); 
 			} catch (InterruptedException e1) { 
 				e1.printStackTrace(); 
 			} 
 			e.printStackTrace(); 
 		} 
 	} 
  
 	@Override 
 	public void salvar() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& this.contrato.getIdcontrato() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
ContratoDAO contratoDAO = new ContratoDAO(); 
	contratoDAO.updateReg(this.contrato) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.contrato); 
	} 
				this.vincular(); 
 			} 
 		} catch (Exception e) { 
 			e.printStackTrace(); 
 		} 
 		this.limpar(); 
 	} 
  
 	@Override 
 	public void apagar() { 
 		 
 		try { 
 			if (Messagebox.show("Deseja realmente excluir ?", "Aviso!!!", 
 					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) { 
 				
try {
  new ContratoDAO().deleteReg(contrato);
			Messagebox.show("Registro excluído com sucesso!");
	this.limpar();
	} catch (DaoException e) {
	Messagebox.show("Registro excluído com sucesso!");
} 
 			} 
 			 
 		} catch (InterruptedException e) { 
 			try { 
 				Messagebox.show("Registro não pode ser excluído!"); 
 			} catch (InterruptedException e1) { 
 				e1.printStackTrace(); 
 			} 
 			e.printStackTrace(); 
 		} 
 	} 
  
 	@Override 
 	public void limpar() { 
 		
this.contrato = new Contrato();
 this.imovel=new Imovel();
 this.inquilino=new Inquilino(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/contratolis.zul", param, this, 
 				"retContrato"); 
 	} 
  
 	public void retContrato(Object ret) { 
 		if (ret != null) { 
 			this.contrato = (Contrato) ret; 
 			
 this.contrato = (Contrato) ret;
this.imovel=this.contrato.getImovel();
this.inquilino=this.contrato.getInquilino(); 
 			this.getCrdBar().getBotao(0).setDisabled(true); 
 			this.getCrdBar().getBotao(1).setDisabled(false); 
 			this.getCrdBar().getBotao(2).setDisabled(false); 
 			this.vincular(); 
 		} 
 	} 
  
 	@Override 
 	public void imprimir() { 
 	} 
  
 	public boolean trataVO() { 
 		if (this.contrato != null) { 
 			//this.contrato.setUsualt(this.getLogin()); 
 			//this.contrato.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.contrato.getImovel() == null) {
try{
Messagebox.show("Selecione o Imovel.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.contrato.getInquilino() == null) {
try{
Messagebox.show("Selecione o Inquilino.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 			 
 			return true; 
 		} 
 		return false; 
 	} 
  
 	@Override 
 	public void sair() { 
 		if (this.abertoPeloMenu) { 
 			this.detach(); 
 		} else { 
 			Events.postEvent("onClose", this, this.contrato); 
 		} 
 	} 
 	
 public Imovel getImovel() {return imovel;}
 public Inquilino getInquilino() {return inquilino;}
 public void setImovel(Imovel imovel) {
 if(imovel!=null){
this.contrato.setImovel(imovel);
 }this.imovel=imovel;}
 public void setInquilino(Inquilino inquilino) {
 if(inquilino!=null){
this.contrato.setInquilino(inquilino);
 }this.inquilino=inquilino;} 
 	
public void pesquisarImovel() {
	new WinUtils().abrirLis("forms/imovellis.zul", null, this, "recebeImovel");
}
public void recebeImovel(Object obj) {
 if(obj!=null){
this.imovel=(Imovel) obj;
this.contrato.setImovel(imovel);
 }
this.vincular();
}
public void pesquisarInquilino() {
	new WinUtils().abrirLis("forms/inquilinolis.zul", null, this, "recebeInquilino");
}
public void recebeInquilino(Object obj) {
 if(obj!=null){
this.inquilino=(Inquilino) obj;
this.contrato.setInquilino(inquilino);
 }
this.vincular();
} 
 	
public void addImovel() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "contratocad.zul");
	String url = "forms/imovelcad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeImovel");
}
public void addInquilino() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "contratocad.zul");
	String url = "forms/inquilinocad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeInquilino");
} 
 	 
 } 
 