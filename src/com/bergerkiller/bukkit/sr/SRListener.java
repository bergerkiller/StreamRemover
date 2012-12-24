package com.bergerkiller.bukkit.sr;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class SRListener implements Listener {
	private static final BlockFace[] AXIS = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};

	private static boolean isValidGoal(Block block) {
		final int id = block.getTypeId();
		if (id == Material.AIR.getId()) {
			return true;
		}
		if (id == Material.WATER.getId() || id == Material.LAVA.getId()) {
			return block.getData() != 0;
		}
		return false;
	}

	private static boolean isValidSource(Block block) {
		// Is a source block? Check data
		if (block.getData() == 0) {
			final int id = block.getTypeId();
			if (id == Material.WATER.getId() || id == Material.STATIONARY_WATER.getId()) {
				return true;
			}
			if (StreamRemover.allowLava && (id == Material.LAVA.getId() || id == Material.STATIONARY_LAVA.getId())) {
				return true;
			}
		}
		return false;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockFromTo(BlockFromToEvent event) {
		Block from = event.getBlock();
		Block to = event.getToBlock();
		// Flowing from a stationary source towards air?
		if (isValidGoal(to) && isValidSource(from)) {
			//check surrounding blocks: 2 are water?
			//ignore the from block (we know it is water)
			for (BlockFace face : AXIS) {
				if (face == event.getFace().getOppositeFace()) {
					continue;
				}
				if (isValidSource(to.getRelative(face))) {
					event.getToBlock().setTypeId(from.getTypeId(), true);
					event.setCancelled(true);
					break;
				}
			}
		}
	}
}
