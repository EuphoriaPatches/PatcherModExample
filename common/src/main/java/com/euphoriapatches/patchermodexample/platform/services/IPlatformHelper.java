package com.euphoriapatches.patchermodexample.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Checks if the game is running on the physical client, shaders only exist there.
     *
     * @return True on the client, false on a dedicated server.
     */
    boolean isClient();

    /**
     * Gets the default shaderpacks folder of the loader, which is {@code <game directory>/shaderpacks}.
     * Use {@link #getShaderpacksDir()} unless you need the default specifically.
     *
     * @return The default path of the shaderpacks folder, it might not exist yet.
     */
    Path getDefaultShaderpacksDir();

    /**
     * Gets the shaderpacks folder of the game, which is where shaderpacks are installed and where the patched
     * packs are written to. Usually that is {@code <game directory>/shaderpacks}, but some launchers (Dawn Client
     * for example) move it elsewhere while Iris still works, so Iris is asked where it reads shaderpacks from.
     * If anything goes wrong, fall back to {@link #getDefaultShaderpacksDir()}.
     *
     * @return The path of the shaderpacks folder, it might not exist yet.
     */
    default Path getShaderpacksDir() {

        Path defaultDir = getDefaultShaderpacksDir();
        try {
            Object result = Class.forName("net.irisshaders.iris.Iris").getMethod("getShaderpacksDirectory").invoke(null);
            return result instanceof Path ? (Path) result : defaultDir;
        } catch (Throwable t) {
            return defaultDir;
        }
    }

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }
}
