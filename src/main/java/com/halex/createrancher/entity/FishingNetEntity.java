package com.halex.createrancher.entity;

import com.halex.createrancher.index.EntityRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class FishingNetEntity extends Projectile {
    private static final EntityDataAccessor<Boolean> CAUGHT_FISH = SynchedEntityData.defineId(FishingNetEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean hasCaughtFish = false;
    private int luck;
    private int lureSpeed;

    public FishingNetEntity(EntityType<? extends Entity> type, Level world) {
        super((EntityType<? extends Projectile>) type, world);
    }

    public FishingNetEntity(Player player, Level world, int luck, int lureSpeed) {
        super(EntityRegistry.FISHINGNET.get(), world);
        this.refreshDimensions();
        this.luck = luck;
        this.lureSpeed = lureSpeed;
        this.setPos(player.getX(), player.getY(), player.getZ());
    }

    @Override
    @Nonnull
    public EntityDimensions getDimensions(@Nonnull Pose pose) {
        // Adjust width and height to desired hitbox size
        float width = 3F;
        float height = 1F;
        return EntityDimensions.scalable(width, height);
    }

    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CAUGHT_FISH, false);
    }

    @Override
    public void tick() {
        super.tick();
        moveEntityAlongLine();
        checkForCatch();
    }

    private void moveEntityAlongLine() {
        // movement logic for the net, similar to a fishing rod's bobber behavior
    }

    private void checkForCatch() {
        if (this.getCommandSenderWorld().isClientSide) {
            // Check if the net has moved far enough to trigger a splash effect
            if (shouldCatchFish()) {
                spawnSplashParticles();
                this.hasCaughtFish = true; // You could toggle this or use a cooldown system
            }
        }
    }

    private boolean shouldCatchFish() {
        // Implement some logic to determine if the net should catch fish
        // You can use random chance or distance checks to simulate fish catching
        return Math.random() < 0.1; // Example: 10% chance to catch fish
    }

    private void spawnSplashParticles() {
        if (!this.getCommandSenderWorld().isClientSide) return;

        // Spawn splash particles (similar to vanilla fishing rod behavior)
        for (int i = 0; i < 5; i++) {  // Adjust number of particles
            this.getCommandSenderWorld().addParticle(ParticleTypes.SPLASH, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
    }

    public void readAdditionalSaveData(CompoundTag tag) {
    }
}

