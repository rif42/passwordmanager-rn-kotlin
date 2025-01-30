package com.passwordmanager.rifky

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/books")
class BooksController(private val service: BooksService) {
    @GetMapping
    fun listBooks() = service.findBooks()

    @PostMapping
    fun post(@RequestBody books: Books): ResponseEntity<Books> {
        val savedBooks = service.save(books)
        return ResponseEntity.created(URI("/books/${books.id}")).body(savedBooks)
    }

    // @GetMapping("/{id}")
    // fun getMessage(@PathVariable id: String): ResponseEntity<Message> =
    //     service.findMessageById(id).toResponseEntity()

    private fun Books?.toResponseEntity(): ResponseEntity<Books> =
        // If the message is null (not found), set response code to 404
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}