package net.waucka.evernight.forge;

import net.waucka.evernight.EvernightConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
//import java.util.regex.Pattern;

public class EvernightForgeConfig {
    public static ForgeConfigSpec.IntValue FIXED_TIME;
    public static ForgeConfigSpec.ConfigValue<List<String>> DIMENSIONS;
    //public static final Pattern DIMENSION_PATTERN = Pattern.compile("[a-zA-Z0-9]+:[a-zA-Z0-9]+");

    public static void registerConfig() {
	ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        FIXED_TIME = SERVER_BUILDER
	    .comment("Time of day (in ticks) at which celestial time should appear to be frozen")
	    .defineInRange("fixedTime", 14000, 0, 23999);
	List<String> defaultDimensions = Arrays.asList(new String[]{"minecraft:overworld"});
        DIMENSIONS = SERVER_BUILDER
	    .comment("Dimensions in which celestial time should appear to be frozen")
	    .define(
			"dimensions",
			new ArrayList<>()//defaultDimensions,
			//dim -> (DIMENSION_PATTERN.matcher((String)dim).matches())
			);

	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SERVER_BUILDER.build());
    }

    public static EvernightConfig get() {
	HashSet<ResourceLocation> dims = new HashSet<ResourceLocation>();
	for (String dim : DIMENSIONS.get()) {
	    dims.add(new ResourceLocation(dim));
	}

	return new EvernightConfig(FIXED_TIME.get(), dims);
    }
}
