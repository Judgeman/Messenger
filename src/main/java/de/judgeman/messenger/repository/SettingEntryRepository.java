package de.judgeman.messenger.repository;

import de.judgeman.messenger.model.SettingEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul Richter on Sat 24/07/2021
 */
@Repository
public interface SettingEntryRepository extends CrudRepository<SettingEntry, String> {

}
