 
 
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
  
 import model.dao.impl.hibernate.EstadoDAO; 
 import model.entity.hibernate.Estado; 
  
  
 @SuppressWarnings("serial") 
 public class Estadolis extends WindowList<Estado> { 
 	 
 	public Estado estado = new Estado(); 
 	private List<Estado> hmSis; 
 	private EstadoDAO dao = new EstadoDAO(); 
 public Estado obj; 
 	public Estadolis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listEstado");
 Paging pag =(Paging) this.getComponente("pagEstado");
 int pagativa =pag.getActivePage();
  obj = new Estado();
try {
	obj=(Estado) BeanUtils.cloneBean(estado);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new EstadoDAO().countAllLimit(obj).intValue());
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
	private void redraw(Estado obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new EstadoDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.estado = new Estado(); 
 		 
 		 
 	} 
 	 
 	 
 	 
 } 
 