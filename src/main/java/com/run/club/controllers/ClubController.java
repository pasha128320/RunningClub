package com.run.club.controllers;


import com.run.club.dto.ClubDto;
import com.run.club.dto.EventDto;
import com.run.club.models.Club;
import com.run.club.models.UserEntity;
import com.run.club.security.SecurityUtil;
import com.run.club.service.ClubService;
import com.run.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClubController {

    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model){
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if(username !=null){
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        model.addAttribute("title","Home page");
        return "clubs-list";
    }





    @GetMapping("/clubs/new")
    public String clubsCreate(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        model.addAttribute("title","Create a running club");
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/{clubId}")
    public String clubDetail(@PathVariable(name="clubId") Long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-detail";
    }

    @GetMapping("/")
    public String redirectToHomePage(){
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value = "query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }


    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable(name="clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }


    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId
                            , @Valid @ModelAttribute("club") ClubDto club
                            , BindingResult result){
        if(result.hasErrors()) {
            return "clubs-edit";
        }

        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
