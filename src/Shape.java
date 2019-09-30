import java.awt.*;
import java.util.Random;

public class Shape {
    private Tetromino shape;
    private int centerX;
    private int centerY;
    private int[][] coordinates;

    Shape() {
        coordinates = new int[4][2];
        centerX = 0;
        centerY = 0;
        setShape(Tetromino.NO_TETROMINO);
    }

    public void setShape(Tetromino shape) {
        this.shape = shape;
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                coordinates[i][j] = shape.coordinates[i][j];
            }
        }
    }

    public Tetromino getShape() {
        return shape;
    }

    public void setRandomShape() {
        Random r = new Random();

        switch (r.nextInt(7)) {
            case 0:
                setShape(Tetromino.ITETROMINO);
                break;
            case 1:
                setShape(Tetromino.OTETROMINO);
                break;
            case 2:
                setShape(Tetromino.TTETROMINO);
                break;
            case 3:
                setShape(Tetromino.ZTETROMINO);
                break;
            case 4:
                setShape(Tetromino.STETROMINO);
                break;
            case 5:
                setShape(Tetromino.LTETROMINO);
                break;
            case 6:
                setShape(Tetromino.JTETROMINO);
            default:
                break;
        }
    }

    public void moveLeft() {
        centerX--;
    }

    public void moveRight() {
        centerX++;
    }

    public void moveDown() {
        centerY++;
    }

    public void rotateRight() {
        if(shape != Tetromino.OTETROMINO) {
            for (int i = 0; i < length(); i++) {
                int temp = coordinates[i][0];
                coordinates[i][0] = -coordinates[i][1];
                coordinates[i][1] = temp;
            }
        }
    }
    public void rotateLeft() {
        if(shape != Tetromino.OTETROMINO) {
            for (int i = 0; i < length(); i++) {
                int temp = coordinates[i][0];
                coordinates[i][0] = coordinates[i][1];
                coordinates[i][1] = -temp;
            }
        }
    }

    public int bottomY() {
        int max = 0;
        for (int i = 0; i < length(); i++) {
            max = Math.max(max, coordinates[i][1]);
        }
        return max + centerY;
    }

    public int topY() {
        int max = 0;
        for (int i = 0; i < length(); i++) {
            max = Math.min(max, coordinates[i][1]);
        }
        return max + centerY;
    }

    public int rightX() {
        int max = 0;
        for (int i = 0; i < length(); i++) {
            max = Math.max(max, coordinates[i][0]);
        }
        return max + centerX;
    }

    public int leftX() {
        int max = 0;
        for (int i = 0; i < length(); i++) {
            max = Math.min(max, coordinates[i][0]);
        }
        return max + centerX;
    }

    public int getXCoordinate(int i) {
        return coordinates[i][0] + centerX;
    }

    public int getYCoordinate(int i) {
        return coordinates[i][1] + centerY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Color getColor() {
        return shape.color;
    }

    public int length() {
        return coordinates.length;
    }


    public int getXOffset(int i) {
        return coordinates[i][0];
    }

    public int getYOffset(int i) {
        return coordinates[i][1];
    }
}
