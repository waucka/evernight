package net.waucka.evernight;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;

import java.nio.file.Path;

public class EvernightExpectPlatform {
    /**
     * We can use {@link Platform#getConfigFolder()} but this is just an example of {@link ExpectPlatform}.
     * <p>
     * This must be a <b>public static</b> method. The platform-implemented solution must be placed under a
     * platform sub-package, with its class suffixed with {@code Impl}.
     * <p>
     * Example:
     * Expect: net.waucka.evernight.ExpectPlatform#getConfigDirectory()
     * Actual Fabric: net.waucka.evernight.fabric.FabricExpectPlatformImpl#getConfigDirectory()
     * Actual Forge: net.waucka.evernight.forge.ForgeExpectPlatformImpl#getConfigDirectory()
     * <p>
     * <a href="https://plugins.jetbrains.com/plugin/16210-architectury">You should also get the IntelliJ plugin to help with @ExpectPlatform.</a>
     */
    @ExpectPlatform
    public static EvernightConfig getConfig() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerConfig() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
