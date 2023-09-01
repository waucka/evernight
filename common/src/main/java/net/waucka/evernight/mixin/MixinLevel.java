package net.waucka.evernight.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import net.waucka.evernight.Evernight;

@Mixin(Level.class)
public class MixinLevel {
    private static final int[] SKY_DARKNESS_MAP = new int[]{11, 12, 13, 14, 15, 14, 13, 12};

    @Shadow
    private int skyDarken;

    @Shadow
    public ResourceKey<Level> dimension() { return null; }

    @Inject(method = "getSunAngle", at = @At(value = "HEAD"), cancellable = true)
    private void onGetSunAngle(float unused, CallbackInfoReturnable<Float> cir) {
	final ResourceLocation dimension = this.dimension().location();

	if (Evernight.CONFIG.dimensions.contains(dimension)) {
	    // Convert from ticks to the getTimeOfDay() scheme, where
	    // 0.0 = noon
	    // 0.5 = midnight
	    float timeOfDay = ((Evernight.CONFIG.fixedTime + 18000) % 24000) / 24000f;
	    cir.setReturnValue(timeOfDay * ((float)Math.PI * 2F));
	}
    }

    @Inject(method = "updateSkyBrightness", at = @At(value = "RETURN"))
    private void onUpdateSkyBrightness(CallbackInfo ci) {
	final int moonphase = Evernight.getMoonPhase((Level) (Object) this);
	this.skyDarken = SKY_DARKNESS_MAP[moonphase];
    }
}
