 
 
package model.view; 
 
import model.entity.hibernate.Cidade;
import model.dao.impl.hibernate.CidadeDAO; 
  
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
  
 import model.dao.impl.hibernate.InquilinoDAO; 
 import model.entity.hibernate.Inquilino; 
  
  
 @SuppressWarnings("serial") 
 public class Inquilinolis extends WindowList<Inquilino> { 
 	
 private Cidade cidade= new Cidade(); 
 	public Inquilino inquilino = new Inquilino(); 
 	private List<Inquilino> hmSis; 
 	private InquilinoDAO dao = new InquilinoDAO(); 
 public Inquilino obj; 
 	public Inquilinolis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listInquilino");
 Paging pag =(Paging) this.getComponente("pagInquilino");
 int pagativa =pag.getActivePage();
  obj = new Inquilino();
try {
	obj=(Inquilino) BeanUtils.cloneBean(inquilino);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new InquilinoDAO().countAllLimit(obj).intValue());
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
	private void redraw(Inquilino obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new InquilinoDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.inquilino = new Inquilino();
 this.cidade=new Cidade(); 
 		 
 		 
 	} 
 	
 public Cidade getCidade() {return cidade;}
 public void setCidade(Cidade cidade) {
 if(cidade!=null){
this.inquilino.setCidade(cidade);
 }this.cidade=cidade;} 
 	
public void pesquisarCidade() {
	new WinUtils().abrirLis("forms/cidadelis.zul", null, this, "recebeCidade");
}
public void recebeCidade(Object obj) {
 if(cidade!=null){
this.cidade=(Cidade) obj;
this.inquilino.setCidade(cidade);
 }
this.vincular();
} 
 	 
 } 
 