import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener, ActionListener {
    private Field field;
    private Timer timer;

    Controller(Field field) {
        this.field = field;

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!field.gameOver()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    field.tryMoveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    field.tryMoveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    field.tryMoveDown();
                    break;
                case KeyEvent.VK_SPACE:
                    field.tryRotate();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        field.tryMoveDown();
        field.repaint();
        timer.setDelay(field.getDelay());
        if (field.gameOver()) {
            timer.stop();
        }
    }
}
