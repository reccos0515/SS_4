package com.atristan.Dao;

import com.atristan.Entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDao {

    private static Map<Integer, Member> members;

    static{

        members = new HashMap<Integer, Member>(){

            {
                put(1, new Member(1, "Tristan", "SpringBoot"));
                put(2, new Member(2, "Jessie", "Fibonacci"));
                put(3, new Member(3, "Ben", "Git"));
                put(4, new Member(4, "Maggie", "CprE 310"));
            }
        };
    }

    public Collection<Member> getAllMembers(){
        return this.members.values();

    }

    public Member getMemberByID(int id){
        return this.members.get(id);
    }
    public void allByInterest(String interest){

    }

    public Member getMemberByInterest(String interest){
        return this.members.get(interest);
    }

    public void deleteMemberByID(int id) {
        this.members.remove(id);
    }

    public void updateMember(Member member){
        Member m = members.get(member.getId());
        m.setInterest(member.getInterest());
        m.setName(member.getName());
        members.put(member.getId(), member);
    }

    public void insertMember(Member member) {
        this.members.put(member.getId(), member);
    }
}
