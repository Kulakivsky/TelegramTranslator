import com.FrenchToEnglishTranslationImpl;
import com.TgDictionaryBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class FrenchToEnglishTranslationImplTest {

    @Autowired
    private FrenchToEnglishTranslationImpl frenchToEnglishTranslation;

    @Test
    public void test() {
        String testWord = "Hello";

        frenchToEnglishTranslation.engToFrTranslate(testWord);
    }
}