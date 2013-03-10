package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class ChessGrid extends JPanel {
	
	private Dimension size;
	Graphics g = null;
	Graphics2D g2d = null;

	public ChessGrid(Dimension size) {
		// TODO Auto-generated constructor stub
	
		this.setLayout(null);
		this.setOpaque(true);
		this.size = size;
		setSize(size);
		this.setBackground(Color.white);
	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		this.setOpaque(true);
			g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int width = 2;
			g2d.setColor(Color.gray);
			g2d.setStroke(new BasicStroke(width));

			Font font1 = new Font("HELVETICA", Font.BOLD, (int) (Math.min(Math.max(
					11, 300 / 8), 40)));
			g2d.setFont(font1);

			Integer height = null;
			
			// HORIZONTAL LINES
			for (Integer i = 0; i < 9; i++) {
				
				height = (int) (size.getHeight() - 100 - i
						* ((size.getHeight() -200) / ((double) 8.0)));
				
				g2d.drawLine(100, height, (int)size.getWidth() -100, height);

			// DRAW NUMBERS
				if (i == 8) {
					break;
				}
				g2d
						.drawString(
								new Integer(i+1).toString(),
								50,
								height);
			}

			width = 2;
			g2d.setColor(Color.gray);
			g2d.setStroke(new BasicStroke(width));

			// VERTICAL LINES
			Integer gridwidth = null;
			
			for (Integer i = 0; i < 9; i++) {
				
				gridwidth = (int) (size.getWidth() - 100 - i* ((size.getWidth()-200) / ((double) 8.0)));
				
				g2d.drawLine(gridwidth, 
						(int)size.getHeight() - 100, 
						gridwidth, 
						100);
				
				if (i == 8) {
					break;
				}
				
				g2d
				.drawString(
						Character.toString((char)((72-i))),
						gridwidth-50,
						this.getHeight()-5);
			}
			
			g.finalize();
			g2d.finalize();
		}
	



}
