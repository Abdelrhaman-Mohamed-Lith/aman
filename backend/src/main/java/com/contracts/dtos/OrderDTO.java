package com.contracts.dtos;

public class OrderDTO extends DocumentFileDTO{
    private MemberDTO member;
    private Boolean cards;
    private Integer couponsCount;
    private AttachmentDTO attachment1;
    private AttachmentDTO attachment2;
    private AttachmentDTO attachment3;

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
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

    public AttachmentDTO getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(AttachmentDTO attachment1) {
        this.attachment1 = attachment1;
    }

    public AttachmentDTO getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(AttachmentDTO attachment2) {
        this.attachment2 = attachment2;
    }

    public AttachmentDTO getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(AttachmentDTO attachment3) {
        this.attachment3 = attachment3;
    }
}
