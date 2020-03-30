package controller;

import controller.viewmodels.ListModel;
import entities.Memo;
import entities.Tag;

import java.util.ArrayList;

public class NoteAdapter {
    public static String createUserString(Memo memo) {
        return String.format("ID: %s | Memo: %s", memo.getMemoID(), memo.getName());
    }

    public static String createUserString(Tag tag) {
        return String.format("ID: %s | Tag: %s", tag.getTagID(), tag.getName());
    }
    public static ListModel createMemoListModel(ArrayList<Memo> memos) {
        ArrayList<String> memoList = new ArrayList<>();
        for (Memo memo: memos) {
            memoList.add(createUserString(memo));
        }
        return new ListModel(memoList);
    }

    public static ListModel createTagListModel(ArrayList<Tag> tags) {
        ArrayList<String> tagList = new ArrayList<>();
        for (Tag tag: tags) {
            tagList.add(createUserString(tag));
        }
        return new ListModel(tagList);
    }
}