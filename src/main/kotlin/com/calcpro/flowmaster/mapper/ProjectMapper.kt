package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.Project
import com.calcpro.flowmaster.dto.ProjectRequest
import org.springframework.stereotype.Component

@Component
class ProjectMapper {
    fun projectToProjectRequest(project: Project) = ProjectRequest(
        projectName = project.projectName,
        projectLocation = project.projectLocation,
        userId = project.user?.id
    )

    fun projectRequestToProject(projectRequest: ProjectRequest) = Project(
        projectName = projectRequest.projectName,
        projectLocation = projectRequest.projectLocation
    )
}