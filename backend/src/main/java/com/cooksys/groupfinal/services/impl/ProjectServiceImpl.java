package com.cooksys.groupfinal.services.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.entities.Project;
import com.cooksys.groupfinal.entities.Team;
import com.cooksys.groupfinal.mappers.ProjectMapper;
import com.cooksys.groupfinal.repositories.ProjectRepository;
import com.cooksys.groupfinal.services.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;

	@Override
	public ProjectDto createProject(ProjectDto projectDto) {
		// Create a new Project entity and set the fields from the ProjectDto
		Project project = new Project();
		project.setId(projectDto.getId());
		project.setName(projectDto.getName());
		project.setDescription(projectDto.getDescription());
		project.setActive(projectDto.isActive());

		// Set the team id on the Project entity
		if (projectDto.getTeam() != null) {
			Team team = new Team();
			team.setId(projectDto.getTeam().getId());
			project.setTeam(team);
		}
		// Save the project using the ProjectRepository
		project = projectRepository.save(project);
		// Map the saved Project entity back to a ProjectDto and return it
		return projectMapper.entityToDto(project);
	}

	@Override
	public ProjectDto getProjectById(Long id) {
		// Retrieve the Project entity from the ProjectRepository
		Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
		// Map the Project entity to a ProjectDto and return it
		return projectMapper.entityToDto(project);
	}

	@Override
	public ProjectDto updateProject(Long id, ProjectDto projectDto) {
		// Retrieve the existing Project entity from the ProjectRepository
		Project existingProject = projectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not found"));
		// Update the fields of the existing Project entity with the values from the
		// ProjectDto
		existingProject.setName(projectDto.getName());
		existingProject.setDescription(projectDto.getDescription());
		existingProject.setActive(projectDto.isActive());
		// Set the team id on the Project entity if provided
		if (projectDto.getTeam() != null) {
			Team team = new Team();
			team.setId(projectDto.getTeam().getId());
			// Set other fields of the Team object if needed
			existingProject.setTeam(team);
		} else {
			existingProject.setTeam(null); // If team id is not provided, set it to null
		}
	    // Update the lastEdited field with the current timestamp
		existingProject.setLastEdited(new Timestamp(System.currentTimeMillis()));
		// Save the updated project using the ProjectRepository
		existingProject = projectRepository.save(existingProject);
		// Map the saved Project entity back to a ProjectDto and return it
		return projectMapper.entityToDto(existingProject);
	}

	@Override
	public void deleteProject(Long id) {
		// Check if the project exists in the repository
		if (!projectRepository.existsById(id)) {
			throw new RuntimeException("Project not found");
		}
		// Delete the project by ID using the ProjectRepository
		projectRepository.deleteById(id);
	}

	@Override
	public List<ProjectDto> getAllProjects() {
		// Retrieve all projects from the ProjectRepository
		List<Project> projects = projectRepository.findAll();
		// Map the list of projects to a list of ProjectDto using the ProjectMapper
		List<ProjectDto> projectDtos = projectMapper.entityListToDtoList(projects);
		return projectDtos;
	}

}
