package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ChessPlayer;

import models.Bishop;
import models.ChessPiece;
import models.Horse;
import models.King;
import models.Move;
import models.Pawn;
import models.Queen;
import models.Rook;

public class ChessViewFrame extends JFrame implements Observer {

	private Dimension size = null;
	private ChessGrid chessGridPanel = null;
	private JPanel rootPanel = null;
	private JPanel headerNorthPanel = null;
	private JPanel westPanel = null;
	private JPanel eastPanel = null;
	private JPanel southPanel = null;
	private Hashtable<String, ImageIcon> pieceImgHash = new Hashtable<String, ImageIcon>();
	
//	ImageIcon imgPawnBlack;
//	ImageIcon imgRookBlack;
//	ImageIcon imgHorseBlack;
//	ImageIcon imgBishopBlack;
//	ImageIcon imgQueenBlack;
//	ImageIcon imgKingBlack;
//	ImageIcon imgPawnWhite;
//	ImageIcon imgRookWhite;
//	ImageIcon imgHorseWhite;
//	ImageIcon imgBishopWhite;
//	ImageIcon imgQueenWhite;
//	ImageIcon imgKingWhite;
//	ImageIcon imgQueenBlack;
	
	private JPanel clearPanel;
	

	public ChessViewFrame() {

		this.setSize(new Dimension(700, 700));
		size = getSize();
		this.setLayout(new BorderLayout());

		rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout());
		this.add(rootPanel);

		headerNorthPanel = new JPanel();
		headerNorthPanel.setBackground(Color.cyan);
		eastPanel = new JPanel();
		eastPanel.setBackground(Color.yellow);
		chessGridPanel = new ChessGrid(new Dimension(600, 600));
		chessGridPanel.setOpaque(true);

		southPanel = new JPanel();
		southPanel.setBackground(Color.yellow);
		westPanel = new JPanel();
		westPanel.setBackground(Color.yellow);

		rootPanel.add(headerNorthPanel, BorderLayout.NORTH);
		rootPanel.add(eastPanel, BorderLayout.EAST);
		rootPanel.add(chessGridPanel, BorderLayout.CENTER);
		rootPanel.add(westPanel, BorderLayout.WEST);
		rootPanel.add(southPanel, BorderLayout.SOUTH);

		chessGridPanel.setBackground(Color.white);

		clearPanel = new JPanel();
		clearPanel.setLayout(null);
		clearPanel.setOpaque(false);
		chessGridPanel.add(clearPanel);
		Insets insets = chessGridPanel.getInsets();

		int width = (int) chessGridPanel.getBounds().getWidth();
		int height = (int) chessGridPanel.getBounds().getHeight();

		clearPanel.setBounds(insets.left, insets.top, width, height);

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {

				double widthRatio = (((Component) e.getSource()).getBounds()
						.getWidth() - 200) / (size.width - 200);
				double heightRatio = (((Component) e.getSource()).getBounds()
						.getHeight() - 200) / (size.height - 200);
				double widthRatio2 = (((Component) e.getSource()).getBounds()
						.getWidth()) / (size.width);
				double heightRatio2 = (((Component) e.getSource()).getBounds()
						.getHeight()) / (size.height);

				size = getSize();

				rootPanel.remove(chessGridPanel);

				chessGridPanel.revalidate();

				chessGridPanel = new ChessGrid(size);

				rootPanel.add(chessGridPanel, BorderLayout.CENTER);

				chessGridPanel.add(clearPanel);
				Insets insets = chessGridPanel.getInsets();
				clearPanel.setBounds(insets.left, insets.top,
						(int) chessGridPanel.getBounds().getWidth(),
						(int) chessGridPanel.getBounds().getHeight());

				chessGridPanel.revalidate();

				Component[] jc = clearPanel.getComponents();

				for (Component c : jc) {

					c.setBounds((int) ((c.getX() - 100) * widthRatio) + 100,
							(int) ((c.getY() - 100) * heightRatio) + 100,
							(int) ((c.getWidth()) * widthRatio2),
							(int) ((c.getHeight()) * heightRatio2));

				}

				clearPanel.revalidate();
			}
		});
		
		width = 50;
		height = 50;

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/files/pawnBlack.png"));
			Image img2 = (Image) img.getScaledInstance(width, height,img.getType());
			ImageIcon imgPawnBlack = new ImageIcon(img2);
			pieceImgHash.put("PawnBlack", imgPawnBlack);

			img = ImageIO.read(new File("src/files/rookBlack.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgRookBlack = new ImageIcon(img2);
			pieceImgHash.put("RookBlack", imgRookBlack);

			img = ImageIO.read(new File("src/files/horseBlack.jpg"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgHorseBlack = new ImageIcon(img2);
			pieceImgHash.put("HorseBlack", imgHorseBlack);

			img = ImageIO.read(new File("src/files/bishopBlack.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgBishopBlack = new ImageIcon(img2);
			pieceImgHash.put("BishopBlack",imgBishopBlack);

			img = ImageIO.read(new File("src/files/queenBlack.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgQueenBlack = new ImageIcon(img2);
			pieceImgHash.put("QueenBlack",imgQueenBlack);

			img = ImageIO.read(new File("src/files/kingBlack.jpg"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgKingBlack = new ImageIcon(img2);
			pieceImgHash.put("KingBlack", imgKingBlack);

			img = ImageIO.read(new File("src/files/pawnWhite.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgPawnWhite = new ImageIcon(img2);
			pieceImgHash.put("PawnWhite", imgPawnWhite);

			img = ImageIO.read(new File("src/files/rookWhite.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgRookWhite = new ImageIcon(img2);
			pieceImgHash.put("RookWhite", imgRookWhite);

			img = ImageIO.read(new File("src/files/horseWhite.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgHorseWhite = new ImageIcon(img2);
			pieceImgHash.put("HorseWhite", imgHorseWhite);

			img = ImageIO.read(new File("src/files/bishopWhite.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgBishopWhite = new ImageIcon(img2);
			pieceImgHash.put("BishopWhite", imgBishopWhite);

			img = ImageIO.read(new File("src/files/queenWhite.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgQueenWhite = new ImageIcon(img2);
			pieceImgHash.put("QueenWhite", imgQueenWhite);

			img = ImageIO.read(new File("src/files/kingWhite.png"));
			img2 = (Image) img.getScaledInstance(width, height, img.getType());
			ImageIcon imgKingWhite = new ImageIcon(img2);
			pieceImgHash.put("KingWhite", imgKingWhite);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable arg0, final Object arg1) {

		if (arg0 instanceof ChessPlayer) {

			if (arg1 instanceof Move) {
				
				if(((Move)arg1).getCapturedPiece() != null){
				clearPanel.remove(((Move)arg1).getCapturedPiece().getJlImage());
				}
				
				clearPanel.revalidate();
				
				Runnable r = new Runnable(){

					@Override
					public void run() {
					
						for(int i = 1; i < 15; i++){
						
						((Move)arg1).getMovedPiece().getJlImage().
						setBounds(100+ ((Move) arg1).getToCell().getRow()*500/8,
										100+ ((Move) arg1).getToCell().getColumn()*500/8,
										500/8, 500/8);
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						clearPanel.revalidate();
						}
						
					}
					
				};
				
				r.run();
				
				
				this.validate();

				System.out.println("Moved Piece: "
						+ ((Move) arg1).getMovedPiece() + "\n"
						+ "Captured Piece: " + ((Move) arg1).getCapturedPiece() + "\n" 
						+ "From Cell: "
						+ ((Move) arg1).getFromCell().getSquareName() + "\n"
						+ "To Cell: "
						+ ((Move) arg1).getToCell().getSquareName());

			}
		} else if (arg0 instanceof ChessPiece) {

			if (arg0 instanceof ChessPiece) {

				JLabel jl = ((ChessPiece) arg1).getJlImage();
				
				if(jl == null){
					System.out.println("null image: " + ((ChessPiece) arg1).getPieceType());
				} else {
				clearPanel.add(jl);
				jl.setBounds(100+ ((ChessPiece) arg1).getCurrentCell().getRow()*500/8,
						100+ ((ChessPiece) arg1).getCurrentCell().getColumn()*500/8,
						500/8, 500/8);

				System.out.println("Bounds: " + jl.getBounds());
				}
			}
			
			

		}

	}

	public Hashtable<String, ImageIcon> getPieceImgHash() {
		return pieceImgHash;
	}

	public void setPieceImgHash(Hashtable<String, ImageIcon> pieceImgHash) {
		this.pieceImgHash = pieceImgHash;
	}
}
