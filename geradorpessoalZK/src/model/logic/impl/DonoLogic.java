
package model.logic.impl;
 import model.entity.hibernate.Dono;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class DonoLogic extends GenericLogic {
	public Dono insertReg(Dono entity) throws LogicException {
		try {
			return daoFactory.getDonoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public Dono updateReg(Dono entity) throws LogicException {
		try {
			return daoFactory.getDonoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(Dono entity) throws LogicException {
		try {
			daoFactory.getDonoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<Dono> findAll() {
		return daoFactory.getDonoDAO().findAll();
	}
	public List<Dono> getRegByExample(Dono example) {
		return daoFactory.getDonoDAO().getRegByExample(example);
	}
	public void deleteAll(Dono entity) throws LogicException {
		try {
		daoFactory.getDonoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getDonoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public Dono findById(Integer id) throws LogicException {
		try {
	return daoFactory.getDonoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}