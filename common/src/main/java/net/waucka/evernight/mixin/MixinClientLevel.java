package net.waucka.evernight.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.RegistryAccess;
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
    protected MixinClientLevel(WritableLevelData p_270739_, ResourceKey<Level> p_270683_, RegistryAccess p_270200_, Holder<DimensionType> p_270240_, Supplier<ProfilerFiller> p_270692_, boolean p_270904_, boolean p_270470_, long p_270248_, int p_270466_)
    {
	super(p_270739_, p_270683_, p_270200_, p_270240_, p_270692_, p_270904_, p_270470_, p_270248_, p_270466_);
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
