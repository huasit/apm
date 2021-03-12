package com.huasit.apm.business.bid.form;

import com.huasit.apm.business.bid.entity.BidArgueFile;
import com.huasit.apm.core.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidArgueForm {

    /**
     *
     */
    private Long workitemId;

    /**
     *
     */
    private Long targetId;

    /**
     *
     */
    private Comment.CommentType type;

    /**
     *
     */
    private String comment;

    /**
     *
     */
    private List<BidArgueFile> argueFiles;

}
