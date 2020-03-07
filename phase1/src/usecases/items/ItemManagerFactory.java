package usecases.items;

import usecases.events.IEventManager;

/**
 * EventManagerFactory which instantiates EventManagers
 */
public class ItemManagerFactory {
    public static IItemManager build(IMemoRepository memoRepository, ITagRepository tagRepository, IEventManager em) {
        return new ItemManager(memoRepository, tagRepository, em);
    }
}
