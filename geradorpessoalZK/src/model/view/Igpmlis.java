 
 
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
  
 import model.dao.impl.hibernate.IgpmDAO; 
 import model.entity.hibernate.Igpm; 
  
  
 @SuppressWarnings("serial") 
 public class Igpmlis extends WindowList<Igpm> { 
 	 
 	public Igpm igpm = new Igpm(); 
 	private List<Igpm> hmSis; 
 	private IgpmDAO dao = new IgpmDAO(); 
 public Igpm obj; 
 	public Igpmlis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listIgpm");
 Paging pag =(Paging) this.getComponente("pagIgpm");
 int pagativa =pag.getActivePage();
  obj = new Igpm();
try {
	obj=(Igpm) BeanUtils.cloneBean(igpm);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new IgpmDAO().countAllLimit(obj).intValue());
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
	private void redraw(Igpm obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new IgpmDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.igpm = new Igpm(); 
 		 
 		 
 	} 
 	 
 	 
 	 
 } 
 