package ImageHoster.service;/* Create by Mansi Elhance */

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {

        return commentRepository.createComment(comment);
    }

    public List<Comment> getAllComments(Integer imageId) {
        return commentRepository.getAllComments(imageId);
    }
}
