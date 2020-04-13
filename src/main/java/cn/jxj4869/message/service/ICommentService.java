package cn.jxj4869.message.service;

import cn.jxj4869.message.entity.Comment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
public interface ICommentService extends IService<Comment> {

    public boolean saveComment(Comment comment);

    public void replyRemind(Comment comment);

    public List<Comment> commentList();

    public IPage<Comment> commentPage(Integer currentPage);

    public boolean deleteComment(Integer cid);
}
