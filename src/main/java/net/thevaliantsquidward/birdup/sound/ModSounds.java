package net.thevaliantsquidward.birdup.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.birdup.BirdUp;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BirdUp.MOD_ID);

    public static final RegistryObject<SoundEvent> ANNOYING_ACCORDION = createSoundEvent("annoying_accordion");
    public static final RegistryObject<SoundEvent> SCREAMER_ATTACK = createSoundEvent("screamer_attack");
    public static final RegistryObject<SoundEvent> ANNOYING_ACCORDION_ATTACK_STRONG = createSoundEvent("accordion_attack");
    public static final RegistryObject<SoundEvent> BIRDS_ARISE = createSoundEvent("birds_arise");
    public static final RegistryObject<SoundEvent> BOWERD = createSoundEvent("bowerd");
    public static final RegistryObject<SoundEvent> GUNFIRE = createSoundEvent("gunfire");
    public static final RegistryObject<SoundEvent> RELOAD = createSoundEvent("reload");
    public static final RegistryObject<SoundEvent> ROCKETFIRE = createSoundEvent("rocket_fire");
    public static final RegistryObject<SoundEvent> AUTOMATICFIRE = createSoundEvent("automatic_fire");
    public static final RegistryObject<SoundEvent> SHOTGUNFIRE = createSoundEvent("shotgun_fire");

    private static RegistryObject<SoundEvent> createSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BirdUp.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}
