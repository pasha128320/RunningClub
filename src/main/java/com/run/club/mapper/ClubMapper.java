package com.run.club.mapper;

import com.run.club.dto.ClubDto;
import com.run.club.models.Club;

import java.util.stream.Collectors;

import static com.run.club.mapper.EventMapper.mapToEventDto;

public class ClubMapper {

    public static Club mapToClub(ClubDto club) {
        Club clubDto = Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .createdBy(club.getCreatedBy())
                .photoUrl(club.getPhotoUrl())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }

    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .photoUrl(club.getPhotoUrl())
                .updatedOn(club.getUpdatedOn())
                .createdBy(club.getCreatedBy())
                .events(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }

}
