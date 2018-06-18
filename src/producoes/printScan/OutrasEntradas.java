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
public class OutrasEntradas extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new OutrasEntradas();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		
		if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(!consumir(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)){
				return false;
			}
			
                        
			
			//TODO: <Entrada>
                        else if(!Entrada.getInstancia().analisar(gerenciadorToken)){
                            return false;
                            
                        }
			
			//TODO: <OutrasEntradas> 
                        
                        else if(!OutrasEntradas.getInstancia().analisar(gerenciadorToken)){
                            return false;
                        }
                        
                        // Todo: Vazio
			
                       return true;
			
		}
		
		return false;
	}

	@Override
	protected void gerarFirst() {
		//{ E, ‘,’}
		first.add(TipoToken.E);
                first.add(TipoToken.DELIMITADOR_VIRGULA);
	}

	@Override
	protected void gerarFollow() {
		//{ ) }
		follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
	}

}