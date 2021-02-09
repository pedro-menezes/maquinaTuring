/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.util.ArrayList;

/** Classe utilizada para criar objetos do tipo Máquina e será contidos os atributos 
 * como estados, alfabetos, estado inicial e entrada.
 * @author pedro-menezes
 * @author lucasSilverio98
 */
public class Maquina {
    private final ArrayList<Estado> estados;
    private final ArrayList<Character> alfabetoEntrada;
    private final ArrayList<Character> alfabetoFita;
    private Estado estadoInicial;
    private String entrada;
    
    /**  
     * Construtor responsável por instanciar os ArrayList de estados e alfabetos.
     */
    public Maquina() {
        this.estados = new ArrayList<>();
        this.alfabetoEntrada = new ArrayList<>();
        this.alfabetoFita = new ArrayList<>();
    }
    
    /** Método para adicionar um estado no array de estados da máquina.
     * @param estado Estado - estado que será adicionado.
     */
    public void addEstado(Estado estado){
        estados.add(estado);
    }
    
    /** Método para adicionar argumento no array referente ao alfabeto da entrada.
     * @param argumento char - argumento que será adicionado.
     */
    public void addAlfaEntrada(char argumento){
        alfabetoEntrada.add(argumento);
    }
    
    /** Método para adicionar argumento no array referente ao alfabeto da fita.
    * @param argumento char - argumento que será adicionado.
    */
    public void addAlfaFita(char argumento){
        alfabetoFita.add(argumento);
    }
    
    /** Método que faz busca pelos estados da máquina pelo código
    * e adiciona o estado inicial da máquina através da busca.
    * @param codigo String - codigo do estado inicial.
    */
    public void addEstadoInicial(String codigo){
        estadoInicial = buscaEstado(codigo);
    }
    
    /** Método que busca estado da máquina através do código.
     * @param codigo String - codigo do estado que deseja ser encotrado.
     * @return Estado - estado encontrado através do código.
     */
    public Estado buscaEstado(String codigo){
        for (Estado estado: estados) {
            if (estado.getCodigo().equals(codigo)) {
                return estado;
            }
        }
        return null;
    }

    /** Método para adicionar entrada na máquina.
     * @param entrada String - entrada que será usada pela máquina.
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    /** Método que retorna a entrada que está sendo usada na máquina.
     * @return String - entrada usada na máquina.
     */
    public String getEntrada() {
        return entrada;
    }
    
    /** Método que retorna o estado inicial da máquina.
     * @return Estado - estado inicial da máquina.
     */
    public Estado getEstadoInicial() {
        return estadoInicial;
    }
}
