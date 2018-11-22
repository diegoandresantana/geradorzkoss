
package model.logic.impl;
 import model.entity.hibernate.Igpm;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class IgpmLogic extends GenericLogic {
	public Igpm insertReg(Igpm entity) throws LogicException {
		try {
			return daoFactory.getIgpmDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public Igpm updateReg(Igpm entity) throws LogicException {
		try {
			return daoFactory.getIgpmDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(Igpm entity) throws LogicException {
		try {
			daoFactory.getIgpmDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<Igpm> findAll() {
		return daoFactory.getIgpmDAO().findAll();
	}
	public List<Igpm> getRegByExample(Igpm example) {
		return daoFactory.getIgpmDAO().getRegByExample(example);
	}
	public void deleteAll(Igpm entity) throws LogicException {
		try {
		daoFactory.getIgpmDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getIgpmDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public Igpm findById(Integer id) throws LogicException {
		try {
	return daoFactory.getIgpmDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}