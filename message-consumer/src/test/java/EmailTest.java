import com.owpk.AppApplication;
import com.owpk.RabbitMQConfiguration;
import com.owpk.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {AppApplication.class, RabbitMQConfiguration.class})
public class EmailTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmail() {
        emailService.sendSimpleMessage("voyzvz@gmail.com", "test", "test");
    }

}
