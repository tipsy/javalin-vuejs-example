package io.javalin

import io.javalin.apibuilder.ApiBuilder.*

data class Todo(val id: Long, val title: String, val completed: Boolean)

fun main(args: Array<String>) {

    var todos = arrayOf(Todo(123123123, "My very first todo", false))

    val app = Javalin.create().apply {
        port(getHerokuAssignedPort())
        enableStaticFiles("/public")
    }.start()

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

private fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"))
    }
    return 7000
}
