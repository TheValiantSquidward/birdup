package net.thevaliantsquidward.birdup.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.birdup.BirdUp;
import net.thevaliantsquidward.birdup.item.custom.*;
import net.thevaliantsquidward.birdup.item.tier.ModItemTiers;
import net.thevaliantsquidward.birdup.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BirdUp.MOD_ID);

    public static final RegistryObject<Item> GREATBILL_LANCE = ITEMS.register("greatbill_lance",
            () -> new GreatbillLanceItem(ModItemTiers.HERON, 5, -3.6F));

    public static final RegistryObject<Item> BIRDSTEEL_INGOT = ITEMS.register("birdsteel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OBNOXIOUS_ACCORDION = ITEMS.register("obnoxious_accordion",
            () -> new ObnoxiousAccordionItem(ModItemTiers.LYRE, 2, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CLOTH_SCRAPS = ITEMS.register("blue_cloth_scraps",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BEATEN_SHAFT = ITEMS.register("beaten_shaft",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STICKY_SALIVA = ITEMS.register("sticky_saliva",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LYRE_FEATHERS = ITEMS.register("lyre_feathers",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HERONS_BILL = ITEMS.register("herons_bill",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRINE_SHRIMP = ITEMS.register("brine_shrimp",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> BRINE_SHRIMP_EGG_PACKET = ITEMS.register("brine_shrimp_egg_packet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LYREBIRD_CAP = ITEMS.register("lyre_cap",
            () -> new LyreHelmetItem(ModArmorMaterials.LYRE, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryObject<Item> BIRDS_ARISE_DISC = ITEMS.register("birds_arise_disc",
            () -> new RecordItem(8, ModSounds.BIRDS_ARISE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 4040));

    public static final RegistryObject<Item> BOWERD_DISC = ITEMS.register("bowerd_disc",
            () -> new RecordItem(3, ModSounds.BOWERD, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 2820));

    public static final RegistryObject<Item> CASSOWARY_EGG = ITEMS.register("cassowary_egg",
            () -> new CassowaryEggItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> GRENADE_EGG = ITEMS.register("grenade_egg",
            () -> new GrenadeEggItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> FLAMINGUN = ITEMS.register("flamingun",
            () -> new FlamingunItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
