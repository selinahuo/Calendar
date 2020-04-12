package controller;

import controller.viewmodels.ListModel;
import entities.Memo;
import entities.Tag;

import java.util.ArrayList;

class NoteAdapter {
    static String createMemoString(Memo memo) {
        return String.format("ID: %s | Memo: %s | Note: %s | Count: %s", memo.getMemoID(), memo.getName(),
                memo.getNote(), memo.getCount());
    }
    static String createTagString(Tag tag) {
        return String.format("ID: %s | Tag: %s | Count: %s", tag.getTagID(), tag.getName(), tag.getCount());
    }

    static ListModel createMemoListModel(ArrayList<Memo> memos) {
        ArrayList<String> memoList = new ArrayList<>();
        for (Memo memo: memos) {
            memoList.add(createMemoString(memo));
        }
        return new ListModel(memoList);
    }

    static ListModel createTagListModel(ArrayList<Tag> tags) {
        ArrayList<String> tagList = new ArrayList<>();
        for (Tag tag: tags) {
            tagList.add(createTagString(tag));
        }
        return new ListModel(tagList);
    }
}
