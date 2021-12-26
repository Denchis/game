package game;


public class WinModel {

    public static String getWinner(int indexOfBotMove, int indexOfPlayerMove, int numOfMoves){
        if (indexOfBotMove == indexOfPlayerMove){
            return Game.A_DRAW;
        }
        int i = indexOfBotMove;
        //System.out.println((numOfMoves/2) % numOfMoves);
        while (i != (indexOfBotMove+numOfMoves/2) % numOfMoves){
            if (i == indexOfPlayerMove ){
                return Game.PLAYER_WIN;
            }
            i = (i + 1) % numOfMoves;
        }
        if (i == indexOfPlayerMove ){
            return Game.PLAYER_WIN;
        }
        i = (i + 1) % numOfMoves;
        return Game.BOT_WIN;
    }
}
