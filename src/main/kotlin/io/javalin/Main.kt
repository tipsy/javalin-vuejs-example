package io.javalin

import javalin.ApiBuilder.get
import javalin.ApiBuilder.put
import javalin.Javalin

fun main(args: Array<String>) {

    var todos = arrayOf(Todo(123123123, "My very first todo", false))

    val app = Javalin.create()
            .port(7000)
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