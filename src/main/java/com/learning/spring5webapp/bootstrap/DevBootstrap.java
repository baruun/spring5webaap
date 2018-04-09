package com.learning.spring5webapp.bootstrap;

import com.learning.spring5webapp.model.Author;
import com.learning.spring5webapp.model.Book;
import com.learning.spring5webapp.model.Publisher;
import com.learning.spring5webapp.repositories.AuthorRepository;
import com.learning.spring5webapp.repositories.BookRepository;
import com.learning.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        //David
        Author david = new Author("Dawid", "Nowak");

        Publisher publisher = new Publisher("foo", "");
        publisherRepository.save(publisher);

        Book book = new Book("Kosmos", "1234", publisher);
        david.getBooks().add(book);
        book.getAuthors().add(david);

        authorRepository.save(david);
        bookRepository.save(book);


        //Rod
        Author rod = new Author("Rod", "Johnson");

        Book noEjb = new Book("J2EE development without EJB", "23412", publisher);
        rod.getBooks().add(noEjb);

        authorRepository.save(rod);
        bookRepository.save(noEjb);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
