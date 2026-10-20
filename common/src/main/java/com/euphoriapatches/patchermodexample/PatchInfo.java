package com.euphoriapatches.patchermodexample;

import com.euphoriapatches.shaderpatcher.PatchSpec;

/**
 * Describes the patch in one place. The mod reads it to apply the patch, and the {@code tools} project compiles this
 * same file to generate it, so the two can never disagree.
 */
public final class PatchInfo {

    public static final PatchSpec SPEC = PatchSpec.builder()
            .patchName("MyEdit").patchVersion("_1.0") // this should match the name of the patch and your version
            .baseName("Base-120").baseVersion("-v2") // name and version of the base pack you are patching, together they are its file name (Base-120-v2.zip)
            .build();

    /** File name of the pack the patch was made for. Needs to be identical to the base pack name the user has, otherwise it won't be found. */
    public static final String BASE_PACK = SPEC.getBaseName() + SPEC.getBaseVersion() + ".zip";

    /** Name of the patch file in the mod's resources: MyEdit_1.0.patch */
    public static final String PATCH_RESOURCE = SPEC.getPatchFileName();

    private PatchInfo() {
    }
}
