package net.thevaliantsquidward.birdup.entity.custom;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.thevaliantsquidward.birdup.entity.ModEntities;
import net.thevaliantsquidward.birdup.item.ModItems;

public class GrenadeEgg extends ThrowableItemProjectile {


    public GrenadeEgg(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GrenadeEgg(Level pLevel) {
        super(ModEntities.CASSOWARY_EGG.get(), pLevel);
    }
    public GrenadeEgg(Level pLevel, LivingEntity livingEntity) {
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

    private int explosionPower = 2;
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 3.0F);
    }
    private int changeTimer = -1;

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        Level level = level();
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);

            this.discard();
        }

        if (this.changeTimer == -1) {
            this.changeTimer = 10;
            this.level().broadcastEntityEvent(this, (byte) 68);
        }

        if (!this.level().isClientSide) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.CASSOWARY_EGG.get();
    }
}
