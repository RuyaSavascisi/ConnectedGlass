package com.supermartijn642.connectedglass.data;

import com.supermartijn642.connectedglass.CGGlassType;
import com.supermartijn642.core.generator.ResourceCache;
import com.supermartijn642.core.generator.TagGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;

/**
 * Created 5/26/2020 by SuperMartijn642
 */
public class CGTagGenerator extends TagGenerator {

    public CGTagGenerator(ResourceCache cache){
        super("connectedglass", cache);
    }

    @Override
    public void generate(){
        ArrayList<Block> glass = new ArrayList<>();
        ArrayList<Block> glassColored = new ArrayList<>();
        ArrayList<Block> glassColorless = new ArrayList<>();
        ArrayList<Block> glassTinted = new ArrayList<>();
        ArrayList<Block> panes = new ArrayList<>();
        ArrayList<Block> panesColored = new ArrayList<>();
        ArrayList<Block> panesColorless = new ArrayList<>();
        ArrayList<Block> panesTinted = new ArrayList<>();

        for(CGGlassType type : CGGlassType.values()){
            glass.addAll(type.blocks);
            if(type.isTinted)
                glassTinted.addAll(type.blocks);
            else{
                glassColored.addAll(type.colored_blocks.values());
                glassColorless.add(type.block);
            }
            if(type.hasPanes){
                panes.addAll(type.panes);
                if(type.isTinted)
                    panesTinted.addAll(type.panes);
                else{
                    panesColored.addAll(type.colored_panes.values());
                    panesColorless.add(type.pane);
                }
            }
        }

        glass.forEach(this.blockTag(Tags.Blocks.GLASS_BLOCKS)::add);
        glass.stream().map(Block::asItem).forEach(this.itemTag(Tags.Items.GLASS_BLOCKS)::add);
        glassColorless.forEach(this.blockTag(Tags.Blocks.GLASS_BLOCKS_COLORLESS)::add);
        glassColorless.stream().map(Block::asItem).forEach(this.itemTag(Tags.Items.GLASS_BLOCKS_COLORLESS)::add);
        glassTinted.forEach(this.blockTag(Tags.Blocks.GLASS_BLOCKS_TINTED)::add);
        glassTinted.stream().map(Block::asItem).forEach(this.itemTag(Tags.Items.GLASS_BLOCKS_TINTED)::add);
        panes.forEach(this.blockTag(Tags.Blocks.GLASS_PANES)::add);
        panes.stream().map(Block::asItem).forEach(this.itemTag(Tags.Items.GLASS_PANES)::add);
        panesColorless.forEach(this.blockTag(Tags.Blocks.GLASS_PANES_COLORLESS)::add);
        panesColorless.stream().map(Block::asItem).forEach(this.itemTag(Tags.Items.GLASS_PANES_COLORLESS)::add);
        panesTinted.forEach(this.blockTag("forge", "glass_panes/tinted")::add);
        panesTinted.stream().map(Block::asItem).forEach(this.itemTag("forge", "glass_panes/tinted")::add);

        // Impermeable tag
        TagBuilder<Block> impermeable = this.blockTag(BlockTags.IMPERMEABLE);
        for(CGGlassType type : CGGlassType.values())
            type.blocks.forEach(impermeable::add);
    }
}
