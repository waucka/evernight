package net.waucka.evernight.fabric;

import net.waucka.evernight.Evernight;
import net.fabricmc.api.ModInitializer;

public class EvernightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Evernight.init();
	Evernight.loadConfig();
    }
}
