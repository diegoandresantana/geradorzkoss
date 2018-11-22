 
 
package model.view; 
  
  
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
  
 import model.dao.impl.hibernate.TipoContaDAO; 
 import model.entity.hibernate.TipoConta; 
  
  
 @SuppressWarnings("serial") 
 public class TipoContalis extends WindowList<TipoConta> { 
 	 
 	public TipoConta tipoConta = new TipoConta(); 
 	private List<TipoConta> hmSis; 
 	private TipoContaDAO dao = new TipoContaDAO(); 
 public TipoConta obj; 
 	public TipoContalis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listTipoConta");
 Paging pag =(Paging) this.getComponente("pagTipoConta");
 int pagativa =pag.getActivePage();
  obj = new TipoConta();
try {
	obj=(TipoConta) BeanUtils.cloneBean(tipoConta);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new TipoContaDAO().countAllLimit(obj).intValue());
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
	private void redraw(TipoConta obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new TipoContaDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.tipoConta = new TipoConta(); 
 		 
 		 
 	} 
 	 
 	 
 	 
 } 
 