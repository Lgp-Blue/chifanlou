package com.lgp.chifanlou.service.impl;

import com.lgp.chifanlou.mapper.MerNoteMapper;
import com.lgp.chifanlou.pojo.Note;
import com.lgp.chifanlou.service.MerNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerNoteServiceImpl implements MerNoteService {
    @Autowired
    MerNoteMapper merNoteMapper;
    @Override
    public void insertNote(Note note) {
       this.merNoteMapper.insertNote(note);
    }

    @Override
    public List<Map<Object,Object>> getNotesByAuthor(String author) {
        List<Map<Object,Object>> MTmap=this.merNoteMapper.getNotesByAuthor(author);
        System.out.println(MTmap+"------------------------");
        return MTmap;
    }

    @Override
    public void deleteNote(Integer noteID) {
        this.merNoteMapper.deleteNote(noteID);
    }

    @Override
    public void updateNote(Note note) {
        this.merNoteMapper.updateNote(note);
    }
}
