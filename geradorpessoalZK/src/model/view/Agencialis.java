 
 
package model.view; 
 
import model.entity.hibernate.Banco;
import model.dao.impl.hibernate.BancoDAO; 
  
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
  
 import model.dao.impl.hibernate.AgenciaDAO; 
 import model.entity.hibernate.Agencia; 
  
  
 @SuppressWarnings("serial") 
 public class Agencialis extends WindowList<Agencia> { 
 	
 public ListModelList lmbanco;
 private Banco banco= new Banco(); 
 	public Agencia agencia = new Agencia(); 
 	private List<Agencia> hmSis; 
 	private AgenciaDAO dao = new AgenciaDAO(); 
 public Agencia obj; 
 	public Agencialis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 lmbanco=new ListModelList(new BancoDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listAgencia");
 Paging pag =(Paging) this.getComponente("pagAgencia");
 int pagativa =pag.getActivePage();
  obj = new Agencia();
try {
	obj=(Agencia) BeanUtils.cloneBean(agencia);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new AgenciaDAO().countAllLimit(obj).intValue());
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
	private void redraw(Agencia obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new AgenciaDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.agencia = new Agencia();
 this.banco=new Banco(); 
 		 
 		 
 	} 
 	
 public Banco getBanco() {return banco;}
 public void setBanco(Banco banco) {
 if(banco!=null){
this.agencia.setBanco(banco);
 }this.banco=banco;} 
 	 
 	 
 } 
 