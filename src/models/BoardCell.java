package models;

public class BoardCell {
	
	Integer width;
	Integer depth;
	String desc;

	public BoardCell(Integer i, Integer j, String desc) {
		
		this.width = i;
		this.depth = j;
		this.desc = desc;
	}

}
