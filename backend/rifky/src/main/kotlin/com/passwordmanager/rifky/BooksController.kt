package com.passwordmanager.rifky

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import java.net.URI
import java.util.Optional

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
    
    @PatchMapping("/{id}")
    fun patch(@PathVariable id: String, 
            @RequestBody request: Books): ResponseEntity<Books> {
        return service.update(id, request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        val result = service.delete(id)
        return if (result){
            ResponseEntity.status(HttpStatus.OK).build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    private fun Books?.toResponseEntity(): ResponseEntity<Books> =
        // If the message is null (not found), set response code to 404
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}