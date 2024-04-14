package io.github.lieonlion.lolmbv.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FireBlock.class)
public interface FireBlockInvoker {
    @Invoker
    void callRegisterFlammableBlock(Block $$0, int $$1, int $$2);
}