package com.lgp.chifanlou.mapper;

import com.lgp.chifanlou.pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface BookMapper {
    @Insert("INSERT INTO `goforfood`.`book` ( `userid`, `booktype`, `bookstate`, `turnout`, `contactname`, `contactsex`, `contactphone`) " +
            "VALUES (#{userid}, #{booktype}, #{bookstate}, #{turnout},#{contactname},#{contactsex},#{contactphone})")
    @Options(useGeneratedKeys = true,keyProperty = "bookid",keyColumn = "bookid")
    int add(Book book);

    @Delete("DELETE FROM `goforfood`.`book` WHERE `bookid` = #{bookid} ")
    int remove(Integer bookid);
}
