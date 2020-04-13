package cn.jxj4869.message.controller;


import cn.jxj4869.message.entity.Comment;
import cn.jxj4869.message.service.ICommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.accessibility.AccessibleComponent;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
@Controller

public class CommentController {

    @Autowired
    ICommentService commentService;


    @RequestMapping("/comment/postcomment")

    public String postcomment(Comment comment) {
        System.out.println(comment);
        commentService.saveComment(comment);
        return "redirect:/comment/commentlist";
    }

    @RequestMapping("/comment/commentlist")
    public String commentlist(Integer page,Model model){
        if(page==null){
            page=1;
        }
        IPage<Comment> commentIPage = commentService.commentPage(page);
        model.addAttribute("page",commentIPage);
        List<Comment> comments = commentIPage.getRecords();
        model.addAttribute("comments",comments);
        System.out.println("page "+page);
        return "note::commentslist";
    }

    @RequestMapping("/comment/delete")
    public String deleteComment(Integer cid) {
        boolean b = commentService.deleteComment(cid);
        return "redirect:/comment/commentlist";
    }

    @RequestMapping({"/comment"})
    public String getcomment(Model model, HttpSession session){
        IPage<Comment> commentIPage = commentService.commentPage(1);
        model.addAttribute("page",commentIPage);
        List<Comment> comments = commentIPage.getRecords();
        model.addAttribute("comments",comments);
        return "note";
    }

}
