package usecases.items;

import entities.Tag;

import entities.Memo;

class ItemManager implements IItemManager {
    private IItemRepository repository;

    ItemManager(IItemRepository repository) {this.repository = repository;}

    /**
     * Create and save a Memo.
     * @param memo the Memo to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createMemo(Memo memo){
        this.repository.saveMemo(memo);

        return true;
    }
    /**
     * Create and save a Tag.
     * @param tag the Tag to save
     * @return true if event creation was successful, false otherwise
     */
    @Override
    public boolean createTag(Tag tag) {
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

    /**
     * Get a Tag by its name.
     * @param name the name to filter by.
     * @return the corresponding Tag or null if it does not exist
     */
    @Override
    public Tag getTagByName(String name) {
        return null;
    }
}
