package com.halex.createrancher;

import com.halex.createrancher.index.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreateRancherTabs {
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "createrancher");

    public static final RegistryObject<CreativeModeTab> CREATERANCHER =
            TAB_REGISTER.register("tabs",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("item_group.createrancher.tabs"))
                            .icon(() -> new ItemStack(ItemRegistry.FISHING_NET.get()))
                            .displayItems((parameters, tabData) -> {
                                tabData.accept(ItemRegistry.FISHING_NET.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        TAB_REGISTER.register(eventBus);
    }
}
