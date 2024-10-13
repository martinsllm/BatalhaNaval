import java.util.InputMismatchException;
import java.util.Scanner;

public class Tabuleiro {

    private int tamanhoX;
    private int tamanhoY;
    public int quantidadeDeNavios, limiteMaximoDeNavios;
    static Scanner input = new Scanner(System.in);

    public Tabuleiro() {
        super();
    }

    public int getTamanhoX() {
        return tamanhoX;
    }

    public void setTamanhoX(int tamanhoX) {
        this.tamanhoX = tamanhoX;
    }

    public int getTamanhoY() {
        return tamanhoY;
    }

    public void setTamanhoY(int tamanhoY) {
        this.tamanhoY = tamanhoY;
    }

    public void obterTamanhoDosTabuleiros() {
        for(;;) {
            boolean tudoOk = false;
            try {
                System.out.println("Informe a altura do tabuleiro:");
                this.setTamanhoY(input.nextInt());
                System.out.println("Informe o comprimento do tabuleiro:");
                this.setTamanhoX(input.nextInt());
                tudoOk = true;
            } catch (InputMismatchException error) {
                System.out.println("Informe um valor numérico!");
            }
            if(tudoOk) {
                break;
            }
        }
    }

    public void calcularQuantidadeMaximaDeNavios() {
        limiteMaximoDeNavios = (tamanhoX * tamanhoY) / 3;
    }

    public void obterQuantidadeNavios() {
        System.out.println("Informe a quantidade de navios do jogo:");
        System.out.println("Max: " + limiteMaximoDeNavios + " navios.");
        quantidadeDeNavios = input.nextInt();
        if(quantidadeDeNavios < 1 || quantidadeDeNavios > limiteMaximoDeNavios) {
            quantidadeDeNavios = limiteMaximoDeNavios;
        }
    }



    
    
   
}
