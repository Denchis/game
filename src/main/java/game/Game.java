package game;

import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static final String BOT_WIN = "Computer win!";
    public static final String PLAYER_WIN = "You win!";
    public static final String A_DRAW = "It's a draw!";
    public static final int REQUEST_HELP = -1;
    public static final int OKEY = -2;
    public static final int ILLEGAL_MOVE = -3;
    private int numOfMoves;
    private String[] moves;
    private SecretKey hmacKey;
    public Game(String[] moves){
        this.numOfMoves = moves.length;
        this.moves = moves;
    }
    public int BotMove() throws NoSuchAlgorithmException, InvalidKeyException {
        hmacKey = UtilsHmac.generateHmacKey();
        Random random = new Random();
        int indexOfBotMove = random.nextInt(numOfMoves);
        String botMove = moves[indexOfBotMove];
        byte[] hmac = UtilsHmac.getHmac(botMove,hmacKey);
        System.out.println(UtilsHmac.bytesToHex(hmac));
        return indexOfBotMove;

    }
    public int playerMove(int indexOfBotMove){
        View.printMenu(moves);
        System.out.println("Enter your move: ");
        int indexOfPlayerMove = checkInputMove();
        if (indexOfPlayerMove == REQUEST_HELP){
            return REQUEST_HELP;
        }
        if (indexOfPlayerMove == ILLEGAL_MOVE){
            return ILLEGAL_MOVE;
        }
        System.out.println("Your move: " + moves[indexOfPlayerMove]);
        System.out.println("Computer move: " + moves[indexOfBotMove]);
        System.out.println(WinModel.getWinner(indexOfBotMove,indexOfPlayerMove,numOfMoves));
        System.out.println("HMAC key: \n" + UtilsHmac.bytesToHex(hmacKey.getEncoded()));
        return OKEY;
    }
    public void start() throws NoSuchAlgorithmException, InvalidKeyException {
        int indexOfBotMove = BotMove();
        int status = playerMove(indexOfBotMove);
        while(status != OKEY) {
            if (status == ILLEGAL_MOVE) {
                System.out.println("Illegal argument! Please enter the characters from the menu");
                status = playerMove(indexOfBotMove);
            }
            if (status == REQUEST_HELP) {
                View.printHelp(moves);
                status = playerMove(indexOfBotMove);
            }
        }
    }

    private int checkInputMove(){
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()){
            if (scanner.next().charAt(0) == '?'){
                return REQUEST_HELP;
            }else {
                return ILLEGAL_MOVE;
            }
        }
        int indexOfPlayerMove = scanner.nextInt();
        if (indexOfPlayerMove == 0){
            System.exit(0);
        }
        if (indexOfPlayerMove > numOfMoves){
            return ILLEGAL_MOVE;
        }
        return indexOfPlayerMove-1;
    }
}
