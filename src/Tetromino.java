import java.awt.*;

public enum Tetromino {
    NO_TETROMINO(new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}}, new Color(255, 255, 255)),
    ITETROMINO(new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}, new Color(0, 255, 255)),
    OTETROMINO(new int[][]{{0, -1}, {1, -1}, {0, 0}, {1, 0}}, new Color(255, 255, 0)),
    TTETROMINO(new int[][]{{0, -1}, {-1, 0}, {0, 0}, {1, 0}}, new Color(255, 0, 255)),
    ZTETROMINO(new int[][]{{1, -1}, {0, 0}, {1, 0}, {0, 1}}, new Color(255, 0, 0)),
    STETROMINO(new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}}, new Color(0, 255, 0)),
    LTETROMINO(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}, new Color(0, 0, 255)),
    JTETROMINO(new int[][]{{0, -1}, {1, -1}, {0, 0}, {0, 1}}, new Color(255, 165, 0));

    public int[][] coordinates;
    public Color color;

    Tetromino(int[][] coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

}
