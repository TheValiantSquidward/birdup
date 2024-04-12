package net.thevaliantsquidward.birdup.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class WeaponEnchants extends Enchantment {
    private int levels;
    private int minXP;
    private String registryName;

    protected WeaponEnchants(String name, Rarity rarity, EnchantmentCategory category, int levels, int minXP, EquipmentSlot... equipmentSlot) {
        super(rarity, category, equipmentSlot);
        this.levels = levels;
        this.minXP = minXP;
        this.registryName = name;
    }

    public int getMinCost(int i) {
        return 1 + (i - 1) * minXP;
    }

    public int getMaxCost(int i) {
        return super.getMinCost(i) + 30;
    }

    public int getMaxLevel() {
        return levels;
    }


    protected boolean checkCompatibility(Enchantment enchantment) {
        return this != enchantment && ModEnchantments.areCompatible(this, enchantment);
    }

    public float getAttackKnockbackBonus() {
        return 0.0F;
    }

    public String getName(){
        return registryName;
    }
}
