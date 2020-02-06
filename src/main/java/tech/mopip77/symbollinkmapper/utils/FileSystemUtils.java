package tech.mopip77.symbollinkmapper.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.dto.RenameResultDTO;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileSystemUtils {
    /**
     * for sake of the strength of the software, all path need to be absolute path
     * just check if it is start with '/' and contains no '/../'
     *
     * @param path
     * @return
     */
    public static boolean isAbsolutePath(String path) {
        return StringUtils.isNotBlank(path) &&
                StringUtils.startsWith(path, "/") &&
                !StringUtils.contains(path, "/../");
    }

    public static boolean areAbsolutePaths(String... paths) {
        return Arrays.stream(paths).allMatch(FileSystemUtils::isAbsolutePath);
    }

    public static boolean isFolder(String path) {
        File folder = new File(path);
        return folder.isDirectory();
    }

    public static boolean areAllFolder(String... paths) {
        return Arrays.stream(paths).allMatch(FileSystemUtils::isFolder);
    }

    public static boolean areAllExist(String... paths) {
        return Arrays.stream(paths).map(File::new).allMatch(File::exists);
    }

    public static boolean rename(String path, String newName) {
        File sourceFile = new File(path);

        if (!sourceFile.exists()) {
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);
        }

        // new name validity check
        File parentFile = sourceFile.getParentFile();
        File newFilePath = new File(parentFile, newName);
        return sourceFile.renameTo(newFilePath);
    }

    /**
     * create real directory tree as sourcePath by BFS
     * for folder -> create folder indeed, for file -> create symbol link
     *
     * @param sourcePath     sourcePath needs to be a folder
     * @param destPath
     * @param allowOverWrite if the destPath exists and allowOverWrite is true then delete destPath and recreate
     * @return
     */
    public static boolean recursiveCreateSymbolLink(String sourcePath, String destPath, boolean allowOverWrite) {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        if (!sourceFile.isDirectory()) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_A_FOLDER);
        }

        if (destFile.exists()) {
            if (allowOverWrite) {
                boolean deleted = destFile.delete();
                if (!deleted) {
                    throw new CustomizeException(CustomizeErrorCode.DEST_FILE_DELETE_FAIL);
                }
            } else throw new CustomizeException(CustomizeErrorCode.DEST_PATH_EXIST);
        }

        return true;
    }

    public static boolean deleteRecursively(File deleteFile) {
//        if the file is symbol link and the source file is deleted, then the exists() result is false,
//        so we can not quick check using exists()
//        if (!deleteFile.exists()) return true;

        boolean deleted = true;

        if (deleteFile.isDirectory()) {
            for (File f : deleteFile.listFiles()) {
                deleted &= deleteRecursively(f);
            }
        }

        return deleted && deleteFile.delete();
    }

    public static String[] listFiles(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_A_FOLDER);
        }

        return file.list();
    }

    public static boolean permittedFileName(String name) {
        return StringUtils.containsNone(name, "/\\:*\"<>|?");
    }

    /**
     * Get rename mapper by regx using linux `rename` command, but will not really execute.
     *
     * @param paths should exist, and are absolute paths (rename command needs to input real file path, by the way it can check permission and duplicate name error)
     * @param regx
     * @return If success return with output data, if rename fail return with error output data, if other error throw error
     */
    public static RenameResultDTO renameByRegxDryly(String[] paths, String regx) {
        RenameResultDTO result = new RenameResultDTO();
        String[] execCmd = ArrayUtils.addAll(ArrayUtils.add(new String[]{"rename", "-n"}, regx), paths);

        try {
            Process exec = Runtime.getRuntime().exec(execCmd);
            String line;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String[] outputLines = {};
            while ((line = bufferedReader.readLine()) != null) {
                outputLines = ArrayUtils.add(outputLines, line);
            }

            BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            String[] errorOutputLines = {};
            while ((line = errorBufferedReader.readLine()) != null) {
                errorOutputLines = ArrayUtils.add(errorOutputLines, line);
            }

            int exitCode = exec.waitFor();
            result.setExitCode(exitCode);
            result.setRegx(regx);

            System.out.println("output--");
            for (String outputLine : outputLines) {
                System.out.println(outputLine);
            }

            System.out.println("error--");
            for (String errorOutputLine : errorOutputLines) {
                System.out.println(errorOutputLine);
            }

            if (exitCode == 0) {
                // exec success
                result.setOutput(outputLines);
                // build map
                Map<String, String> renameMap = extractMapFromRenameOutput(paths, outputLines);
                result.setRenameMap(renameMap);
            } else {
                result.setOutput(errorOutputLines);
            }

            return result;

        } catch (IOException | InterruptedException e) {
            // exec error
            throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);
        }
    }

    /**
     * Get old and new name mapper.
     *
     * @param oldNames
     * @param output
     * @return if the length of old names and the length of rename mapper are not equal, return null
     */
    private static Map<String, String> extractMapFromRenameOutput(String[] oldNames, String[] output) {
        if (oldNames == null || output == null || output.length == 0 || oldNames.length == 0)
            return null;

        // EXCLUDED LINE RULE
        // 1. using () extract part then using \1 to use them, it will cause warning
        // \1 better written as $1 at (eval 5) line 1. (but $1 means the first argument, so we can only use \1)
        // 惯性思维坑人奥，直接找rename开头的即可

        // RENAME MAP FORMAT
        // rename(As01e01.mp3, Bs02e01.mp3)
        // but file name allows to contain ',', so we can not use ',' to separate old and new name
        // we can straightly separate by old name

        // MAC OS version
//        List<String> collect = Arrays.stream(output).filter(line -> StringUtils.startsWith(line, "'")).collect(Collectors.toList());
//        String[] mapper = new String[collect.size()];
//        collect.toArray(mapper);
//
//
//        if (oldNames.length != mapper.length)
//            return null;
//
//        HashMap<String, String> map = new HashMap<>();
//        for (int i = 0; i < oldNames.length; i++) {
//            String[] split = StringUtils.splitByWholeSeparator(mapper[i], "would be renamed to '");
//            if (split.length != 2)
//                throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);
//
//            String newName = StringUtils.substring(split[1], 0, split[1].length() - 1);
//            map.put(oldNames[i], newName);
//        }
//
//        return map;

        // LINUX version
        // Object[] can not cast to String[]
        List<String> collect = Arrays.stream(output).filter(line -> StringUtils.startsWith(line, "rename(")).collect(Collectors.toList());
        String[] mapper = new String[collect.size()];
        collect.toArray(mapper);

        if (oldNames.length != mapper.length)
            return null;

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < oldNames.length; i++) {
            String[] split = StringUtils.splitByWholeSeparator(mapper[i], oldNames[i]);
            if (split.length != 2)
                throw new CustomizeException(CustomizeErrorCode.SYS_ERROR);

            String newName = StringUtils.substring(split[1], 2, split[1].length() - 1);
            map.put(oldNames[i], newName);
        }

        return map;
    }

    public static Comparator<File> comparator() {
        return (o1, o2) -> {
            if (o1.isDirectory() != o2.isDirectory()) {
                return o1.isDirectory() ? -1 : 1;
            } else return o1.getName().compareTo(o2.getName());
        };
    }
}
