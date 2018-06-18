/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.precedenciaOperadores;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ExpressaoAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub
        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

             if (!consumir(gerenciadorToken, TipoToken.OPERADOR_LOGICO_OU)) {
                return false;
            }else if (!Expressao.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            //producao vazio
            

            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{  E, ||}
        first.add(TipoToken.OPERADOR_LOGICO_OU);
        first.add(TipoToken.E);
        
        
        
      
        
    }

    @Override
    protected void gerarFollow() {
        //{ ), ‘,’, ;, ]}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
        
        

    }

}
