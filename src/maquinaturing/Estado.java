/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.util.ArrayList;

/** Classe que define os objetos dos estados da máquina e seus atributos: o
 * código e as funções de cada estado.
 * @author pedro-menezes
 * @author lucasSilverio98
 */
public class Estado {
    private final String codigo;
    private final ArrayList<Funcao> funcoes;

    /** Construtor para iniciar ArrayList e código definido através de parâmetro.
     * @param codigo String - código do estado.
     */
    public Estado(String codigo) {
        this.codigo = codigo;
        this.funcoes = new ArrayList<>();
    }

    /** Método que retorna código do estado.
     * @return  String - código do estado.
     */
    public String getCodigo() {
        return codigo;
    }
    
    /** Método para adicionar determinada função no estado.
     * @param funcao Funcao - funcao que será adicionada ao estado.
     */
    public void addFuncao(Funcao funcao){
        funcoes.add(funcao);
    }
    
    /** Método para buscar um função do estado de acordo com o simbolo passado.
     * @param simboloAtual char - simbolo que será passado para realizar a busca.
     * @return Funcao - retorna a funcao encontrada e retorna null caso função não seja encontrada.
     */
    public Funcao buscarFuncao(char simboloAtual){
        for (Funcao funcao : funcoes) {
            if (funcao.getSimboloAtual() == simboloAtual) {
                return funcao;
            }
        }
        return null;
    }
}
