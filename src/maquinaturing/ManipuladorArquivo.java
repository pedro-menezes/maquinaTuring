/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Classe para manipular os arquivos de leitura de dados, para executar a máquina,
 * e de saída com os resultados gerados.
 * @author pedro-menezes
 * @author lucasSilverio98
 */
public class ManipuladorArquivo {

        /** Método para ler o arquivo e inserir os dados em uma máquina.
         * @param path String - diretório do arquivo.
         * @return Maquina - máquina configurada com os valores lidos ddo arquivo.
         * @throws IOException usado para verificar algum erro encontrado ao buscas diretório do arquivo. 
         */
	public Maquina leitor(String path) throws IOException {
            try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
                String linha;
                Maquina maquina = new Maquina();
               
                buffRead.readLine();
                linha = buffRead.readLine();
      
                organizarEstadosAlfabetos(linha, maquina, "estados");
                
                linha = buffRead.readLine();
                organizarEstadosAlfabetos(linha, maquina, "alfaEntradas");
                
                linha = buffRead.readLine();
                organizarEstadosAlfabetos(linha, maquina, "alfaFita");
                
                buffRead.readLine();
                linha = buffRead.readLine().trim();
                
                while (linha.length() > 1) {                    
                    organizarLinhasFuncoes(linha, maquina);
                    linha = buffRead.readLine().trim();
                }
                
                linha = buffRead.readLine();
                organizarEstadosAlfabetos(linha, maquina, "estadoFinal");
                
                buffRead.readLine();
                maquina.setEntrada(buffRead.readLine().trim());
                
                return maquina;
            }
	}

        /** Método utilizado para escrever os resultados em um arquivo de saída.
         * @param path String - diretório do arquivo.
         * @param linha String - texto que será escrito na próxima linha.
         * @throws IOException usado para verificar algum erro encontrado ao buscas diretório do arquivo. 
         */
	public  void escritor(String path, String linha) throws IOException {
            try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true))) {
                buffWrite.append(linha + "\n");
            }
	}
        
        /** Método que recebe a linha, de acordo com seu tipo, no formato do arquivo
         * e configura os atributos que serão utilizados na máquina.
         * @param linha String - linha de texto lida do arquivo de entrada.
         * @param maquina Maquina - objeto da máquina que será utilizada no sistema.
         * @param tipo String - qual é o tipo de objeto está sendo lido. Ex: estado, alfabeto.
         */
        public void organizarEstadosAlfabetos(String linha, Maquina maquina, String tipo){
            String[] linhaAux = linha.split("[{,}]");
            String[] linhaOrganizada = removeLinhaVazia(linhaAux);
            
            switch (tipo) {
                case "estados" -> {
                    for (String texto : linhaOrganizada) {
                        Estado estado = new Estado(texto.trim());
                        maquina.addEstado(estado);
                    }
                }
                case "alfaEntradas" -> {
                    for (String texto : linhaOrganizada) {
                        maquina.addAlfaEntrada(texto.trim().charAt(0));
                    }
                }
                case "alfaFita" -> {
                    for (String texto : linhaOrganizada) {
                        maquina.addAlfaFita(texto.trim().charAt(0));
                    }
                }
                case "estadoFinal" -> {
                    maquina.addEstadoInicial(linhaOrganizada[0]);
                }
            }
        }
        
        /** Método que recebe a linha referentes as funções no formato do arquivo
         * e configura os atributos que serão utilizados na máquina.
         * @param linha String - linha de funções.
         * @param maquina Maquina - objeto da máquina uusada pelo sistema.
         */
        public void organizarLinhasFuncoes(String linha, Maquina maquina){
            String linhaAux[] = linha.split("[(,)>-]");
            linha = "";
            for (String texto : linhaAux) {
                if (!texto.trim().equals("")) {
                  linha += texto.trim()+".";
                }
            }
            linhaAux = linha.split("[.]");
           
            Estado estado = maquina.buscaEstado(linhaAux[0]);
            
            if (estado != null) {
                estado.addFuncao(new Funcao(linhaAux[1].charAt(0), maquina.buscaEstado(linhaAux[2]), linhaAux[3].charAt(0), linhaAux[4].charAt(0)));
            }
        }
        
        /** Remove linha vazia que é gerada ao organizar o arquivo.
         * @param linha String[] - linha a ser organizada.
         * @return String[] - linha organizada.
         */
        public String[] removeLinhaVazia(String[] linha){
            String[] linhaOrganizada = new String[linha.length -1];
            for (int i = 0; i < linhaOrganizada.length; i++) {
                    linhaOrganizada[i] = linha[i+1];
            }
            return linhaOrganizada;
        }
}