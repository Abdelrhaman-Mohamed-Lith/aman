package com.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class MemberOrder extends DocumentFile {
    private Member member;
    private Boolean cards;
    private Integer couponsCount;
    private Attachment attachment1;
    private Attachment attachment2;
    private Attachment attachment3;

    @ManyToOne
    @JoinColumn(name = "member_id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Boolean getCards() {
        return cards;
    }

    public void setCards(Boolean cards) {
        this.cards = cards;
    }

    public Integer getCouponsCount() {
        return couponsCount;
    }

    public void setCouponsCount(Integer couponsCount) {
        this.couponsCount = couponsCount;
    }

    @OneToOne
    @JoinColumn(name = "attachment1_id")
    public Attachment getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(Attachment attachment1) {
        this.attachment1 = attachment1;
    }

    @OneToOne
    @JoinColumn(name = "attachment2_id")
    public Attachment getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(Attachment attachment2) {
        this.attachment2 = attachment2;
    }

    @OneToOne
    @JoinColumn(name = "attachment3_id")
    public Attachment getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(Attachment attachment3) {
        this.attachment3 = attachment3;
    }
}
