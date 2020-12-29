package com.mascene.sms;

import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.mascene.sms.component.LoginScreen;

@SpringBootApplication
public class SmsApplication {
	
	public static void main(String[] args) {
        
        ConfigurableApplicationContext context = createApplicationContext(args);
        displayMainFrame(context);
    }

    private static ConfigurableApplicationContext createApplicationContext(String[] args) {
        return new SpringApplicationBuilder(SmsApplication.class)
                .headless(false)
                .run(args);
    }

    private static void displayMainFrame(ConfigurableApplicationContext context) {
        SwingUtilities.invokeLater(() -> {
        	LoginScreen loginScreen = context.getBean(LoginScreen.class);
        	loginScreen.getFrame().setVisible(true);
        });
    }

}
