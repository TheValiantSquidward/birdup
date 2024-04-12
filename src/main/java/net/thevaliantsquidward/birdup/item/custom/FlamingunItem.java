package net.thevaliantsquidward.birdup.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.thevaliantsquidward.birdup.enchantment.ModEnchantments;

import net.thevaliantsquidward.birdup.entity.custom.ThrownCassowaryEgg;
import net.thevaliantsquidward.birdup.item.ModItems;
import net.thevaliantsquidward.birdup.sound.ModSounds;

import java.util.function.Predicate;


public class FlamingunItem extends Item {

    private static final int MAX_CHARGE = 10;

    public static boolean hasCharge(ItemStack stack) {
        return getCharge(stack) < MAX_CHARGE;
    }

    public static final Predicate<ItemStack> AMMO = (stack) -> {
        return stack.getItem() == ModItems.BRINE_SHRIMP.get().asItem();
    };


    public static int getCharge(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null ? compoundtag.getInt("ChargeUsed") : 0;
    }

    public static void setCharge(ItemStack stack, int charge) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putInt("ChargeUsed", charge);
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean held) {
        super.inventoryTick(stack, level, entity, i, held);

    }

    public boolean isBarVisible(ItemStack stack) {
        return getCharge(stack) != 0;
    }

    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F - (float) getCharge(stack) * 13.0F / (float) MAX_CHARGE);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return Mth.hsvToRgb(3.45f, 0.0f, 1.0f);
    }


    public FlamingunItem(Properties pProperties) {
        super(pProperties);
    }



    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (hasCharge(itemstack)) {
            int charge = getCharge(itemstack);
            setCharge(itemstack, Math.min(charge + 1, MAX_CHARGE));

            if(itemstack.getEnchantmentLevel(ModEnchantments.AUTOMATIC.get()) > 0) {

                pPlayer.level().playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.AUTOMATICFIRE.get(), pPlayer.getSoundSource(), 1.0F, 1.0F);
                return InteractionResultHolder.consume(itemstack);
            } else

                if(itemstack.getEnchantmentLevel(ModEnchantments.ROCKET.get()) > 0) {

                pPlayer.getCooldowns().addCooldown(this, 60);
                pPlayer.level().playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.ROCKETFIRE.get(), pPlayer.getSoundSource(), 1.0F, 1.0F);
                return InteractionResultHolder.consume(itemstack);
            } else
                if(itemstack.getEnchantmentLevel(ModEnchantments.SPREADFIRE.get()) > 0) {
                pPlayer.getCooldowns().addCooldown(this, 20);
                pPlayer.level().playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.SHOTGUNFIRE.get(), pPlayer.getSoundSource(), 1.0F, 1.0F);
                return InteractionResultHolder.consume(itemstack);
            } else

            {

                pPlayer.getCooldowns().addCooldown(this, 10);
                pPlayer.level().playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.GUNFIRE.get(), pPlayer.getSoundSource(), 1.0F, 1.0F);
                return InteractionResultHolder.consume(itemstack);
            }

        } else{
            ItemStack ammo = findAmmo(pPlayer);
            boolean flag = pPlayer.isCreative();
            if (!ammo.isEmpty()) {
                ammo.shrink(1);
                flag = true;
            }
            if (flag) {
                pPlayer.getCooldowns().addCooldown(this, 60);
                setCharge(itemstack, 0);
                pPlayer.level().playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.RELOAD.get(), pPlayer.getSoundSource(), 1.0F, 1.0F);

            }
        }
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    private ItemStack findAmmo(Player entity) {
        if (entity.isCreative()) {
            return ItemStack.EMPTY;
        }
        for (int i = 0; i < entity.getInventory().getContainerSize(); ++i) {
            ItemStack itemstack1 = entity.getInventory().getItem(i);
            if (AMMO.test(itemstack1)) {
                return itemstack1;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return stack.getCount() == 1;
    }
}

