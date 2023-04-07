package com.lgp.chifanlou.mapper;
import com.lgp.chifanlou.pojo.Book;
import com.lgp.chifanlou.pojo.Coupon;
import com.lgp.chifanlou.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO `goforfood`.`user` (`nickname`, `avatar`,`openid`) VALUES (#{nickname},#{avatar},#{openid})")
    @Options(useGeneratedKeys = true,keyProperty = "userid",keyColumn = "userid")
    int add(User user);
    @Select("SELECT allot_coupon(#{userid})")
    int allot_coupon(Integer userid);

    @Select("SELECT COUNT(*) FROM `user` WHERE openid=#{openid}")
    int checkAdd(String openid);

    @Select("SELECT userid FROM `user` WHERE openid=#{openid}")
    int getUseridByOpenid(String openid);

    @Select("SELECT `coupon`.`couponid`,`couponcondition`,`coupondeduct`,`coupontime` FROM coupon,`usercoupon`,`user` WHERE " +
            "`usercoupon`.`userid`=`user`.`userid`AND `usercoupon`.`couponid`=`coupon`.`couponid`;")
    List<Coupon> getCoupon(User user);

    @Select("SELECT `bookid`,`book`.`userid`,`booktype`,`bookstate`,`booktime`,`turnout`,`contactname`,`contactsex`,`contactphone`FROM `book`,`user`WHERE `book`.`userid`=#{user.userid}")
    List<Book> getBook(@Param("user")User user);

    @Select("SELECT * FROM `user` WHERE userid=#{userid}")
    User getUserById(@Param("userid") Integer userid);



}
