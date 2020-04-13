package cn.jxj4869.message.service.impl;

import cn.jxj4869.message.entity.Comment;
import cn.jxj4869.message.mapper.CommentMapper;
import cn.jxj4869.message.service.ICommentService;
import cn.jxj4869.message.utils.MainUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.model.IComment;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MainUtil mainUtil;

    @Override
    public IPage<Comment> commentPage(Integer currentPage) {
        Page<Comment> page=new Page<>(currentPage,1);
        IPage<Comment> commentIPage = commentMapper.selectAllParentCommentNullPage(page);

        return commentIPage;
    }

    @Override
    public boolean deleteComment(Integer cid) {
        int i = commentMapper.deleteById(cid);
        return true;
    }

    @Override
    public void replyRemind(Comment comment) {
        String content="您的评论有回复了，点击查看";
        mainUtil.sendReplyRemind(comment.getEmail(),content);
    }

    @Transactional
    @Override
    public boolean saveComment(Comment comment) {
        comment.setCreateTime(new Date());

        Integer parentComment = comment.getParentCommentId();
        if (parentComment.equals(-1)) {
            comment.setParentCommentId(null);

        }else{
            Comment comment1 = commentMapper.selectById(comment.getParentCommentId());
            if(comment1.isRemind()) {
                replyRemind(comment1);
            }
        }
        Integer replyComment = comment.getReplyCommentId();
        if (replyComment.equals(-1)) {
            comment.setReplyCommentId(null);
        }
        int cnt = commentMapper.insert(comment);




        if (cnt == 1)
            return true;
        else
            return false;
    }

    @Override
    public List<Comment> commentList() {
        List<Comment> comments = commentMapper.selectAllParentCommentNull();
        return comments;
    }


}
