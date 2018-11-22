
package model.logic.impl;
 import model.entity.hibernate.Conta;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class ContaLogic extends GenericLogic {
	public Conta insertReg(Conta entity) throws LogicException {
		try {
			return daoFactory.getContaDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public Conta updateReg(Conta entity) throws LogicException {
		try {
			return daoFactory.getContaDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(Conta entity) throws LogicException {
		try {
			daoFactory.getContaDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<Conta> findAll() {
		return daoFactory.getContaDAO().findAll();
	}
	public List<Conta> getRegByExample(Conta example) {
		return daoFactory.getContaDAO().getRegByExample(example);
	}
	public void deleteAll(Conta entity) throws LogicException {
		try {
		daoFactory.getContaDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getContaDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public Conta findById(Integer id) throws LogicException {
		try {
	return daoFactory.getContaDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}