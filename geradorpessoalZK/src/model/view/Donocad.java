 
 
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
 
import model.dao.impl.hibernate.DonoDAO;
import model.entity.hibernate.Dono;
import model.entity.hibernate.Cidade;
import model.dao.impl.hibernate.CidadeDAO;
import model.entity.hibernate.Agencia;
import model.dao.impl.hibernate.AgenciaDAO; 
 public class Donocad extends WindowCrud { 
 	
public Dono dono = new Dono();
 private Cidade cidade= new Cidade();
 private Agencia agencia= new Agencia(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Donocad() { 
 		super("donocad.zul"); 
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
 				 
DonoDAO donoDAO = new DonoDAO(); 
dono = donoDAO.insertReg(this.dono);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.dono);
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
 					&& this.dono.getIddono() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
DonoDAO donoDAO = new DonoDAO(); 
	donoDAO.updateReg(this.dono) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.dono); 
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
  new DonoDAO().deleteReg(dono);
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
 		
this.dono = new Dono();
 this.cidade=new Cidade();
 this.agencia=new Agencia(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/donolis.zul", param, this, 
 				"retDono"); 
 	} 
  
 	public void retDono(Object ret) { 
 		if (ret != null) { 
 			this.dono = (Dono) ret; 
 			
 this.dono = (Dono) ret;
this.cidade=this.dono.getCidade();
this.agencia=this.dono.getAgencia(); 
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
 		if (this.dono != null) { 
 			//this.dono.setUsualt(this.getLogin()); 
 			//this.dono.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.dono.getCidade() == null) {
try{
Messagebox.show("Selecione o Cidade.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.dono.getProcurador() == null) {
try{
Messagebox.show("Selecione o Tem procurador.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.dono.getAgencia() == null) {
try{
Messagebox.show("Selecione o Agencia.");
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
 			Events.postEvent("onClose", this, this.dono); 
 		} 
 	} 
 	
 public Cidade getCidade() {return cidade;}
 public Agencia getAgencia() {return agencia;}
 public void setCidade(Cidade cidade) {
 if(cidade!=null){
this.dono.setCidade(cidade);
 }this.cidade=cidade;}
 public void setAgencia(Agencia agencia) {
 if(agencia!=null){
this.dono.setAgencia(agencia);
 }this.agencia=agencia;} 
 	
public void pesquisarCidade() {
	new WinUtils().abrirLis("forms/cidadelis.zul", null, this, "recebeCidade");
}
public void recebeCidade(Object obj) {
 if(obj!=null){
this.cidade=(Cidade) obj;
this.dono.setCidade(cidade);
 }
this.vincular();
}
public void pesquisarAgencia() {
	new WinUtils().abrirLis("forms/agencialis.zul", null, this, "recebeAgencia");
}
public void recebeAgencia(Object obj) {
 if(obj!=null){
this.agencia=(Agencia) obj;
this.dono.setAgencia(agencia);
 }
this.vincular();
} 
 	
public void addCidade() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "donocad.zul");
	String url = "forms/cidadecad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeCidade");
}
public void addAgencia() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "donocad.zul");
	String url = "forms/agenciacad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeAgencia");
} 
 	 
 } 
 