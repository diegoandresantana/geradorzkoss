
package model.logic.impl;
 import model.entity.hibernate.Inquilino;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class InquilinoLogic extends GenericLogic {
	public Inquilino insertReg(Inquilino entity) throws LogicException {
		try {
			return daoFactory.getInquilinoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public Inquilino updateReg(Inquilino entity) throws LogicException {
		try {
			return daoFactory.getInquilinoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(Inquilino entity) throws LogicException {
		try {
			daoFactory.getInquilinoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<Inquilino> findAll() {
		return daoFactory.getInquilinoDAO().findAll();
	}
	public List<Inquilino> getRegByExample(Inquilino example) {
		return daoFactory.getInquilinoDAO().getRegByExample(example);
	}
	public void deleteAll(Inquilino entity) throws LogicException {
		try {
		daoFactory.getInquilinoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getInquilinoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public Inquilino findById(Integer id) throws LogicException {
		try {
	return daoFactory.getInquilinoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}