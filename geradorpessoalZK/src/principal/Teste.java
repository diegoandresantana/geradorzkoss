package principal;

import java.util.Date;

import model.dao.impl.hibernate.CidadeDAO;
import model.dao.impl.hibernate.EstadoDAO;
import model.entity.hibernate.Cidade;
import model.entity.hibernate.Estado;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date data=new Date();
		data.setMonth(data.getMonth()-10);
		//Contratocad.datasLancamento(new Date(), data, 10);
		System.out.println(new CidadeDAO().countAllLimit(new Cidade()));
		System.out.println(new EstadoDAO().countAllLimit(new Estado()));
	
	}

}
