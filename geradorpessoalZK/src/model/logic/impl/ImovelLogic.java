
package model.logic.impl;
 import model.entity.hibernate.Imovel;
import java.util.List;
import model.exceptions.DaoException;
import model.exceptions.LogicException;
		public class ImovelLogic extends GenericLogic {
	public Imovel insertReg(Imovel entity) throws LogicException {
		try {
			return daoFactory.getImovelDAO().insertReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel inserir registro.", e);
		}
	}
	public Imovel updateReg(Imovel entity) throws LogicException {
		try {
			return daoFactory.getImovelDAO().updateReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel atualizar o registro.",	e);
		}
	}
	public void deleteReg(Imovel entity) throws LogicException {
		try {
			daoFactory.getImovelDAO().deleteReg(entity);
		} catch (DaoException e) {
			throw new LogicException("N�o foi poss�vel deletar o registro.", e);
		}
	}
	public List<Imovel> findAll() {
		return daoFactory.getImovelDAO().findAll();
	}
	public List<Imovel> getRegByExample(Imovel example) {
		return daoFactory.getImovelDAO().getRegByExample(example);
	}
	public void deleteAll(Imovel entity) throws LogicException {
		try {
		daoFactory.getImovelDAO().insertReg(entity);
		} catch (DaoException e) {
		throw new LogicException(
				"N�o foi poss�vel deletar todos os registros.", e);
		}
	}
public void delByCodigos(String codigos) throws LogicException {
	try {
		daoFactory.getImovelDAO().delByCodigos(codigos);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel deletar os registros.", e);
	}
}
	public Imovel findById(Integer id) throws LogicException {
		try {
	return daoFactory.getImovelDAO().findById(id);
	} catch (DaoException e) {
		throw new LogicException("N�o foi poss�vel buscar por ID.", e);
	}
	}
		}