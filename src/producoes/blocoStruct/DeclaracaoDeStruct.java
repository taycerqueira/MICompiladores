/**
 * 
 */
package producoes.blocoStruct;

import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class DeclaracaoDeStruct extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new DeclaracaoDeStruct();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
		
	}

}
