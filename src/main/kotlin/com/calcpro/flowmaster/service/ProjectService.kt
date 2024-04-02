package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.entity.Project
import com.calcpro.flowmaster.dao.repository.CustomerRepository
import com.calcpro.flowmaster.dao.repository.ProjectRepository
import com.calcpro.flowmaster.dto.ProjectRequest
import com.calcpro.flowmaster.mapper.CustomerMapper
import com.calcpro.flowmaster.mapper.ProjectMapper
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val customerService: CustomerService,
    private val customerRepository: CustomerRepository,
    private val projectMapper: ProjectMapper,
    private val customerMapper: CustomerMapper
) {
    fun getProjectById(id: Long): Project = projectRepository.findById(id).get()

    fun addNewProject(id: Long, projectRequest: ProjectRequest): Project {
        val customer = customerRepository.findById(id).get()

        val project = projectMapper.projectRequestToProject(projectRequest)
        project.customer = customer

        return projectRepository.save(project)
    }
}