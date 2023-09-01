package net.waucka.evernight.forge;

import net.waucka.evernight.EvernightConfig;
import net.waucka.evernight.EvernightExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class EvernightExpectPlatformImpl {
    /**
     * This is our actual method to {@link EvernightExpectPlatform#getConfigDirectory()}.
     */
    public static EvernightConfig getConfig() {
	return EvernightForgeConfig.get();
    }

    public static void registerConfig() {
	EvernightForgeConfig.registerConfig();
    }
}
