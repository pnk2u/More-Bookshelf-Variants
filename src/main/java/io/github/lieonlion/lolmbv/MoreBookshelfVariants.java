package io.github.lieonlion.lolmbv;

import io.github.lieonlion.lolmbv.init.MbvItemInit;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import io.github.lieonlion.lolmbv.mixin.FireBlockInvoker;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(MoreBookshelfVariants.MODID)
public class MoreBookshelfVariants {
    public static final String MODID = "lolmbv";

    public MoreBookshelfVariants() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MbvBlockInit.registerBlocks(modEventBus);
        MbvItemInit.registerItems(modEventBus);

        modEventBus.addListener(MbvItemInit::addItemsToTab);
        modEventBus.addListener(this::register);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.BLOCKS, (helper) -> {
            registerFlammableBlocks();
        });
    }

    private void registerFlammableBlocks() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.SPRUCE_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.BIRCH_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.JUNGLE_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.ACACIA_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.DARK_OAK_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.MANGROVE_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.CHERRY_BOOKSHELF.get(), 30, 20);
        ((FireBlockInvoker) fireBlock).callSetFlammable(MbvBlockInit.BAMBOO_BOOKSHELF.get(), 30, 20);
    }
}