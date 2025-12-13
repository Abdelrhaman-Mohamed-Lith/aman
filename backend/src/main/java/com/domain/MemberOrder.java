package com.domain;

import com.Persistence.Persister;
import com.utilities.ObjectChecker;
import com.utilities.Result;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
public class MemberOrder extends DocumentFile {
    private Member member;
    private Boolean orderCouponsOnly;
    private Integer couponsCount;
    private String remarks;
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

    public Boolean getOrderCouponsOnly() {
        return orderCouponsOnly;
    }

    public void setOrderCouponsOnly(Boolean orderCouponsOnly) {
        this.orderCouponsOnly = orderCouponsOnly;
    }

    public Integer getCouponsCount() {
        return couponsCount;
    }

    public void setCouponsCount(Integer couponsCount) {
        this.couponsCount = couponsCount;
    }

    @Nationalized
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public void updateCalculatedFields(Result result) {
        super.updateCalculatedFields(result);
        setCode(getMember().getName() + "_" + ObjectChecker.toStringOrEmpty(getMember().getSyndicate()));
    }

    @Override
    public void isValidForCommit(Result result) {
        super.isValidForCommit(result);
        if (member == null) {
            result.failure("Member is required");
            return;
        }
        int count = Persister.countOf(getClass().getSimpleName(), " where  member_id=:member", Persister.params("member", getMember().getId()));
        if (count > 0) result.failure("There is order for this member");
    }
}
