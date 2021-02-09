/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

/** Classe que define as funções de um estado e os seus atributos, como:
 * símbolos, estado de destino e movimento.
 * @author pedro-menezes
 * @author lucasSilverio98
 */
public class Funcao {
    private final char simboloAtual;
    private final Estado estadoDestino;
    private final char simboloNovo;
    private final char movimento;

    /** Construtor que recebe todos os valore de atributos por parâmetros e atribuem 
     * aos da classe.
     * @param simboloAtual char - valor referente ao símbolo atual quando a função for usada.
     * @param estadoDestino Estado - valor do estado para onde a máquina deve percorrer após a execução da função
     * @param simboloNovo char - valor do simbolo escrito na após execução da função.
     * @param movimento char - valor do movimento que será realizado após a execução da função: R, L ou S.
     */
    public Funcao(char simboloAtual, Estado estadoDestino, char simboloNovo, char movimento) {
        this.simboloAtual = simboloAtual;
        this.estadoDestino = estadoDestino;
        this.simboloNovo = simboloNovo;
        this.movimento = movimento;
    }

    /** Método que retorna o símbolo atual.
     * @return char - símbolo atual necessário para executar a função.
     */
    public char getSimboloAtual() {
        return simboloAtual;
    }

    /** Método que retorna o símbolo que deve ser escrito após a execução da função.
     * @return char - símbolo que deve escrever após a execução.
     */
    public char getSimboloNovo() {
        return simboloNovo;
    }

    /** Método que retorna o movimento do ponteiro após a função ser executada.
     * @return char - movimento do ponteiro : 'R', 'L' ou 'S'.
     */
    public char getMovimento() {
        return movimento;
    }

    /** Método que retorno o estado de destino após a execução da função.
     * @return Estado - o estado que a máquina deverar ir após essa função.
     */
    public Estado getEstadoDestino() {
        return estadoDestino;
    }
}
