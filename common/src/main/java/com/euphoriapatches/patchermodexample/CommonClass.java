package com.euphoriapatches.patchermodexample;

import com.euphoriapatches.patchermodexample.platform.Services;

// This class is part of the common project meaning it is shared between all supported loaders. It is invoked by the
// entry point of each loader specific project.
public class CommonClass {

    public static void init() {

        // Shaders only exist on the client. This is where the ShaderPatcher library is used.
        if (Services.PLATFORM.isClient()) {
            ExampleShaderPatching.run(Services.PLATFORM.getShaderpacksDir());
        }
    }
}
