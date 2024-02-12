package fr.epita.assistants.notifyme.notify;

public class ShellNotifier implements INotificationSender {
    final boolean parStdErr;

    public ShellNotifier(final boolean parStdErr) {
        this.parStdErr = parStdErr;
    }

    @Override
    public void notify(String parSender, String parReceiver, String parMessage) {
        String message = "Notification from " + parSender + " to " + parReceiver + " received: " + parMessage;
        if (parStdErr)
            System.err.println(message);
        else
            System.out.println(message);
    }
}
