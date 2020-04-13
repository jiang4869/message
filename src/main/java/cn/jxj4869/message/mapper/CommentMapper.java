package cn.jxj4869.message.mapper;

import cn.jxj4869.message.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from Comment where parentCommentId is null")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "user",column = "uid",one = @One(select = "cn.jxj4869.message.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many = @Many(select = "cn.jxj4869.message.mapper.CommentMapper.selectByParentCommentId"))
    }
    )
    List<Comment> selectAllParentCommentNull();

    @Select("select * from Comment where parentCommentId is null")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "user",column = "uid",one = @One(select = "cn.jxj4869.message.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many = @Many(select = "cn.jxj4869.message.mapper.CommentMapper.selectByParentCommentId"))
    }
    )
    IPage<Comment> selectAllParentCommentNullPage(IPage<Comment> page);


    @Select("select * from Comment where parentCommentId=#{cid}")
    @Results(value = {
            @Result(property = "replyCommentId",column = "replyCommentId"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "user",column = "uid",one = @One(select = "cn.jxj4869.message.mapper.UserMapper.selectById")),
        @Result(property = "replyToComment",column = "replyCommentId",one = @One(select = "cn.jxj4869.message.mapper.CommentMapper.selectById"))
    })
    List<Comment> selectByParentCommentId(Integer cid);
}
