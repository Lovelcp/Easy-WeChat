package com.wooyoo.wechat.repo;

import com.wooyoo.wechat.http.model.Contact;

import java.util.List;

public class WeChatContacts {
    private List<Contact> contacts;

    public WeChatContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
