package tech.mopip77.symbollinkmapper.utils;

import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.exception.SupplimentErrorCode;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SymbolLinkUtils {
    public static boolean createDirectSymbolLink(Path sourcePath, Path destPath) {
        if (destPath.toFile().exists()) {
            return false;
        }

        try {
            Files.createSymbolicLink(destPath, sourcePath);
            return true;
        } catch (IOException e) {
            throw new CustomizeException(new SupplimentErrorCode(e.toString()));
        }
    }
}
