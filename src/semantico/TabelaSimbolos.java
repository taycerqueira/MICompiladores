/**
 *
 */
package semantico;

import java.util.ArrayList;
import java.util.List;
import lexico.Token;

/**
 * @author Tayane
 *
 */
public class TabelaSimbolos {

    //private ArrayList<Entrada> functionsProcedures;
    //public ArrayList<Entrada> variaveis;
    //private ArrayList<String> test;
    private List<Listas> tabelaSimbolosVariavel;
    private List<Listas> tabelaSimbolosConst;
    private List<Listas> tabelaSimbolosStruct;
    private ArrayList<ErroSemantico> erro;
    private List<Token> defineOTipoDeclarado;
    private List<Listas> tabelaSimbolosConstAux;
    private List<Listas> tabelaSimbolosVarAux;
    public boolean ajuda;

    private ArrayList<Funcao> tabelaSimbolosFuncao;
    public Funcao bufferFuncao;

    public TabelaSimbolos() {
        //functionsProcedures = new ArrayList<Entrada>();
        //variaveis = new ArrayList<Entrada>();

        tabelaSimbolosVariavel = new ArrayList<Listas>();
        tabelaSimbolosConst = new ArrayList<Listas>();
        tabelaSimbolosStruct = new ArrayList<Listas>();
        tabelaSimbolosFuncao = new ArrayList<Funcao>();
        tabelaSimbolosConstAux = new ArrayList<Listas>();
        tabelaSimbolosVarAux = new ArrayList<Listas>();
        erro = new ArrayList<ErroSemantico>();
        defineOTipoDeclarado = new ArrayList<Token>();
        this.ajuda = ajuda;

    }

    public void addTipo(Token tipo) {

        defineOTipoDeclarado.add(tipo);

        //ImpressaoVariavel();
    }

    public String encontraTipo(Token tipo) {
        for (Token t : defineOTipoDeclarado) {
            if (t.getLinha() == tipo.getLinha()) {
                return t.getLexema().toString();
            }
        }
        return null;
    }
    
    /**
     * Retorna o tipo de uma variáve. Caso a variável não seja encontrada na tabela de símbolos, uma string vazia é retornada. 
     * obs: não está sendo considerado o escopo
     * @param nomeVariavel
     * @return String tipo da variável
     */
    public String getTipoVariavel(String nomeVariavel){
    	for (Listas variavel : tabelaSimbolosVariavel) {
			if(variavel.nome.equals(nomeVariavel)){
				return variavel.foiDeclaradocomo;
			}
		}
    	return "";
    }

    public void addTabelaVariaveis(String nome, Token tipo) {
        if (JafoiDeclarado(nome, tabelaSimbolosConst)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Variável " + nome + " já foi declarada anteriomente com uma constante";
            erro.add(er);
        } else if (JafoiDeclarado(nome, tabelaSimbolosVariavel)) {

            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Variável local duplicada";
            erro.add(er);
        } else {
            atualizaVar();
            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);

            tabelaSimbolosVariavel.add(etds);
        }
        //ImpressaoVariavel();

    }

    public void addTabelaConst(String nome, Token tipo) {
        if (JafoiDeclarado(nome, tabelaSimbolosConst)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Const";
            erro.add(er);
        } else {
            atualizaConst();
            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);
            //antes dele adiciona verifica se existe o tipo atribuido tá correto
            //if (atribuicaoCorretaPeloTipo(tipo, nome)) {
            tabelaSimbolosConst.add(etds);
            //} else {

            //}
        }
        //ImpressaoVariavel();

    }

    public void atualizaConst() {
        if (tabelaSimbolosConst.size() != 0 && tabelaSimbolosConstAux.size() != 0) {
            for (int i = 0; i < tabelaSimbolosConst.size(); i++) {
                Listas a = tabelaSimbolosConst.get(i);
                for (int x = 0; x < tabelaSimbolosConstAux.size(); x++) {
                    Listas b = tabelaSimbolosConstAux.get(x);
                    if (a.equals(b)) {
                        tabelaSimbolosConst.remove(i);
                    }
                }
            }
        }
    }

    public void atualizaVar() {
        if (tabelaSimbolosVariavel.size() != 0 && tabelaSimbolosVarAux.size() != 0) {
            for (int i = 0; i < tabelaSimbolosVariavel.size(); i++) {
                Listas a = tabelaSimbolosVariavel.get(i);
                for (int x = 0; x < tabelaSimbolosVarAux.size(); x++) {
                    Listas b = tabelaSimbolosVarAux.get(x);
                    if (a.equals(b)) {
                        tabelaSimbolosVariavel.remove(i);
                    }
                }
            }
        }
    }

    public void addTabelaStruct(String nome, Token tipo) {
        if (JafoiDeclarado(nome, tabelaSimbolosStruct)) {
            //throw new RuntimeException("Erro semantico: Variavel "+ nome+ " foi declarada duas vezes");
            ErroSemantico er = new ErroSemantico();
            er.tipo = tipo;
            er.tipoDoErro = "Struct";
            erro.add(er);
        } else {

            Listas etds = new Listas();
            //System.out.println("  " + nome);
            etds.nome = nome;
            etds.tipo = tipo;
            etds.foiDeclaradocomo = encontraTipo(tipo);
            tabelaSimbolosStruct.add(etds);
        }
        //ImpressaoVariavel();

    }

    public boolean JafoiDeclarado(String nome, List<Listas> a) {
        for (Listas etds : a) {
            String naruto = etds.nome;
            if (naruto.equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void ImpressaoVariavel() {
        //int a = 0;
        System.out.println("TABELA DE SIMBOLOS VARIAVEL");
        for (Listas etds : tabelaSimbolosVariavel) {
            //a++;
            System.out.println("Variavel " + etds.nome.toUpperCase() + " Linha: " + etds.tipo.getLinha() + " ele é um " + etds.foiDeclaradocomo.toUpperCase());

        }

        System.out.println("\nTABELA DE SIMBOLOS CONSTANTE");
        for (Listas n : tabelaSimbolosConst) {
            //a++;
            System.out.println("Const " + n.nome.toUpperCase() + " Linha: " + n.tipo.getLinha() + " ele é um " + n.foiDeclaradocomo.toUpperCase());

        }
        System.out.println("\nTABELA DE SIMBOLOS STRUCT");
        for (Listas n : tabelaSimbolosStruct) {
            //a++;
            System.out.println("Struct " + n.nome.toUpperCase() + "Linha : " + n.tipo.getLinha() + " ele é um " + n.foiDeclaradocomo.toUpperCase());

        }
        System.out.println("\nTABELA DE SIMBOLOS FUNCAO");
        for (Funcao n : tabelaSimbolosFuncao) {
            //a++;
            n.print();
        }
    }

    public void ImpressaoVariavelErro() {
        //int a = 0;
        System.out.println("Erros semânticos (" + erro.size() + "): ");
        for (ErroSemantico e : erro) {
            // a++;
            System.out.println(e.getMensagem());
        }
    }

    /*
    esse metodo não deixa fazer atribuição em const
    exemplo a=10; a é um tipo const e não pode ser alterado
     */
    public void atribuicaoConst(Token recebe) {
        for (Listas a : tabelaSimbolosConst) {
            if (a.tipo.getLexema().equals(recebe.getLexema())) {
                ErroSemantico er = new ErroSemantico();
                er.tipo = recebe;
                er.tipoDoErro = "Alterar valor de uma const";
                erro.add(er);

            }
        }
    }

    /*
    
    depois tem que colocar a atribuicao na tabela nao sei se precisa
     */
    public void atribuicaoCorretaPeloTipoConst(Token t, Token a) {

        for (Listas lista : tabelaSimbolosConst) {
            if (lista.tipo.getLexema().equals(a.getLexema())) {
                //System.out.println("asas");
                if (lista.foiDeclaradocomo.equals("int")) {
                    // System.out.println("asas 1");
                    if (t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        //Listas b =new Listas();
                        tabelaSimbolosConstAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                        erro.add(er);

                    }

                } else if (lista.foiDeclaradocomo.equals("float")) {
                    if (!t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        //tabelaSimbolosConst.remove(lista);
                        tabelaSimbolosConstAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                        erro.add(er);

                    }

                } else if (lista.foiDeclaradocomo.equals("string")) {
                    if (!t.getTipoToken().toString().equals("CADEIA_CARACTERES") || t.getLexema().equals("true") || t.getLexema().equals("false")) {
                        //tabelaSimbolosConst.remove(lista);
                        tabelaSimbolosConstAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de Const";
                        erro.add(er);
                        //tabelaSimbolosConst.remove(lista);

                    }

                }
            }

        }
    }

    public void atribuicaoCorretaPeloTipoVar(Token t, Token a) {
        for (Listas lista : tabelaSimbolosVariavel) {
            if (lista.tipo.getLexema().equals(a.getLexema())) {
                //System.out.println("asas");
                if (lista.foiDeclaradocomo.equals("int")) {
                    // System.out.println("asas 1");
                    if (t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        tabelaSimbolosVarAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de var";
                        erro.add(er);
                        // tabelaSimbolosVariavel.remove(lista);
                    }
                } else if (lista.foiDeclaradocomo.equals("float")) {
                    if (!t.getLexema().contains(".") || t.getLexema().equals("true") || t.getLexema().equals("false") || t.getTipoToken().toString().equals("CADEIA_CARACTERES")) {
                        tabelaSimbolosVarAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de var";
                        erro.add(er);
                        //tabelaSimbolosVariavel.remove(lista);
                    }
                } else if (lista.foiDeclaradocomo.equals("string")) {
                    if (!t.getTipoToken().toString().equals("CADEIA_CARACTERES") || t.getLexema().equals("true") || t.getLexema().equals("false")) {
                        tabelaSimbolosVarAux.add(lista);
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = t;
                        er.tipoDoErro = "Tipo de atribuicao invalida na declaracao de var";
                        erro.add(er);
                        // tabelaSimbolosVariavel.remove(lista);
                    }
                }
            }

        }

    }

    /**
     * Adiciona a função que está no buffer na tabela de simbolos de function
     */
    public void addFuncao() {
        //Verificar se já existe uma função com o mesmo nome mesmos parametros (mesma quantidade e mesmos tipos)
        if (!funcaoBufferJaExiste()) {
            tabelaSimbolosFuncao.add(bufferFuncao);
        } else {
            ErroSemantico er = new ErroSemantico();
            er.tipoDoErro = "Função já declarada " + bufferFuncao.getNome();
            erro.add(er);
        }
    }
    
    /**
     * Valida a chamada de uma função. Em casa de erro, é adicionado na lista de erros semânticos
     * A função a ser avaliada é sempre a que está no buffer de função
     */
    public void validaChamadaFuncao(){
    	//Verifica se a função foi declarada
    	if(getFuncao(bufferFuncao) != null){
    		//Verifica se a quantidade de parâmetros e os tipos estão corretos
    		Funcao funcaoDeclarada = getFuncao(bufferFuncao);
    		if(funcaoDeclarada.getQtdParametros() == bufferFuncao.getQtdParametros()){
    			for (int i = 0; i < funcaoDeclarada.getQtdParametros(); i++) {
					if(funcaoDeclarada.getParametros().get(i).foiDeclaradocomo != bufferFuncao.getParametros().get(i).foiDeclaradocomo){
						//erro: chama de função com tipos diferentes
		                ErroSemantico er = new ErroSemantico();
		                er.tipoDoErro = "A chamada da função " + funcaoDeclarada.getNomeComArgumentos() + " não é aplicável para os argumentos " + bufferFuncao.getStringArgumentos();
		                erro.add(er);
		                break;
					}
				}
    		}
    		else{
    			//erro: chamada de função errada - quantidade de parametros diferentes
                ErroSemantico er = new ErroSemantico();
                er.tipoDoErro = "A chamada da função " + funcaoDeclarada.getNomeComArgumentos() + " não é aplicável para os argumentos " + bufferFuncao.getStringArgumentos();
                erro.add(er);
    		}
    	}
    	else{
    		//erro: chamando função não declarada
    		if(existeFuncaoMesmoNome(bufferFuncao)){
    			//Verifica se os parametros foram declarados
    			if(bufferFuncao.possuiParametrosNaoDeclarados()){
    				for (Listas parametro : bufferFuncao.getParametros()) {
    					System.out.println(parametro.isDeclarado());
    					if(!parametro.isDeclarado()){
    						//erro: parâmetro de função não declarado
    						ErroSemantico er = new ErroSemantico();
    		                er.tipoDoErro = "A variável " + parametro.nome + " não foi declarada";
    		                erro.add(er);
    					}
    				}
    			}
    			else{
                    ErroSemantico er = new ErroSemantico();
                    er.tipoDoErro = "A chamada da função " + bufferFuncao.getNome() + " não é aplicável para os argumentos " + bufferFuncao.getStringArgumentos();
                    erro.add(er);
    			}
    		}
    		else{
                ErroSemantico er = new ErroSemantico();
                er.tipoDoErro = "Chamada de função não declarada " + bufferFuncao.getNome();
                erro.add(er);
    		}
    	}
    	
    }
    
    /**
     * Verifica se existe uma função com declarada com o mesmo nome (não verifica se os parâmetros são iguais)
     * @param Funcao funcao
     * @return boolean
     */
    public boolean existeFuncaoMesmoNome(Funcao funcao){
    	for (Funcao f : tabelaSimbolosFuncao) {
			if(f.getNome().equals(funcao.getNome())){
				return true;
			}
		}
    	return false;
    }
    
    /**
     * Retorna uma função declarada a partir de uma função chamada. Caso a função chamada não tenha sido declarada, é retornado null
     * @param Funcao funcao
     * @return Funcao 
     */
    public Funcao getFuncao(Funcao funcao){
    	for (Funcao f : tabelaSimbolosFuncao) {
			if(f.isEquals(funcao)){
				return f;
			}
		}
    	return null;
    }

    /**
     * Limpa o buffer de função
     */
    public void clearBufferFuncao() {
        bufferFuncao = new Funcao();
    }

    /**
     * Verifica se a função que está no buffer já existe
     *
     * @return boolean
     */
    public boolean funcaoBufferJaExiste() {

        for (Funcao funcao : tabelaSimbolosFuncao) {
            if (funcao.isEquals(bufferFuncao)) {
                return true;
            }
        }

        return false;

    }

    public void expressaoIf(Token anterior, Token atual, Token proximo) {
        Listas primeiroTipo = retornaTokenDeclarado(anterior.getLexema(), tabelaSimbolosVariavel);
        if(primeiroTipo==null){
            primeiroTipo = retornaTokenDeclarado(anterior.getLexema(), tabelaSimbolosConst);
        }
        Listas segundoTipo = retornaTokenDeclarado(proximo.getLexema(), tabelaSimbolosVariavel);
        if(segundoTipo==null){
            segundoTipo = retornaTokenDeclarado(proximo.getLexema(), tabelaSimbolosConst);
        }

        if (!JafoiDeclarado(anterior.getLexema(), tabelaSimbolosVariavel) && !JafoiDeclarado(proximo.getLexema(), tabelaSimbolosVariavel)) {
            System.out.println("1");
            if (!anterior.getTipoToken().toString().equals(proximo.getTipoToken().toString())) {

                ErroSemantico er = new ErroSemantico();
                er.tipo = anterior;
                er.tipoDoErro = "Expressao condicional errada";
                erro.add(er);

                ErroSemantico e = new ErroSemantico();
                e.tipo = proximo;
                e.tipoDoErro = "Expressao condicional errada";
                erro.add(e);
            } else {
                if (anterior.getTipoToken().toString().equals("NUMERO")) {
                    System.out.println("2");
                    if ((!anterior.getLexema().contains(".") && proximo.getLexema().contains(".")) || (anterior.getLexema().contains(".") && !proximo.getLexema().contains("."))) {
                        ErroSemantico er = new ErroSemantico();
                        er.tipo = anterior;
                        er.tipoDoErro = "Expressao condicional errada";
                        erro.add(er);

                        ErroSemantico e = new ErroSemantico();
                        e.tipo = proximo;
                        e.tipoDoErro = "Expressao condicional errada";
                        erro.add(e);
                    }
                } else if (anterior.getTipoToken().toString().equals("PALAVRA_RESERVADA_TRUE") || proximo.getTipoToken().toString().equals("PALAVRA_RESERVADA_FALSE") || anterior.getTipoToken().toString().equals("PALAVRA_RESERVADA_FALSE") || proximo.getTipoToken().toString().equals("PALAVRA_RESERVADA_TRUE")) {
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = anterior;
                    er.tipoDoErro = "Expressao condicional errada";
                    erro.add(er);

                    ErroSemantico e = new ErroSemantico();
                    e.tipo = proximo;
                    e.tipoDoErro = "Expressao condicional errada";
                    erro.add(e);
                } else {
                    System.out.println("asdasdv");
                    ErroSemantico er = new ErroSemantico();
                    er.tipo = anterior;
                    er.tipoDoErro = "Expressao em IF Não foi declarado em Var";
                    erro.add(er);

                    ErroSemantico e = new ErroSemantico();
                    e.tipo = proximo;
                    e.tipoDoErro = "Expressao em IF Não foi declarado em Var";
                    erro.add(e);
                }
            }
        } else if (primeiroTipo == null) {
            System.out.println("3");
            ErroSemantico er = new ErroSemantico();
            er.tipo = anterior;
            er.tipoDoErro = "Expressao em IF Não foi declarado em Var ";
            erro.add(er);
        }else if (segundoTipo==null) {
            System.out.println("3");
            ErroSemantico er = new ErroSemantico();
            er.tipo = proximo;
            er.tipoDoErro = "Expressao em IF Não foi declarado em Var";
            erro.add(er);
        }  else {
            //System.out.println(primeiroTipo.foiDeclaradocomo + segundoTipo.foiDeclaradocomo);
            if (!primeiroTipo.foiDeclaradocomo.equals(segundoTipo.foiDeclaradocomo)) {
                ErroSemantico er = new ErroSemantico();
                er.tipo = proximo;
                er.tipoDoErro = "Expressao em IF Não são do msm tipo";
                erro.add(er);

            }
        }

    }

    public Listas retornaTokenDeclarado(String nome, List<Listas> a) {
        for (Listas etds : a) {
            String naruto = etds.nome;
            if (naruto.equals(nome)) {
                return etds;
            }
        }
        return null;
    }

}
