
package model.logic.impl;
 import model.entity.hibernate.StatusLancamento;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class StatusLancamentoLogic extends GenericLogic {
	public StatusLancamento insertReg(StatusLancamento entity) throws LogicException {
		try {
			return daoFactory.getStatusLancamentoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public StatusLancamento updateReg(StatusLancamento entity) throws LogicException {
		try {
			return daoFactory.getStatusLancamentoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(StatusLancamento entity) throws LogicException {
		try {
			daoFactory.getStatusLancamentoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<StatusLancamento> findAll() {
		return daoFactory.getStatusLancamentoDAO().findAll();
	}
	public List<StatusLancamento> getRegByExample(StatusLancamento example) {
		return daoFactory.getStatusLancamentoDAO().getRegByExample(example);
	}
	public void deleteAll(StatusLancamento entity) throws LogicException {
		try {
		daoFactory.getStatusLancamentoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getStatusLancamentoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public StatusLancamento findById(Integer id) throws LogicException {
		try {
	return daoFactory.getStatusLancamentoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}