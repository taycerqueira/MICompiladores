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
public class ValorRelacionalAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ValorRelacionalAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub
        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!consumir(gerenciadorToken, TipoToken.OPERADOR_ARITIMETICO_ADICAO)) {
                return false;
            } else if (!OpMult.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!ValorRelacionalAux.getInstancia().analisar(gerenciadorToken)) {
                return false;
            } else if (!consumir(gerenciadorToken, TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO)) {
                return false;
            }
           else if (gerenciadorToken.eof()) {
                return true;
            }

            return true;

        }

        return false;
    }

    @Override
    protected void gerarFirst() {
        //{  { E, - , + }

        first.add(TipoToken.EOF);
        first.add(TipoToken.OPERADOR_ARITIMETICO_ADICAO);
        first.add(TipoToken.OPERADOR_ARITIMETICO_SUBTRACAO);

    }

    @Override
    protected void gerarFollow() {
        //{ != , < , <=, == , > , >= , &&, || , ), ‘,’, ;, ]}
        follow.add(TipoToken.DELIMITADOR_FECHA_PARENTESES);
        follow.add(TipoToken.DELIMITADOR_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        follow.add(TipoToken.DELIMITADOR_FECHA_COLCHETE);
        follow.add(TipoToken.OPERADOR_LOGICO_OU);
        follow.add(TipoToken.OPERADOR_LOGICO_E);
        follow.add(TipoToken.OPERADOR_RELACIONAL_DIFERENTE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_IGUAL);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE);

        follow.add(TipoToken.OPERADOR_RELACIONAL_MAIOR_QUE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MENOR_IGUAL_QUE);
        follow.add(TipoToken.OPERADOR_RELACIONAL_MENOR_QUE);

    }

}
