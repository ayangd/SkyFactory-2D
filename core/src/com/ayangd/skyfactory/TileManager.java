package com.ayangd.skyfactory;

import java.util.List;

import static com.ayangd.skyfactory.SkyFactory.gridSize;

public class TileManager {
	
	public TileManager() {}
	
	public void add(Tile tile, List<Tile> tiles, Worker worker) {
		boolean occupied = false;
		if(tiles.size() != 0)
			for(int i = 0; i != tiles.size(); i++) {
				if((tile.x == tiles.get(i).x) && (tile.y == tiles.get(i).y)) {
					occupied = true;
					break;
				}
			}
		occupied |= isBlockedByWorker(tile.x, tile.y, worker);
		if(!occupied) {
			tiles.add(tile);
		}
	}
	
	public boolean isBlockedByWorker(int x, int y, Worker worker) {
		System.out.println("aa");
		System.out.println(x * gridSize);
		System.out.println(y * gridSize);
		System.out.println((int) (worker.position.x - (worker.position.x % gridSize)));
		System.out.println((int) (worker.position.x + (worker.position.x % gridSize)));
		System.out.println((int) (worker.position.y - (worker.position.y % gridSize)));
		System.out.println((int) (worker.position.y + (worker.position.y % gridSize)));
		// Lower Worker
		if(x * gridSize == (int) (worker.position.x - (worker.position.x % gridSize))) {
			if(y * gridSize == (int) (worker.position.y - (worker.position.y % gridSize))) {
				return true;
			} else if(y * gridSize == (int) (worker.position.y + (worker.position.y % gridSize))) {
				return true;
			}
		} else if(x * gridSize == (int) (worker.position.x + (worker.position.x % gridSize))) {
			if(y * gridSize == (int) (worker.position.y - (worker.position.y % gridSize))) {
				return true;
			} else if(y * gridSize == (int) (worker.position.y + (worker.position.y % gridSize))) {
				return true;
			}
		}
		// Higher Worker
		if(x * gridSize == (int) (worker.position.x - (worker.position.x % gridSize))) {
			if(y * gridSize == (int) (worker.position.y - (worker.position.y % gridSize)) + gridSize) {
				return true;
			} else if(y * gridSize == (int) (worker.position.y + (worker.position.y % gridSize)) + gridSize) {
				return true;
			}
		} else if(x * gridSize == (int) (worker.position.x + (worker.position.x % gridSize))) {
			if(y * gridSize == (int) (worker.position.y - (worker.position.y % gridSize)) + gridSize) {
				return true;
			} else if(y * gridSize == (int) (worker.position.y + (worker.position.y % gridSize)) + gridSize) {
				return true;
			}
		}
		return false;
	}
}
