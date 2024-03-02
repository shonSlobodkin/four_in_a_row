// import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class View implements IView{
    private final Presenter presenterApp;
    private Scanner sc = new Scanner(System.in);

    /** View Class Constructor.
     * @param inputPresenter Presenter Class Object to be linked with new View Class Object.*/
    public View(Presenter inputPresenter)
    {
        // Link input Presenter class object with this View class object
        inputPresenter.setView(this);
        this.presenterApp = inputPresenter;

        // Initialize the game.
        this.presenterApp.openApplication();
        this.newGameView();
    }

    /** Run the Game, from first move until the end of the game. (WIN or DRAW)*/
    private void runGame()
    {
        // Set gameOver flag to 0, continue running that game until flag is equal to 1.
        int gameOver = 0, newGame;

        // Run the game until gameOver == 1
        while(gameOver!=1)
        {
            gameOver = this.continueGame();
        }

        // Asking user for a new game.
        System.out.println("\nDO YOU WISH TO PLAY AGAIN? (YES - Press 1, NO - Press 0)");
        newGame = sc.nextInt();
        switch (newGame)
        {
            // Building new game
            case 1:
                this.newGameView();
                break;
            // Closing Game.
            case 0:
                this.presenterApp.closeApplication();

        }
    }

    /** Asking for current player Move.
     * @return Returning 1 in case a WIN was detected by input move, 0 otherwise.*/
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

    /** Displaying message in Project console.*/
    @Override
    public void displayMessage(String var1) {
        System.out.println(var1);
    }

    /** Displaying End Of Game message.*/
    @Override
    public void displayEndOfGameMessage(String var1) {
        System.out.println(var1);
    }

    /** Displaying first time Welcome message.*/
    @Override
    public void newGameView() {
        this.presenterApp.newGame();
        this.displayMessage("\nWELCOME TO 4 IN A ROW!\nSIMPLY BEGIN PLAYING BY INPUTTING COLUMN TO FILL!\nEACH PLAYER (1/2) AT ONE TIME\nHAVE FUN:)\n");
        this.runGame();
    }
}
