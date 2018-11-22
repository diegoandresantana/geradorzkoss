
package model.logic.impl;
 import model.entity.hibernate.TipoConta;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class TipoContaLogic extends GenericLogic {
	public TipoConta insertReg(TipoConta entity) throws LogicException {
		try {
			return daoFactory.getTipoContaDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel inserir registro.", e);
		}
	}
	public TipoConta updateReg(TipoConta entity) throws LogicException {
		try {
			return daoFactory.getTipoContaDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel atualizar o registro.",	e);
		}
	}
	public void deleteReg(TipoConta entity) throws LogicException {
		try {
			daoFactory.getTipoContaDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel deletar o registro.", e);
		}
	}
	public List<TipoConta> findAll() {
		return daoFactory.getTipoContaDAO().findAll();
	}
	public List<TipoConta> getRegByExample(TipoConta example) {
		return daoFactory.getTipoContaDAO().getRegByExample(example);
	}
	public void deleteAll(TipoConta entity) throws LogicException {
		try {
		daoFactory.getTipoContaDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"N�o foi poss�vel deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getTipoContaDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel deletar os registros.", e);
	}
}
	public TipoConta findById(Integer id) throws LogicException {
		try {
	return daoFactory.getTipoContaDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel buscar por ID.", e);
	}
	}
		}