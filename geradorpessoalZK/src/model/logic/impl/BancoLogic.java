
package model.logic.impl;
 import model.entity.hibernate.Banco;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class BancoLogic extends GenericLogic {
	public Banco insertReg(Banco entity) throws LogicException {
		try {
			return daoFactory.getBancoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel inserir registro.", e);
		}
	}
	public Banco updateReg(Banco entity) throws LogicException {
		try {
			return daoFactory.getBancoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel atualizar o registro.",	e);
		}
	}
	public void deleteReg(Banco entity) throws LogicException {
		try {
			daoFactory.getBancoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel deletar o registro.", e);
		}
	}
	public List<Banco> findAll() {
		return daoFactory.getBancoDAO().findAll();
	}
	public List<Banco> getRegByExample(Banco example) {
		return daoFactory.getBancoDAO().getRegByExample(example);
	}
	public void deleteAll(Banco entity) throws LogicException {
		try {
		daoFactory.getBancoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"N�o foi poss�vel deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getBancoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel deletar os registros.", e);
	}
}
	public Banco findById(Integer id) throws LogicException {
		try {
	return daoFactory.getBancoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel buscar por ID.", e);
	}
	}
		}