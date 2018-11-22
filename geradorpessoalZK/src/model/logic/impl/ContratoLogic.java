
package model.logic.impl;
 import model.entity.hibernate.Contrato;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class ContratoLogic extends GenericLogic {
	public Contrato insertReg(Contrato entity) throws LogicException {
		try {
			return daoFactory.getContratoDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public Contrato updateReg(Contrato entity) throws LogicException {
		try {
			return daoFactory.getContratoDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(Contrato entity) throws LogicException {
		try {
			daoFactory.getContratoDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<Contrato> findAll() {
		return daoFactory.getContratoDAO().findAll();
	}
	public List<Contrato> getRegByExample(Contrato example) {
		return daoFactory.getContratoDAO().getRegByExample(example);
	}
	public void deleteAll(Contrato entity) throws LogicException {
		try {
		daoFactory.getContratoDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getContratoDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public Contrato findById(Integer id) throws LogicException {
		try {
	return daoFactory.getContratoDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}