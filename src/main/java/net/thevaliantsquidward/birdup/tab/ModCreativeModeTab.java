package net.thevaliantsquidward.birdup.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.birdup.BirdUp;
import net.thevaliantsquidward.birdup.block.ModBlocks;
import net.thevaliantsquidward.birdup.enchantment.ModEnchantments;
import net.thevaliantsquidward.birdup.item.ModItems;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BirdUp.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BIRDUP_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BIRDSTEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.birdup_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.GREATBILL_LANCE.get());
                        pOutput.accept(ModItems.OBNOXIOUS_ACCORDION.get());
                        pOutput.accept(ModItems.FLAMINGUN.get());
                        pOutput.accept(ModItems.LYREBIRD_CAP.get());

                        pOutput.accept(ModItems.BIRDS_ARISE_DISC.get());
                        pOutput.accept(ModItems.BOWERD_DISC.get());

                        pOutput.accept(ModItems.CASSOWARY_EGG.get());
                        pOutput.accept(ModItems.GRENADE_EGG.get());

                        pOutput.accept(ModItems.BIRDSTEEL_INGOT.get());
                        pOutput.accept(ModItems.BEATEN_SHAFT.get());
                        pOutput.accept(ModItems.BLUE_CLOTH_SCRAPS.get());
                        pOutput.accept(ModItems.STICKY_SALIVA.get());
                        pOutput.accept(ModItems.HERONS_BILL.get());
                        pOutput.accept(ModItems.LYRE_FEATHERS.get());

                        pOutput.accept(ModItems.BRINE_SHRIMP_EGG_PACKET.get());
                        pOutput.accept(ModItems.BRINE_SHRIMP.get());

                        pOutput.accept(ModBlocks.BIRDSTEEL_BLOCK.get());
                        ModEnchantments.addAllEnchantsToCreativeTab(pOutput, ModEnchantments.LANCE);
                        ModEnchantments.addAllEnchantsToCreativeTab(pOutput, ModEnchantments.ACCORDION);
                        ModEnchantments.addAllEnchantsToCreativeTab(pOutput, ModEnchantments.GUN);

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
