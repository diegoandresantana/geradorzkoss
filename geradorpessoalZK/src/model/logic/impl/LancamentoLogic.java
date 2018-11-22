
package model.logic.impl;
 import model.entity.hibernate.Lancamento;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class LancamentoLogic extends GenericLogic {
	public Lancamento insertReg(Lancamento entity) throws LogicException {
		try {
			return daoFactory.getLancamentoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel inserir registro.", e);
		}
	}
	public Lancamento updateReg(Lancamento entity) throws LogicException {
		try {
			return daoFactory.getLancamentoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel atualizar o registro.",	e);
		}
	}
	public void deleteReg(Lancamento entity) throws LogicException {
		try {
			daoFactory.getLancamentoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel deletar o registro.", e);
		}
	}
	public List<Lancamento> findAll() {
		return daoFactory.getLancamentoDAO().findAll();
	}
	public List<Lancamento> getRegByExample(Lancamento example) {
		return daoFactory.getLancamentoDAO().getRegByExample(example);
	}
	public void deleteAll(Lancamento entity) throws LogicException {
		try {
		daoFactory.getLancamentoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"N�o foi poss�vel deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getLancamentoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel deletar os registros.", e);
	}
}
	public Lancamento findById(Integer id) throws LogicException {
		try {
	return daoFactory.getLancamentoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel buscar por ID.", e);
	}
	}
		}