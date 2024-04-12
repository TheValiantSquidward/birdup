package net.thevaliantsquidward.birdup.entity.custom;

import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.thevaliantsquidward.birdup.entity.ModEntities;
import net.thevaliantsquidward.birdup.item.ModItems;

import javax.annotation.Nullable;

public class ThrownCassowaryEgg extends ThrowableItemProjectile {

    public ThrownCassowaryEgg(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public ThrownCassowaryEgg(Level pLevel) {
        super(ModEntities.CASSOWARY_EGG.get(), pLevel);
    }
    public ThrownCassowaryEgg(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.CASSOWARY_EGG.get(), livingEntity, pLevel);
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CASSOWARY_EGG.get();
    }
}