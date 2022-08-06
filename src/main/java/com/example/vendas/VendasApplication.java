package com.example.vendas;

import com.example.vendas.model.Cliente;
import com.example.vendas.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClientesRepository clientesRepository){
		return args -> {
			//salvando dois clientes
			clientesRepository.save(new Cliente("Luiz"));
			clientesRepository.save(new Cliente("Felipe"));

			List<Cliente> todosOsClientes = clientesRepository.findAll();
			todosOsClientes.forEach(System.out::println);

			todosOsClientes.forEach( c-> {
				c.setName(c.getName() + " atualizado");
				clientesRepository.save(c);
					});
			List<Cliente> todosClientes = clientesRepository.findAll();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando por nome");
			clientesRepository.findByNameLike("Lui").forEach(System.out::println);

			System.out.println("Deletando todos os clientes");
			clientesRepository.findAll().forEach(c -> {
				clientesRepository.delete(c);
			});

			System.out.println("Printando os clientes após delete");
			todosClientes = clientesRepository.findAll();
			if(todosClientes.isEmpty()){
				System.out.println("Nenhum cliente encontrado");
			}
			else {
				todosClientes.forEach(System.out::println);
			}

		};

	}

	@Autowired
	@Qualifier("applicationName")
	private String applicationName;

	@GetMapping("/hello")
	public String helloWorld(){
		return applicationName;
	}

	@GetMapping("/ping")
	public String ping(){
		return "PONG";
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
