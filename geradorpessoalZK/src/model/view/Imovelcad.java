 
 
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
 
import model.dao.impl.hibernate.ImovelDAO;
import model.entity.hibernate.Imovel;
import model.entity.hibernate.Cidade;
import model.dao.impl.hibernate.CidadeDAO;
import model.entity.hibernate.Dono;
import model.dao.impl.hibernate.DonoDAO;
import model.entity.hibernate.TipoImovel;
import model.dao.impl.hibernate.TipoImovelDAO; 
 public class Imovelcad extends WindowCrud { 
 	
public Imovel imovel = new Imovel();
 public ListModelList lmtipoimovel;
 private Cidade cidade= new Cidade();
 private Dono dono= new Dono();
 private TipoImovel tipoimovel= new TipoImovel(); 
 	 
 	Boolean abertoPeloMenu = Boolean.TRUE; 
  
 	public Imovelcad() { 
 		super("imovelcad.zul"); 
 		Map<String, String> map = Executions.getCurrent().getArg(); 
 		if (map.get("pageName") != null) { 
 			abertoPeloMenu = Boolean.FALSE; 
 		} 
 	} 
  
 	public void initComponentes() { 
 		 
 lmtipoimovel=new ListModelList(new TipoImovelDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void incluir() { 
 		try { 
 			if (this.validarForm() && this.trataVO() 
 					&& !this.getCrdBar().getBotao(0).isDisabled()) { 
 				 
ImovelDAO imovelDAO = new ImovelDAO(); 
imovel = imovelDAO.insertReg(this.imovel);
	if (this.abertoPeloMenu) {
			Messagebox.show("Registro incluído com sucesso!");
	}
	else {
	Events.postEvent("onClose", this, this.imovel);
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
 					&& this.imovel.getIdimovel() != null 
 					&& !this.getCrdBar().getBotao(1).isDisabled()) { 
 				 
ImovelDAO imovelDAO = new ImovelDAO(); 
	imovelDAO.updateReg(this.imovel) ; 
				Messagebox.show("Registro Salvo com sucesso!"); 
	if (this.abertoPeloMenu) { 
		Messagebox.show("Registro Salvo com sucesso!"); 
	} else { 
	Events.postEvent("onClose", this, this.imovel); 
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
  new ImovelDAO().deleteReg(imovel);
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
 		
this.imovel = new Imovel();
 this.cidade=new Cidade();
 this.dono=new Dono();
 this.tipoimovel=new TipoImovel(); 
 		 
 		this.getCrdBar().getBotao(0).setDisabled(false); 
 		this.getCrdBar().getBotao(1).setDisabled(true); 
 		this.getCrdBar().getBotao(2).setDisabled(true); 
 		this.vincular(); 
 	} 
  
 	@Override 
 	public void pesquisar() { 
 		 
 		Map<String, String> param = new HashMap<String, String>(); 
 		new WinUtils().abrirLis("/forms/imovellis.zul", param, this, 
 				"retImovel"); 
 	} 
  
 	public void retImovel(Object ret) { 
 		if (ret != null) { 
 			this.imovel = (Imovel) ret; 
 			
 this.imovel = (Imovel) ret;
this.cidade=this.imovel.getCidade();
this.dono=this.imovel.getDono();
this.tipoimovel=this.imovel.getTipoimovel(); 
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
 		if (this.imovel != null) { 
 			//this.imovel.setUsualt(this.getLogin()); 
 			//this.imovel.setDatalt(WinUtils.getDataBanco()); 
 			
if (this.imovel.getCidade() == null) {
try{
Messagebox.show("Selecione o Cidade.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.imovel.getDono() == null) {
try{
Messagebox.show("Selecione o Dono.");
}catch(InterruptedException e){
e.printStackTrace();}
return false;
} 
if (this.imovel.getTipoimovel() == null) {
try{
Messagebox.show("Selecione o Tipo de Imovel.");
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
 			Events.postEvent("onClose", this, this.imovel); 
 		} 
 	} 
 	
 public Cidade getCidade() {return cidade;}
 public Dono getDono() {return dono;}
 public TipoImovel getTipoimovel() {return tipoimovel;}
 public void setCidade(Cidade cidade) {
 if(cidade!=null){
this.imovel.setCidade(cidade);
 }this.cidade=cidade;}
 public void setDono(Dono dono) {
 if(dono!=null){
this.imovel.setDono(dono);
 }this.dono=dono;}
 public void setTipoimovel(TipoImovel tipoimovel) {
 if(tipoimovel!=null){
this.imovel.setTipoimovel(tipoimovel);
 }this.tipoimovel=tipoimovel;} 
 	
public void pesquisarCidade() {
	new WinUtils().abrirLis("forms/cidadelis.zul", null, this, "recebeCidade");
}
public void recebeCidade(Object obj) {
 if(obj!=null){
this.cidade=(Cidade) obj;
this.imovel.setCidade(cidade);
 }
this.vincular();
}
public void pesquisarDono() {
	new WinUtils().abrirLis("forms/donolis.zul", null, this, "recebeDono");
}
public void recebeDono(Object obj) {
 if(obj!=null){
this.dono=(Dono) obj;
this.imovel.setDono(dono);
 }
this.vincular();
}
public void recebeTipoImovel(Object obj) { 
 lmtipoimovel=new ListModelList(new TipoImovelDAO().getRegByExample(null));
 if(obj!=null){
this.tipoimovel=(TipoImovel) obj;
this.imovel.setTipoimovel(tipoimovel);
 }
this.vincular();
} 
 	
public void addCidade() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "imovelcad.zul");
	String url = "forms/cidadecad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeCidade");
}
public void addDono() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "imovelcad.zul");
	String url = "forms/donocad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeDono");
}
public void addTipoImovel() {
	Map<String, String> map = new HashMap<String, String>();
	map.put("pageName", "imovelcad.zul");
	String url = "forms/tipoimovelcad.zul";
	new WinUtils().abrirLis(url, map, this, "recebeTipoImovel");
} 
 	 
 } 
 