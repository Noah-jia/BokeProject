package com.jiaxiaojie.bokeproject.domain;

public class ReplyDto {
    private Integer id;
    private String content;
    private String authorId;
    private Integer targetId;
    private User target;
    private User author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "ReplyDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", authorId='" + authorId + '\'' +
                ", targetId=" + targetId +
                ", target=" + target +
                ", author=" + author +
                '}';
    }
}
