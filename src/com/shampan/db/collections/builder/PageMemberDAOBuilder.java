/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.PageMemberDAO;
import com.shampan.db.collections.fragment.page.MemberInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sampan IT
 */
public class PageMemberDAOBuilder {

    PageMemberDAO memberInfo;

    public PageMemberDAOBuilder() {
        memberInfo = new PageMemberDAO();
    }

    private String _id;
    private String pageId;
    private List<MemberInfo> memberList = new ArrayList<>();

    public PageMemberDAOBuilder setMemberInfo(PageMemberDAO memberInfo) {
        this.memberInfo = memberInfo;
        return this;
    }

    public PageMemberDAOBuilder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public PageMemberDAOBuilder setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public PageMemberDAOBuilder setMemberList(List<MemberInfo> memberList) {
        this.memberList = memberList;
        return this;
    }

    PageMemberDAO build() {
        memberInfo.set_id(_id);
        memberInfo.setPageId(pageId);
        memberInfo.setMemberList(memberList);
        return memberInfo;
    }

    public PageMemberDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            memberInfo = mapper.readValue(daoContent, PageMemberDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return memberInfo;
    }
}
