/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoConstante;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class DeclaracaoDeConstanteCorpo extends RegraProducao{
	
	public static RegraProducao getInstancia() {
		return new DeclaracaoDeConstanteCorpo();
	}

	@Override
	public boolean analisar(GerenciadorToken gerenciadorToken) {
		// TODO Auto-generated method stub
                
                if(isFirst(gerenciadorToken.getTokenAtual().getTipoToken())){
			
			
                        if(!DeclaracaoDeConstanteLinha.getInstancia().analisar(gerenciadorToken)){
                            return false;
                        }
                        
                       else if(!DeclaracaoDeConstanteCorpoAux.getInstancia().analisar(gerenciadorToken)){
                            return false;
                        }
                         
                        
                        return true;
                        
			
                       
			
			
			
		}
		return false;
		
	}

	@Override
	protected void gerarFirst() {
		// TODO Auto-generated method stub
                // bool, float, identificador, int, string, struct 
                first.add(TipoToken.PALAVRA_RESERVADA_BOOL);
                first.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
                first.add(TipoToken.IDENTIFICADOR);
                first.add(TipoToken.PALAVRA_RESERVADA_INT);
                first.add(TipoToken.PALAVRA_RESERVADA_STRING);
                first.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
                
		
	}

	@Override
	protected void gerarFollow() {
		// TODO Auto-generated method stub
                // }
                
                follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);
                
                
		
	}

}