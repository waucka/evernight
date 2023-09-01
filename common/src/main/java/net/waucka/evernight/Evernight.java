package net.waucka.evernight;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Evernight {
    public static final String MOD_ID = "evernight";
    public static EvernightConfig CONFIG = null;
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static void init() {
	EvernightExpectPlatform.registerConfig();
    }

    public static void loadConfig() {
	CONFIG = EvernightExpectPlatform.getConfig();
	LOGGER.info("Loading config...");
	LOGGER.info("fixed time of day: {}", CONFIG.fixedTime);
	ArrayList<String> dimensions = new ArrayList<String>();
	for (ResourceLocation dim : CONFIG.dimensions) {
	    dimensions.add(dim.toString());
	}
	LOGGER.info("dimensions with fixed time of day: {}", String.join(", ", dimensions));
	LOGGER.info("Config loaded!");
    }

    // This method exists purely to get around the fact that getMoonPhase() comes from an interface default.
    public static int getMoonPhase(Level world) {
	return world.getMoonPhase();
    }
}
