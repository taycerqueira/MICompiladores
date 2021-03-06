package lexico;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Tayane
 *
 */
public class Main {

    public static void main(String[] args) {

        try {
           
        	//testarArquivosDiretorio("arquivos/testes");
        	//testarArquivo("arquivos/testes/teste02_numeros.txt");
        	testarArquivo("correcao/teste.txt");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
        
    private static void testarArquivosDiretorio(String nomeDiretorio) throws FileNotFoundException{
    	
        File arquivos[];
        File diretorio = new File(nomeDiretorio);
        arquivos = diretorio.listFiles();
        int i=0;
        
        while(i < arquivos.length){
        	//System.out.println(nomeDiretorio);
        	testarArquivo(nomeDiretorio+"/"+(arquivos[i].getName()));
        	i++;
        } 
    	
    }
    
    private static void testarArquivo(String nomeArquivo) throws FileNotFoundException{
    	
        Buffer buffer = new Buffer(nomeArquivo);
        Lexico analisador = new Lexico(buffer);
        analisador.run();
        analisador.printTokens();
        try {
      
        	String output = "default_output";
        	String[] aux = nomeArquivo.split("/");
        	if(aux.length == 2){
        		output = aux[0] + "/saida_" + aux[1].replace(".txt", "");
        	}
        	else if(aux.length == 1){
        		output = "saida_" + aux[0].replace(".txt", "");
        	}
        	analisador.printTokensToFile(output);
        	
        	//System.out.println("\nArquivo gerado com sucesso");
        	
		} catch (Exception e) {
			System.out.println("Erro ao gerar arquivo de saída: " + e.getMessage());
		}
        
    }

}
