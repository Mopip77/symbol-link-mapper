package tech.mopip77.symbollinkmapper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.mopip77.symbollinkmapper.SymbolLinkMapperApplication;

import java.util.Arrays;

public class LoggerUtils {

    private static Logger globalLogger = LoggerFactory.getLogger(SymbolLinkMapperApplication.class);

    public static Logger getGlobalLogger() {
        return globalLogger;
    }

    public static <T> String joinArray(T[] array, String separator) {
        if (array == null || array.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].toString());
            if (i != array.length - 1)
                sb.append(separator);
        }

        return sb.toString();
    }
}
