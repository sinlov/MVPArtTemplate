package com.github.sinlov.mvparttemplate.services

import com.github.sinlov.mvparttemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
