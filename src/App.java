import java.util.Scanner;

public class App {  
    static Jogador jogador1 = new Jogador();
    static Jogador jogador2 = new Jogador();
    static Tabuleiro tabuleiro = new Tabuleiro();
    static Scanner input;

    public static void obterNomeDosJogadores() {
        jogador1.obterNomeDoJogador();
        jogador2.obterNomeDoJogador();
    }

    public static void instanciarContadoresDeNaviosDosJogadores() {
        jogador1.quantidadeDeNaviosDoJogador = tabuleiro.quantidadeDeNavios;
        jogador2.quantidadeDeNaviosDoJogador = tabuleiro.quantidadeDeNavios;
    }

    public static void inserirNaviosNosTabuleirosDosJogadores() {
        jogador1.tabuleiro = jogador1.retornarNovoTabuleiroComOsNavios();
        jogador2.tabuleiro = jogador2.retornarNovoTabuleiroComOsNavios();
    }

    public static void iniciarTabuleiroDosJogadores() {
        jogador1.iniciarTabuleiroJogador(tabuleiro.getTamanhoX(), tabuleiro.getTamanhoY());
        jogador2.iniciarTabuleiroJogador(tabuleiro.getTamanhoX(), tabuleiro.getTamanhoY());
    }

    
    public static void exibirTabuleirosDosJogadores() {
        jogador1.exibirTabuleiro(jogador1.tabuleiro, true);
        jogador2.exibirTabuleiro(jogador2.tabuleiro, false);
    }

    public static void executaJogo(boolean jogoAtivo) {
        do {
            exibirTabuleirosDosJogadores();
            if(jogador1.acaoDoJogador(jogador2)) {
                if(jogador2.quantidadeDeNaviosDoJogador <= 0) {
                    System.out.println(jogador1.getNome() + " venceu o jogo!");
                    break;
                }
                jogador2.acaoDoComputador(jogador1);
                if(jogador1.quantidadeDeNaviosDoJogador <= 0) {
                    jogoAtivo = false;
                    System.out.println(jogador2.getNome() + " venceu o jogo!");
                }
            }
        } while(jogoAtivo);
        exibirTabuleirosDosJogadores();
    }

    public static void main(String[] args) throws Exception {
        obterNomeDosJogadores();
        tabuleiro.obterTamanhoDosTabuleiros();
        tabuleiro.calcularQuantidadeMaximaDeNavios();
        iniciarTabuleiroDosJogadores();
        tabuleiro.obterQuantidadeNavios();
        instanciarContadoresDeNaviosDosJogadores();
        inserirNaviosNosTabuleirosDosJogadores();
        executaJogo(true);
    }
}
