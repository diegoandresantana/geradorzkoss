 
 
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
 
import model.dao.impl.hibernate.CidadeDAO;
import model.entity.hibernate.Cidade;
import model.entity.hibernate.Estado;
import model.dao.impl.hibernate.EstadoDAO; 
 public class Cidadecad extends WindowCrud { 
 	
public Cidade cidade = new Cidade();
 public ListModelList lmestado;
 private Estado estado= new Estado(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Cidadecad() { 
 		super("cidadecad.zul"); 
 		Map<String, String> map = Executions.getCurrent().getArg(); 
 		if (map.get("pageName") != null) { 
 			abertoPeloMenu = Boolean.FALSE; 
 		} 
 	} 
  
 	public void initComponentes() { 
 		 
 lmestado=new ListModelList(new EstadoDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void incluir() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& !this.getCrdBar().getBotao(0).isDisabled()) { 
 				 
CidadeDAO cidadeDAO = new CidadeDAO(); 
cidade = cidadeDAO.insertReg(this.cidade);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.cidade);
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
 					&& this.cidade.getIdcidade() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
CidadeDAO cidadeDAO = new CidadeDAO(); 
	cidadeDAO.updateReg(this.cidade) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.cidade); 
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
  new CidadeDAO().deleteReg(cidade);
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
 		
this.cidade = new Cidade();
 this.estado=new Estado(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/cidadelis.zul", param, this, 
 				"retCidade"); 
 	} 
  
 	public void retCidade(Object ret) { 
 		if (ret != null) { 
 			this.cidade = (Cidade) ret; 
 			
 this.cidade = (Cidade) ret;
this.estado=this.cidade.getEstado(); 
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
 		if (this.cidade != null) { 
 			//this.cidade.setUsualt(this.getLogin()); 
 			//this.cidade.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.cidade.getEstado() == null) {
try{
Messagebox.show("Selecione o Estado.");
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
 			Events.postEvent("onClose", this, this.cidade); 
 		} 
 	} 
 	
 public Estado getEstado() {return estado;}
 public void setEstado(Estado estado) {
 if(estado!=null){
this.cidade.setEstado(estado);
 }this.estado=estado;} 
 	
public void recebeEstado(Object obj) { 
 lmestado=new ListModelList(new EstadoDAO().getRegByExample(null));
 if(obj!=null){
this.estado=(Estado) obj;
this.cidade.setEstado(estado);
 }
this.vincular();
} 
 	
public void addEstado() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "cidadecad.zul");
	String url = "forms/estadocad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeEstado");
} 
 	 
 } 
 