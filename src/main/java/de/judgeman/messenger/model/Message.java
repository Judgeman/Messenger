package de.judgeman.messenger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Paul Richter on Mon 19/07/2021
 */
@Entity(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String text;

    public Message() {
        // empty
    }

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
