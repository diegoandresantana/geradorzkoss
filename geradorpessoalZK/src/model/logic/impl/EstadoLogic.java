
package model.logic.impl;
 import model.entity.hibernate.Estado;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class EstadoLogic extends GenericLogic {
	public Estado insertReg(Estado entity) throws LogicException {
		try {
			return daoFactory.getEstadoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel inserir registro.", e);
		}
	}
	public Estado updateReg(Estado entity) throws LogicException {
		try {
			return daoFactory.getEstadoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel atualizar o registro.",	e);
		}
	}
	public void deleteReg(Estado entity) throws LogicException {
		try {
			daoFactory.getEstadoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel deletar o registro.", e);
		}
	}
	public List<Estado> findAll() {
		return daoFactory.getEstadoDAO().findAll();
	}
	public List<Estado> getRegByExample(Estado example) {
		return daoFactory.getEstadoDAO().getRegByExample(example);
	}
	public void deleteAll(Estado entity) throws LogicException {
		try {
		daoFactory.getEstadoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"N�o foi poss�vel deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getEstadoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel deletar os registros.", e);
	}
}
	public Estado findById(Integer id) throws LogicException {
		try {
	return daoFactory.getEstadoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel buscar por ID.", e);
	}
	}
		}