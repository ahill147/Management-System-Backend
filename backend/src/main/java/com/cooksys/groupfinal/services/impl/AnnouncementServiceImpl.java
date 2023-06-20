package com.cooksys.groupfinal.services.impl;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.AnnouncementRequestDto;
import com.cooksys.groupfinal.entities.Announcement;
import com.cooksys.groupfinal.entities.Company;
import com.cooksys.groupfinal.entities.User;
import com.cooksys.groupfinal.exceptions.BadRequestException;
import com.cooksys.groupfinal.exceptions.NotFoundException;
import com.cooksys.groupfinal.mappers.AnnouncementMapper;
import com.cooksys.groupfinal.repositories.AnnouncementRepository;
import com.cooksys.groupfinal.repositories.CompanyRepository;
import com.cooksys.groupfinal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.cooksys.groupfinal.services.AnnouncementService;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    // GET - ALL ANNOUNCEMENTS //
    @Override
    public Set<AnnouncementDto> getAllAnnouncements() {
        return announcementMapper.entitiesToDtos(new HashSet<>(announcementRepository.findAllByDeletedFalse()));
    }

    // POST - CREATE A NEW ANNOUNCEMENT //
    @Override
    public AnnouncementDto createAnnouncement(AnnouncementRequestDto announcementRequestDto) {

        // find a user by credentials - username //
        User author = userRepository
                .findByCredentialsUsernameAndActiveTrue(announcementRequestDto.getCredentials().getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        // if the credentials do not match throw bad request error //
        if (!author.isAdmin() || !author.getCredentials().getPassword()
                .equals(announcementRequestDto.getCredentials().getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        // find  a company by its companyId - else throw not found exception //
        Company company = companyRepository.findById(announcementRequestDto.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found"));

        // map announcement from dto back to entity //
        Announcement announcement = announcementMapper.dtoToEntity(announcementRequestDto);

        // set author and company of announcement //
        announcement.setAuthor(author);
        announcement.setCompany(company);

        // save announcement to repository //
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.entityToDto(savedAnnouncement);
    }

}