package com.halex.createrancher.index;

import com.halex.createrancher.CreateRancher;
import com.halex.createrancher.entity.FishingNetEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.halex.createrancher.CreateRancher.REGISTRATE;
import static com.mojang.text2speech.Narrator.LOGGER;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CreateRancher.MODID);

    public static final RegistryObject<EntityType<FishingNetEntity>> FISHINGNET = ENTITY_TYPES.register("fishing_net", () -> EntityType.Builder.<FishingNetEntity>of(FishingNetEntity::new, MobCategory.MISC)
                    .noSave()
                    .setTrackingRange(4)
                    .setUpdateInterval(5)
                    .build(CreateRancher.asResource("fishing_net").toString()) //.noSummon()
    );

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering Entities...");
        ENTITY_TYPES.register(eventBus);
        LOGGER.info("FishingNet Entity Registered!");
    }
}
