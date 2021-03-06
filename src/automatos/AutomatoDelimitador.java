/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatos;

import lexico.Buffer;
import lexico.TipoToken;
import lexico.Token;

/**
 *
 * @author wstro
 */
public class AutomatoDelimitador extends Automato {

	public AutomatoDelimitador(Buffer buffer) {
		super(buffer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Token executar() {

		lexema = "";
		int estado = 0;

		while (!buffer.fimCodigo()) {
			char c = buffer.lookAhead();
			switch (estado) {
			case 0:
				if (c == ';') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_PONTO_VIRGULA);
				} else if (c == '.') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_PONTO);
				} else if (c == '(') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_ABRE_PARENTESES);
				} else if (c == ')') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_FECHA_PARENTESES);
				} else if (c == '{') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_ABRE_CHAVE);
				} else if (c == '}') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_FECHA_CHAVE);
				} else if (c == ',') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_VIRGULA);
				} else if (c == ']') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_FECHA_COLCHETE);
				} else if (c == '[') {
					consumirCaractere();
					return getToken(TipoToken.DELIMITADOR_ABRE_COLCHETE);
				} else {
					estado = -1;
				}
				break;

			default:
				return getToken(TipoToken.INDEFINIDO);
			}

		}

		return getToken(TipoToken.INDEFINIDO);

	}

}
