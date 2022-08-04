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
	public CommandLineRunner init(@Autowired ClientesRepository clientes){
		return args -> {
			//salvando dois clientes
			clientes.salvar(new Cliente("Luiz"));
			clientes.salvar(new Cliente("Felipe"));

			List<Cliente> todosOsClientes = clientes.obterTodos();
			todosOsClientes.forEach(System.out::println);

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
