package com.lgp.chifanlou.service;

import com.lgp.chifanlou.pojo.Note;

import java.util.List;
import java.util.Map;

public interface MerNoteService {
    public void insertNote(Note note);
    public List<Map<Object,Object>> getNotesByAuthor(String author);
    public  void  deleteNote(Integer noteID);
    public  void  updateNote(Note note);
}
