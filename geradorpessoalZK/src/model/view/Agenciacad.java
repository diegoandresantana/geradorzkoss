 
 
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
 
import model.dao.impl.hibernate.AgenciaDAO;
import model.entity.hibernate.Agencia;
import model.entity.hibernate.Banco;
import model.dao.impl.hibernate.BancoDAO; 
 public class Agenciacad extends WindowCrud { 
 	
public Agencia agencia = new Agencia();
 public ListModelList lmbanco;
 private Banco banco= new Banco(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Agenciacad() { 
 		super("agenciacad.zul"); 
 		Map<String, String> map = Executions.getCurrent().getArg(); 
 		if (map.get("pageName") != null) { 
 			abertoPeloMenu = Boolean.FALSE; 
 		} 
 	} 
  
 	public void initComponentes() { 
 		 
 lmbanco=new ListModelList(new BancoDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void incluir() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& !this.getCrdBar().getBotao(0).isDisabled()) { 
 				 
AgenciaDAO agenciaDAO = new AgenciaDAO(); 
agencia = agenciaDAO.insertReg(this.agencia);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.agencia);
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
 					&& this.agencia.getIdagencia() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
AgenciaDAO agenciaDAO = new AgenciaDAO(); 
	agenciaDAO.updateReg(this.agencia) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.agencia); 
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
  new AgenciaDAO().deleteReg(agencia);
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
 		
this.agencia = new Agencia();
 this.banco=new Banco(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/agencialis.zul", param, this, 
 				"retAgencia"); 
 	} 
  
 	public void retAgencia(Object ret) { 
 		if (ret != null) { 
 			this.agencia = (Agencia) ret; 
 			
 this.agencia = (Agencia) ret;
this.banco=this.agencia.getBanco(); 
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
 		if (this.agencia != null) { 
 			//this.agencia.setUsualt(this.getLogin()); 
 			//this.agencia.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.agencia.getBanco() == null) {
try{
Messagebox.show("Selecione o Banco.");
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
 			Events.postEvent("onClose", this, this.agencia); 
 		} 
 	} 
 	
 public Banco getBanco() {return banco;}
 public void setBanco(Banco banco) {
 if(banco!=null){
this.agencia.setBanco(banco);
 }this.banco=banco;} 
 	
public void recebeBanco(Object obj) { 
 lmbanco=new ListModelList(new BancoDAO().getRegByExample(null));
 if(obj!=null){
this.banco=(Banco) obj;
this.agencia.setBanco(banco);
 }
this.vincular();
} 
 	
public void addBanco() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "agenciacad.zul");
	String url = "forms/bancocad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeBanco");
} 
 	 
 } 
 