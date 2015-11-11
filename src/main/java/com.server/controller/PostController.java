package com.server.controller;

import com.server.entities.AppUser;
import com.server.entities.Comment;
import com.server.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class PostController {

    @PersistenceContext
    private EntityManager entityManager;



    public Post[] allOwnPosts( int userId ) {

        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GETUSER, Post.class );
        query.setParameter( "appuserId", userId );

        List<Post> postList = query.getResultList();
        return postList.toArray(new Post[ postList.size()]);
    }



    public void createComment( int id, AppUser userId, String text ) {
        Comment comment = new Comment();
        comment.setContent( text );
        comment.setDate( Calendar.getInstance() );
        comment.setUser( userId );
        comment.setPostId( id );

        entityManager.persist( comment );
    }



    public Comment[] getNextComments( int id, int userId, int comNum ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GETPOSTCOMMENTS, Comment.class );
        query.setParameter( "postId", id);

        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Comment> commentList = query.getResultList();

        List<Comment> returnList = commentList.subList( 0, comNum );

        return returnList.toArray(new Comment[ returnList.size()]);


    }



    public Comment[] getNextComments( int id, int userId, int comNum, int lastCommentId ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GETPOSTCOMMENTS, Comment.class );
        query.setParameter( "postId", id);

        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<Comment> commentList = query.getResultList();

        int i = 0;
        for( ; i < commentList.size(); i++){
            if (commentList.get( i ).getId() == lastCommentId){
                break;
            }
        }
        if (i == commentList.size()){
            return new Comment[0];
        }

        List<Comment> returnList = commentList.subList( i, i + comNum );
        return returnList.toArray(new Comment[ returnList.size()]);


    }



    public void deletePost( int id, int userId ) {

        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GET, Post.class);
        query.setParameter( "id", id );
        Post post = query.getSingleResult();

        TypedQuery<AppUser> queryUser = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter( "id", id );
        AppUser user = queryUser.getSingleResult();

        if (user.equals( post.getUser() )){
            entityManager.remove( post );
        }


    }



    public void likePost( int id, int userId ) {

        TypedQuery<Post> query = entityManager.createNamedQuery( Post.GET, Post.class);
        query.setParameter( "id", id );
        Post post = query.getSingleResult();

        TypedQuery<AppUser> queryUser = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter( "id", userId );
        AppUser user = queryUser.getSingleResult();


        int likes = post.getLikes() + 1;
        post.setLikes( likes );

        entityManager.persist( post );


    }
}
