package lexico;

/**
 * 
 * @author Tayane
 *
 */
public enum TipoToken {
	
	PALAVRA_RESERVADA_CONST,
	PALAVRA_RESERVADA_VAR,
	PALAVRA_RESERVADA_STRUCT,
	PALAVRA_RESERVADA_TYPEDEF,
	PALAVRA_RESERVADA_PROCEDURE,
	PALAVRA_RESERVADA_FUNCTION,
	PALAVRA_RESERVADA_RETURN,
	PALAVRA_RESERVADA_START,
	PALAVRA_RESERVADA_IF,
	PALAVRA_RESERVADA_THEN,
	PALAVRA_RESERVADA_ELSE,
	PALAVRA_RESERVADA_WHILE,
	PALAVRA_RESERVADA_SCAN,
	PALAVRA_RESERVADA_PRINT,
	PALAVRA_RESERVADA_INT,
	PALAVRA_RESERVADA_FLOAT,
	PALAVRA_RESERVADA_BOOL,
	PALAVRA_RESERVADA_STRING,
	PALAVRA_RESERVADA_TRUE,
	PALAVRA_RESERVADA_FALSE,
	PALAVRA_RESERVADA_EXTENDS,
	
	IDENTIFICADOR,
	
	NUMERO,
	
	OPERADOR_ARITIMETICO_ADICAO,
	OPERADOR_ARITIMETICO_SUBTRACAO,
	OPERADOR_ARITIMETICO_MULTIPLICACAO,
	OPERADOR_ARITIMETICO_DIVISAO,
	OPERADOR_ARITIMETICO_INCREMENTO,
	OPERADOR_ARITIMETICO_DECREMENTO,
	
	OPERADOR_RELACIONAL_DIFERENTE,
	OPERADOR_RELACIONAL_IGUAL,
	OPERADOR_RELACIONAL_MENOR_QUE,
	OPERADOR_RELACIONAL_MENOR_IGUAL_QUE,
	OPERADOR_RELACIONAL_MAIOR_QUE,
	OPERADOR_RELACIONAL_MAIOR_IGUAL_QUE,
	OPERADOR_RELACIONAL_ATRIBUICAO,
	
	DELIMITADOR_COMENTARIO_LINHA,
	DELIMITADOR_COMENTARIO_BLOCO_INICIO,
	DELIMITADOR_COMENTARIO_BLOCO_FIM,
	
	DELIMITADOR_PONTO_VIRGULA,
	DELIMITADOR_VIRGULA,
	DELIMITADOR_ABRE_PARENTESES,
	DELIMITADOR_FECHA_PARENTESES,
	DELIMITADOR_ABRE_COLCHETE,
	DELIMITADOR_FECHA_COLCHETE,
	DELIMITADOR_ABRE_CHAVE,
	DELIMITADOR_FECHA_CHAVE,
	DELIMITADOR_PONTO,
	
	CADEIA_CARACTERES,
	CADEIA_CARACTERES_MAL_FORMADA,
        
    OPERADOR_LOGICO_EXCLAMACAO_NEGADO,
    OPERADOR_LOGICO_E,
    OPERADOR_LOGICO_OU,
    
    COMENTARIO_LINHA,
    COMENTARIO_BLOCO,
	
	INDEFINIDO,
    INDEFINIDO_E,
    INDEFINIDO_OU,
    COMENTARIO_MAU_FORMADO


}
