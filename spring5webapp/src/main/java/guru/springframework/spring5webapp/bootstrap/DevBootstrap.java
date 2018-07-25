package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}
	
	private void initData() {
		
		Publisher publisher = new Publisher();
		publisher.setName("Harper");
		publisherRepository.save(publisher);
		
		//Eric
		Author eric = new Author("Eric", "Evants");
		Book book1 = new Book("DDD", "1234", publisher);
		eric.getBooks().add(book1);
		book1.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(book1);
		
		Publisher publisher2 = new Publisher();
		publisher2.setName("Worx");
		publisherRepository.save(publisher2);
		
		//Rod
		Author rod = new Author("Rod", "Rod");
		book1 = new Book("J2EE", "4567", publisher2);
		rod.getBooks().add(book1);
		
		authorRepository.save(rod);
		bookRepository.save(book1);
		
	}
}
