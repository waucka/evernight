package net.waucka.evernight.fabric;

import net.waucka.evernight.EvernightConfig;
import net.waucka.evernight.EvernightExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.ConfigSpec;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EvernightExpectPlatformImpl {
    /**
     * This is our actual method to {@link EvernightExpectPlatform#getConfigDirectory()}.
     */
    public static EvernightConfig getConfig() {
        Path configDir = FabricLoader.getInstance().getConfigDir();
	Path configFilePath = configDir.resolve("evernight.toml");
	FileConfig config = FileConfig.of(configFilePath);
	config.load();

	ConfigSpec spec = new ConfigSpec();
	spec.defineInRange("fixedTime", 14000, 0, 23999);
	List<String> defaultDimensions = Arrays.asList(new String[]{"minecraft:overworld"});
	spec.define("dimensions", defaultDimensions);
	if (!spec.isCorrect(config)) {
	    spec.correct(config);
	    config.save();
	}

	int fixedTime = config.get("fixedTime");
	List<String> dimensionsStr = config.get("dimensions");
	HashSet<ResourceLocation> dimensions = new HashSet<ResourceLocation>();
	for (String dim : dimensionsStr) {
	    dimensions.add(new ResourceLocation(dim));
	}

	EvernightConfig cfg = new EvernightConfig(fixedTime, dimensions);
	return cfg;
    }

    public static void registerConfig() {
    }
}
