package net.waucka.evernight;

import java.util.HashSet;
import net.minecraft.resources.ResourceLocation;

public class EvernightConfig {
    public EvernightConfig() {
	this.fixedTime = 14000;
	this.dimensions = new HashSet<ResourceLocation>();
	this.dimensions.add(new ResourceLocation("minecraft:overworld"));
    }

    public EvernightConfig(int fixedTime, HashSet<ResourceLocation> dimensions) {
	this.fixedTime = fixedTime;
	this.dimensions = dimensions;
    }

    public int fixedTime;
    public HashSet<ResourceLocation> dimensions;
}
