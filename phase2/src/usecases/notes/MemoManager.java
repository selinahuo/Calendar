package usecases.notes;

import entities.Memo;

import java.util.ArrayList;


public class MemoManager {
    private IMemoRepository repository;

    public MemoManager(IMemoRepository repository) {
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

    // delete - Memo
    boolean deleteMemo(String memoID, String ownerID){
        return repository.deleteMemo(memoID, ownerID);
    }
}
