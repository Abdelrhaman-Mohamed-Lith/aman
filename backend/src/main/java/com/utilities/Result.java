package com.utilities;

import java.text.MessageFormat;

public class Result {
    private boolean failed;
    private String message = "";

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String massage) {
        this.message = massage;
    }

    public boolean succeeded() {
        return !failed;
    }

    public static Result createSuccessfulResult() {
        return new Result();
    }

    public static Result createFailureResult(String message) {
        Result result = new Result();
        result.setFailed(true);
        result.setMessage(Translator.translateMessage(message));
        return result;
    }

    public void failure(String message) {
        failure(message, "");
    }

    public <T extends Object> void failure(String message, T obj) {
        setFailed(true);
        String newMessage = Translator.translateMessage(MessageFormat.format(message, obj)) ;
        setMessage(ObjectChecker.isNotEmptyOrZeroOrNull(getMessage()) ? getMessage() + "\n" + newMessage : newMessage);
    }

    public void accmulate(Result result) {
        if (result == null)
            return;
        if (failed == false)
            failed = result.isFailed();
        String resultMessage = result.getMessage();
        if (ObjectChecker.isEmptyOrZeroOrNull(resultMessage))
            return;
        message = ObjectChecker.isEmptyOrZeroOrNull(message) ? resultMessage
                : message + "\n" + Translator.translateMessage(resultMessage);
    }
}
