package com.huasit.apm.business.bid.form;

import com.huasit.apm.core.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidCheckApprovesForm {

    private Long workitemId;

    private Long targetId;

    private Comment.CommentType type;

    private String comment;

    private int status;

}
