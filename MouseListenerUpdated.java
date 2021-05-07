package projectPart3;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseListenerUpdated implements MouseListener {
	private JLabel label;
	JPanel panel = new JPanel();

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = new JLabel(("Mouse was clicked at(" + e.getX() + "," + e.getY() + ")" ));
		panel.add(label);
		panel.setVisible(true);
		System.out.println(e.getPoint());

		
		
	}
	
	public MouseListenerUpdated(JLabel label) {
		this.label = label;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
