package dao;

import java.lang.reflect.Field;
import java.util.List;

import util.GeraConfig;
import util.GeraUtils;
import annotations.ZKId;

public class HibernateDAO {
	public static void geraHibernateDAO(Class clazz) {
	

			StringBuilder sb = new StringBuilder();

			sb.append("\npackage " + GeraConfig.PACOTEDAO + ";");
			sb.append("\nimport java.util.List;");
			sb.append("\nimport org.hibernate.Query;");

			sb.append("\nimport " + GeraConfig.PACOTEENTIDADE + "."
					+ clazz.getSimpleName() + ";");

			sb.append("\n@SuppressWarnings(\"unchecked\")");
			String tipoidclasse = "";
			for (Field field : GeraUtils.getAllFields(clazz, null)) {
				if (field.getAnnotation(ZKId.class) != null) {
					tipoidclasse = field.getType().getSimpleName();
					break;
				}
			}
			sb.append("\npublic class " + clazz.getSimpleName()
					+ "DAO extends GenericHibernateDAO<"
					+ clazz.getSimpleName() + ", " + tipoidclasse + "> {");

			sb.append("\n}");

			GeraUtils.gravaArquivo(clazz.getSimpleName() + "DAO",
					GeraConfig.SOURCE + GeraConfig.DIRDAO, sb.toString(),
					"java");

		}
	
}
