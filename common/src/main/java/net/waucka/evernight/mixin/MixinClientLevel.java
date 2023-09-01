package net.waucka.evernight.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.core.Holder;
import net.minecraft.world.level.dimension.DimensionType;
import java.util.function.Supplier;
import net.minecraft.util.profiling.ProfilerFiller;

import net.waucka.evernight.Evernight;

@Mixin(ClientLevel.class)
public abstract class MixinClientLevel extends Level {
    protected MixinClientLevel(WritableLevelData p_204149_, ResourceKey<Level> p_204150_, Holder<DimensionType> p_204151_, Supplier<ProfilerFiller> p_204152_, boolean p_204153_, boolean p_204154_, long p_204155_)
    {
	super(p_204149_, p_204150_, p_204151_, p_204152_, p_204153_, p_204154_, p_204155_);
    }

    @Override
    public float getTimeOfDay(float unused) {
	final ResourceLocation dimension = this.dimension().location();

	if (Evernight.CONFIG.dimensions.contains(dimension)) {
	    // Convert from ticks to the getTimeOfDay() scheme, where
	    // 0.0 = noon
	    // 0.5 = midnight
	    return ((Evernight.CONFIG.fixedTime + 18000) % 24000) / 24000f;
	}

	return super.getTimeOfDay(unused);
    }

    @Override
    public long getDayTime() {
	return Evernight.CONFIG.fixedTime;
    }
}
