package principal;

import java.util.List;

import util.GeraConfig;
import view.Tela;
import annotations.ZKEntity;
import backview.BackView;
import dao.GeraDAOFactory;
import dao.HibernateDAO;

/**
 * gerador principal
 * 
 * @author diego
 * 
 */
public class Gerador {
	/**
	 * Captura todas as entidades que serão geradas
	 */
	public static List<Class<?>> entidades = GeraConfig.getClassEntidades();

	

	public static void gerar(boolean telaCad,boolean telaLis,boolean viewCad,boolean viewLis,boolean DAOFactory,boolean dao){
		for (Class clazz : entidades) {			
	
			if(telaCad){
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==1){
					Tela.telacad(clazz);
				}
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==2){
					Tela.telacadDupla(clazz);
				}
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==3){
					Tela.telacad(clazz);;
				}
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==0){
					
				}
				
				
			}
			if(telaLis){
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoPaginacao()==0){
					Tela.telalis(clazz);
				}
             if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoPaginacao()==1){
            	    Tela.telalisdynamic(clazz);
				}
			
			}
			if(viewCad){
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==1){
					BackView.backViewCad(clazz);
				}
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==2){
					BackView.backViewCadDupla(clazz);
				}
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==3){
					BackView.backViewCadDupla(clazz);
				}
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==0){
					
				}
			}
			if(viewLis){
				
			
				if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoPaginacao()==0){
						BackView.backViewLis(clazz);
					}
	             if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoPaginacao()==1){
	            	   BackView.backViewLisPaginadoDinamico(clazz);
					}
				
				
			}
			if(dao){
				HibernateDAO.geraHibernateDAO(clazz);
			}				
			
		}
		if(DAOFactory){
			GeraDAOFactory.geraDAOFactory(entidades);
		}
		//if(logic==true) {
		//	GerarLogic.geraLogic(entidades);
		//}
	}
	
	

}
