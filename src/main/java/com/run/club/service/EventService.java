package com.run.club.service;


import com.run.club.dto.EventDto;
import com.run.club.models.Event;

import java.util.List;

public interface EventService {

    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(Long eventId);
}
