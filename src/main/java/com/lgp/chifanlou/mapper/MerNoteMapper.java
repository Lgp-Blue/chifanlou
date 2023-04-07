package com.lgp.chifanlou.mapper;

import com.lgp.chifanlou.pojo.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MerNoteMapper {
    @Insert("INSERT INTO `goforfood`.`note`(`author`, `content`,`savedate`) VALUES (#{author}, #{content},#{savedate});")
    public void insertNote(Note note);
    @Select("SELECT noteID, content, savedate FROM `goforfood`.`note` WHERE `note`.`author`=#{author};")
    public List<Map<Object,Object>> getNotesByAuthor(String author);

    @Delete("DELETE FROM `note` WHERE noteID=#{noteID};")
    public  void  deleteNote(Integer noteID);

    @Update("UPDATE `goforfood`.`note` SET `content`=#{content} , `savedate`=#{savedate} WHERE `noteID` =#{noteID}; ")
    public  void  updateNote(Note note);
}
