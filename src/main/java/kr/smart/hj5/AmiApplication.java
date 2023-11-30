package kr.smart.hj5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"kr.smart.hj5"})
public class AmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmiApplication.class, args);
		System.out.println("서버 올라옴");
	}
}