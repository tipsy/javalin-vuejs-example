package io.javalin

import io.javalin.apibuilder.ApiBuilder.*

data class Todo(val id: Long, val title: String, val completed: Boolean)

fun main(args: Array<String>) {

    var todos = arrayOf(Todo(123123123, "My very first todo", false))

    val app = Javalin.create {
        it.addStaticFiles("/public")
    }.start(getHerokuAssignedPort())

    app.routes {
        path("todos") {
            get { ctx ->
                ctx.json(todos)
            }
            put { ctx ->
                todos = ctx.body<Array<Todo>>()
                ctx.status(204)
            }
        }
    }

}

private fun getHerokuAssignedPort() = ProcessBuilder().environment()["PORT"]?.toInt() ?: 7000
