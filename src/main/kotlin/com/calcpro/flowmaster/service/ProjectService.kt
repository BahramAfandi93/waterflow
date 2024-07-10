package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.entity.Project
import com.calcpro.flowmaster.dao.repository.ProjectRepository
import com.calcpro.flowmaster.dao.repository.EngineerRepository
import com.calcpro.flowmaster.dto.ProjectRequest
import com.calcpro.flowmaster.mapper.ProjectMapper
import com.calcpro.flowmaster.mapper.EngineerMapper
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val engineerService: EngineerService,
    private val engineerRepository: EngineerRepository,
    private val projectMapper: ProjectMapper,
    private val engineerMapper: EngineerMapper
) {
    fun getProjectById(id: Long): Project = projectRepository.findById(id).get()

    fun addNewProject(id: Long, projectRequest: ProjectRequest): Project {
        val engineer = engineerRepository.findById(id).get()

        val project = projectMapper.projectRequestToProject(projectRequest)
        project.engineer = engineer

        val engineerResponse = projectRepository.save(project)

        return projectRepository.save(project)
    }
}