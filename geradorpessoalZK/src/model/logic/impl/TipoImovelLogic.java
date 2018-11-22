
package model.logic.impl;
 import model.entity.hibernate.TipoImovel;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class TipoImovelLogic extends GenericLogic {
	public TipoImovel insertReg(TipoImovel entity) throws LogicException {
		try {
			return daoFactory.getTipoImovelDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível inserir registro.", e);
		}
	}
	public TipoImovel updateReg(TipoImovel entity) throws LogicException {
		try {
			return daoFactory.getTipoImovelDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível atualizar o registro.",	e);
		}
	}
	public void deleteReg(TipoImovel entity) throws LogicException {
		try {
			daoFactory.getTipoImovelDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("Não foi possível deletar o registro.", e);
		}
	}
	public List<TipoImovel> findAll() {
		return daoFactory.getTipoImovelDAO().findAll();
	}
	public List<TipoImovel> getRegByExample(TipoImovel example) {
		return daoFactory.getTipoImovelDAO().getRegByExample(example);
	}
	public void deleteAll(TipoImovel entity) throws LogicException {
		try {
		daoFactory.getTipoImovelDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"Não foi possível deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getTipoImovelDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível deletar os registros.", e);
	}
}
	public TipoImovel findById(Integer id) throws LogicException {
		try {
	return daoFactory.getTipoImovelDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("Não foi possível buscar por ID.", e);
	}
	}
		}