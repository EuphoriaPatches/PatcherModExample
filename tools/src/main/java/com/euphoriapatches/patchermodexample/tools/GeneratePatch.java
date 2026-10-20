package com.euphoriapatches.patchermodexample.tools;

import com.euphoriapatches.patchermodexample.PatchInfo;
import com.euphoriapatches.shaderpatcher.exception.ShaderPatcherException;
import com.euphoriapatches.shaderpatcher.generate.GenerateRequest;
import com.euphoriapatches.shaderpatcher.generate.GeneratedPatch;
import com.euphoriapatches.shaderpatcher.generate.PatchGenerator;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creates the patch that ships with the mod. This is the only place that needs the edited pack: the mod itself only
 * contains the small .patch file, so players never receive the full MyEdit pack.
 * <p>
 * Run it with {@code ./gradlew :tools:generatePatch}, arguments are: the shaderpacks directory, the output directory.
 * Name and version of the patch come from {@link PatchInfo}, which is the same file the mod uses. The base is
 * {@code <shaderpacks>/Base-120-v2.zip} and the edited pack is the folder {@code <shaderpacks>/MyEdit}.
 */
public final class GeneratePatch {

    static void main(String[] args) throws ShaderPatcherException {
        // entry point for the generatePatch Gradle task
        Path shaderpacks = Paths.get(args[0]);
        Path outputDir = Paths.get(args[1]);

        Path base = shaderpacks.resolve(PatchInfo.BASE_PACK);
        Path edited = shaderpacks.resolve(PatchInfo.SPEC.getPatchName());

        GeneratedPatch patch = PatchGenerator.generate(GenerateRequest.builder(PatchInfo.SPEC)
                .base(base)         // exactly what the players will have, its size and hash are stored in the patch
                .edited(edited)     // the finished edit of the base
                .addOutputDir(outputDir)
                .debugLog(System.out::println)
                .build());

        System.out.println("Wrote " + patch.getPatchFiles());
    }

    private GeneratePatch() {
    }
}
