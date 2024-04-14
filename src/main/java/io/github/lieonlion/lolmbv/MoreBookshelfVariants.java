package io.github.lieonlion.lolmbv;

import io.github.lieonlion.lolmbv.init.MbvItemInit;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import io.github.lieonlion.lolmbv.mixin.FireBlockInvoker;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(MoreBookshelfVariants.MODID)
public class MoreBookshelfVariants {
    public static final String MODID = "lolmbv";

    public MoreBookshelfVariants(IEventBus modBus) {

        MbvBlockInit.registerBlocks(modBus);
        MbvItemInit.registerItems(modBus);

        modBus.addListener(MbvItemInit::addItemsToTab);
        modBus.addListener(this::register);
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        event.register(Registries.BLOCK, (helper) -> {
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