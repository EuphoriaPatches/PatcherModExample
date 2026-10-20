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
     * Gets the shaderpacks folder of the game, which is where shaderpacks are installed and where the patched
     * packs are written to. It is {@code <game directory>/shaderpacks}.
     *
     * @return The path of the shaderpacks folder, it might not exist yet.
     */
    Path getShaderpacksDir();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }
}
