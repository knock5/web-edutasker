package id.co.mii.serverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "id.co.mii.serverapp")
public class ServerappApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerappApplication.class, args);

    System.out.println("\nServerApp is running...\n");
  }
}
