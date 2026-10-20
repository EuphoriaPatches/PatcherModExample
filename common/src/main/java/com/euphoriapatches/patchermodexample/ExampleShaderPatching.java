package com.euphoriapatches.patchermodexample;

import com.euphoriapatches.shaderpatcher.OutputNaming;
import com.euphoriapatches.shaderpatcher.PatchResult;
import com.euphoriapatches.shaderpatcher.PatchSource;
import com.euphoriapatches.shaderpatcher.ShaderPatcher;
import com.euphoriapatches.shaderpatcher.exception.BaseHashMismatchException;
import com.euphoriapatches.shaderpatcher.exception.BaseSizeMismatchException;
import com.euphoriapatches.shaderpatcher.exception.OutputExistsException;
import com.euphoriapatches.shaderpatcher.exception.ShaderPatcherException;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Shows how to use the ShaderPatcher library: turn the shaderpack the user already has (Base-120-v2.zip) into the
 * MyEdit pack, using the small MyEdit_1.0.patch file that is bundled in this mod's resources.
 * <p>
 * The patch is created by {@code ./gradlew :tools:generatePatch}, see GeneratePatch in the tools project.
 * What the patch is called is defined once, in {@link PatchInfo}.
 */
public final class ExampleShaderPatching {

    /**
     * Patches the base pack found in the shaderpacks folder and creates the result packs next to it.
     *
     * @param shaderpacks the game's shaderpacks folder, used for both the base pack and the results
     */
    public static void run(Path shaderpacks) {
        Path base = shaderpacks.resolve(PatchInfo.BASE_PACK);
        if (!Files.exists(base)) {
            Constants.LOG.warn("{} not found, nothing to patch. Put the shaderpacks/{} of this repository into {}", base, PatchInfo.BASE_PACK, shaderpacks);
            return;
        }

        // BASE_PLUS_PATCH is the default: "<base name> + MyEdit_1.0"
        // The result is named after the base it was made from
        apply(base, shaderpacks, OutputNaming.BASE_PLUS_PATCH);

        // PATCH_ONLY ignores the base name: "MyEdit_1.0"
        // Use this if you don't want to have the base pack name in your name.
        apply(base, shaderpacks, OutputNaming.PATCH_ONLY);
    }

    private static void apply(Path base, Path shaderpacks, OutputNaming naming) {
        try {
            // A patcher only reads the patch header when it is built and is immutable, so it is cheap to create and could be kept
            ShaderPatcher patcher = ShaderPatcher
                    .builder(PatchSource.classpath(ExampleShaderPatching.class, PatchInfo.PATCH_RESOURCE))
                    .expectPatchName(PatchInfo.SPEC.getPatchName()) // catches packaging mistakes: wrong .patch file in the jar
                    .expectPatchVersion(PatchInfo.SPEC.getPatchVersion())
                    .outputNaming(naming)
                    .debugLog(Constants.LOG::debug) // the library never logs by itself, attach your own logger if you want to see what it does
                    .build();

            // Checks the base, applies the patch and creates the pack folder. The base is never modified
            PatchResult result = patcher.apply(base, shaderpacks);
            Constants.LOG.info("Created {}", result.getOutputDir());

        } catch (OutputExistsException e) {
            // The default policy ExistingOutput.FAIL never overwrites anything. Use ExistingOutput.REPLACE to update instead
            Constants.LOG.info("{} already exists, skipping", e.getPath());
        } catch (BaseSizeMismatchException e) {
            // The user has another version of the pack, or an incomplete download
            Constants.LOG.error("{} is not the pack this patch was made for (expected {} bytes, found {})", e.getPath(), e.getExpected().getSize(), e.getActual().getSize());
        } catch (BaseHashMismatchException e) {
            // Right size, but the content was changed
            Constants.LOG.error("{} was modified, the patch only works on the original pack", e.getPath());
        } catch (ShaderPatcherException e) {
            // Everything else, the class name says what happened and who has to act
            Constants.LOG.error("Failed to apply the patch to {}", base, e);
        }
    }

    private ExampleShaderPatching() {
    }
}
