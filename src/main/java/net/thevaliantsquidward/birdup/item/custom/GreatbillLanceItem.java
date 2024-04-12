package net.thevaliantsquidward.birdup.item.custom;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.birdup.enchantment.ModEnchantments;

public class GreatbillLanceItem extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> toolAttributes;
    public GreatbillLanceItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Properties()
                .stacksTo(1)
                .defaultDurability(tier.getUses() * 3)
        );
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -3.1F, AttributeModifier.Operation.ADDITION));
        this.toolAttributes = builder.build();
    }
    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return stack.getCount() == 1;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player livingEntityIn, InteractionHand hand) {
        ItemStack itemstack = livingEntityIn.getItemInHand(hand);
        Vec3 view = livingEntityIn.getViewVector(1.0F);
        if(itemstack.getEnchantmentLevel(ModEnchantments.TWIRLING.get()) > 0) {
            livingEntityIn.setDeltaMovement(view.multiply(1.5D, 1.1D, 1.5D));
            livingEntityIn.getCooldowns().addCooldown(this, 5);
            livingEntityIn.startAutoSpinAttack(5);
        } else if(itemstack.getEnchantmentLevel(ModEnchantments.LAUNCHING.get()) > 0) {
            livingEntityIn.setDeltaMovement(view.multiply(4.9D, 3.5D, 4.9D));
            livingEntityIn.getCooldowns().addCooldown(this, 200);
            livingEntityIn.startAutoSpinAttack(45);
        }
        else {
            livingEntityIn.setDeltaMovement(view.multiply(2.5D, 1.5D, 2.5D));
            livingEntityIn.getCooldowns().addCooldown(this, 30);
            livingEntityIn.startAutoSpinAttack(20);
        }
        itemstack.hurtAndBreak(5, livingEntityIn, (player) -> {
            player.broadcastBreakEvent(livingEntityIn.getUsedItemHand());
        });
        return super.use(level, livingEntityIn, hand);
    }

}
