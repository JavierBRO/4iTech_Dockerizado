package com.iTech.services;

import com.iTech.models.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findCommentsOrderByDateTimeDesc();

    List<Comment> findByKeynote_Id(Long keynoteId);

    Comment findById(Long id);

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment comment);

    boolean deleteComment(Long id);
}
