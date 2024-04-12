package net.thevaliantsquidward.birdup.item.tier;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.thevaliantsquidward.birdup.item.ModItems;

import java.util.function.Supplier;

public enum ModItemTiers implements Tier {

    HERON(3, 589, -3.1F, 2.0F, 1, 9, () -> Ingredient.of(ModItems.BIRDSTEEL_INGOT.get())),
    LYRE(3, 200, -1.3F, 1.5F,1, 9, () -> Ingredient.of(ModItems.LYRE_FEATHERS.get()))

    ;


    private final int level;
    private final int durability;
    private final float speed;
    private final float damage;
    private final float knockback;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModItemTiers(int level, int durability, float speed, float damage, int knockback, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.durability = durability;
        this.speed = speed;
        this.damage = damage;
        this.knockback = knockback;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() {
        return this.durability;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }
    public float getAttackKnockbackBonus() {
        return this.knockback;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
