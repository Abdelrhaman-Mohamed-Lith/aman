package com.utilities;

public class ValidatorUtil
{
    public static void isEmptyRequired(Object field, String fieldId, Result result)
    {
        if (ObjectChecker.isEmptyOrZeroOrNull(field))
            result.failure(Translator.translate(fieldId) + Translator.translate(" required"));
    }
}
