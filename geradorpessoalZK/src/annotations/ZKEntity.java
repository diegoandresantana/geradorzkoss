package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotattion para identificar a Entidade
 * 
 * @author diego
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ZKEntity {
	/**
	 * Label referente ao Nome da Entidade
	 * 
	 * @return
	 */
	public String label() default "";
	
	/**
	 * Label 
	 * Valores
	 * 0- Sem ação
	 * 1-Tela Simples- Cadastro Simples e Listagem Simples
	 * 2-Tela Dupla - Tela De Cadastro Um para Muitos
	 * 3- Tela Tripla -  Tela de Cadastro que utiliza 3 tabelas(Tabela Associativa).
	 * @return
	 */
	public int tipoTela() default 1;
	/**
	 * Label 
	 * Valores
	 * 0- LISTA SIMPLES - carrega tudo na tela
	 * 1- LISTA PAGINADA - vai ao banco a cada pagina	
	 * @return
	 */
	public int tipoPaginacao() default 1;
}
