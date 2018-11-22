 
 
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
 
import model.dao.impl.hibernate.InquilinoDAO;
import model.entity.hibernate.Inquilino;
import model.entity.hibernate.Cidade;
import model.dao.impl.hibernate.CidadeDAO; 
 public class Inquilinocad extends WindowCrud { 
 	
public Inquilino inquilino = new Inquilino();
 private Cidade cidade= new Cidade(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Inquilinocad() { 
 		super("inquilinocad.zul"); 
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
 				 
InquilinoDAO inquilinoDAO = new InquilinoDAO(); 
inquilino = inquilinoDAO.insertReg(this.inquilino);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.inquilino);
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
 					&& this.inquilino.getIdinquilino() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
InquilinoDAO inquilinoDAO = new InquilinoDAO(); 
	inquilinoDAO.updateReg(this.inquilino) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.inquilino); 
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
  new InquilinoDAO().deleteReg(inquilino);
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
 		
this.inquilino = new Inquilino();
 this.cidade=new Cidade(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/inquilinolis.zul", param, this, 
 				"retInquilino"); 
 	} 
  
 	public void retInquilino(Object ret) { 
 		if (ret != null) { 
 			this.inquilino = (Inquilino) ret; 
 			
 this.inquilino = (Inquilino) ret;
this.cidade=this.inquilino.getCidade(); 
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
 		if (this.inquilino != null) { 
 			//this.inquilino.setUsualt(this.getLogin()); 
 			//this.inquilino.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.inquilino.getCidade() == null) {
try{
Messagebox.show("Selecione o Cidade.");
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
 			Events.postEvent("onClose", this, this.inquilino); 
 		} 
 	} 
 	
 public Cidade getCidade() {return cidade;}
 public void setCidade(Cidade cidade) {
 if(cidade!=null){
this.inquilino.setCidade(cidade);
 }this.cidade=cidade;} 
 	
public void pesquisarCidade() {
	new WinUtils().abrirLis("forms/cidadelis.zul", null, this, "recebeCidade");
}
public void recebeCidade(Object obj) {
 if(obj!=null){
this.cidade=(Cidade) obj;
this.inquilino.setCidade(cidade);
 }
this.vincular();
} 
 	
public void addCidade() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "inquilinocad.zul");
	String url = "forms/cidadecad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeCidade");
} 
 	 
 } 
 