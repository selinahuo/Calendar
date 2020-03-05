package usecases.items;

import entities.Tag;

import entities.Memo;

import java.util.ArrayList;

class ItemManager implements IItemManager {
    private IItemRepository repository;

    ItemManager(IItemRepository repository) {this.repository = repository;}

    /**
     * Create a Memo.
     * @param name the name of the memo
     * @param events all of the events associated to this memo
     * @param note the note which the memo contains
     * @param id the unique id of the memo
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createMemo(String name, ArrayList<String> events, String note, String id) {
        Memo memo = new Memo(name, events, note, id);
        this.repository.saveMemo(memo);
        return true;
    }

    /**
     * Create a Tag.
     * @param name the Tag to create
     * @param events the events associated with this tag
     * @param id the unique id of this tag
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createTag(String name, ArrayList<String> events, String id) {
        Tag tag = new Tag(name, events, id);
        this.repository.saveTag(tag);
        return true;
    }

    /**
     * Get a Memo by its name.
     * @param name the name to filter by.
     * @return the corresponding Memo or null if it does not exist
     */
    @Override
    public Memo getMemoByName(String name) {
        return null;
    }

    @Override
    public Memo getMemoById(String id) {
        return null;
    }

    /**
     * Get a Tag by its name.
     * @param name the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public Tag getTagByName(String name) {
        return null;
    }

    @Override
    public Tag getTagById(String id) {
        return null;
    }
}
