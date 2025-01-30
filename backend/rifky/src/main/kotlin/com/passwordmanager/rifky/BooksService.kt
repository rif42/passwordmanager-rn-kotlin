package com.passwordmanager.rifky

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import java.util.UUID
import org.springframework.util.MultiValueMap
import java.util.Optional
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@Service
class BooksService(private val db: JdbcTemplate) {
    private fun findBookById(id: String): Books? {
        return db.query(
            "SELECT id, title, author, quantity FROM books WHERE id = ?",
            id
        ) { result, _ ->
            Books(
                id = result.getString("id"),
                title = result.getString("title"),
                author = result.getString("author"),
                quantity = result.getInt("quantity")
            )
        }.singleOrNull()
    }

    fun findBooks(): List<Books> = db.query("select * from books") { response, _ ->
        Books(response.getString("id"), response.getString("title"), response.getString("author"), response.getInt("quantity"))
    }
    

    fun update(id: String, request: Books): ResponseEntity<Books> {
        // Build the update statement dynamically based on provided fields
        val fields = mutableListOf<String>()
        val values = mutableListOf<Any>()

        if (request.title != null) {
            fields += "title = ?"
            values += request.title
        }
        if (request.author != null) {
            fields += "author = ?"
            values += request.author
        }
        if (request.quantity != null) {
            fields += "quantity = ?"
            values += request.quantity
        }

        if (fields.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build<Books>()
        }

        val updatedBooks = findBookById(id) ?: Books(id, request.title, request.author, request.quantity)
        return ResponseEntity.status(HttpStatus.OK).body(updatedBooks)      

    }

    fun save(books: Books): Books {
        val id = books.id ?: UUID.randomUUID().toString() // Generate new id if it is null
        db.update(
            "insert into books values ( ?, ?, ?, ? )",
            id, books.title, books.author, books.quantity
        )
        return books.copy(id = id) // Return a copy of the message with the new id
    }
}