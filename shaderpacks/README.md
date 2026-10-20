# Example shaderpacks

Both packs in this folder are **only examples** to show how the [ShaderPatcher](https://github.com/EuphoriaPatches/ShaderPatcher) library is used. They are not meant to be good shaderpacks.
### VERY IMPORTANT: In production you would .gitignore the `shaderpacks/` folder and only ship the patch file, not the edited shaderpack. 
### 

| File           | What it is                                                                                                                                                                               |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Base-120-v2.zip` | The *base*: the Base-120 template shaderpack, zipped and given a version (`-v2`) so the example shows both `baseName` and `baseVersion`. This is the pack a player already has. [From ShaderLABS](https://github.com/shaderLABS/Base-120)                                   |
| `MyEdit/`      | The *edit*: `Base-120` with one change, `shaders/composite.fsh` boosts the saturation and warms the image. This is **your** finished pack, kept as a folder so you can edit it directly. |

## How they are used

1. `./gradlew :tools:generatePatch` compares `Base-120-v2.zip` with the `MyEdit` folder and writes the small
   `common/src/main/resources/MyEdit_1.0.patch`. This is the only thing that ships with the mod, players never receive `MyEdit`.
   You only need `MyEdit` on your own machine, to create the patch. (Can also be a ZIP file, does not need to be a folder.)
2. When the game starts, the mod looks for `Base-120-v2.zip` in the game's `shaderpacks` folder and applies the patch to it,
   see `ExampleShaderPatching` in the `common` project. It creates two packs there, to show both naming options:
   * `OutputNaming.BASE_PLUS_PATCH` (the default): `<base name> + MyEdit_1.0`
   * `OutputNaming.PATCH_ONLY`: `MyEdit_1.0`

   Both contain exactly the same files as the `MyEdit` folder.

`./gradlew :fabric:runClient` and `./gradlew :neoforge:runClient` copy `Base-120-v2.zip` into the dev game's `shaderpacks` folder for you.
In a normal install, the player just needs `Base-120-v2.zip` in their `shaderpacks` folder.

If you change the `MyEdit` folder or `Base-120-v2.zip`, run `./gradlew :tools:generatePatch` again. The patch stores the size and SHA-256 of the base, so a changed
`Base-120-v2.zip` will be rejected by the old patch.

`Base-120` is public domain, see the `LICENSE` file inside the zip.
