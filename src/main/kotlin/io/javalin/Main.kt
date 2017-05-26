package io.javalin

import javalin.ApiBuilder.get
import javalin.ApiBuilder.put
import javalin.Javalin

fun main(args: Array<String>) {

    var todos = arrayOf(Todo(123123123, "My very first todo", false))

    val app = Javalin.create()
            .port(getHerokuAssignedPort())
            .enableStaticFiles("/public")

    app.routes {
        get("/todos") { req, res ->
            res.json(todos)
        }
        put("/todos") { req, res ->
            todos = req.bodyAsClass(Array<Todo>::class.java)
            res.status(204)
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