package il.cshaifasweng.OCSFMediatorExample.client.Subscribers;

import il.cshaifasweng.Message;

public class LogoutSubscriber {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public LogoutSubscriber(Message message) {
        this.message = message;
    }
}