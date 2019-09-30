import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private int width;
    private int height;
    private int squareSize;
    private int tetroCount;
    private int delay;
    private Shape playerShape;

    private Tetromino[][] field;

    Field(int width, int height, int squareSize) {
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;
        this.field = new Tetromino[width / squareSize][height / squareSize];
        this.tetroCount = 0;
        this.delay = 400;

        playerShape = new Shape();

        clearBoard();
        newTetromino();

        setPreferredSize(new Dimension(width, height));
    }

    private void clearBoard() {
        for (int i = 0; i < width / squareSize; i++) {
            for (int j = 0; j < height / squareSize; j++) {
                field[i][j] = Tetromino.NO_TETROMINO;
            }
        }
    }

    public boolean gameOver() {
        for (int i = 0; i < playerShape.length(); i++) {
            int x = playerShape.getXCoordinate(i);
            int y = playerShape.getYCoordinate(i);
            if (field[x][y] != Tetromino.NO_TETROMINO) {
                return true;
            }
        }
        return false;
    }

    private void newTetromino() {
        playerShape.setRandomShape();
        if (playerShape.getShape() != Tetromino.LTETROMINO) {
            playerShape.setCenterX(((width / squareSize) / 2) - 1);
        } else {
            playerShape.setCenterX((width / squareSize) / 2);
        }

        tetroCount++;
        if (tetroCount % 20 == 0) {
            updateDelay();
        }

        System.out.println(tetroCount);
        System.out.println(delay);

        playerShape.setCenterY(1);
        repaint();
    }

    private void updateDelay() {
        delay -= 25;
        if (delay <= 100) {
            delay = 100;
        }
    }


    public boolean downCollision() {
        for (int i = 0; i < playerShape.length(); i++) {
            int x = playerShape.getXCoordinate(i);
            int y = playerShape.getYCoordinate(i);

            if (y >= (height / squareSize) - 1 || field[x][y + 1] != Tetromino.NO_TETROMINO) {
                for (int j = 0; j < playerShape.length(); j++) {
                    int a = playerShape.getXCoordinate(j);
                    int b = playerShape.getYCoordinate(j);
                    field[a][b] = playerShape.getShape();
                }

                checkFullLine(playerShape.bottomY());
                newTetromino();

                return true;
            }
        }
        return false;
    }

    public boolean leftCollision() {

        if (playerShape.leftX() <= 0) {
            return true;
        }

        for (int i = 0; i < playerShape.length(); i++) {
            int x = playerShape.getXCoordinate(i);
            int y = playerShape.getYCoordinate(i);
            if (field[x - 1][y] != Tetromino.NO_TETROMINO) {
                return true;
            }
        }
        return false;
    }

    public boolean rightCollision() {
        if (playerShape.rightX() >= (width / squareSize) - 1) {
            return true;
        }

        for (int i = 0; i < playerShape.length(); i++) {
            int x = playerShape.getXCoordinate(i);
            int y = playerShape.getYCoordinate(i);
            if (field[x + 1][y] != Tetromino.NO_TETROMINO) {
                return true;
            }
        }
        return false;
    }

    public boolean rotationCollision() {
        if (playerShape.rightX() >= (width / squareSize) || playerShape.leftX() < 0) {
            return true;
        }

        for (int i = 0; i < playerShape.length(); i++) {
            int x = playerShape.getXCoordinate(i);
            int y = playerShape.getYCoordinate(i);
            if (field[x][y] != Tetromino.NO_TETROMINO) {
                return true;
            }
        }
        return false;
    }

    public void tryMoveLeft() {
        if (!leftCollision()) {
            playerShape.moveLeft();
            repaint();
        }
    }

    public void tryMoveRight() {
        if (!rightCollision()) {
            playerShape.moveRight();
            repaint();
        }
    }

    public void tryMoveDown() {
        if (!downCollision()) {
            playerShape.moveDown();
            repaint();
        }
    }

    public void tryRotate() {
        playerShape.rotateRight();
        if (rotationCollision()) {
            playerShape.rotateLeft();
        }
        repaint();
    }

    public void checkFullLine(int line) {
        boolean[] fullLines = {false, false, false, false};

        for (int i = line; i > Math.max(line - 4, 0); i--) {
            for (int j = 0; j < width / squareSize; j++) {
                if (field[j][i] == Tetromino.NO_TETROMINO) {
                    break;
                }
                if (j == width / squareSize - 1) {
                    fullLines[line - i] = true;
                }
            }
        }

        for (boolean fullLine : fullLines) {
            if (fullLine) {
                removeFullLines(line, fullLines);
                break;
            }
        }
    }

    private void removeFullLines(int line, boolean[] fullLine) {
        int count = 0;

        for (int i = 0; i < fullLine.length; i++) {
            if (fullLine[i]) {
                for (int j = line - i + count; j >= 1; j--) {
                    for (int k = 0; k < width / squareSize; k++) {
                        field[k][j] = field[k][j - 1];
                    }
                }
                count++;
            }
        }
    }

    public void paint(Graphics g) {
        drawBackground(g);
        drawGrid(g);
        drawField(g);
        drawTetromino(g);
        drawOutline(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    private void drawGrid(Graphics g) {
        int widthSquares = width / squareSize;
        int heightSquares = height / squareSize;

        g.setColor(new Color(176, 176, 176));
        for (int i = 0; i < widthSquares; i++) {
            g.drawLine(i * squareSize, 0, i * squareSize, height);
        }

        for (int i = 0; i < heightSquares; i++) {
            g.drawLine(0, i * squareSize, width, i * squareSize);
        }
    }

    private void drawField(Graphics g) {
        for (int i = 0; i < width / squareSize; i++) {
            for (int j = 0; j < height / squareSize; j++) {
                Tetromino currentTetro = field[i][j];
                if (currentTetro != Tetromino.NO_TETROMINO) {
                    g.setColor(currentTetro.color);
                    g.fillRect(i * squareSize + 1, j * squareSize + 1, squareSize - 2, squareSize - 2);
                }
            }
        }
    }

    private void drawOutline(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(0, 0, width, height);
    }

    private void drawTetromino(Graphics g) {
        g.setColor(playerShape.getColor());
        for (int i = 0; i < playerShape.length(); i++) {
            int x = playerShape.getXCoordinate(i);
            int y = playerShape.getYCoordinate(i);
            g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
        }
    }

    public int getDelay() {
        return delay;
    }

    public Shape getPlayerShape() {
        return playerShape;
    }
}
