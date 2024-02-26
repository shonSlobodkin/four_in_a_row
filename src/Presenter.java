import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Presenter extends JFrame{
    public Presenter()
    {

        setTitle("4 IN A ROW");
        setBounds(300,90,900,600);
        setBackground(Color.gray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        BoardPaint myGraphicBoard = new BoardPaint();
        getContentPane().add(myGraphicBoard);

        setVisible(true);
    }

    public class BoardPaint extends JPanel {
        private static final int ROWS = 6;
        private static final int COLS = 7;
        private static final int CIRCLE_SIZE = 50; // Diameter of each circle
        private static final int CELL_SIZE = CIRCLE_SIZE + 10; // Size of each cell (circle + gap)

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Calculate the starting position for the game board
            int startX = (getWidth() - COLS * CELL_SIZE) / 2;
            int startY = (getHeight() - ROWS * CELL_SIZE) / 2;

            // Draw the circles for the game board
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
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