/**
 * 
 */
package producoes.estruturasCondicionais;

import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class EstruturaCondicional extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new EstruturaCondicional();
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