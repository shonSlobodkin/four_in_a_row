// import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class View implements IView{

    private final int ROWS;
    private final int COLS;
    private Presenter presenterApp;
    private Scanner sc = new Scanner(System.in);
    //private int currentPlayer;
    public View(Presenter inputPresenter, int rows, int cols)
    {
        inputPresenter.setView(this);
        this.ROWS = rows;
        this.COLS = cols;
        this.presenterApp = inputPresenter;
        this.presenterApp.openApplication();
        this.newGameView();
    }
    private void runGame()
    {
        int gameOver = 0, newGame;
        while(gameOver!=1)
        {
            gameOver = this.continueGame();
        }
        System.out.println("\nDO YOU WISH TO PLAY AGAIN? (YES - Press 1, NO - Press 0)");
        newGame = sc.nextInt();
        switch (newGame)
        {
            case 1:
                this.newGameView();
                break;
            case 0:
                this.presenterApp.closeApplication();

        }

    }
    private int continueGame()
    {
        int currentPlayer = this.presenterApp.getCurrentPlayer();
        switch (currentPlayer)
        {
            case 1:
                System.out.println("\033[0;31mPlayer: " + this.presenterApp.getCurrentPlayer() + " Enter column: \033[0m");
                break;
            case 2:
                System.out.println("\033[0;32mPlayer: " + this.presenterApp.getCurrentPlayer() + " Enter column: \033[0m");
        }
        int column = this.sc.nextInt();
        return this.presenterApp.userChoice(column);
    }
    @Override
    public void updateUIBoard(int var1, int var2, char var3) {

    }

    @Override
    public void displayMessage(String var1) {
        System.out.println(var1);
    }

    @Override
    public void displayEndOfGameMessage(String var1) {
        System.out.println(var1);
    }

    @Override
    public void displayBoard() {

    }

    @Override
    public void newGameView() {
        this.presenterApp.newGame();
        this.displayMessage("\nWELCOME TO 4 IN A ROW!\nSIMPLY BEGIN PLAYING BY INPUTTING COLUMN TO FILL!\nEACH PLAYER (1/2) AT ONE TIME\nHAVE FUN:)\n");
        this.runGame();
    }
}
