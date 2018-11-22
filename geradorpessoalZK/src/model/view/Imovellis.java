 
 
package model.view; 
 
import model.entity.hibernate.Cidade;
import model.dao.impl.hibernate.CidadeDAO;
import model.entity.hibernate.Dono;
import model.dao.impl.hibernate.DonoDAO;
import model.entity.hibernate.TipoImovel;
import model.dao.impl.hibernate.TipoImovelDAO; 
  
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
  
 import model.dao.impl.hibernate.ImovelDAO; 
 import model.entity.hibernate.Imovel; 
  
  
 @SuppressWarnings("serial") 
 public class Imovellis extends WindowList<Imovel> { 
 	
 public ListModelList lmtipoimovel;
 private Cidade cidade= new Cidade();
 private Dono dono= new Dono();
 private TipoImovel tipoimovel= new TipoImovel(); 
 	public Imovel imovel = new Imovel(); 
 	private List<Imovel> hmSis; 
 	private ImovelDAO dao = new ImovelDAO(); 
 public Imovel obj; 
 	public Imovellis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 lmtipoimovel=new ListModelList(new TipoImovelDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listImovel");
 Paging pag =(Paging) this.getComponente("pagImovel");
 int pagativa =pag.getActivePage();
  obj = new Imovel();
try {
	obj=(Imovel) BeanUtils.cloneBean(imovel);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new ImovelDAO().countAllLimit(obj).intValue());
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
	private void redraw(Imovel obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new ImovelDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.imovel = new Imovel();
 this.cidade=new Cidade();
 this.dono=new Dono();
 this.tipoimovel=new TipoImovel(); 
 		 
 		 
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
 if(cidade!=null){
this.cidade=(Cidade) obj;
this.imovel.setCidade(cidade);
 }
this.vincular();
}
public void pesquisarDono() {
	new WinUtils().abrirLis("forms/donolis.zul", null, this, "recebeDono");
}
public void recebeDono(Object obj) {
 if(dono!=null){
this.dono=(Dono) obj;
this.imovel.setDono(dono);
 }
this.vincular();
} 
 	 
 } 
 