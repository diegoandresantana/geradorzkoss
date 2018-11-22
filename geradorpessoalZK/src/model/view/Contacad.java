 
 
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
 
import model.dao.impl.hibernate.ContaDAO;
import model.entity.hibernate.Conta;
import model.entity.hibernate.TipoConta;
import model.dao.impl.hibernate.TipoContaDAO; 
 public class Contacad extends WindowCrud { 
 	
public Conta conta = new Conta();
 public ListModelList lmtipoconta;
 private TipoConta tipoconta= new TipoConta(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Contacad() { 
 		super("contacad.zul"); 
 		Map<String, String> map = Executions.getCurrent().getArg(); 
 		if (map.get("pageName") != null) { 
 			abertoPeloMenu = Boolean.FALSE; 
 		} 
 	} 
  
 	public void initComponentes() { 
 		 
 lmtipoconta=new ListModelList(new TipoContaDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void incluir() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& !this.getCrdBar().getBotao(0).isDisabled()) { 
 				 
ContaDAO contaDAO = new ContaDAO(); 
conta = contaDAO.insertReg(this.conta);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.conta);
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
 					&& this.conta.getIdconta() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
ContaDAO contaDAO = new ContaDAO(); 
	contaDAO.updateReg(this.conta) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.conta); 
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
  new ContaDAO().deleteReg(conta);
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
 		
this.conta = new Conta();
 this.tipoconta=new TipoConta(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/contalis.zul", param, this, 
 				"retConta"); 
 	} 
  
 	public void retConta(Object ret) { 
 		if (ret != null) { 
 			this.conta = (Conta) ret; 
 			
 this.conta = (Conta) ret;
this.tipoconta=this.conta.getTipoconta(); 
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
 		if (this.conta != null) { 
 			//this.conta.setUsualt(this.getLogin()); 
 			//this.conta.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.conta.getTipoconta() == null) {
try{
Messagebox.show("Selecione o Tipo de Conta.");
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
 			Events.postEvent("onClose", this, this.conta); 
 		} 
 	} 
 	
 public TipoConta getTipoconta() {return tipoconta;}
 public void setTipoconta(TipoConta tipoconta) {
 if(tipoconta!=null){
this.conta.setTipoconta(tipoconta);
 }this.tipoconta=tipoconta;} 
 	
public void recebeTipoConta(Object obj) { 
 lmtipoconta=new ListModelList(new TipoContaDAO().getRegByExample(null));
 if(obj!=null){
this.tipoconta=(TipoConta) obj;
this.conta.setTipoconta(tipoconta);
 }
this.vincular();
} 
 	
public void addTipoConta() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "contacad.zul");
	String url = "forms/tipocontacad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeTipoConta");
} 
 	 
 } 
 