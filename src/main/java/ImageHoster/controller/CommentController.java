package ImageHoster.controller;/* Create by Mansi Elhance */

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comment", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String comments, @PathVariable("imageId") Integer imageId,
                             @PathVariable("imageTitle") String imageTitle, Model model, HttpSession session){

        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggedUser");

        String tags = convertTagsToString(image.getTags());
        model.addAttribute("image", image);
        model.addAttribute("tags", tags);
        Comment comment = new Comment();
        comment.setUser(user); //sets to current user
        comment.setImage(image);//sets to current image
        commentService.createComment(comment); //creates comment for that user
        comment.settext(comments);
        comment.setCreatedDate(LocalDate.ofYearDay(2019,12));

        return "redirect:/images/" + image.getId() + " " + image.getTitle();
    }


    private String convertTagsToString(List<Tag> tags) {
        StringBuilder tagString = new StringBuilder();

        for (int i = 0; i <= tags.size() - 2; i++) {
            tagString.append(tags.get(i).getName()).append(",");
        }

        Tag lastTag = tags.get(tags.size() - 1);
        tagString.append(lastTag.getName());

        return tagString.toString();
    }
}
