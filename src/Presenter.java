import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Presenter extends JFrame{
    private static int rows;
    private static int columns;
    private static BoardModel gameBoardModel;
    public Presenter(int rows_input, int columns_input,BoardModel boardModel)
    {
        rows = rows_input;
        columns = columns_input;

        gameBoardModel = boardModel;

        setTitle("4 IN A ROW");
        setBounds(300,90,900,600);
        setBackground(Color.gray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        BoardPaint myGraphicBoard = new BoardPaint();
        getContentPane().add(myGraphicBoard);

        setVisible(true);
    }

    public static int getRows()
    {
        return rows;
    }
    public static int getColumns()
    {
        return columns;
    }

    public static class BoardPaint extends JPanel {
        private static final int ROWS = getRows();
        private static final int COLS = getColumns();
        private static final int CIRCLE_SIZE = 50; // Diameter of each circle
        private static final int CELL_SIZE = CIRCLE_SIZE + 10; // Size of each cell (circle + gap)
        private JButton[] columnButtons;
        public void GameBoard() {
            setLayout(new BorderLayout());

            // Create buttons for each column
            JPanel buttonPanel = new JPanel(new GridLayout(1, COLS));
            columnButtons = new JButton[COLS];
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton("Add");
                int column = col; // For using in lambda
                button.addActionListener(e -> addCircle(column));
                buttonPanel.add(button);
                columnButtons[col] = button;
            }
            add(buttonPanel, BorderLayout.SOUTH);
        }

        private void addCircle(int column) {
            System.out.println("Adding circle to column " + column);
            // Add your logic here to add a circle to the specified column
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
                    drawCircle(g, x, y);
                }
            }
        }

        private void drawCircle(Graphics g, int x, int y) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
        }
    }
}