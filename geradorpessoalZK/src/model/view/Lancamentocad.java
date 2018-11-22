 
 
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
 
import model.dao.impl.hibernate.LancamentoDAO;
import model.entity.hibernate.Lancamento;
import model.entity.hibernate.Conta;
import model.dao.impl.hibernate.ContaDAO;
import model.entity.hibernate.Contrato;
import model.dao.impl.hibernate.ContratoDAO;
import model.entity.hibernate.StatusLancamento;
import model.dao.impl.hibernate.StatusLancamentoDAO; 
 public class Lancamentocad extends WindowCrud { 
 	
public Lancamento lancamento = new Lancamento();
 public ListModelList lmconta;
 public ListModelList lmstatuslancamento;
 private Conta conta= new Conta();
 private Contrato contrato= new Contrato();
 private StatusLancamento statuslancamento= new StatusLancamento(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Lancamentocad() { 
 		super("lancamentocad.zul"); 
 		Map<String, String> map = Executions.getCurrent().getArg(); 
 		if (map.get("pageName") != null) { 
 			abertoPeloMenu = Boolean.FALSE; 
 		} 
 	} 
  
 	public void initComponentes() { 
 		 
 lmconta=new ListModelList(new ContaDAO().getRegByExample(null)); 
 lmstatuslancamento=new ListModelList(new StatusLancamentoDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void incluir() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& !this.getCrdBar().getBotao(0).isDisabled()) { 
 				 
LancamentoDAO lancamentoDAO = new LancamentoDAO(); 
lancamento = lancamentoDAO.insertReg(this.lancamento);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.lancamento);
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
 					&& this.lancamento.getIdlancamento() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
LancamentoDAO lancamentoDAO = new LancamentoDAO(); 
	lancamentoDAO.updateReg(this.lancamento) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.lancamento); 
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
  new LancamentoDAO().deleteReg(lancamento);
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
 		
this.lancamento = new Lancamento();
 this.conta=new Conta();
 this.contrato=new Contrato();
 this.statuslancamento=new StatusLancamento(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/lancamentolis.zul", param, this, 
 				"retLancamento"); 
 	} 
  
 	public void retLancamento(Object ret) { 
 		if (ret != null) { 
 			this.lancamento = (Lancamento) ret; 
 			
 this.lancamento = (Lancamento) ret;
this.conta=this.lancamento.getConta();
this.contrato=this.lancamento.getContrato();
this.statuslancamento=this.lancamento.getStatuslancamento(); 
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
 		if (this.lancamento != null) { 
 			//this.lancamento.setUsualt(this.getLogin()); 
 			//this.lancamento.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.lancamento.getConta() == null) {
try{
Messagebox.show("Selecione o Conta.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.lancamento.getContrato() == null) {
try{
Messagebox.show("Selecione o Contrato.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.lancamento.getStatuslancamento() == null) {
try{
Messagebox.show("Selecione o Status do Lancamento.");
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
 			Events.postEvent("onClose", this, this.lancamento); 
 		} 
 	} 
 	
 public Conta getConta() {return conta;}
 public Contrato getContrato() {return contrato;}
 public StatusLancamento getStatuslancamento() {return statuslancamento;}
 public void setConta(Conta conta) {
 if(conta!=null){
this.lancamento.setConta(conta);
 }this.conta=conta;}
 public void setContrato(Contrato contrato) {
 if(contrato!=null){
this.lancamento.setContrato(contrato);
 }this.contrato=contrato;}
 public void setStatuslancamento(StatusLancamento statuslancamento) {
 if(statuslancamento!=null){
this.lancamento.setStatuslancamento(statuslancamento);
 }this.statuslancamento=statuslancamento;} 
 	
public void recebeConta(Object obj) { 
 lmconta=new ListModelList(new ContaDAO().getRegByExample(null)); 
 lmconta=new ListModelList(new ContaDAO().getRegByExample(null));
 if(obj!=null){
this.conta=(Conta) obj;
this.lancamento.setConta(conta);
 }
this.vincular();
}
public void pesquisarContrato() {
	new WinUtils().abrirLis("forms/contratolis.zul", null, this, "recebeContrato");
}
public void recebeContrato(Object obj) {
 if(obj!=null){
this.contrato=(Contrato) obj;
this.lancamento.setContrato(contrato);
 }
this.vincular();
}
public void recebeStatusLancamento(Object obj) { 
 lmstatuslancamento=new ListModelList(new StatusLancamentoDAO().getRegByExample(null)); 
 lmstatuslancamento=new ListModelList(new StatusLancamentoDAO().getRegByExample(null));
 if(obj!=null){
this.statuslancamento=(StatusLancamento) obj;
this.lancamento.setStatuslancamento(statuslancamento);
 }
this.vincular();
} 
 	
public void addConta() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "lancamentocad.zul");
	String url = "forms/contacad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeConta");
}
public void addContrato() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "lancamentocad.zul");
	String url = "forms/contratocad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeContrato");
}
public void addStatusLancamento() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "lancamentocad.zul");
	String url = "forms/statuslancamentocad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeStatusLancamento");
} 
 	 
 } 
 