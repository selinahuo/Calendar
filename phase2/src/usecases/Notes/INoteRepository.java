package usecases.Notes;

import entities.Memo;
import entities.Tag;
import java.util.ArrayList;

public interface INoteRepository {
    // write - Memos
    boolean saveMemo(Memo memo);
    // write - Tags
    boolean saveTag(Tag tag);

    // Fetch - singular - Memos
    Memo fetchMemoByMemoID(String memoID);
    Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID);
    Memo fetchMemoByMemoIDAndCollaboratorID(String memoID, String collaboratorID);
    Memo fetchMemoByMemoIDAndUserID(String memoID, String userID);

    // Fetch - singular - Tags
    Tag fetchTagByTagID(String tagID);
    Tag fetchTagByTagIDAndOwnerID(String tagID, String ownerID);
    Tag fetchTagByTagIDAndCollaboratorID(String tagID, String collaboratorID);
    Tag fetchTagByTagIDAndUserID(String tagID, String userID);

    // Fetch - plural - Memos
    // Generic
    ArrayList<Memo> fetchMemosByOwnerID(String ownerID);
    ArrayList<Memo> fetchMemosByCollaboratorID(String collaboratorID);
    ArrayList<Memo> fetchMemosByUserID(String userID);

    // Fetch - plural - Tags
    // Generic
    ArrayList<Tag> fetchTagsByOwnerID(String ownerID);
    ArrayList<Tag> fetchTagsByCollaboratorID(String collaboratorID);
    ArrayList<Tag> fetchTagsByUserID(String userID);

    // Name - Memos
    ArrayList<Memo> fetchMemosByNameAndOwnerID(String name, String ownerID);
    ArrayList<Memo> fetchMemosByNameAndCollaboratorID(String name, String collaboratorID);
    ArrayList<Memo> fetchMemosByNameAndUserID(String name, String userID);

    // Name - Tags
    ArrayList<Tag> fetchTagsByNameAndOwnerID(String name, String ownerID);
    ArrayList<Tag> fetchTagsByNameAndCollaboratorID(String name, String collaboratorID);
    ArrayList<Tag> fetchTagsByNameAndUserID(String name, String userID);

    // edit - Memos
    boolean editMemoName(String memoID, String name, String newName, String OwnerID);
    boolean editMemoNote(String memoID, String note, String newNNote, String OwnerID);
    boolean editMemoID(String memoID, String newID, String OwnerID);

    // edit - Tags
    boolean editTagName(String tagID, String name, String ownerID);
    boolean editTagID(String tagID, String newID, String ownerID);
    boolean editTagCountAdd(String tagID, String ownerID);
    boolean editTagCountRemove(String tagID, String ownerID);

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID);

    // delete - Tag
    boolean deleteTag(String tagID, String ownerID);
}
