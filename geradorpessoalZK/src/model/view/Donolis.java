 
 
package model.view; 
 
import model.entity.hibernate.Cidade;
import model.dao.impl.hibernate.CidadeDAO;
import model.entity.hibernate.Agencia;
import model.dao.impl.hibernate.AgenciaDAO; 
  
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
  
 import model.dao.impl.hibernate.DonoDAO; 
 import model.entity.hibernate.Dono; 
  
  
 @SuppressWarnings("serial") 
 public class Donolis extends WindowList<Dono> { 
 	
 private Cidade cidade= new Cidade();
 private Agencia agencia= new Agencia(); 
 	public Dono dono = new Dono(); 
 	private List<Dono> hmSis; 
 	private DonoDAO dao = new DonoDAO(); 
 public Dono obj; 
 	public Donolis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listDono");
 Paging pag =(Paging) this.getComponente("pagDono");
 int pagativa =pag.getActivePage();
  obj = new Dono();
try {
	obj=(Dono) BeanUtils.cloneBean(dono);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new DonoDAO().countAllLimit(obj).intValue());
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
	private void redraw(Dono obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new DonoDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.dono = new Dono();
 this.cidade=new Cidade();
 this.agencia=new Agencia(); 
 		 
 		 
 	} 
 	
 public Cidade getCidade() {return cidade;}
 public Agencia getAgencia() {return agencia;}
 public void setCidade(Cidade cidade) {
 if(cidade!=null){
this.dono.setCidade(cidade);
 }this.cidade=cidade;}
 public void setAgencia(Agencia agencia) {
 if(agencia!=null){
this.dono.setAgencia(agencia);
 }this.agencia=agencia;} 
 	
public void pesquisarCidade() {
	new WinUtils().abrirLis("forms/cidadelis.zul", null, this, "recebeCidade");
}
public void recebeCidade(Object obj) {
 if(cidade!=null){
this.cidade=(Cidade) obj;
this.dono.setCidade(cidade);
 }
this.vincular();
}
public void pesquisarAgencia() {
	new WinUtils().abrirLis("forms/agencialis.zul", null, this, "recebeAgencia");
}
public void recebeAgencia(Object obj) {
 if(agencia!=null){
this.agencia=(Agencia) obj;
this.dono.setAgencia(agencia);
 }
this.vincular();
} 
 	 
 } 
 