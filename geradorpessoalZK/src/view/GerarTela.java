package view;

import java.util.List;

import annotations.ZKEntity;

public class GerarTela {

	public static void gerarTela(List<Class<?>> entidades) {
		for (Class clazz : entidades) {
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
			
			Tela.telalis(clazz);
		}
	}
}
