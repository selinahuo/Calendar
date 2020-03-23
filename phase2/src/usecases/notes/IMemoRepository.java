package usecases.notes;

import entities.Memo;
import java.util.ArrayList;

public interface IMemoRepository {
    // write - Memos
    boolean saveMemo(Memo memo);

    // Fetch - singular - Memos
    Memo fetchMemoByMemoID(String memoID);
    Memo fetchMemoByMemoIDAndOwnerID(String memoID, String ownerID);
    Memo fetchMemoByMemoIDAndCollaboratorID(String memoID, String collaboratorID);
    Memo fetchMemoByMemoIDAndUserID(String memoID, String userID);

    // Fetch - plural - Memos
    // Generic
    ArrayList<Memo> fetchMemosByOwnerID(String ownerID);
    ArrayList<Memo> fetchMemosByCollaboratorID(String collaboratorID);
    ArrayList<Memo> fetchMemosByUserID(String userID);

    // Name - Memos
    ArrayList<Memo> fetchMemosByNameAndOwnerID(String name, String ownerID);
    ArrayList<Memo> fetchMemosByNameAndCollaboratorID(String name, String collaboratorID);
    ArrayList<Memo> fetchMemosByNameAndUserID(String name, String userID);

    // edit - Memos
    boolean editMemoName(String memoID, String name, String newName, String OwnerID);
    boolean editMemoNote(String memoID, String note, String newNNote, String OwnerID);
    boolean editMemoID(String memoID, String newID, String OwnerID);

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID);
}
