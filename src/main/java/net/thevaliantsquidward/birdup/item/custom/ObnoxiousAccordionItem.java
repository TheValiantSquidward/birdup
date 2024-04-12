package net.thevaliantsquidward.birdup.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thevaliantsquidward.birdup.BirdUp;

import net.thevaliantsquidward.birdup.enchantment.ModEnchantments;
import net.thevaliantsquidward.birdup.item.ModItems;
import net.thevaliantsquidward.birdup.item.tier.ModItemTiers;
import net.thevaliantsquidward.birdup.sound.ModSounds;

import java.util.UUID;

public class ObnoxiousAccordionItem extends Item {
    protected static final UUID ATTACK_KNOCKBACK_UUID = UUID.fromString("e56350e0-8756-464d-92f9-54289ab41e0a");
    private final Multimap<Attribute, AttributeModifier> toolAttributes;
    private final float attackKnockback;
    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    public ObnoxiousAccordionItem(ModItemTiers pTier, int pAttackKnockbackModifier, Properties pProperties) {
        super(pProperties.defaultDurability(200));
        float attackDamage = 1.5F;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        this.attackKnockback = (float)pAttackKnockbackModifier + pTier.getAttackKnockbackBonus();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(ATTACK_KNOCKBACK_UUID, "Tool modifier", (double)this.attackKnockback, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -1.3F, AttributeModifier.Operation.ADDITION));
        this.toolAttributes = builder.build();
    }


    @Override
    public boolean isEnchantable(ItemStack stack) {
        return stack.getCount() == 1;
    }
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.gameEvent(GameEvent.ITEM_INTERACT_START);
        if(itemstack.getEnchantmentLevel(ModEnchantments.SCREAMER.get()) > 0) {
            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.SCREAMER_ATTACK.get(), SoundSource.PLAYERS, 0.6f, 1f);
            pPlayer.getCooldowns().addCooldown(this, 5);
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            itemstack.hurtAndBreak(1, pPlayer, (player) -> {
                player.broadcastBreakEvent(pPlayer.getUsedItemHand());
            });
        } else {
            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.ANNOYING_ACCORDION.get(), SoundSource.PLAYERS, 1.8f, 1f);
            pPlayer.getCooldowns().addCooldown(this, 5);
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            itemstack.hurtAndBreak(1, pPlayer, (player) -> {
                player.broadcastBreakEvent(pPlayer.getUsedItemHand());
            });
        }
        return InteractionResultHolder.success(itemstack);
    }
    @Mod.EventBusSubscriber(modid = BirdUp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class AccordionEvents {
        @SubscribeEvent
        public static void PlayAccordionAttackSound(LivingDamageEvent event) {
            DamageSource damageSource = event.getSource();
            Entity attacker = damageSource.getDirectEntity();

            if (!(attacker instanceof LivingEntity livingEntity)) return;
            if (!livingEntity.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.OBNOXIOUS_ACCORDION.get())) return;
            float pitch = 0.9F + (livingEntity.getRandom().nextFloat() * 0.2F);
            if (livingEntity instanceof Player player) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(ModEnchantments.SCREAMER.get()) > 0) {
                    livingEntity.getCommandSenderWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ModSounds.SCREAMER_ATTACK.get(), SoundSource.PLAYERS, 0.7F, pitch);
                } else {
                    livingEntity.getCommandSenderWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ModSounds.ANNOYING_ACCORDION_ATTACK_STRONG.get(), SoundSource.PLAYERS, 1.0F, pitch);
                }

            }
        }

    }


    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(10, attacker, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }


    public boolean isValidRepairItem(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return ModItems.LYRE_FEATHERS.get() == p_82789_2_.getItem() || super.isValidRepairItem(p_82789_1_, p_82789_2_);
    }
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.toolAttributes : super.getDefaultAttributeModifiers(equipmentSlot);
    }
}
