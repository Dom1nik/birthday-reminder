package com.birthReminder.person;

public class Settings {
    private boolean optionSendViberNotification;
    private boolean optionSendEmailNotification;

    public Settings() {
        // Default constructor
    }

    public void setOptionSendEmailNotification(boolean optionSendEmailNotification) {
        optionSendEmailNotification = optionSendEmailNotification;
    }

    public boolean isOptionSendEmailNotification() {
        return optionSendEmailNotification;
    }

    public void setOptionSendViberNotification(boolean optionSendViberNotification) {
        optionSendViberNotification = optionSendViberNotification;
    }

    public boolean isOptionSendViberNotification() {
        return optionSendViberNotification;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "optionSendViberNotification=" + optionSendViberNotification +
                ", optionSendEmailNotification=" + optionSendEmailNotification +
                '}';
    }
}
