package com.example.librarymanagementsystem.controller;


import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/members")

public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }

    @GetMapping("/{id}")
    public String getMemberDetails(@PathVariable("id") int id, Model model) {
        Member member = memberRepository.findById(id);
        if (member != null) {
            model.addAttribute("member", member);
            return "memberDetails";
        } else {
            return "redirect:/members";
        }
    }

    @GetMapping("/add")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "addMember";
    }

    @PostMapping("/add")
    public String saveMember(@ModelAttribute("member") Member member) {
        member.setRegistrationDate(LocalDate.now());
        memberRepository.save(member);
        return "redirect:/members";
    }
}
