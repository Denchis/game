package game;

import dnl.utils.text.table.TextTable;

public class View {
    public static void printMenu(String[] moves){
        System.out.println("Available moves:");
        for (int i = 0; i < moves.length;i++){
            System.out.println(i+1 + " - " + moves[i]);
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
    }
    public static void printHelp(String[] moves){
        String[][] data = new String[moves.length+1][moves.length+1];
        String[] s = new String[moves.length+1];
        s[0] = "You\\/ Bot->";
        for (int i = 0; i < moves.length;i++){
            s[i+1] = moves[i];
        }
        for (int i = 0; i < moves.length;i++){
            data[i][0] = moves[i];
            for (int j = 1; j < moves.length+1;j++){
                if (WinModel.getWinner(j-1,i, moves.length).equals(Game.PLAYER_WIN)){
                    data[i][j] = "Win";
                } else if (WinModel.getWinner(j-1,i, moves.length).equals(Game.BOT_WIN)){
                    data[i][j] = "Lose";
                }else{
                    data[i][j] = "Draw";
                }
            }
        }
        TextTable tt = new TextTable(s,data);
        tt.printTable();
    }
}
