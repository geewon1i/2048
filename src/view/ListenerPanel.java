package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * This class is to enable key events.
 *
 */
public abstract class ListenerPanel extends JPanel implements Serializable {
    private static final long serialVersionUID = -5040750448393930848L;
    public ListenerPanel() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        this.setFocusable(true);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> doMoveRight();
                //todo: complete other move event
            }
        }
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> doMoveLeft();
                //todo: complete other move event
            }
        }
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> doMoveUp();
                //todo: complete other move event
            }
        }
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> doMoveDown();
                //todo: complete other move event
            }
        }
    }



    public abstract void doMoveRight();
    public abstract void doMoveLeft();
    public abstract void doMoveDown();
    public abstract void doMoveUp();

}
