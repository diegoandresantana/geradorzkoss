package backview;

import java.util.List;

import view.Tela;
import annotations.ZKEntity;

public class GerarBackView {
	/**
	 * Chama os metods geradores dos BackView tanto o lis.java e o cad.java
	 * 
	 * @param entidades
	 */
	public static void geraBackView(List<Class<?>> entidades) {
		for (Class clazz : entidades) {
			
			if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==1){
				BackView.backViewCad(clazz);
			}
			if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==2){
				BackView.backViewCadDupla(clazz);
			}
			if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==3){
				BackView.backViewCad(clazz);
			}
			if(((ZKEntity) clazz.getAnnotation(ZKEntity.class)).tipoTela()==0){
				
			}
		
			
			
			BackView.backViewLis(clazz);
		}
	}
}
