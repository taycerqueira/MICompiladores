/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoConstante;

import lexico.TipoToken;
import producoes.RegraProducao;
import producoes.precedenciaOperadores.Expressao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadorConst extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new ExpressaoIdentificadorConst();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
                
                if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			if(!consumir(gerenciadorToken, TipoToken.IDENTIFICADOR)){
				return false;
			}
                        else if(!consumir(gerenciadorToken, TipoToken.OPERADOR_RELACIONAL_ATRIBUICAO)){
                            return false;
                        }
                        
                        // falta: <Expressao>
                        else if(!Expressao.getInstancia().analisar(gerenciadorToken)){
                            return false;
                        }
                        
                        
                        
                        return true;
                        
			
                       
			
			
			
		}
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
                // Identificador
                first.add(TipoToken.IDENTIFICADOR);
                
		
	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
                //  ‘,’ , ;
                
                follow.add(TipoToken.DELIMITADOR_VIRGULA);
                follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
                
                
		
	}

}