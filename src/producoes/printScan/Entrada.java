/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.printScan;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class Entrada extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new Entrada();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)){
				return false;
			}
			
                       
			//TODO: <Final>
			
			return true;
			
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{ Identificador }
		first.add(TipoToken.IDENTIFICADOR);
	}

	@Override
	protected void gerarFollow() {
		//{ ‘,’, ) }
		follow.add(TipoToken.DELIMITADOR_VIRGULA);
                follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
	}

}
