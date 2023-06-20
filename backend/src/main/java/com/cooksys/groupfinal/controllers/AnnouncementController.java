package com.cooksys.groupfinal.controllers;

import com.cooksys.groupfinal.dtos.AnnouncementDto;
import com.cooksys.groupfinal.dtos.AnnouncementRequestDto;
import com.cooksys.groupfinal.dtos.CredentialsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cooksys.groupfinal.services.AnnouncementService;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
	
	private final AnnouncementService announcementService;

	@GetMapping
	public Set<AnnouncementDto> getAllAnnouncements() {
		return announcementService.getAllAnnouncements();
	}

	@PostMapping("/{companyId}")
	public AnnouncementDto createAnnouncement(@RequestBody AnnouncementRequestDto announcementRequestDto) {
		return announcementService.createAnnouncement(announcementRequestDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AnnouncementDto> deleteAnnouncement(@PathVariable Long id,
															  @RequestBody CredentialsDto credentialsDto) {
		return ResponseEntity.ok(announcementService.deleteAnnouncement(id, credentialsDto));
	}

}
