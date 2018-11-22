package logic;

import java.lang.reflect.Field;
import java.util.List;

import model.entity.hibernate.Cidade;
import model.exceptions.DaoException;
import model.exceptions.LogicException;

import util.GeraConfig;
import util.GeraUtils;
import annotations.ZKId;

public class GerarLogic {

	public static void geraLogic(List<Class<?>> entidades) {

		for (Class<?> clazz : entidades) {

			StringBuilder sb = new StringBuilder();
			String idtipoclasse, nomeentidade,tipoid = null;
			
			for (Field f : GeraUtils.getAllFields(clazz, null)) {
				if (f.getAnnotation(ZKId.class) != null) {
					idtipoclasse = f.getName();
					tipoid= f.getType().getSimpleName();
					break;
				}
			}
			nomeentidade = clazz.getSimpleName();
			sb.append("\npackage " + GeraConfig.PACOTELOGIC + ";");

			//sb.append("\nimport " + GeraConfig.PACOTEDAO + "."+ clazz.getSimpleName() + ";");

			sb.append("\n import " + GeraConfig.PACOTEENTIDADE + "."
					+ clazz.getSimpleName() + ";");
			sb.append("\nimport java.util.List;");

			sb.append("\nimport model.exceptions.DaoException;");
			sb.append("\nimport model.exceptions.LogicException;");
			
			sb.append("\n		public class " + clazz.getSimpleName()
					+ "Logic extends GenericLogic {");

			sb.append("\n	public " + nomeentidade + " insertReg("
					+ nomeentidade + " entity) throws LogicException {");
			sb.append("\n		try {");
			sb.append("\n			return daoFactory.get" + nomeentidade
					+ "DAO().insertReg(entity);");
			sb.append("\n		} catch (DaoException e) {");
			sb
					.append("\n			throw new LogicException(\"Não foi possível inserir registro.\", e);");
			sb.append("\n		}");
			sb.append("\n	}");

			sb.append("\n	public " + nomeentidade + " updateReg("
					+ nomeentidade + " entity) throws LogicException {");
			sb.append("\n		try {");
			sb.append("\n			return daoFactory.get" + nomeentidade
					+ "DAO().updateReg(entity);");
			sb.append("\n		} catch (DaoException e) {");
			sb
					.append("\n			throw new LogicException(\"Não foi possível atualizar o registro.\",	e);");
			sb.append("\n		}");
			sb.append("\n	}");

			sb.append("\n	public void deleteReg(" + nomeentidade
					+ " entity) throws LogicException {");
			sb.append("\n		try {");
			sb.append("\n			daoFactory.get" + nomeentidade
					+ "DAO().deleteReg(entity);");
			sb.append("\n		} catch (DaoException e) {");
			sb
					.append("\n			throw new LogicException(\"Não foi possível deletar o registro.\", e);");
			sb.append("\n		}");
			sb.append("\n	}");

			sb.append("\n	public List<" + nomeentidade + "> findAll() {");
			sb.append("\n		return daoFactory.get" + nomeentidade
					+ "DAO().findAll();");
			sb.append("\n	}");

			sb.append("\n	public List<" + nomeentidade + "> getRegByExample("
					+ nomeentidade + " example) {");
			sb.append("\n		return daoFactory.get" + nomeentidade
					+ "DAO().getRegByExample(example);");
			sb.append("\n	}");

			sb.append("\n	public void deleteAll(" + nomeentidade
					+ " entity) throws LogicException {");
			sb.append("\n		try {");
			sb.append("\n		daoFactory.get" + nomeentidade
					+ "DAO().insertReg(entity);");
			sb.append("\n		} catch (DaoException e) {");
			sb.append("\n		throw new LogicException(");
			sb
					.append("\n				\"Não foi possível deletar todos os registros.\", e);");
			sb.append("\n		}");
			sb.append("\n	}");
			sb.append("\npublic void delByCodigos(String codigos) throws LogicException {");
					sb.append("\n	try {");
							sb.append("\n		daoFactory.get"+nomeentidade+"DAO().delByCodigos(codigos);");
					sb.append("\n	} catch (DaoException e) {");
							sb.append("\n		throw new LogicException(\"Não foi possível deletar os registros.\", e);");
					sb.append("\n	}");
							sb.append("\n}");
			sb.append("\n	public " + nomeentidade
					+ " findById("+tipoid+" id) throws LogicException {");
			sb.append("\n		try {");
			sb.append("\n	return daoFactory.get" + nomeentidade
					+ "DAO().findById(id);");
			sb.append("\n	} catch (DaoException e) {");
			sb
					.append("\n		throw new LogicException(\"Não foi possível buscar por ID.\", e);");
			sb.append("\n	}");

			sb.append("\n	}");
			sb.append("\n		}");

			GeraUtils.gravaArquivo(clazz.getSimpleName() + "Logic",
					GeraConfig.SOURCE + GeraConfig.DIRLOGIC, sb.toString(),
					"java");
		}

	}
}
