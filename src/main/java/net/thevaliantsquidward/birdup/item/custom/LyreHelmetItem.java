package net.thevaliantsquidward.birdup.item.custom;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import java.util.function.Consumer;

public class LyreHelmetItem extends ArmorItem {
    public LyreHelmetItem(ArmorMaterial material, ArmorItem.Type slot, Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 0, 0, false, false, true));
    }

}
