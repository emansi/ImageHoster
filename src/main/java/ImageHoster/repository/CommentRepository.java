package ImageHoster.repository;/* Create by Mansi Elhance */

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment createComment(Comment newComment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }

    public List<Comment> getAllComments(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.image.id =:imageId", Comment.class).setParameter("imageId", imageId);
            List<Comment> commentsList = typedQuery.getResultList();
            return commentsList;
        } catch (NoResultException nre) {
            return null;
        }
    }

//    public void deleteComment(Integer imageId) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//        Comment comment = CommentRepository.getComment();
//
//        try {
//            transaction.begin();
//            Image image = em.find(Comment.class, commentId);
//            em.remove(comment);
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//        }
//    }
}
