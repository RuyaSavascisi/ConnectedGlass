package com.supermartijn642.connectedglass;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Created 7/23/2021 by SuperMartijn642
 */
public class CGTintedGlassBlock extends CGGlassBlock {

    public CGTintedGlassBlock(String registryName, String texture, boolean connected){
        super(registryName, texture, connected);
    }

    public CGTintedGlassBlock(String registryName, boolean connected){
        super(registryName, connected);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter world, BlockPos pos){
        return false;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos){
        return world.getMaxLightLevel();
    }
}