package net.thevaliantsquidward.birdup.block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.birdup.BirdUp;
import net.thevaliantsquidward.birdup.item.ModItems;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.item.Items.registerBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            BirdUp.MOD_ID);

    public static final RegistryObject<Block> BIRDSTEEL_BLOCK = registerBlock("birdsteel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        Supplier<T> entry = create(key, block);
        ModItems.ITEMS.register(key, () -> item.apply(entry));
        return entry;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block, CreativeModeTab tab) {
        return create(key, block, entry -> new BlockItem(entry.get(), new Item.Properties()));
    }



    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }
}
