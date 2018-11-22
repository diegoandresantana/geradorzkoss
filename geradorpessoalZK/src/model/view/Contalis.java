 
 
package model.view; 
 
import model.entity.hibernate.TipoConta;
import model.dao.impl.hibernate.TipoContaDAO; 
  
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
  
 import model.dao.impl.hibernate.ContaDAO; 
 import model.entity.hibernate.Conta; 
  
  
 @SuppressWarnings("serial") 
 public class Contalis extends WindowList<Conta> { 
 	
 public ListModelList lmtipoconta;
 private TipoConta tipoconta= new TipoConta(); 
 	public Conta conta = new Conta(); 
 	private List<Conta> hmSis; 
 	private ContaDAO dao = new ContaDAO(); 
 public Conta obj; 
 	public Contalis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 lmtipoconta=new ListModelList(new TipoContaDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listConta");
 Paging pag =(Paging) this.getComponente("pagConta");
 int pagativa =pag.getActivePage();
  obj = new Conta();
try {
	obj=(Conta) BeanUtils.cloneBean(conta);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new ContaDAO().countAllLimit(obj).intValue());
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
	private void redraw(Conta obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new ContaDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.conta = new Conta();
 this.tipoconta=new TipoConta(); 
 		 
 		 
 	} 
 	
 public TipoConta getTipoconta() {return tipoconta;}
 public void setTipoconta(TipoConta tipoconta) {
 if(tipoconta!=null){
this.conta.setTipoconta(tipoconta);
 }this.tipoconta=tipoconta;} 
 	 
 	 
 } 
 