 
 
package model.view; 
 
import model.entity.hibernate.Imovel;
import model.dao.impl.hibernate.ImovelDAO;
import model.entity.hibernate.Inquilino;
import model.dao.impl.hibernate.InquilinoDAO; 
  
 import org.zkoss.zul.Listbox; 
 import org.zkoss.zul.Paging; 
 import org.zkoss.zul.event.PagingEvent; 
 import org.apache.commons.beanutils.BeanUtils; 
 import org.zkoss.zk.ui.event.Event; 
 import org.zkoss.zk.ui.event.EventListener; 
 import java.lang.reflect.InvocationTargetException; 
 import java.util.List; 
 import org.zkoss.zul.ListModelList; 
 import org.zkoss.zul.Messagebox; 
 import org.zkoss.zul.Textbox; 
 import pmcg.framework.ui.WindowList; 
 import pmcg.framework.util.WinUtils; 
  
 import model.dao.impl.hibernate.ContratoDAO; 
 import model.entity.hibernate.Contrato; 
  
  
 @SuppressWarnings("serial") 
 public class Contratolis extends WindowList<Contrato> { 
 	
 private Imovel imovel= new Imovel();
 private Inquilino inquilino= new Inquilino(); 
 	public Contrato contrato = new Contrato(); 
 	private List<Contrato> hmSis; 
 	private ContratoDAO dao = new ContratoDAO(); 
 public Contrato obj; 
 	public Contratolis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listContrato");
 Paging pag =(Paging) this.getComponente("pagContrato");
 int pagativa =pag.getActivePage();
  obj = new Contrato();
try {
	obj=(Contrato) BeanUtils.cloneBean(contrato);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new ContratoDAO().countAllLimit(obj).intValue());
   final int PAGE_SIZE = pag.getPageSize();
   redraw(obj,0,PAGE_SIZE);
     pag.setActivePage(0);
    pag.setDetailed(true);
    pag.addEventListener("onPaging", new EventListener() {
       public void onEvent(Event event) {
        	PagingEvent pe = (PagingEvent) event;
       	int pgno = pe.getActivePage(); 
        	int ofs = pgno * PAGE_SIZE;    
       	redraw(obj,ofs,PAGE_SIZE); 
      	vincular();
   	      	}
   	   });
   	    vincular();
   	}		
	private void redraw(Contrato obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new ContratoDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.contrato = new Contrato();
 this.imovel=new Imovel();
 this.inquilino=new Inquilino(); 
 		 
 		 
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
 if(imovel!=null){
this.imovel=(Imovel) obj;
this.contrato.setImovel(imovel);
 }
this.vincular();
}
public void pesquisarInquilino() {
	new WinUtils().abrirLis("forms/inquilinolis.zul", null, this, "recebeInquilino");
}
public void recebeInquilino(Object obj) {
 if(inquilino!=null){
this.inquilino=(Inquilino) obj;
this.contrato.setInquilino(inquilino);
 }
this.vincular();
} 
 	 
 } 
 