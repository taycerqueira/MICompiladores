/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producoes.blocoVariaveis;

import lexico.TipoToken;
import producoes.RegraProducao;
import sintatico.GerenciadorToken;

/**
 *
 * @author wstro
 */
public class ExpressaoIdentificadoresVarAux extends RegraProducao {

    public static RegraProducao getInstancia() {
        return new ExpressaoIdentificadoresVarAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {
        // TODO Auto-generated method stub

        if (isFirst(gerenciadorToken.getTokenAtual().getTipoToken())) {

            if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_PONTO_VIRGULA)) {
                return false;
            } else if (!consumir(gerenciadorToken, TipoToken.DELIMITADOR_VIRGULA)) {
                return false;
            } //<ExpressaoIdentificadoresVar>
            else if (!ExpressaoIdentificadoresVar.getInstancia().analisar(gerenciadorToken)) {
                return false;
            }
            return true;

        }
        return false;
    }

    @Override
    protected void gerarFirst() {
        // TODO Auto-generated method stub
        // ',’ , ;
        first.add(TipoToken.DELIMITADOR_PONTO_VIRGULA);
        first.add(TipoToken.DELIMITADOR_VIRGULA);
    }

    @Override
    protected void gerarFollow() {
        // TODO Auto-generated method stub
        // bool, float, identificador, int, string, struct }

        follow.add(TipoToken.PALAVRA_RESERVADA_BOOL);
        follow.add(TipoToken.PALAVRA_RESERVADA_FLOAT);
        follow.add(TipoToken.PALAVRA_RESERVADA_INT);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRING);
        follow.add(TipoToken.PALAVRA_RESERVADA_STRUCT);
        follow.add(TipoToken.IDENTIFICADOR);
        follow.add(TipoToken.DELIMITADOR_FECHA_CHAVE);

    }

}
