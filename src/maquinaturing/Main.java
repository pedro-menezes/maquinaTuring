/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Classe principal.
 * @author pedro-menezes
 * @author lucasSilverio98
 */
public class Main {

    /**
     * @param args argumentos utilizados para ler e escrever.
     * É necessário passar a localização do arquivo de entrada e saída ao executar.
     * Ex:  java -jar maquinaTuring.jar '../teste.txt' '../saida.txt'
     */
    public static void main(String[] args) {
        Maquina maquina;
        try {
            ManipuladorArquivo arquivo = new ManipuladorArquivo();
            maquina = arquivo.leitor(args[0]);
            new Controlador(maquina, args[1]);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
