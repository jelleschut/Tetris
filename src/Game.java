import javax.swing.*;

public class Game {
    private JFrame f;
    private Scoreboard scoreboard;
    private Field field;
    private Controller controller;

    Game() {
        f = new JFrame();
        scoreboard = new Scoreboard();
        field = new Field(200, 400, 20);
        controller = new Controller(field);

        f.addKeyListener(controller);
        f.add(scoreboard);
        f.add(field);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
