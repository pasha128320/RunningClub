package com.run.club.service.impl;

import com.run.club.dto.EventDto;
import com.run.club.models.Club;
import com.run.club.models.Event;
import com.run.club.repository.ClubRepository;
import com.run.club.repository.EventRepository;
import com.run.club.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.run.club.mapper.EventMapper.mapToEvent;
import static com.run.club.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;
    ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }



    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }


}
