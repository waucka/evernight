package net.waucka.evernight.forge;

import dev.architectury.platform.forge.EventBuses;
import net.waucka.evernight.Evernight;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Evernight.MOD_ID)
public class EvernightForge {
    public EvernightForge() {
        // Submit our event bus to let architectury register our content on the right time
	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
	eventBus.addListener(this::config);
        EventBuses.registerModEventBus(Evernight.MOD_ID, eventBus);
        Evernight.init();
    }

    private void config(final ModConfigEvent evt) {
	if (evt.getConfig().getModId().equals(Evernight.MOD_ID)) {
	    Evernight.loadConfig();
	}
    }
}
