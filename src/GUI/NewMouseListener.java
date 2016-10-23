package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public abstract class NewMouseListener implements MouseListener{
    private boolean enabled=true;
   @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public abstract void mouseEntered(MouseEvent e);

    @Override
    public abstract void mouseExited(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled(){
        return enabled;
    }
}
