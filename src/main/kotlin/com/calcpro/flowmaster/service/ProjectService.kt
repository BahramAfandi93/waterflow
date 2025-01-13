package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.entity.Project
import com.calcpro.flowmaster.dao.repository.ProjectRepository
import com.calcpro.flowmaster.dao.repository.AppUserRepository
import com.calcpro.flowmaster.dto.ProjectRequest
import com.calcpro.flowmaster.mapper.ProjectMapper
import com.calcpro.flowmaster.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val userService: UserService,
    private val appUserRepository: AppUserRepository,
    private val projectMapper: ProjectMapper,
    private val userMapper: UserMapper
) {
    fun getProjectById(id: Long): Project = projectRepository.findById(id).get()

    fun addNewProject(id: Long, projectRequest: ProjectRequest): Project {
        val project = projectMapper.projectRequestToProject(projectRequest)
        return projectRepository.save(project)
    }
}