package de.beyondjava.demos.bytecode;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class CircleWithoutImplementation extends JPanel  {
	private static final long serialVersionUID = 1L;
	
    public CircleWithoutImplementation()
    {
        JFrame frmMain = new JFrame();
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setSize(440, 420);
        
        frmMain.add(this);
        frmMain.setVisible(true);
    }

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int y = 0; y < 71; y++) {
			calculateAndDrawPixel(g, y);
		}
	}

	private void draw8Pixels(int x, int y, Graphics g) {
		drawPixel(x+200, y+200, g);
		drawPixel(200-x, y+200, g);
		drawPixel(x+200, 200-y, g);
		drawPixel(200-x, 200-y, g);
		drawPixel(y+200, x+200, g);
		drawPixel(200-y, x+200, g);
		drawPixel(y+200, 200-x, g);
		drawPixel(200-y, 200-x, g);
	}

	private void drawPixel(int x, int y, Graphics g) {
		g.drawLine(x, y, x, y);
	}
	
	private void calculateAndDrawPixel(Graphics g, int y) {
	};

}
