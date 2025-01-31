package com.halex.createrancher;

import com.halex.createrancher.entity.*;
import com.halex.createrancher.index.EntityRegistry;
import com.halex.createrancher.index.ItemRegistry;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateRancher.MODID)
public class CreateRancher {

  public static final String MODID = "createrancher";
  public static final Logger LOGGER = LogManager.getLogger();
  public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateRancher.MODID);

  public CreateRancher() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ItemRegistry.register();
    EntityRegistry.register(eventBus);
    CreateRancherTabs.register(eventBus);
    REGISTRATE.registerEventListeners(eventBus);
    new ConfigManager();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
  }

  private void setup(final FMLCommonSetupEvent event) {
    //    MinecraftForge.EVENT_BUS.register(new WhateverEvents()); 
  }

  private void setupClient(final FMLClientSetupEvent event) {
    LOGGER.info("Setting up client...");

    // Log to confirm the baking of the layer
    LOGGER.info("Baking FishingNetModel Layer...");

    // This is where we bake the model layer for the renderer
    EntityRenderers.register(EntityRegistry.FISHINGNET.get(), context -> {
      LOGGER.info("Creating FishingNet Renderer...");
      return new FishingNetRenderer(context);
    });

    // Ensure that the model layer is baked properly
    LOGGER.info("FishingNet Model Layer Registered!");
  }



  public static ResourceLocation asResource(String path) {
    return new ResourceLocation(MODID, path);
  }

}
