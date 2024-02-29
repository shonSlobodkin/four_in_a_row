import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Presenter extends JFrame{
    private static int rows;
    private static int columns;
    private static BoardModel gameBoardModel;
    private BoardPaint myGraphicBoard;
    private View gameView;
    public Presenter(BoardModel boardModel)
    {
        gameBoardModel = boardModel;
        rows = boardModel.getRows();
        columns = boardModel.getColumns();

        setTitle("4 IN A ROW");
        setBounds(300,90,900,600);
        setBackground(Color.gray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        myGraphicBoard = new BoardPaint();
        getContentPane().add(myGraphicBoard);

        setVisible(true);
    }
    public void updateView(View inputView)
    {
        this.gameView = inputView;
    }

    public void userChoice(int column, int player)
    {
        if(!gameBoardModel.checkIfColumnFull(column)) {
            myGraphicBoard.addCircle(gameBoardModel.getNextFreeSpace(column),column,player);
            gameBoardModel.addCircleToColumn(column,player);
            gameView.displayMessage("PRINTED SUCCESSFULLY!");
        }
        else {
            gameView.displayMessage("ERROR, tried inserting circle in a FULL column!\nTry other columns!");
        }
    }
    public static class BoardPaint extends JPanel {
        private static final int ROWS = gameBoardModel.getRows();
        private static final int COLS = gameBoardModel.getColumns();
        private static final int CIRCLE_SIZE = 50; // Diameter of each circle
        private static final int CELL_SIZE = CIRCLE_SIZE + 10; // Size of each cell (circle + gap)
        private Color[][] circleColors = new Color[ROWS][COLS];

        public BoardPaint()
        {
            setLayout(new BorderLayout());

            // Initialize the array to store circle colors
            this.initCircles();
            //for(Color[] carr : circleColors)
            //{
            //    for(Color c : carr)
            //    {
            //        c = Color.YELLOW;
           //     }
          //  }
        }
        private void initCircles()
        {
            for(int i = 0; i< rows; i++)
            {
                for(int j = 0; j<columns;j++)
                {
                    this.circleColors[i][j]=Color.cyan;
                }
            }
            /*for(Color[] carr : circleColors)
            {
                for(Color c : carr)
                {
                    c = Color.YELLOW;
                }
            }*/
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

        private void drawCircle(Graphics g, int x, int y, Color circleColor) {
            g.setColor(circleColor); // Color.WHITE
            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
            g.setColor(Color.WHITE);
            g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
        }
        private void addCircle(int row, int column, int player) {
            switch (player+1)
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