package com.run.club.service;


import com.run.club.dto.ClubDto;
import com.run.club.models.Club;

import java.util.List;

public interface ClubService {
    Club saveClub(ClubDto club);
    List<ClubDto> findAllClubs();
    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto club);

    void delete(Long clubId);

    List<ClubDto> searchClubs(String query);



}
