package com.halex.createrancher.item;

import com.halex.createrancher.entity.FishingNetEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;

public class FishingNetItem extends FishingRodItem {

    public FishingNetItem() {
        super(new Item.Properties().durability(20).defaultDurability((20)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (player.fishing != null) {
            if (!world.isClientSide) {
                int retrieve = player.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(retrieve, player, (p_41288_) -> p_41288_.broadcastBreakEvent(hand));
            }

            world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!world.isClientSide) {
                int luck = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int lureSpeed = EnchantmentHelper.getFishingLuckBonus(itemstack);
                world.addFreshEntity(new FishingNetEntity(player, world, luck, lureSpeed));
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }
}
