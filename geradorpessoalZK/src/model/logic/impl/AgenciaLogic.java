
package model.logic.impl;
 import model.entity.hibernate.Agencia;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class AgenciaLogic extends GenericLogic {
	public Agencia insertReg(Agencia entity) throws LogicException {
		try {
			return daoFactory.getAgenciaDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel inserir registro.", e);
		}
	}
	public Agencia updateReg(Agencia entity) throws LogicException {
		try {
			return daoFactory.getAgenciaDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel atualizar o registro.",	e);
		}
	}
	public void deleteReg(Agencia entity) throws LogicException {
		try {
			daoFactory.getAgenciaDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel deletar o registro.", e);
		}
	}
	public List<Agencia> findAll() {
		return daoFactory.getAgenciaDAO().findAll();
	}
	public List<Agencia> getRegByExample(Agencia example) {
		return daoFactory.getAgenciaDAO().getRegByExample(example);
	}
	public void deleteAll(Agencia entity) throws LogicException {
		try {
		daoFactory.getAgenciaDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"N�o foi poss�vel deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getAgenciaDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel deletar os registros.", e);
	}
}
	public Agencia findById(Integer id) throws LogicException {
		try {
	return daoFactory.getAgenciaDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel buscar por ID.", e);
	}
	}
		}