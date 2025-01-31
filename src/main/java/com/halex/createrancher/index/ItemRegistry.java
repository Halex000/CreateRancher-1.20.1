package com.halex.createrancher.index;

import com.halex.createrancher.CreateRancher;
import com.simibubi.create.AllTags;
import com.halex.createrancher.item.*;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.halex.createrancher.CreateRancher.REGISTRATE;

public class ItemRegistry {
    public static final ItemEntry<FishingNetItem> FISHING_NET = REGISTRATE.item("fishing_net", properties -> new FishingNetItem())
            .register();


    public static void register() {
    }
}
