package piece;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import board.Board;
import board.Tile;

public class Piece {
	private int file, rank, x, y;
	private int tileSize = Tile.getTileSize();
	private final int TILE_SIZE_HALF = Tile.getTileSize() / 2;
	private final int INDICATOR_SIZE = 30;
	private final int INDICATOR_OFFSET = TILE_SIZE_HALF - (INDICATOR_SIZE / 2);
	private PieceColor pieceColor;
	private PieceType pieceType;
	private Tile tile;
	private ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
	private ArrayList<Tile> indicatedTiles = new ArrayList<Tile>();
	protected BufferedImage pieceImage;
	private BufferedImage moveableTileImage;

	public Piece(PieceColor pieceColor, PieceType pieceType, Tile tile) {
		this.file = tile.getFile();
		this.rank = tile.getRank();
		this.x = tile.getFile() * tileSize;
		this.y = tile.getRank() * tileSize;
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.tile = tile;
	}

	public BufferedImage getPieceImage(PieceType pieceType, PieceColor pieceColor) {
		// Loads the image for each PieceType and PieceColor
		pieceImage = null;
		try {
			pieceImage = ImageIO.read(new FileInputStream("res/pieces/" + pieceType.getPieceTypeName() + "_"
					+ pieceColor.getPieceColorName() + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pieceImage;
	}

	private BufferedImage getMoveableTileImage() {
		// Loads the image for the moveableTileIndicator
		moveableTileImage = null;
		try {
			moveableTileImage = ImageIO.read(new FileInputStream("res/board/circle.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moveableTileImage;
	}

	// BOOLEANS
	protected boolean isWithinBounds(int n) {
		if (n < 0) {
			return false;
		}
		if (n > 7) {
			return false;
		}
		return true;
	}

	// GETTERS
	public int getFile() {
		return file;
	}

	public int getRank() {
		return rank;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public String getPieceColorName() {
		return pieceColor.getPieceColorName();
	}

	public Tile getTile() {
		return tile;
	}

	public Tile[][] getChessBoard() {
		return Board.chessBoard;
	}

	public ArrayList<Tile> getMoveableTiles() {
		return moveableTiles;
	}

	public ArrayList<Tile> getIndicatedTiles() {
		return indicatedTiles;
	}

	// SETTERS
	public void setFile(int file) {
		this.file = file;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
		setFile(tile.getFile());
		setRank(tile.getRank());
		setX(tile.getX());
		setY(tile.getY());
	}

	// VOID
	public void drawPiece(Graphics2D g2) {
		g2.drawImage(pieceImage, x, y, tileSize, tileSize, null);
	}

	public void drawIndicators(Graphics2D g2, ArrayList<Tile> tiles) {
		for (Tile tile : tiles) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
			g2.drawImage(getMoveableTileImage(), tile.getX() + INDICATOR_OFFSET,
					tile.getY() + INDICATOR_OFFSET, INDICATOR_SIZE, INDICATOR_SIZE, null);
		}
	}
}
