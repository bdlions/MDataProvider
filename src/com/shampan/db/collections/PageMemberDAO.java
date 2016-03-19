/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections;

import com.shampan.db.collections.fragment.page.MemberInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sampan IT
 */
public class PageMemberDAO {

    private String _id;
    private String pageId;
    private List<MemberInfo> memberList = new ArrayList<>();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public List<MemberInfo> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberInfo> memberList) {
        this.memberList = memberList;
    }

}
