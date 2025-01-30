package com.passwordmanager.rifky

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import java.util.UUID

@Service
class BooksService(private val db: JdbcTemplate) {
    fun findBooks(): List<Books> = db.query("select * from books") { response, _ ->
        Books(response.getString("id"), response.getString("title"), response.getString("author"), response.getInt("quantity"))
    }

    // fun findMessageById(id: String): Message? = db.query("select * from messages where id = ?", id) { response, _ ->
    //     Message(response.getString("id"), response.getString("text"))
    // }.singleOrNull()

    fun save(books: Books): Books {
        val id = books.id ?: UUID.randomUUID().toString() // Generate new id if it is null
        db.update(
            "insert into books values ( ?, ?, ?, ? )",
            id, books.title, books.author, books.quantity
        )
        return books.copy(id = id) // Return a copy of the message with the new id
    }
}