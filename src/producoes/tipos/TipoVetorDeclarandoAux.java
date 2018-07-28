/**
 *
 */
package producoes.tipos;

import lexico.TipoToken;
import producoes.RegraProducao;
import semantico.TabelaSimbolos;
import sintatico.GerenciadorToken;

/**
 * @author Tayane
 *
 */
public class TipoVetorDeclarandoAux extends RegraProducao {

    
    public static RegraProducao getInstancia() {
        return new TipoVetorDeclarandoAux();
    }

    @Override
    public boolean analisar(GerenciadorToken gerenciadorToken) {

        if (TipoVetorDeclarando.getInstancia().analisar(gerenciadorToken)) {
            return true;
        } else {
            return verificarSimboloVazio(gerenciadorToken, true);
        }

    }

    @Override
    protected void gerarFirst() {
        // [, E
        first.add(TipoToken.EOF);
        first.add(TipoToken.DELIMITADOR_ABRE_COLCHETE);
    }

    @Override
    protected void gerarFollow() {
        // Identificador
        follow.add(TipoToken.IDENTIFICADOR);
    }

}
