package net.thevaliantsquidward.birdup.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.birdup.BirdUp;
import net.thevaliantsquidward.birdup.entity.custom.GrenadeEgg;
import net.thevaliantsquidward.birdup.entity.custom.ThrownCassowaryEgg;

import static net.thevaliantsquidward.birdup.BirdUp.prefix;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BirdUp.MOD_ID);


    public static final RegistryObject<EntityType<ThrownCassowaryEgg>> CASSOWARY_EGG =
            ENTITY_TYPES.register("cassowary_egg", () -> EntityType.Builder.<ThrownCassowaryEgg>of(ThrownCassowaryEgg::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("cassowary_egg"));

    public static final RegistryObject<EntityType<GrenadeEgg>> GRENADE_EGG =
            ENTITY_TYPES.register("grenade_egg", () -> EntityType.Builder.<GrenadeEgg>of(GrenadeEgg::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("grenade_egg"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
