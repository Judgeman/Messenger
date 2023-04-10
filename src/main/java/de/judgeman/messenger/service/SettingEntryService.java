package de.judgeman.messenger.service;

import de.judgeman.messenger.model.SettingEntry;
import de.judgeman.messenger.repository.SettingEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Paul Richter on Sat 24/07/2021
 */
@Service
public class SettingEntryService {

    @Value("${applicationVersion}")
    private String applicationVersion;

    public static String MESSAGE_COUNTER = "MESSAGE_COUNTER";

    @Autowired
    private SettingEntryRepository settingEntryRepository;

    public int incrementMessageCounter() {
        SettingEntry settingEntry = getCounterValueSettingEntry();
        int counterValue = parseToInteger(settingEntry);
        counterValue++;

        settingEntry.setValue("" + counterValue);
        settingEntryRepository.save(settingEntry);

        return counterValue;
    }

    public String getApplicatonVersion() {
        return applicationVersion;
    }

    public int getMessageCounter() {
        SettingEntry settingEntry = getCounterValueSettingEntry();
        return parseToInteger(settingEntry);
    }

    private SettingEntry getCounterValueSettingEntry() {
        return settingEntryRepository.findById(MESSAGE_COUNTER).orElse(new SettingEntry(MESSAGE_COUNTER, "0"));
    }

    private int parseToInteger(SettingEntry settingEntry) {
        return Integer.parseInt(settingEntry.getValue());
    }
}
