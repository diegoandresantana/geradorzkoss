package util;

import java.lang.reflect.Field;
import java.util.List;

import annotations.ZKCombo;
import annotations.ZKComboFix;
import annotations.ZKEntity;
import annotations.ZKField;
import annotations.ZKFieldFind;
import annotations.ZKId;

public class ValidaAnnotation {
	
	public static void valida(List<Class<?>> classes){
		System.out.println("\n-------Inicio Validação---------");
		
		for(Class clazz:classes){
		List<Field> fields=GeraUtils.getAllFields(clazz, null);
		System.out.println("\n###########################################");
		System.out.println("Classe:"+clazz.getSimpleName());
		if(clazz.isAnnotationPresent(ZKEntity.class)){
			System.out.println("Ok:ZKEntity");
		}else{
			System.out.println("Erro:Adicione a Annotation ZKEntity e a propriedade label");
		}
		boolean zkid=false;
		for(Field f:fields){
			if(f.isAnnotationPresent(ZKId.class)){
				zkid=true;
				System.out.println("Ok:ZKId - propriedade:"+f.getName());
				if(f.isAnnotationPresent(ZKField.class)){
					System.out.println("Ok:ZKField");
					if(f.getAnnotation(ZKField.class).label().length()>0){
						System.out.println("Ok:label - propriedade:"+f.getName());
					}else{
						System.out.println("Erro:label - propriedade:"+f.getName()+"- preeencha o label");
					}
				}else{
					System.out.println("Erro:Adicione a Annotation ZKField");
				}
			}
		}
		if(zkid==false){
			System.out.println("Erro:Adicione a Annotation ZKId");
		}
		for(Field f:fields){
			if(f.isAnnotationPresent(ZKField.class) && !f.isAnnotationPresent(ZKId.class)){
				if(f.getAnnotation(ZKField.class).label().length()>0){
					System.out.println("Ok:label - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:label - propriedade:"+f.getName()+"- preeencha o label");
				}
			}else if(f.isAnnotationPresent(ZKFieldFind.class)){
				if(f.getAnnotation(ZKFieldFind.class).label().length()>0){
					System.out.println("Ok:label - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:label - propriedade:"+f.getName()+"- preencha o label");
				}
				if(f.getAnnotation(ZKFieldFind.class).propDisplayFieldFind().length()>0){
					System.out.println("Ok:propDisplayFieldFind - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:propDisplayFieldFind - propriedade:"+f.getName()+"- preencha o propDisplayFieldFind");
				}
			}else if(f.isAnnotationPresent(ZKCombo.class)){
				if(f.getAnnotation(ZKCombo.class).label().length()>0){
					System.out.println("Ok:label - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:label - propriedade:"+f.getName()+"- preeencha o label");
				}
				if(f.getAnnotation(ZKCombo.class).propDisplayCombo().length()>0){
					System.out.println("Ok:propDisplayCombo - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:propDisplayCombo - propriedade:"+f.getName()+"- preencha o propDisplayCombo");
				}
			}else if(f.isAnnotationPresent(ZKComboFix.class)){
				if(f.getAnnotation(ZKComboFix.class).label().length()>0){
					System.out.println("Ok:label - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:label - propriedade:"+f.getName()+"- preencha o label");
				}
				if(f.getAnnotation(ZKComboFix.class).values().length>0){
					System.out.println("Ok:values - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:values - propriedade:"+f.getName()+"- preencha o values");
				}
				if(f.getAnnotation(ZKComboFix.class).properties().length>0){
					System.out.println("Ok:properties - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:properties - propriedade:"+f.getName()+"- preencha o properties");
				}
				if(f.getAnnotation(ZKComboFix.class).properties().length==f.getAnnotation(ZKComboFix.class).values().length){
					System.out.println("Ok:properties e values sao iguais no tamanho - propriedade:"+f.getName());
				}else{
					System.out.println("Erro:properties e values sao diferentes no tamanho - propriedade:"+f.getName()+"- preencha o corretamente");
				}
			}
		}
	    }
		System.out.println("\n-------fim Validação---------");
	}
}
