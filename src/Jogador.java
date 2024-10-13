import java.util.Random;
import java.util.Scanner;

public class Jogador {

    private String nome;
    public int quantidadeDeNaviosDoJogador;
    public int tabuleiro[][];
    static Scanner input = new Scanner(System.in);

    static final int VAZIO = 0;
    static final int NAVIO = 1;
    static final int ERRO = 2;
    static final int ACERTO = 3;
    static final int POSICAO_X = 0;
    static final int POSICAO_Y = 1;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Jogador() {

    }

    public void obterNomeDoJogador() {
        System.out.println("Informe o nome do jogador:");
        this.setNome(input.next());
    }

    public void iniciarTabuleiroJogador(int tamanhoX, int tamanhoY) {
        this.tabuleiro = new int [tamanhoX][tamanhoY];
    }

    public void exibirNumerosDasColunasDoTabuleiro(int tamanho) {
        int numeroDaColuna = 1;
        String numerosDoTabuleiro = "   ";
        for(int i = 0; i < tamanho; i ++) {
            numerosDoTabuleiro += (numeroDaColuna++) + " ";
        }
        System.out.println(numerosDoTabuleiro);
    }

    public int[][] retornarNovoTabuleiroComOsNavios() {
        int quantidadeRestanteDeNavios = quantidadeDeNaviosDoJogador;
        int x = 0, y = 0;
        Random numeroAleatorio = new Random();
        do {
            x = 0;
            y = 0;
            for(int[] linha : tabuleiro) {
                for(int coluna : linha) {
                    if(numeroAleatorio.nextInt(100) <= 10) {
                        if(coluna == VAZIO) {
                            tabuleiro[x][y] = NAVIO;
                            quantidadeRestanteDeNavios--;
                            break;
                        }
                        if(quantidadeRestanteDeNavios <= 0) {
                            break;
                        }
                    }
                    y++;
                }
                y = 0;
                x++;
                if(quantidadeRestanteDeNavios <= 0) {
                    break;
                }   
            }
        } while (quantidadeRestanteDeNavios > 0);
        return tabuleiro;
    }

    public void exibirTabuleiro(int[][] tabuleiro, boolean seuTabuleiro) {
        System.out.println("|-----" + this.getNome() + "-----|");
        exibirNumerosDasColunasDoTabuleiro(tabuleiro.length);
        String linhaDoTabuleiro = "";
        char letraDaLinha = 65;
        for(int[] linha : tabuleiro) {
            linhaDoTabuleiro = (letraDaLinha++) + " |";
            for(int coluna : linha) {
                switch(coluna) {
                    case VAZIO :
                        linhaDoTabuleiro += " |";
                        break;
                    case NAVIO : 
                        if(seuTabuleiro) {
                            linhaDoTabuleiro += "N|";
                            break;
                        } else {
                            linhaDoTabuleiro += " |";
                            break;
                        }
                    case ERRO :
                        linhaDoTabuleiro += "X|";
                        break;

                    case ACERTO :    
                        linhaDoTabuleiro += "D|";
                        break;

                }
            }
            System.out.println(linhaDoTabuleiro);
        }
    }

    public String receberPosicaoInseridaPeloJogador() {
        System.out.println("Informe a posição do tiro:");
        return input.next();
    }

    public boolean validarPosicaoInseridaPeloJogador(int[] posicao) {
        boolean retorno = true;
        if(posicao[0] > tabuleiro[0].length || posicao[1] > tabuleiro[1].length - 1) {
            retorno = false;
            System.out.println("Posição inválida!");
        }
        return retorno;
    }

    public boolean validarTiroDoJogador(String tiroDoJogador) {
        int quantidadeNumeros = (tabuleiro[1].length > 10) ? 2 : 1;
        String expressaoDeVerificacao = "^[A-Za-z]{1}[0-9]{"
            + quantidadeNumeros + "}$";

        return tiroDoJogador.matches(expressaoDeVerificacao);
    }

    public static int[] retornarPosicaoInseridaPeloJogador(String tiroDoJogador) {
        String tiro = tiroDoJogador.toLowerCase();
        int[] retorno = new int[2];
        retorno[0] =  tiro.charAt(0) - 97;
        retorno[1] = Integer.parseInt(tiro.substring(1)) - 1;
        return retorno;
    }

    public void inserirValoresDasAcoesNosTabuleiros(int[] posicoes, Jogador jogador) {
        if(jogador.tabuleiro[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] == NAVIO) {
            jogador.tabuleiro[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] = ACERTO;
            jogador.quantidadeDeNaviosDoJogador--;
            System.out.println(this.nome + " acertou um navio!");
        } else {
            jogador.tabuleiro[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] = ERRO;
            System.out.println("Tiro na água!");
        }   
    }

    public boolean acaoDoJogador(Jogador adversario) {
        boolean acaoValida = true;
        String tiroDoJogador = receberPosicaoInseridaPeloJogador();

        if(validarTiroDoJogador(tiroDoJogador)) {
            int[] posicoes = retornarPosicaoInseridaPeloJogador(tiroDoJogador);          
            if(validarPosicaoInseridaPeloJogador(posicoes)) {
                inserirValoresDasAcoesNosTabuleiros(posicoes, adversario);
            } else {
                acaoValida = false;
            }
        } else {
            System.out.println("Erro!");
            acaoValida = false;
        }
        return acaoValida;
    }

    public void acaoDoComputador(Jogador adversario) {
        int[] posicoes = retornarJogadaDoComputador();
        inserirValoresDasAcoesNosTabuleiros(posicoes, adversario);
    }

    public int[] retornarJogadaDoComputador() {
        int[] posicoes = new int[2];
        posicoes[POSICAO_X] = retornarJogadaAleatoriaDoComputador(tabuleiro[0].length);
        posicoes[POSICAO_Y] = retornarJogadaAleatoriaDoComputador(tabuleiro[1].length);
        return posicoes;
    }

    public int retornarJogadaAleatoriaDoComputador(int limite){
        Random jogadaComputador = new Random();
        int numeroGerado = jogadaComputador.nextInt(limite);
        return (numeroGerado == limite) ? --numeroGerado : numeroGerado;
    }

    
    
    
}
