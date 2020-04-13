package cn.jxj4869.message;

import cn.jxj4869.message.entity.Comment;
import cn.jxj4869.message.entity.User;
import cn.jxj4869.message.mapper.CommentMapper;
import cn.jxj4869.message.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;

    @Test
    public void testFindByEmail(){
        List<Comment> comments = commentMapper.selectAllParentCommentNull();
        for (Comment comment : comments) {
            System.out.println(comment);
            List<Comment> replyComments = comment.getReplyComments();
            if(replyComments!=null){
                for (Comment replyComment : replyComments) {
                    System.out.println(replyComment);
                }
            }
        }
    }


    @Test
    public void testFindByEmail1(){
        Page<Comment> page=new Page<>(1,1);
        IPage<Comment> commentIPage = commentMapper.selectAllParentCommentNullPage(page);
        List<Comment> comments = commentIPage.getRecords();
        System.out.println("当前页"+commentIPage.getCurrent());
        System.out.println("总页数"+commentIPage.getTotal());
        for (Comment comment : comments) {
            System.out.println(comment);
            List<Comment> replyComments = comment.getReplyComments();
            if(replyComments!=null){
                for (Comment replyComment : replyComments) {
                    System.out.println(replyComment);
                }
            }
        }
    }

}
