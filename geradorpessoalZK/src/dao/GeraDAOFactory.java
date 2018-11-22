package dao;

import java.util.List;

import util.GeraConfig;
import util.GeraUtils;

public class GeraDAOFactory {

	public static void geraDAOFactory(List<Class<?>> entidades) {
		StringBuilder sb = new StringBuilder();

		sb.append("\npackage " + GeraConfig.PACOTEDAO + ";");

		for (Class clazz : entidades) {
			sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
					+ clazz.getSimpleName() + "DAO;");
		}
		sb
				.append("\nimport " + GeraConfig.PACOTEDAO
						+ ".DAOFactory;");

		sb.append("\nimport org.hibernate.Session;");
		sb.append("\nimport model.util.HibernateUtil;");
		sb.append("\npublic class DAOFactory {");

		sb.append("\nprivate static DAOFactory daoFactory;");
		for (Class clazz : entidades) {
			sb.append("\nprivate static " + clazz.getSimpleName()
					+ "DAO "
					+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
					+ "DAO;");
		}

		sb.append("\npublic DAOFactory() {");

		sb.append("\n}");

		sb.append("\npublic static DAOFactory instance() {");
		sb.append("	\ntry {");
		sb.append("	\n	if (daoFactory == null) {");
		sb
				.append("	\n		daoFactory = (DAOFactory) (DAOFactory.class).newInstance();");
		sb.append("\n		}");
		sb.append("	\n	return daoFactory;");
		sb.append("	\n} catch (Exception ex) {");
		sb
				.append("	\n	throw new RuntimeException(\"Não pode criar o  DAOFactory\");");
		sb.append("	\n}");
		sb.append("	\n}");

		// You could override this if you don't want HibernateUtil for lookup
		sb.append("\nprotected Session getCurrentSession() {");
		// Obtendo a sessao da Thread atual
		sb.append("\n	return HibernateUtil.getCurrentSession();");
		sb.append("\n}");

		sb.append("\npublic Object classInstance(Class<?> clazz) {");

		sb.append("\n	try {");
		sb.append("\nreturn Class.forName(clazz.getName()).newInstance();");
		sb.append("\n	} catch (IllegalAccessException ex) {");

		sb.append("\n	} catch (InstantiationException ex) {");

		sb.append("\n} catch (Exception e) {");

		sb.append("\n}");
		sb.append("\nreturn null;");
		sb.append("\n}");
		for (Class clazz : entidades) {

			sb.append("\npublic " + clazz.getSimpleName() + "DAO get"
					+ clazz.getSimpleName() + "DAO() {");
			sb.append("\n	if ("
					+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
					+ "DAO == null)");
			sb.append("\n		"
					+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
					+ "DAO = (" + clazz.getSimpleName()
					+ "DAO) classInstance(" + clazz.getSimpleName()
					+ "DAO.class);");
			sb.append("\n	return "
					+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
					+ "DAO;");
			sb.append("\n}");

		}

		sb.append("\n}");
		GeraUtils.gravaArquivo("DAOFactory", GeraConfig.SOURCE
				+ GeraConfig.DIRDAO, sb.toString(), "java");
	}
}
