//IMPORTS

import java.util.Scanner;
import java.util.Random;


public class Main {

    public static int TAMLIN = 10;
    public static int TAMCOL = 10;

    //DECLARAÇÃO DOS IMPORTS
    public static Scanner ler = new Scanner(System.in);
    public static Random aleatorio = new Random();

    //DECLARAÇÕES VARIAVEIS/CONSTANTES
    public static int tamanhoTabuleiro = 10;
    public static int barcos = 10;
    public static String[][] matrizTabuleiro = new String[TAMLIN][TAMCOL];
    public static String[][] matrizTabuleiroJ1 = new String[TAMLIN][TAMCOL];
    public static String[][] matrizTabuleiroJ2 = new String[TAMLIN][TAMCOL];
    public static int linha, coluna;
    public static int[] tamanhosBarcos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    public static int bombLin,bombCol;

    //


    //MAIN
    public static void main(String[] args) {
        int jogarNovamente;
        do{
            inicioDoTabuleiro();
            matrizTabuleiroJ1 = jogador1(matrizTabuleiroJ1);
            matrizTabuleiro = computador(matrizTabuleiro);

            iniciarJogo();

            System.out.println("Você deseja jogar novamente? (1 para sim, outro número para não!)");
            jogarNovamente = ler.nextInt();
        }while(jogarNovamente == 1);
        System.out.println("Foi um bom jogo!");
    }
    //

    //IMPRIME A MATRIZ DO COMPUTADOR, COM OS BARCOS ALOCADOS ALEATÓRIAMENTE
    public static String[][] computador(String[][] matriz){
        int num;
        posicaoDosBarcos(tamanhosBarcos.length, tamanhosBarcos, matriz);
        mostrarTabuleiroComputador(matriz);
        return matriz;
    }
    //

    //VEZ DO JOGADOR 1
    public static String[][] jogador1(String[][] matriz) {
        int num;
        System.out.println("Como você deseja posicionar os navios?");
        System.out.println("Manualmente - 1 \nAleatoriamente - 2 ");
        num = (int) ler.next().toUpperCase().charAt(0);
        num = num - 48;
        switch (num) {
            case 1 -> {

            }
            case 2 -> {
                posicaoDosBarcos(tamanhosBarcos.length, tamanhosBarcos, matriz);
            }
        }
        mostrarTabuleiroPlayer(matriz);
//        mostrarTabuleiro(matriz);
        return matriz;
    }
    //


    //VEZ DO JOGADOR 2, PEDIR AJUDA PARA FAZER
    public static String[][] jogador2(String[][] matriz){

        return matriz;
    }
    public static void inicioDoTabuleiro(){
        for (int aux1=0; aux1<matrizTabuleiro.length; aux1++){
            for (int aux2= 0; aux2<matrizTabuleiro.length; aux2++){
                matrizTabuleiro[aux1][aux2] = "🔵";
                matrizTabuleiroJ1[aux1][aux2] = "🔵";
                matrizTabuleiroJ2[aux1][aux2] = "🔵";
            }
        }
    }
    //


    //AJUSTA A POSIÇÃO DOS BARCOS ALEATORIAMENTE
    public static void posicaoDosBarcos(int quantidadeBarcos, int[] tamanhosBarcos, String[][] matriz) {
        for (int i = 0; i < quantidadeBarcos; i++) {
            int tamanhoBarco = tamanhosBarcos[i];
            for (int shipAmount = 0; shipAmount < 1;) {
                int posI = aleatorio.nextInt(tamanhoTabuleiro);
                int posJ = aleatorio.nextInt(tamanhoTabuleiro);
                if (verificacaoInsercao(posI, posJ, tamanhoBarco, matriz)) {
                    shipAmount++;
                }
            }
        }
    }
    //

    //VERIFICA SE É POSSÍVEL INSERIR O BARCO NA POSIÇÃO DESEJADA E REALIZA A INSERÇÃO SE FOR POSSÍVEL
    public static boolean verificacaoInsercao(int i, int j, int tamanhoBarco, String[][] matriz){
        if(i + tamanhoBarco + j <= tamanhoTabuleiro){
            if(!verifBarcoColuna(i, j,tamanhoBarco, matriz)){
                putShip(i, j, false, tamanhoBarco, matriz);
                return true;
            }
        }else if(j + tamanhoBarco + i<= tamanhoTabuleiro){
            if (!verifBarcoLinha(i, j,tamanhoBarco, matriz)){
                putShip(j, i, true, tamanhoBarco, matriz);
                return true;
            }
        }
        return false;
    }
    //

    //VERIFICA SE HÁ PARTES DE UM BARCO NA COLUNA DESEJADA
    public static boolean verifBarcoColuna(int i, int j,int tamanhoBarco, String[][] matriz) {
        return contaPartesBarcoColuna(i, j, tamanhoBarco,"#", matriz) != 0;
    }


    //CONTA O NUMERO DE PARTES DE UM NAVIO NA COLUNA DESEJADA
    public static int contaPartesBarcoColuna(int i, int j,int tamanhoBarco,String symbol, String[][] matriz){
        int countShips = 0;
        for(int k = i; k < (i + tamanhoBarco); k++ ){ //ARRUMAR O TAMANHO, AO INVES DE 3, SER tamanhoBarco (falar com o vitor pois ele sabe sobre)
            if (matriz[k][j] == symbol) countShips++;
        }
        return countShips;
    }

    //VERIFICA SE HÁ PARTES DE UM BARCO NA LINHA DESEJADA
    public static boolean verifBarcoLinha(int i, int j,int tamanhoBarco, String[][] matriz){
        return contaPartesBarcoLinha(i, j,tamanhoBarco,"#", matriz) != 0;
    }


    //CONTA O NUMERO DE PARTES DE UM NAVIO NA COLUNA DESEJADA
    public static int contaPartesBarcoLinha(int i, int j, int tamanhoBarco,String symbol, String[][] matriz){
        int countShipParts = 0;
        for(int k = j; k < (j + tamanhoBarco); k++){
            if(matriz[i][k] == symbol) countShipParts++;
        }
        return countShipParts;
    }

    //POSICIONA O BARCO NA HORIZONTAL OU NA VERTICAL, NO ALEATORIO, PARA ADAPTAR
    public static void putShip(int i, int j, boolean isRow, int tamanhoBarco, String[][] matriz){
        for(int k = i; k < (i + tamanhoBarco); k++){
            if (isRow){
                matriz[j][k] = "🚢";
            }else{
                matriz[k][j] = "🚢";
            }
        }
    }


    //OCULTA A POSIÇÃO DAS PARTES DOS BARCOS, COLOCANDO O FUNDO AZUL POR CIMA
    public static void ocultaParteBarco(String [] tab){
        for (int i = 0; i<tab.length; i++){
            if (tab[i] == "🚢"){
                System.out.print("🔵 ");
            }else{

                System.out.print(tab[i] + " ");
            }
        }
        System.out.println();
    }

    //IMPRIME O TABULEIRO E COLOCA AS COORDENADAS NA ESQUERDA DO TABULEIRO
    public static void mostrarTabuleiroComputador(String[][] matriz){
        System.out.println("TABULEIRO DO COMPUTADOR");

        for (int i = 0; i < 10; i++){
            System.out.print((char)(i+65) + " |");
            ocultaParteBarco(matriz[i]);
        }
        printBottomLineBoard();
    }
    public static void mostrarTabuleiroPlayer(String[][] matriz){
        int k = 0;
        System.out.println();
        System.out.println("SEU TABULEIRO");
        for (int i = 0; i< TAMLIN; i++){

            System.out.print((char)(k+65) + " |");
            k++;
            for (int j = 0; j < TAMCOL; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        printBottomLineBoard();
    }
    //COLOCA AS COORDENADAS EMBAIXO DO TABULEIRO
    public static void printBottomLineBoard(){
        System.out.println("    0  1  2  3  4   5  6  7  8  9");
        System.out.println("\n");
    }


    //
    public static void iniciarJogo(){
        int round = 0;
        int partShipsSunk = 0;
        int botShipsSunk = 0;
        do{
            readCoordinates(matrizTabuleiroJ1);

            System.out.println("-----------------------------------" +
                    "-------------------");
            System.out.printf("Tentativa: %d \n -----------------", ++round);

            throwBomb();
            throwBombComputer();
            mostrarTabuleiroPlayer(matrizTabuleiroJ1);
            mostrarTabuleiroComputador(matrizTabuleiro);

            if(matrizTabuleiro[linha][coluna].equals("⛵")) botShipsSunk++;
            if(matrizTabuleiroJ1[bombLin][bombCol].equals("❌")) partShipsSunk++;

            if(partShipsSunk == 20) {
                System.out.println("Você perdeu!\nNúmero de tentativas"+ round);
                break;
            }
            if (botShipsSunk == 20) {
                System.out.println("Parabéns, você venceu!\nNúmero de tentativas: " + round);
                break;
            }
        }while(true);

    }

    public static void readCoordinates(String[][] matriz){
        do {
            System.out.println("Digite as coordenadas da sua jogada separadas por enter ou espaço: (Ex: B 4)");
            linha = ler.next().toUpperCase().charAt(0) - 65;
            coluna = ler.next().charAt(0) - 48;
        }while(isCoordinatesInvalid(matriz));
    }

    public static boolean isCoordinatesInvalid(String[][] matriz){
        if(linha<0 || linha >= 10 || coluna < 0 || coluna >= 10){
            System.out.println("Coordenadas inválidas!");
            return true;
        }
        return false;
    }

    public static void throwBomb(){

        System.out.println();
        while(matrizTabuleiro[linha][coluna].equals("🔴") || matrizTabuleiro[linha][coluna].equals("⛵")){
            mostrarTabuleiroComputador(matrizTabuleiro);

            System.out.println("Não é possível jogar duas bombas no mesmo lugar!");
            readCoordinates(matrizTabuleiroJ1);

        }

        if(matrizTabuleiro[linha][coluna].equals("🔵")){
            matrizTabuleiro[linha][coluna] = "🔴";
        }
        else {
            matrizTabuleiro[linha][coluna] = "⛵";
        }

    }
    public static void throwBombComputer(){
        bombLin = aleatorio.nextInt(tamanhoTabuleiro);
        bombCol = aleatorio.nextInt(tamanhoTabuleiro);
        if (matrizTabuleiroJ1[bombLin][bombCol].equals("🔵")) {
            matrizTabuleiroJ1[bombLin][bombCol] = "🔴";
        } else if(matrizTabuleiroJ1[bombLin][bombCol].equals("🚢")){
            matrizTabuleiroJ1[bombLin][bombCol] = "❌";
        }

    }

}
