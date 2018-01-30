package com.atristan.Controller;

import com.atristan.Entity.Member;
import com.atristan.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Member> getAllMembers(){
        return memberService.getallMembers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Member getMemberByID(@PathVariable("id") int id){
        return memberService.getMemberByID(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMemberByID(@PathVariable("id") int id){
        memberService.deleteMemberByID(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMember(@RequestBody Member member){
        memberService.updateMember(member);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertMember(@RequestBody Member member){
        memberService.insertMember(member);
    }
}
