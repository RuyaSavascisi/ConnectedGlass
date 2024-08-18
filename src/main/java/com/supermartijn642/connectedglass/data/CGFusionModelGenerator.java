package com.supermartijn642.connectedglass.data;

import com.supermartijn642.connectedglass.CGGlassType;
import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.ModelInstance;
import com.supermartijn642.fusion.api.model.data.ConnectingModelData;
import com.supermartijn642.fusion.api.predicate.DefaultConnectionPredicates;
import com.supermartijn642.fusion.api.provider.FusionModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

/**
 * Created 26/09/2022 by SuperMartijn642
 */
public class CGFusionModelGenerator extends FusionModelProvider {

    public CGFusionModelGenerator(DataGenerator generator){
        super("connectedglass", generator.getPackOutput());
    }

    @Override
    public void generate(){
        // Create the glass block models
        for(CGGlassType type : CGGlassType.values()){
            this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", type.getRegistryName()),
                ModelInstance.of(
                    DefaultModelTypes.CONNECTING,
                    ConnectingModelData.builder()
                        .parent(ResourceLocation.withDefaultNamespace("block/cube_all"))
                        .texture("all", ResourceLocation.fromNamespaceAndPath("connectedglass", type.getRegistryName() + "/" + type.getRegistryName()))
                        .build()
                )
            );
            for(DyeColor color : DyeColor.values()){
                this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", type.getRegistryName(color)),
                    ModelInstance.of(
                        DefaultModelTypes.CONNECTING,
                        ConnectingModelData.builder()
                            .parent(ResourceLocation.withDefaultNamespace("block/cube_all"))
                            .texture("all", ResourceLocation.fromNamespaceAndPath("connectedglass", type.getRegistryName() + "/" + type.getRegistryName(color)))
                            .build()
                    )
                );
            }
        }

        // Create the pane models
        for(CGGlassType type : CGGlassType.values()){
            if(type.hasPanes){
                this.createPaneModels(type.getPaneRegistryName(), type.getRegistryName() + "/" + type.getRegistryName());
                for(DyeColor color : DyeColor.values())
                    this.createPaneModels(type.getPaneRegistryName(color), type.getRegistryName() + "/" + type.getRegistryName(color));
            }
        }
    }

    private void createPaneModels(String identifier, String texture){
        this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", identifier + "_side"),
            ModelInstance.of(
                DefaultModelTypes.CONNECTING,
                ConnectingModelData.builder()
                    .parent(ResourceLocation.fromNamespaceAndPath("connectedglass", "block/template_glass_pane_side"))
                    .texture("pane", ResourceLocation.fromNamespaceAndPath("connectedglass", texture))
                    .texture("edge", ResourceLocation.fromNamespaceAndPath("connectedglass", texture + "_edge"))
                    .connection(DefaultConnectionPredicates.isSameBlock())
                    .build()
            )
        );
        this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", identifier + "_post"),
            ModelInstance.of(
                DefaultModelTypes.CONNECTING,
                ConnectingModelData.builder()
                    .parent(ResourceLocation.fromNamespaceAndPath("connectedglass", "block/template_glass_pane_post"))
                    .texture("pane", ResourceLocation.fromNamespaceAndPath("connectedglass", texture))
                    .texture("edge", ResourceLocation.fromNamespaceAndPath("connectedglass", texture + "_edge"))
                    .connection(DefaultConnectionPredicates.isSameBlock())
                    .build()
            )
        );
        this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", identifier + "_side_alt"),
            ModelInstance.of(
                DefaultModelTypes.CONNECTING,
                ConnectingModelData.builder()
                    .parent(ResourceLocation.fromNamespaceAndPath("connectedglass", "block/template_glass_pane_side_alt"))
                    .texture("pane", ResourceLocation.fromNamespaceAndPath("connectedglass", texture))
                    .texture("edge", ResourceLocation.fromNamespaceAndPath("connectedglass", texture + "_edge"))
                    .connection(DefaultConnectionPredicates.isSameBlock())
                    .build()
            )
        );
        this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", identifier + "_noside"),
            ModelInstance.of(
                DefaultModelTypes.CONNECTING,
                ConnectingModelData.builder()
                    .parent(ResourceLocation.fromNamespaceAndPath("connectedglass", "block/template_glass_pane_noside"))
                    .texture("pane", ResourceLocation.fromNamespaceAndPath("connectedglass", texture))
                    .texture("edge", ResourceLocation.fromNamespaceAndPath("connectedglass", texture + "_edge"))
                    .connection(DefaultConnectionPredicates.isSameBlock())
                    .build()
            )
        );
        this.addModel(ResourceLocation.fromNamespaceAndPath("connectedglass", identifier + "_noside_alt"),
            ModelInstance.of(
                DefaultModelTypes.CONNECTING,
                ConnectingModelData.builder()
                    .parent(ResourceLocation.fromNamespaceAndPath("connectedglass", "block/template_glass_pane_noside_alt"))
                    .texture("pane", ResourceLocation.fromNamespaceAndPath("connectedglass", texture))
                    .texture("edge", ResourceLocation.fromNamespaceAndPath("connectedglass", texture + "_edge"))
                    .connection(DefaultConnectionPredicates.isSameBlock())
                    .build()
            )
        );
    }
}
