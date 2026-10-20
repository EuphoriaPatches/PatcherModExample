# PatcherModExample

An example mod (Fabric and NeoForge, Minecraft 26.3) for the [ShaderPatcher](https://github.com/EuphoriaPatches/ShaderPatcher) library.
It turns a shaderpack the user already has into a patched pack, using a small patch file bundled in the mod.

Built from the [MultiLoader Template](https://github.com/jaredlll08/MultiLoader-Template). 26.3 version. If you want it to span multiple MC versions, have fun with gradle and the mod loaders

## Where to look

| What                                                                              | Where                                                                     |
|-----------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Applying a patch, both output names, error handling                               | `common/.../ExampleShaderPatching.java`                                   |
| Finding the shaderpacks folder (`<game dir>/shaderpacks`)                         | `getShaderpacksDir()` in the `platform` helper of each loader             |
| Bundling the library into the mod jar                                             | `fabric/build.gradle` (`include`), `neoforge/build.gradle` (`jarJar`)     |
| Name, version and base of the patch (defined once, used by the mod and the tool)  | `common/.../PatchInfo.java`                                               |
| Generating the patch                                                              | `tools/.../GeneratePatch.java`, run with `./gradlew :tools:generatePatch` |
| The example packs                                                                 | `shaderpacks/`, see the README in there                                   |

## Trying it

`./gradlew :fabric:runClient` or `./gradlew :neoforge:runClient`


This copies `shaderpacks/Base-120-v2.zip` into the dev game's `shaderpacks` folder. On startup the mod patches it and creates two packs next to it, one with
`OutputNaming.BASE_PLUS_PATCH` and one with `OutputNaming.PATCH_ONLY`. Starting again skips them because they already exist.

Java 25 is needed to build, like with the template.

## Building

`./gradlew buildAll`

Build the mod. Fabric and Neogorge JARs will be at `PatcherModExample/build/libs`

Cleans `build/libs` first, then builds the Fabric and NeoForge jars into it. Both jars contain the library and the patch.  
Should just work on any 26.3 Minecraft instance as long as you have the base shaderpack `Base-120-v2.zip` in your `shaderpacks` folder. The mod will patch it and create the two output packs.

## License

[MIT](LICENSE). Use it for anything, including as the base of your own mod under a different license, just keep the `LICENSE` in the jar.
