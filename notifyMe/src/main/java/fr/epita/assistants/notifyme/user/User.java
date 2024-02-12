package fr.epita.assistants.notifyme.user;

import fr.epita.assistants.notifyme.notify.INotificationSender;

import java.util.ArrayList;
import java.util.List;

public class User implements IMultiNotificationSender {
    final String username;
    final List<INotificationSender> parNotificationList;

    public User(String username, List<INotificationSender> parNotificationList) {
        this.username = username;
        this.parNotificationList = parNotificationList;
    }

    public User(String username) {
        this.username = username;
        this.parNotificationList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void sendNotifications(String parRecipient, String parMessage) {
        for (INotificationSender notifier:
             parNotificationList) {
            notifier.notify(username, parRecipient, parMessage);
        }
    }

    @Override
    public void addNotifier(INotificationSender parNotifier) {
        if (parNotifier == null)
            return;

        parNotificationList.add(parNotifier);
    }

    @Override
    public List<INotificationSender> getNotifiers() {
        return parNotificationList;
    }
}
