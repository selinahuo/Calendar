package usecases.notes;

import usecases.events.IEventManager;

/**
 * EventManagerFactory which instantiates EventManagers
 */
public class NoteManagerFactory {
    public static INoteManager build(IMemoRepository memoRepository, ITagRepository tagRepository, IEventManager em) {
        return new NoteManager(memoRepository, tagRepository, em);
    }
}
