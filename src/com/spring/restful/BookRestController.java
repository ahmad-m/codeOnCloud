package com.spring.restFull;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookRestController {

    private BookService bookService;

    private Logger logger=LoggerFactory.getLogger(BookRestController.class);


    //Note: The @Named("bookService") is not required in this example (as there only instance of BookService around)
    @Inject
    public BookRestController(@Named("bookService") BookService bookService )
    {
        this.bookService=bookService;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable("id") Long id) {
        logger.debug("Provider has received request to get person with id: " + id);
        return bookService.getBook(id);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectWithId addBook(@RequestBody Book book)
    {
        return new ObjectWithId(bookService.addBook(book));
    }
    
    @RequestMapping(value = "/count",method = RequestMethod.GET ,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public long getBookCount() {
        logger.debug("Provider has received request to get All person ");
        return bookService.getBookCount();
    }
}