package com.lgp.chifanlou.mapper;

import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Merchant;
import com.lgp.chifanlou.pojo.Mer_order;
import com.lgp.chifanlou.pojo.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {
      @Insert("INSERT INTO `goforfood`.`merchant` (`mname`, `password`, `email`, `gender`, `phone`) VALUES (#{mname},#{password},#{email},#{gender},#{phone});")
      @Options(useGeneratedKeys = true,keyProperty = "mid",keyColumn = "mid")
         public int insert(Merchant merchant);
      @Select("SELECT COUNT(*) FROM `merchant` WHERE mname=#{mname} AND password=#{password} ")
         public Integer login(Merchant merchant);
      @Select(" SELECT COUNT(*) FROM `merchant` WHERE mname=#{mname} ")
         public Integer check(Merchant merchant);
      @Update("UPDATE  `merchant` SET PASSWORD=#{password} WHERE mname=#{mname};")
         public Integer update(Merchant merchant);
      @Select(" SELECT email FROM `merchant` WHERE mname=#{mname} ")
         public String getEmail(Merchant merchant);
      @Select("SELECT `mid` FROM `merchant` WHERE `merchant`.`mname`=#{name};")
         public String getMidByName(String name);
      @Select("SELECT * FROM `mer_book` WHERE `mname`=#{mname} ORDER BY `bookid`;")
         public List<Book> getBookBYMname(String mname);
      @Select("SELECT * FROM `mer_dobook` WHERE `mname`=#{mname} ORDER BY `bookid`;")
      public List<Book> getDoBookBYMname(String mname);
        @Update("UPDATE `goforfood`.`book` SET `bookstate` = '预约成功' WHERE `bookid`=#{bookid};")
        public Integer updateBook(String bookid);


}
