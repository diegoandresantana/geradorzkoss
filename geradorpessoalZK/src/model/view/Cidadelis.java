 
 
package model.view; 
 
import model.entity.hibernate.Estado;
import model.dao.impl.hibernate.EstadoDAO; 
  
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
  
 import model.dao.impl.hibernate.CidadeDAO; 
 import model.entity.hibernate.Cidade; 
  
  
 @SuppressWarnings("serial") 
 public class Cidadelis extends WindowList<Cidade> { 
 	
 public ListModelList lmestado;
 private Estado estado= new Estado(); 
 	public Cidade cidade = new Cidade(); 
 	private List<Cidade> hmSis; 
 	private CidadeDAO dao = new CidadeDAO(); 
 public Cidade obj; 
 	public Cidadelis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 lmestado=new ListModelList(new EstadoDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listCidade");
 Paging pag =(Paging) this.getComponente("pagCidade");
 int pagativa =pag.getActivePage();
  obj = new Cidade();
try {
	obj=(Cidade) BeanUtils.cloneBean(cidade);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new CidadeDAO().countAllLimit(obj).intValue());
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
	private void redraw(Cidade obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new CidadeDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.cidade = new Cidade();
 this.estado=new Estado(); 
 		 
 		 
 	} 
 	
 public Estado getEstado() {return estado;}
 public void setEstado(Estado estado) {
 if(estado!=null){
this.cidade.setEstado(estado);
 }this.estado=estado;} 
 	 
 	 
 } 
 