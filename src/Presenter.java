//import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Presenter extends JFrame{
    private static int rows; // How many Rows to display
    private static int columns; // How many Columns to display
    private static BoardModel gameBoardModel; // Game Board Model Class to be linked with this Presenter Application
    private View gameView; // Game View Class to be linked with this Presenter Application
    private final BoardPaint myGraphicBoard; // Inner class that displays live game board
    private int currentPlayer; // Current Player Playing
    public Presenter(BoardModel boardModel)
    {
        // Set board size by input boardModel object's settings
        rows = boardModel.getRows();
        columns = boardModel.getColumns();

        // Link the boarModel object to this Presenter Application
        gameBoardModel = boardModel;

        // Init the current Player counter to 0
        this.currentPlayer = 0;

        // Create a BoardPaint inner class object to later display the gameBoard
        this.myGraphicBoard = new BoardPaint();
        getContentPane().add(myGraphicBoard);
    }

    /** Opens the Application Display Window for the first time */
    public void openApplication()
    {
        setTitle("4 IN A ROW"); // Set Window Title
        setBounds(300,90,900,600); // Set Window size
        setBackground(Color.gray); // Set Window background color
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set Window default close operation
        setResizable(false); // Set Window resizeable parameter
        setVisible(true); // Set Window to bee seen - VISIBLE
    }
    /** Closes the Application Window and ends the Presenter Object Process */
    public void closeApplication()
    {
        setVisible(false); // Close Presenter Application Window
        System.exit(0); // Completely Stop The Presenter Application Process
    }
    /** Link the view class to Presenter Application class*/
    public void setView(View inputView)
    {
        this.gameView = inputView;
    }

    /** Link the view class to Presenter Application class*/
    public int userChoice(int column)
    {
        int playerWon = 0;
        if(gameBoardModel.checkValidColumn(column) && !gameBoardModel.checkIfColumnFull(column)) {
            myGraphicBoard.addCircle(gameBoardModel.getNextFreeSpace(column),column,currentPlayer%2+1);
            playerWon = gameBoardModel.addCircleToColumn(column,currentPlayer%2+1);
            if(playerWon==0)
            {
                this.currentPlayer++;
            }
            else {
                gameView.displayEndOfGameMessage("PLAYER: " + playerWon + " WON THE GAME!!!");
                return 1;
            }
        }
        else {
            gameView.displayMessage("\033[0;31mERROR, tried inserting circle in a FULL column or in a columns OUT OF INDEX!\nTry other columns!\033[0m");
        }
        return 0;
    }
    public int getCurrentPlayer()
    {
        return this.currentPlayer%2+1;
    }
    public void newGame()
    {
        this.myGraphicBoard.initColorBoard();
        this.currentPlayer = 0;
        gameBoardModel.initGame();
    }
    public static class BoardPaint extends JPanel {
        private BoardModel getGameBoardModel;
        private static final int ROWS = gameBoardModel.getRows();
        private static final int COLS = gameBoardModel.getColumns();
        private static final int CIRCLE_SIZE = 50; // Diameter of each circle
        private static final int CELL_SIZE = CIRCLE_SIZE + 10; // Size of each cell (circle + gap)
        private final Color[][] circleColors = new Color[ROWS][COLS];

        public BoardPaint()
        {
            setLayout(new BorderLayout());
            this.initColorBoard();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Calculate the starting position for the game board
            int startX = (getWidth() - columns * CELL_SIZE) / 2;
            int startY = (getHeight() - rows * CELL_SIZE) / 2;

            // Draw the circles for the game board
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    int x = startX + col * CELL_SIZE;
                    int y = startY + row * CELL_SIZE;
                    drawCircle(g, x, y,circleColors[row][col]);
                }
            }
        }

        private void initColorBoard()
        {
            // Initialize the array to store circle colors
            for(int i = 0; i< rows; i++)
            {
                for(int j = 0; j<columns;j++)
                {
                    this.circleColors[i][j]=Color.white;
                }
            }
            repaint();
        }
        private void drawCircle(Graphics g, int x, int y, Color circleColor) {
            g.setColor(circleColor); // Color.WHITE
            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
            g.setColor(Color.WHITE);
            g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
        }
        private void addCircle(int row, int column, int player) {
            switch (player)
            {
                case 1:
                    circleColors[row][column-1] = Color.RED; // Set the color to red
                    repaint(); // Redraw the panel to reflect the change
                    break;
                case 2:
                    circleColors[row][column-1] = Color.GREEN; // Set the color to red
                    repaint(); // Redraw the panel to reflect the change
            }
        }
    }
}