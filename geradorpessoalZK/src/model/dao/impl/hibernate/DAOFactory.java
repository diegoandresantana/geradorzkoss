
package model.dao.impl.hibernate;
import model.dao.impl.hibernate.IgpmDAO;
import model.dao.impl.hibernate.CidadeDAO;
import model.dao.impl.hibernate.EstadoDAO;
import model.dao.impl.hibernate.AgenciaDAO;
import model.dao.impl.hibernate.BancoDAO;
import model.dao.impl.hibernate.ContaDAO;
import model.dao.impl.hibernate.ContratoDAO;
import model.dao.impl.hibernate.DonoDAO;
import model.dao.impl.hibernate.ImovelDAO;
import model.dao.impl.hibernate.InquilinoDAO;
import model.dao.impl.hibernate.LancamentoDAO;
import model.dao.impl.hibernate.StatusLancamentoDAO;
import model.dao.impl.hibernate.TipoContaDAO;
import model.dao.impl.hibernate.TipoImovelDAO;
import model.dao.impl.hibernate.DAOFactory;
import org.hibernate.Session;
import model.util.HibernateUtil;
public class DAOFactory {
private static DAOFactory daoFactory;
private static IgpmDAO igpmDAO;
private static CidadeDAO cidadeDAO;
private static EstadoDAO estadoDAO;
private static AgenciaDAO agenciaDAO;
private static BancoDAO bancoDAO;
private static ContaDAO contaDAO;
private static ContratoDAO contratoDAO;
private static DonoDAO donoDAO;
private static ImovelDAO imovelDAO;
private static InquilinoDAO inquilinoDAO;
private static LancamentoDAO lancamentoDAO;
private static StatusLancamentoDAO statusLancamentoDAO;
private static TipoContaDAO tipoContaDAO;
private static TipoImovelDAO tipoImovelDAO;
public DAOFactory() {
}
public static DAOFactory instance() {	
try {	
	if (daoFactory == null) {	
		daoFactory = (DAOFactory) (DAOFactory.class).newInstance();
		}	
	return daoFactory;	
} catch (Exception ex) {	
	throw new RuntimeException("Não pode criar o  DAOFactory");	
}	
}
protected Session getCurrentSession() {
	return HibernateUtil.getCurrentSession();
}
public Object classInstance(Class<?> clazz) {
	try {
return Class.forName(clazz.getName()).newInstance();
	} catch (IllegalAccessException ex) {
	} catch (InstantiationException ex) {
} catch (Exception e) {
}
return null;
}
public IgpmDAO getIgpmDAO() {
	if (igpmDAO == null)
		igpmDAO = (IgpmDAO) classInstance(IgpmDAO.class);
	return igpmDAO;
}
public CidadeDAO getCidadeDAO() {
	if (cidadeDAO == null)
		cidadeDAO = (CidadeDAO) classInstance(CidadeDAO.class);
	return cidadeDAO;
}
public EstadoDAO getEstadoDAO() {
	if (estadoDAO == null)
		estadoDAO = (EstadoDAO) classInstance(EstadoDAO.class);
	return estadoDAO;
}
public AgenciaDAO getAgenciaDAO() {
	if (agenciaDAO == null)
		agenciaDAO = (AgenciaDAO) classInstance(AgenciaDAO.class);
	return agenciaDAO;
}
public BancoDAO getBancoDAO() {
	if (bancoDAO == null)
		bancoDAO = (BancoDAO) classInstance(BancoDAO.class);
	return bancoDAO;
}
public ContaDAO getContaDAO() {
	if (contaDAO == null)
		contaDAO = (ContaDAO) classInstance(ContaDAO.class);
	return contaDAO;
}
public ContratoDAO getContratoDAO() {
	if (contratoDAO == null)
		contratoDAO = (ContratoDAO) classInstance(ContratoDAO.class);
	return contratoDAO;
}
public DonoDAO getDonoDAO() {
	if (donoDAO == null)
		donoDAO = (DonoDAO) classInstance(DonoDAO.class);
	return donoDAO;
}
public ImovelDAO getImovelDAO() {
	if (imovelDAO == null)
		imovelDAO = (ImovelDAO) classInstance(ImovelDAO.class);
	return imovelDAO;
}
public InquilinoDAO getInquilinoDAO() {
	if (inquilinoDAO == null)
		inquilinoDAO = (InquilinoDAO) classInstance(InquilinoDAO.class);
	return inquilinoDAO;
}
public LancamentoDAO getLancamentoDAO() {
	if (lancamentoDAO == null)
		lancamentoDAO = (LancamentoDAO) classInstance(LancamentoDAO.class);
	return lancamentoDAO;
}
public StatusLancamentoDAO getStatusLancamentoDAO() {
	if (statusLancamentoDAO == null)
		statusLancamentoDAO = (StatusLancamentoDAO) classInstance(StatusLancamentoDAO.class);
	return statusLancamentoDAO;
}
public TipoContaDAO getTipoContaDAO() {
	if (tipoContaDAO == null)
		tipoContaDAO = (TipoContaDAO) classInstance(TipoContaDAO.class);
	return tipoContaDAO;
}
public TipoImovelDAO getTipoImovelDAO() {
	if (tipoImovelDAO == null)
		tipoImovelDAO = (TipoImovelDAO) classInstance(TipoImovelDAO.class);
	return tipoImovelDAO;
}
}