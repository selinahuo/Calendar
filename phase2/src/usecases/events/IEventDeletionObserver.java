package usecases.events;

public interface IEventDeletionObserver {
    public void handleEventDeletion(String eventID);
}
