package net.thevaliantsquidward.birdup.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.birdup.BirdUp;
import net.thevaliantsquidward.birdup.item.ModItems;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, BirdUp.MOD_ID);

    public static final EnchantmentCategory ACCORDION = EnchantmentCategory.create("obnoxious_accordion", (item -> item == ModItems.OBNOXIOUS_ACCORDION.get()));
    public static final EnchantmentCategory LANCE = EnchantmentCategory.create("greatbill_lance", (item -> item == ModItems.GREATBILL_LANCE.get()));
    public static final EnchantmentCategory GUN = EnchantmentCategory.create("flamingun", (item -> item == ModItems.FLAMINGUN.get()));

    public static final RegistryObject<Enchantment> SCREAMER = ENCHANTMENTS.register("screamer", () -> new WeaponEnchants("screamer", Enchantment.Rarity.UNCOMMON, ACCORDION, 1, 10, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> TWIRLING = ENCHANTMENTS.register("twirling", () -> new WeaponEnchants("twirling", Enchantment.Rarity.RARE, LANCE, 1, 18, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> LAUNCHING = ENCHANTMENTS.register("launching", () -> new WeaponEnchants("launching", Enchantment.Rarity.RARE, LANCE, 1, 18, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> AUTOMATIC = ENCHANTMENTS.register("automatic", () -> new WeaponEnchants("automatic", Enchantment.Rarity.RARE, GUN, 1, 18, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> ROCKET = ENCHANTMENTS.register("rocket", () -> new WeaponEnchants("rocket", Enchantment.Rarity.RARE, GUN, 1, 18, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> SPREADFIRE = ENCHANTMENTS.register("spreadfire", () -> new WeaponEnchants("spreadfire", Enchantment.Rarity.RARE, GUN, 1, 18, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> SHRIMP_CATCHER = ENCHANTMENTS.register("shrimp_catcher", () -> new WeaponEnchants("shrimp_catcher", Enchantment.Rarity.UNCOMMON, GUN, 1, 18, EquipmentSlot.MAINHAND));


    public static boolean areCompatible(WeaponEnchants enchantment1, Enchantment enchantment2) {
        if(enchantment1 == TWIRLING.get() && enchantment2 == LAUNCHING.get()){
            return false;
        } if(enchantment1 == LAUNCHING.get() && enchantment2 == TWIRLING.get()){
            return false;
        } if(enchantment1 == ROCKET.get() && enchantment2 == AUTOMATIC.get()){
            return false;
        } if(enchantment1 == AUTOMATIC.get() && enchantment2 == ROCKET.get()){
            return false;
        } if(enchantment1 == ROCKET.get() && enchantment2 == SPREADFIRE.get()){
            return false;
        } if(enchantment1 == SPREADFIRE.get() && enchantment2 == ROCKET.get()){
            return false;
        } if(enchantment1 == AUTOMATIC.get() && enchantment2 == SPREADFIRE.get()){
            return false;
        } if(enchantment1 == SPREADFIRE.get() && enchantment2 == AUTOMATIC.get()){
            return false;
        }
        return true;
    }

    public static void addAllEnchantsToCreativeTab(CreativeModeTab.Output output, EnchantmentCategory enchantmentCategory){
        for (RegistryObject<Enchantment> enchantObject : ENCHANTMENTS.getEntries()) {
            if (enchantObject.isPresent()) {
                Enchantment enchant = enchantObject.get();
                if(enchant.category == enchantmentCategory){
                    output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchant, enchant.getMaxLevel())));
                }
            }
        }
    }
}
