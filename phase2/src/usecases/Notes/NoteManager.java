package usecases.Notes;

import entities.Tag;
import entities.Memo;

import java.util.ArrayList;


public class NoteManager {
    private INoteRepository repository;

    public NoteManager(INoteRepository repository) {
        this.repository = repository;
    }

    // save - Memo
    public String createMemo(String name, String note, String memoID, String userID) {
        Memo memo = new Memo(name, note, memoID, userID);
        boolean success = repository.saveMemo(memo);
        if (success) {
            return memo.getMemoID();
        } else {
            return null;
        }
    }

    // save - Tag
    public String createTag(String tagID, String name, String userID) {
        Tag tag = new Tag(name, tagID, userID);
        boolean success = repository.saveTag(tag);
        if (success) {
            return tag.getTagID();
        } else {
            return null;
        }
    }


    // get - singular - Memos
    public Memo getMemoByMemoID(String memoID){ return repository.fetchMemoByMemoID(memoID); }

    public Memo getMemoByMemoIDAndOwnerID(String memoID, String ownerID){
        return repository.fetchMemoByMemoIDAndOwnerID(memoID, ownerID);
    }

    public Memo getMemoByMemoIDAndCollaboratorID(String memoID, String collaboratorID){
        return repository.fetchMemoByMemoIDAndCollaboratorID(memoID, collaboratorID);
    }

    public Memo getMemoByMemoIDAndUserID(String memoID, String userID){
        return repository.fetchMemoByMemoIDAndUserID(memoID, userID);
    }

    // get - singular - Tags
    public Tag getTagByTagID(String tagID) {return repository.fetchTagByTagID(tagID);}

    public Tag getTagByTagIDAndOwnerID(String tagID, String ownerID){
        return repository.fetchTagByTagIDAndOwnerID(tagID, ownerID);
    }
    public Tag getTagByTagIDAndCollaboratorID(String tagID, String collaboratorID){
        return repository.fetchTagByTagIDAndCollaboratorID(tagID, collaboratorID);
    }
    public Tag getTagByTagIDAndUserID(String tagID, String userID){
        return repository.fetchTagByTagIDAndUserID(tagID, userID);
    }

    // get - plural - Memos
    // Generic
    public ArrayList<Memo> getMemosByOwnerID(String ownerID){
        return repository.fetchMemosByOwnerID(ownerID);
    }

    public ArrayList<Memo> getMemosByCollaboratorID(String collaboratorID){
     return repository.fetchMemosByCollaboratorID(collaboratorID);
    }

    public ArrayList<Memo> getMemosByUserID(String userID){
        return repository.fetchMemosByUserID(userID);
    }

    // get - plural - Tags
    // Generic
    public ArrayList<Tag> getTagsByOwnerID(String ownerID){
        return repository.fetchTagsByOwnerID(ownerID);
    }

    public ArrayList<Tag> getTagsByCollaboratorID(String collaboratorID){
        return repository.fetchTagsByCollaboratorID(collaboratorID);
    }

    public ArrayList<Tag> getTagsByUserID(String userID){
        return repository.fetchTagsByUserID(userID);
    }

    // Name - Memos
    public ArrayList<Memo> getMemosByNameAndOwnerID(String name, String ownerID){
        return repository.fetchMemosByNameAndOwnerID(name, ownerID);
    }
    public ArrayList<Memo> getMemosByNameAndCollaboratorID(String name, String collaboratorID){
        return repository.fetchMemosByNameAndCollaboratorID(name, collaboratorID);
    }
    public ArrayList<Memo> getMemosByNameAndUserID(String name, String userID){
        return repository.fetchMemosByNameAndUserID(name, userID);
    }

    // Name - Tags
    public ArrayList<Tag> getTagsByNameAndOwnerID(String name, String ownerID){
        return repository.fetchTagsByNameAndOwnerID(name, ownerID);
    }
    public ArrayList<Tag> getTagsByNameAndCollaboratorID(String name, String collaboratorID){
        return repository.fetchTagsByNameAndCollaboratorID(name, collaboratorID);
    }
    public ArrayList<Tag> getTagsByNameAndUserID(String name, String userID){
        return repository.fetchTagsByNameAndUserID(name, userID);
    }

    // edit - Memos
    boolean editMemoName(String memoID, String name, String newName, String ownerID){
        return repository.editMemoName(memoID, name, newName, ownerID);
    }

    boolean editMemoNote(String memoID, String note, String newNote, String ownerID){
        return repository.editMemoNote(memoID, note, newNote, ownerID);
    }

    boolean editMemoID(String memoID, String newID, String OwnerID){
        return repository.editMemoID(memoID, newID, OwnerID);
    }

    // edit - Tags
    boolean editTagName(String tagID, String name, String OwnerID){
        return repository.editTagName(tagID, name, OwnerID);
    }

    boolean editTagID(String tagID, String newID, String OwnerID){
        return repository.editTagID(tagID, newID, OwnerID);
    }

    boolean editTagCountAdd(String tagID, String ownerID){
        return repository.editTagCountAdd(tagID, ownerID);
    }

    boolean editTagCountRemove(String tagID, String ownerID){
        return repository.editTagCountRemove(tagID, ownerID);
    }

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID){
        return repository.deleteMemo(memoID, ownerID);
    }

    // delete - Tag
    boolean deleteTag(String tagID, String ownerID){
     return repository.deleteTag(tagID, ownerID);
    }
}
