package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    Member getMemberById(int id);

    Member saveMember(Member member);
}