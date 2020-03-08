package usecases.notes;

import entities.CalendarEvent;
import entities.Tag;

import entities.Memo;
import usecases.events.IEventManager;

import java.util.ArrayList;
import java.util.UUID;


class NoteManager implements INoteManager {
    private IMemoRepository memoRepository;
    private ITagRepository tagRepository;
    private IEventManager eventManager;

    NoteManager(IMemoRepository memoRepository, ITagRepository tagRepository, IEventManager eventManager) {
        this.memoRepository = memoRepository;
        this.tagRepository = tagRepository;
        this.eventManager = eventManager;
    }

    @Override
    public boolean createMemo(String name, String note, String userID) {
        Memo memo = new Memo(name, note, UUID.randomUUID().toString(), userID);
        return this.memoRepository.saveMemo(memo);
    }

    /**
     * Create a Tag.
     * @param name the Tag to create
     * @param userID the unique id of this tag
     * @return true if event creation was successful, false otherwise
     */
//    @Override
    public boolean createTag(String name, int count, String userID) {
        Tag tag = new Tag(UUID.randomUUID().toString(), name, count, userID);
        return this.tagRepository.saveTag(tag);
    }

    @Override
    public boolean attachMemoToEvent(String memoID, String eventID, String userID) {
        Memo memo = this.memoRepository.fetchMemoByIDAndUserID(memoID, userID);
        if (memo == null) {
            return false;
        }
        ArrayList<String> memoArr = new ArrayList<String>();
        memoArr.add(memoID);
        return this.eventManager.setEventMemoIDs(memoArr, eventID, userID);
    }

    /**
     * Create a Tag.
     *
     * @param eventID
     * @param tagName
     * @param userID  the unique id of this tag
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean tagEvent(String eventID, String tagName, String userID) {
        Tag tag = this.tagRepository.fetchTagByNameAndUserID(tagName, userID);
        if (tag == null) {
            this.createTag(tagName, 1, userID);
            tag = this.tagRepository.fetchTagByNameAndUserID(tagName, userID);
        }
        ArrayList<String> tagArr = new ArrayList<String>();
        tagArr.add(tag.getTagID());
        return this.eventManager.setEventTagIDs(tagArr, eventID, userID);
    }

    /**
     * Get a Memo by its name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
//    @Override
//    public Memo getMemoByName(String name) {return this.memoRepository.fetchMemoByName(name); }

    /**
     * Get a Memo by its id.
     * @param id the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    @Override
    public Memo getMemoById(String id) {return this.memoRepository.fetchMemoByMemoId(id);}

    /**
     * Get a Tag by its id.
     * @param id the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public Tag getTagByID(String tagID) {return this.tagRepository.fetchTagByID(tagID);}

    @Override
    public CalendarEvent[] getEventsByTagNameAndUserID(String tagName, String userID) {
        Tag tag = this.tagRepository.fetchTagByNameAndUserID(tagName, userID);
        if (tag == null) {
            return new CalendarEvent[0];
        }
        return this.eventManager.getEventsByTagIDAndUserID(tag.getTagID(), userID);
    }

    @Override
    public CalendarEvent[] getEventsByMemoIDAndUserID(String memoID, String userID) {
        Memo memo = this.memoRepository.fetchMemoByIDAndUserID(memoID, userID);
        if (memo == null) {
            return new CalendarEvent[0];
        }
        return this.eventManager.getEventsByMemoIDAndUserID(memoID, userID);
    }

    @Override
    public Memo[] getMemosByUserID(String userID) {
        return this.memoRepository.fetchMemosByUserID(userID);
    }
}
