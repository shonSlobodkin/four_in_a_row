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

    /** Presenter Class constructor method
     * @param boardModel The BoardModel Class object to be linked with new Presenter Class Object*/
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

    /** Method that Opens the Application Display Window for the first time */
    public void openApplication()
    {
        setTitle("4 IN A ROW"); // Set Window Title
        setBounds(300,90,900,600); // Set Window size
        setBackground(Color.gray); // Set Window background color
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set Window default close operation
        setResizable(false); // Set Window resizeable parameter
        setVisible(true); // Set Window to bee seen - VISIBLE
    }

    /** Closes the Application Window and ends the Presenter Class Object Process */
    public void closeApplication()
    {
        setVisible(false); // Close Presenter Application Window
        System.exit(0); // Completely Stop The Presenter Application Process
    }

    /** Link the View Class object to Presenter Class object
     * @param inputView The View Class object to be linked with this Presenter Class object*/
    public void setView(View inputView)
    {
        this.gameView = inputView;
    }

    /** Performs user choice move and updates both logical and graphic game board.
     * <br>
     * Method also checks with BoardModel class if user choice move is legal.
     * @param column The column chosen by the current player */
    public int userChoice(int column)
    {
        // Init PlayerWon to 0. Variable will be updated if a winning move was done by one of players.
        int playerWon = 0;

        // Check if column input was VALID input (if column input is VALID index)
        if(gameBoardModel.checkValidColumn(column) && !gameBoardModel.checkIfColumnFull(column)) {

            // Add to the graphic circle board the new circle at the right position
            myGraphicBoard.addCircle(gameBoardModel.getNextFreeSpace(column),column,currentPlayer%2+1);

            // Update the logic gameBoard with current player's move and check for a possible win for current player.
            playerWon = gameBoardModel.addCircleToColumn(column,currentPlayer%2+1);
            if(playerWon==0)
            {
                // Set next move to be done by the other player.
                this.currentPlayer++;
            }
            else {
                // Display message that current player has Won.
                gameView.displayEndOfGameMessage("PLAYER: " + playerWon + " WON THE GAME!!!");
                return 1;
            }
        }
        // Display ERROR message in case input was ILLEGAL
        else {
            gameView.displayMessage("\033[0;31mERROR, tried inserting circle in a FULL column or in a columns OUT OF INDEX!\nTry other columns!\033[0m");
        }
        return 0;
    }

    /** Returns the current player
     * @return Current Player (VALUES 1 OR 2)*/
    public int getCurrentPlayer()
    {
        return this.currentPlayer%2+1;
    }

    /** Init both the graphic board and logic board, set current player counter to 0.*/
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

        /** BoardPaint inner class constructor method.
         * <br>
         * Initializes the graphic board. */
        public BoardPaint()
        {
            setLayout(new BorderLayout());
            this.initColorBoard();
        }

        /** Displaying the graphic board on application Window
         * @param g Graphics object where board is drawn.*/
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

        /** Initialize the graphic board*/
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

        /** Draw a Circle on given position with a give color.
         * @param x X Coordinate to draw the Circle.
         * @param y Y Coordinate to draw the Circle.
         * @param circleColor The Color of the Circle.
         * @param g Graphics Object where Circle is drawn*/
        private void drawCircle(Graphics g, int x, int y, Color circleColor) {
            g.setColor(circleColor); // Color.WHITE
            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
            g.setColor(Color.WHITE);
            g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
        }

        /** Add a new circle to graphic board according to current player's move.
         * @param player Current Player, transferred to determine the color of the Circle.
         * @param row Row index of new Circle to be added to graphic board.
         * @param column Column index of new Circle to be added to graphic board.*/
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