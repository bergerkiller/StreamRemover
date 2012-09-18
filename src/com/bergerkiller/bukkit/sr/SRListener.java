package com.bergerkiller.bukkit.sr;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import com.bergerkiller.bukkit.common.utils.FaceUtil;

public class SRListener implements Listener {

	public boolean allowLava;
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockFromTo(BlockFromToEvent event) {
		if (!event.isCancelled()) {
			Block b = event.getToBlock();
			if (b.getTypeId() == 0) {
				Block f = event.getBlock();
				int fid = f.getTypeId();
				if (f.getData() == 0 && (fid == 9 || (allowLava && fid == 11))) {
					//check surrounding blocks: 2 are water?
					//ignore the from block (we know it is water)
					for (BlockFace face : FaceUtil.axis) {
						Block r = b.getRelative(face);
						if (f.getX() == r.getX()) {
							if (f.getY() == r.getY()) {
								if (f.getZ() == r.getZ()) {
									continue;
								}
							}
						}
						if (r.getData() == 0) {
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
	
}
