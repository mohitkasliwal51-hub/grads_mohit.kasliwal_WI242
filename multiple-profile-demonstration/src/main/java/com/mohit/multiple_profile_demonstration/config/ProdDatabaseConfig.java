package com.mohit.multiple_profile_demonstration.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdDatabaseConfig {

}
