package com.run.club.controllers;


import com.run.club.dto.EventDto;
import com.run.club.models.Event;
import com.run.club.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String eventsList(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }


    @GetMapping("/events/{clubId}/new")
    public String getCreatingEventPage(Model model, @PathVariable(name="clubId") Long clubId){
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}/new")
    public String createEvent(Model model, @ModelAttribute("event")EventDto eventDto, @PathVariable(name="clubId") Long clubId){
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events/{eventId}")
    public String getEventDetails(@PathVariable("eventId") Long eventId, Model model){
        EventDto event = eventService.findEventById(eventId);
        model.addAttribute("event", event);
        return "events-detail";
    }



}
