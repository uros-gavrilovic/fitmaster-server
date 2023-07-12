package com.mastercode.fitmaster.dataloader;

import com.github.javafaker.Faker;
import com.mastercode.fitmaster.model.Gender;
import com.mastercode.fitmaster.model.Trainer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class DataLoaderImpl extends DataLoader implements CommandLineRunner {
    Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        loadTestData();
    }

    @Override
    void loadTestData() {
        loadTrainers(0);
    }

    private void loadTrainers(int counter) {
        for (int i = 0; i < counter; i++) {
            Trainer t = new Trainer();

            t.setFirstName(faker.name().firstName());
            t.setLastName(faker.name().lastName());
            t.setGender(Gender.valueOf(faker.demographic().sex().toUpperCase()));
            t.setAddress(faker.address().streetAddress());
            t.setUsername(faker.name().username());
            t.setPassword(faker.internet().password(8, 32));
            t.setPhoneNumber(faker.phoneNumber().cellPhone());
            t.setHireDate(LocalDate.ofInstant(faker.date().between(Date.valueOf(LocalDate.of(2000, 1, 1)), Date.valueOf(LocalDate.now())).toInstant(), ZoneId.systemDefault()));

            trainerRepository.saveAndFlush(t);
        }
    }
}
