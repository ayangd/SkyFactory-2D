package com.ayangd.skyfactory;

import java.util.List;

public class TileManager {
	
	public TileManager() {}
	
	public void add(Tile tile, List<Tile> tiles) {
		boolean occupied = false;
		if(tiles.size() != 0)
			for(int i = 0; i != tiles.size(); i++) {
				if((tile.x == tiles.get(i).x) && (tile.y == tiles.get(i).y)) {
					occupied = true;
					break;
				}
			}
		if(!occupied) {
			tiles.add(tile);
		}
	}
}
