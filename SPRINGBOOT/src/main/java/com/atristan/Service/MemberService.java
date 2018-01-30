package com.atristan.Service;

import com.atristan.Dao.MemberDao;
import com.atristan.Entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
//Use mySQL.
//Refractor>Extract>Interface. Extract all methods to interface.
@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public Collection<Member> getallMembers(){
        return memberDao.getAllMembers();
    }

    public Member getMemberByID(int id){
        return this.memberDao.getMemberByID(id);
    }

    public void deleteMemberByID(int id) {
        this.memberDao.deleteMemberByID(id);
    }

    public void updateMember(Member member){
        this.memberDao.updateMember(member);
    }

    public void insertMember(Member member) {
        memberDao.insertMember(member);
    }
}
