package me.burnet.fcore.file.impl;

import me.burnet.fcore.FactionCore;
import me.burnet.fcore.file.CustomFile;
import me.burnet.fcore.utils.Message;

public class MessageFile extends CustomFile {

    public MessageFile() {
        super(FactionCore.instance, "", "messages");
        for (Message message : Message.values()) {
            getConfig().addDefault(message.getConfig(), message.getMessage());
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    public void init() {
        for (Message message : Message.values()) {
            message.setMessage(getConfig().getString(message.getConfig()));
        }
    }
}
