package net.thevaliantsquidward.birdup;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thevaliantsquidward.birdup.block.ModBlocks;
import net.thevaliantsquidward.birdup.enchantment.ModEnchantments;
import net.thevaliantsquidward.birdup.entity.ModEntities;
import net.thevaliantsquidward.birdup.item.ModItems;
import net.thevaliantsquidward.birdup.sound.ModSounds;
import net.thevaliantsquidward.birdup.tab.ModCreativeModeTab;
import org.slf4j.Logger;

import java.util.Locale;


@Mod(BirdUp.MOD_ID)
public class BirdUp
{
    public static final String MOD_ID = "birdup";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BirdUp()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);

        ModBlocks.BLOCKS.register(modEventBus);

        ModEntities.ENTITY_TYPES.register(modEventBus);

        ModEnchantments.ENCHANTMENTS.register(modEventBus);

        ModCreativeModeTab.register(modEventBus);

        ModSounds.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.CASSOWARY_EGG.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.GRENADE_EGG.get(), ThrownItemRenderer::new);

        }
    }
}
