/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.io.IOException;
import java.util.ArrayList;

/** Classe responsável por controlar o funcionamento da máquina.
 * @author pedro-menezes
 * @author lucasSilverio98
 */
public final class Controlador {
    private final Maquina maquina;
    private final String path;
    
    /** Construtor que recebe o objeto da máquina e o diretório do arquivo onde 
     * será gravado o resultado da execução.
     * @param maquina Maquina - objeto da máquina do sistema.
     * @param path String - diretório do arquivo de saída.
     * @throws IOException verifica erro durante a busca do arquivo
     */
    public Controlador(Maquina maquina, String path) throws IOException {
        this.maquina = maquina;
        this.path = path;
        executarEntrada();
    }
    
    /** Método que executa a entrada que foi passada para a máquina e as funções
     * que serão necessárias, é o principal método para gerar a saída.
     * @throws IOException verifica algum erro durante o trabalho com o arquivo.
     */
    public void executarEntrada() throws IOException {
        int ponteiro = 0;
        ArrayList<Character> fita = new ArrayList();

        for (int i = 0; i < maquina.getEntrada().length(); i++) {
            fita.add(maquina.getEntrada().charAt(i));
        }

        Estado estadoAtual = maquina.getEstadoInicial();
        Funcao funcao = buscaFuncao(estadoAtual, ponteiro, fita);
        escreverSaida(fita, ponteiro, estadoAtual);

        while (funcao != null) {
            fita.remove(ponteiro);
            fita.add(ponteiro, funcao.getSimboloNovo());

            ponteiro = movimentaPonteiro(ponteiro, funcao.getMovimento());
            estadoAtual = funcao.getEstadoDestino();
            funcao = buscaFuncao(estadoAtual, ponteiro, fita);
            escreverSaida(fita, ponteiro, estadoAtual);
        }
    }

    /** Método responsável por buscar a função no estado de acordo 
     * com o símbolo atual, que é encontrao pela variável ponteiro.
     * @param estadoAtual Estado - objeto do estado atual onde será feita a busca da função.
     * @param ponteiro int - variável que guarda posição de execução da fita. 
     * @param fita ArrayList - fita separada por caracteres.
     * @return Funcao - funcao encontrada na busca, se não encontrada retorna null.
     */
    public Funcao buscaFuncao(Estado estadoAtual, int ponteiro, ArrayList<Character> fita) {
        char simboloAtual = fita.get(ponteiro);
        Funcao funcao = estadoAtual.buscarFuncao(simboloAtual);
        return funcao;
    }

    /** Método responsável por incrementar ou decrementar o valor da variável
     * ponteiro, de acordo com a direção de movimento: 'R' ou 'S'.
     * @param ponteiro int - variável que registra a posição do ponteiro na fita.
     * @param direcao char - movimento: 'R' ou 'S'. 
     * @return int - o valor do ponteiro incrementado ou decrementado.
     */
    public int movimentaPonteiro(int ponteiro, char direcao) {
        if (direcao == 'R') {
            return ++ponteiro;
        } else if (direcao == 'L') {
            return --ponteiro;
        }
        return ponteiro;
    }

    /** Método responsável por enviar as linhas ao método de escrita 
     * na classe que manipula o arquivo.
     * @param fita ArrayList - configuração atual da fita.
     * @param ponteiro int - posição atual de execução na fita.
     * @param estadoAtual int - estado de execução na máquina.
     * @throws IOException tratamento de erro para o trabalho com o arquivo.
     */
    public void escreverSaida(ArrayList<Character> fita, int ponteiro, Estado estadoAtual) throws IOException {
        String linha = "";
        for (int i = 0; i < fita.size(); i++) {
            if (i == ponteiro) {
                linha += "{" + estadoAtual.getCodigo() + "}";
                linha += fita.get(i);
            } else {
                linha += fita.get(i);
            }
        }
        new ManipuladorArquivo().escritor(path, linha);
    }
}
