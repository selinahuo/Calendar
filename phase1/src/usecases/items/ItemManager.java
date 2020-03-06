package usecases.items;

import entities.CalendarEvent;
import entities.Tag;

import entities.Memo;
import usecases.events.IEventManager;

import java.util.UUID;


class ItemManager implements IItemManager {
    private IMemoRepository memoRepository;
    private ITagRepository tagRepository;
    private IEventManager eventManager;

    ItemManager(IMemoRepository memoRepository, ITagRepository tagRepository, IEventManager eventManager) {
        this.memoRepository = memoRepository;
        this.tagRepository = tagRepository;
        this.eventManager = eventManager;
    }

    /**
     * Create a Memo.
     * @param name the name of the memo
     * @param note the note which the memo contains
     * @param id the unique id of the memo
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createMemo(String name, String note, String id) {
        Memo memo = new Memo(name, note, id);
        this.memoRepository.saveMemo(memo);
        return true;
    }

    /**
     * Create a Tag.
     * @param name the Tag to create
     * @param id the unique id of this tag
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createTag(String name, int count, String userID) {
        Tag tag = new Tag(UUID.randomUUID().toString(), name, count, userID);
        return this.tagRepository.saveTag(tag);
    }

    /**
     * Get a Memo by its name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    @Override
    public Memo getMemoByName(String name) {return this.memoRepository.fetchMemoByName(name); }

    /**
     * Get a Memo by its id.
     * @param id the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    @Override
    public Memo getMemoById(String id) {return this.memoRepository.fetchMemoById(id);}

    /**
     * Get a Tag by its id.
     * @param id the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public Tag getTagById(String id) {return this.tagRepository.fetchTagByID(id);}

    @Override
    public CalendarEvent[] getEventsByTagNameAndUserID(String tagName, String userID) {
        Tag tag = this.tagRepository.fetchTagByNameAndUserID(tagName, userID);
        if (tag == null) {
            return new CalendarEvent[0];
        }
        return this.eventManager.getEventsBySeriesIDAndUserID(tag.getTagID(), userID);
    }

    @Override
    public CalendarEvent[] getEventsByMemoIDAndUserID(String memoID, String userID) {
        Memo memo = this.memoRepository.fetchMemoByIDAndUserID(memoID, userID);
        if (memo == null) {
            return new CalendarEvent[0];
        }
        return this.eventManager.getEventsBySeriesIDAndUserID(memoID, userID);
    }
}
