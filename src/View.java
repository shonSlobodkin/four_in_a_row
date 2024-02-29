import java.util.Scanner;

public class View implements IView{

    private final int ROWS;
    private final int COLS;
    private Presenter presenterApp;
    private Scanner sc = new Scanner(System.in);
    private int currentPlayer;
    public View(Presenter inputPresenter, int rows, int cols)
    {
        inputPresenter.updateView(this);
        this.ROWS = rows;
        this.COLS = cols;
        this.presenterApp = inputPresenter;
        this.currentPlayer = 0;
        continueGame();
    }
    public void continueGame()
    {
        System.out.println("Enter column: ");
        int column = this.sc.nextInt();
        this.presenterApp.userChoice(column,this.currentPlayer%2);
        this.currentPlayer++;
    }
    @Override
    public void updateUIBoard(int var1, int var2, char var3) {

    }

    @Override
    public void displayMessage(String var1) {
        System.out.println(var1);
    }

    @Override
    public int displayEndOfGameMessage(String var1) {
        return 0;
    }

    @Override
    public void displayBoard() {

    }

    @Override
    public void newGameView() {

    }
}
