 
 
package model.view; 
 
import model.entity.hibernate.Conta;
import model.dao.impl.hibernate.ContaDAO;
import model.entity.hibernate.Contrato;
import model.dao.impl.hibernate.ContratoDAO;
import model.entity.hibernate.StatusLancamento;
import model.dao.impl.hibernate.StatusLancamentoDAO; 
  
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
  
 import model.dao.impl.hibernate.LancamentoDAO; 
 import model.entity.hibernate.Lancamento; 
  
  
 @SuppressWarnings("serial") 
 public class Lancamentolis extends WindowList<Lancamento> { 
 	
 public ListModelList lmconta;
 public ListModelList lmstatuslancamento;
 private Conta conta= new Conta();
 private Contrato contrato= new Contrato();
 private StatusLancamento statuslancamento= new StatusLancamento(); 
 	public Lancamento lancamento = new Lancamento(); 
 	private List<Lancamento> hmSis; 
 	private LancamentoDAO dao = new LancamentoDAO(); 
 public Lancamento obj; 
 	public Lancamentolis() { 
 		super(); 
 	} 
  
 	public void initComponentes() { 
 		 
 lmconta=new ListModelList(new ContaDAO().getRegByExample(null)); 
 lmstatuslancamento=new ListModelList(new StatusLancamentoDAO().getRegByExample(null)); 
 		this.getCrdBar().getBotao(3).setVisible(true); 
 		this.getCrdBar().getBotao(4).setVisible(true); 
 		pesquisar(); 
 		 
 	} 
  
 	 
 	
public void pesquisar() {
	Listbox grid= (Listbox) this.getComponente("listLancamento");
 Paging pag =(Paging) this.getComponente("pagLancamento");
 int pagativa =pag.getActivePage();
  obj = new Lancamento();
try {
	obj=(Lancamento) BeanUtils.cloneBean(lancamento);
} catch (IllegalAccessException e) {
	e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (InvocationTargetException e) {
	e.printStackTrace();
} catch (NoSuchMethodException e) {
	e.printStackTrace();
}
	 pag.setTotalSize(new LancamentoDAO().countAllLimit(obj).intValue());
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
	private void redraw(Lancamento obj,Integer inicial,Integer maximoPermitido) {	  	
	    this.hmSis = new LancamentoDAO().getRegByExampleLimit(obj,inicial,maximoPermitido);	 
	    setListmodel(new ListModelList(hmSis));
	} 
 	 
  
 	 
 	 
 	public void limpar() { 
 		
this.lancamento = new Lancamento();
 this.conta=new Conta();
 this.contrato=new Contrato();
 this.statuslancamento=new StatusLancamento(); 
 		 
 		 
 	} 
 	
 public Conta getConta() {return conta;}
 public Contrato getContrato() {return contrato;}
 public StatusLancamento getStatuslancamento() {return statuslancamento;}
 public void setConta(Conta conta) {
 if(conta!=null){
this.lancamento.setConta(conta);
 }this.conta=conta;}
 public void setContrato(Contrato contrato) {
 if(contrato!=null){
this.lancamento.setContrato(contrato);
 }this.contrato=contrato;}
 public void setStatuslancamento(StatusLancamento statuslancamento) {
 if(statuslancamento!=null){
this.lancamento.setStatuslancamento(statuslancamento);
 }this.statuslancamento=statuslancamento;} 
 	
public void pesquisarContrato() {
	new WinUtils().abrirLis("forms/contratolis.zul", null, this, "recebeContrato");
}
public void recebeContrato(Object obj) {
 if(contrato!=null){
this.contrato=(Contrato) obj;
this.lancamento.setContrato(contrato);
 }
this.vincular();
} 
 	 
 } 
 