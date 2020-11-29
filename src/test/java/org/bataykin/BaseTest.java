package org.bataykin;

import org.bataykin.config.ApplicationConfigTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Import(ApplicationConfigTest.class)
@ExtendWith(SpringExtension.class)
public abstract class BaseTest {
}
