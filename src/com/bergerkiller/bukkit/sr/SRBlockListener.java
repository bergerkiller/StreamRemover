package com.bergerkiller.bukkit.sr;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

public class SRBlockListener extends BlockListener {

	public boolean allowLava = false;
	
	@Override
	public void onBlockFromTo(BlockFromToEvent event) {
		if (!event.isCancelled()) {
			Block b = event.getToBlock();
			if (b.getTypeId() == 0) {
				Block f = event.getBlock();
				int fid = f.getTypeId();
				if (fid == 9 || (allowLava && fid == 11)) {
					//check surrounding blocks: 2 are water?
					//ignore the from block (we know it is water)
					for (BlockFace face : new BlockFace[] {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST}) {
						Block r = b.getRelative(face);
						if (f.getX() == r.getX()) {
							if (f.getY() == r.getY()) {
								if (f.getZ() == r.getZ()) {
									continue;
								}
							}
						}
						int rid = r.getTypeId();
						if (rid == 8 || (allowLava && rid == 10)) {
							event.getToBlock().setTypeId(fid, true);
							event.setCancelled(true);
							break;
						}
					}
				}
			}
		}
	}
	
}
