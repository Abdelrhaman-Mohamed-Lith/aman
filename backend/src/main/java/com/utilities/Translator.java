package com.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Translator {
    private static Properties arTranslation;

    private static void iniArTranslation() {
        arTranslation = new Properties();
        try (Reader r = new FileReader(
                "resources/ar.properties",
                StandardCharsets.UTF_8)) {
            arTranslation.load(r);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String translate(String key) {
        if (true) return key;
        if (key == null)
            return "";
        if (ObjectChecker.isEmptyOrZeroOrNull(arTranslation))
            iniArTranslation();
        String value = key;
        if (ObjectChecker.isNotEmptyOrZeroOrNull(arTranslation.get(key)))
            value = arTranslation.get(key).toString();
        return value;
    }

    private static Properties arMessageTranslation;

    private static void iniArMessageTranslation() {
        arMessageTranslation = new Properties();
        try (Reader r = new FileReader(
                "resources/ar-message.properties",
                StandardCharsets.UTF_8)) {
            arMessageTranslation.load(r);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String translateMessage(String key) {
        if (true) return key;
        if (key == null)
            return "";
        if (ObjectChecker.isEmptyOrZeroOrNull(arMessageTranslation))
            iniArMessageTranslation();
        String value = key;
        if (ObjectChecker.isNotEmptyOrZeroOrNull(arMessageTranslation.get(key)))
            value = arMessageTranslation.get(key).toString();
        return value;
    }
}
