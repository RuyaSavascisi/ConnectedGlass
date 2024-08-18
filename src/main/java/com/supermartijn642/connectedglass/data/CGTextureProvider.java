package com.supermartijn642.connectedglass.data;

import com.supermartijn642.connectedglass.CGGlassType;
import com.supermartijn642.rechiseled.api.ChiseledTextureProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * Created 13/11/2023 by SuperMartijn642
 */
public class CGTextureProvider extends ChiseledTextureProvider {

    public CGTextureProvider(DataGenerator generator, ExistingFileHelper fileHelper){
        super("connectedglass", generator, fileHelper);
    }

    @Override
    protected void createTextures(){
        for(DyeColor color : DyeColor.values()){
            if(color == DyeColor.RED)
                continue;
            String colorName = color.getName();
            PaletteMap paletteMap = this.createPaletteMap(ResourceLocation.fromNamespaceAndPath("connectedglass", "palettes/red_palette"), ResourceLocation.fromNamespaceAndPath("connectedglass", "palettes/" + colorName + "_palette"));
            for(CGGlassType type : CGGlassType.values()){
                paletteMap.applyToTexture(ResourceLocation.fromNamespaceAndPath("connectedglass", type.getRegistryName() + "/" + type.getRegistryName() + "_red"), type.getRegistryName() + "/" + type.getRegistryName(color));
                paletteMap.applyToTexture(ResourceLocation.fromNamespaceAndPath("connectedglass", type.getRegistryName() + "/" + type.getRegistryName() + "_red_edge"), type.getRegistryName() + "/" + type.getRegistryName(color) + "_edge");
            }
        }
    }
}
