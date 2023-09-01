package com.mastercode.fitmaster.dataloader;

import com.github.javafaker.Faker;
import com.mastercode.fitmaster.model.Member;
import com.mastercode.fitmaster.model.Membership;
import com.mastercode.fitmaster.model.Package;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.model.enums.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class DataLoaderImpl extends DataLoader implements CommandLineRunner {
    Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        //        loadTestData();
    }

    @Override
    void loadTestData() {
        loadPackages(5);
        loadMembers(25);
        loadTrainers(5);
    }

    private void loadPackages(int counter) {
        for (int i = 0; i < counter; i++) {
            Package p = new Package();

            p.setName(faker.lorem().word() + " #" + (i + 1));
            p.setPrice(BigDecimal.valueOf(Double.valueOf(faker.commerce().price(20, 100).replace(',', '.'))));
            // Localization of PostgreSQL might cause issues with number formatting (eg. ',' and '.' characters).

            packageRepository.saveAndFlush(p);
        }
    }

    private void loadMembers(int counter) {
        for (int i = 0; i < counter; i++) {
            Member m = new Member();

            m.setFirstName(faker.name().firstName());
            m.setLastName(faker.name().lastName());
            m.setUsername(faker.name().username());
            m.setPassword(new BCryptPasswordEncoder().encode(faker.internet().password(8, 32)));
            m.setGender(Gender.valueOf(faker.demographic().sex().toUpperCase()));
            m.setAddress(faker.address().streetAddress());
            m.setPhoneNumber(faker.phoneNumber().cellPhone());
            m.setBirthDate(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));

            Membership ms = new Membership();
            ms.setMember(m);
            m.getMemberships().add(ms);
            ms.setMembershipPackage(packageRepository.findAll()
                    .get((int) new Random().nextLong(packageRepository.count())));
            ms.setStartDate(LocalDate.ofInstant(faker.date()
                    .past(30, TimeUnit.DAYS, Date.valueOf(LocalDate.now()))
                    .toInstant(), ZoneId.systemDefault()));
            ms.setEndDate(LocalDate.ofInstant(faker.date()
                    .future(30, TimeUnit.DAYS, Date.valueOf(LocalDate.now()))
                    .toInstant(), ZoneId.systemDefault()));

            membershipRepository.saveAndFlush(ms);
            memberRepository.saveAndFlush(m);
        }
    }

    private void loadTrainers(int counter) {
        for (int i = 0; i < counter; i++) {
            Trainer t = new Trainer();

            t.setFirstName(faker.name().firstName());
            t.setLastName(faker.name().lastName());
            t.setGender(Gender.valueOf(faker.demographic().sex().toUpperCase()));
            t.setAddress(faker.address().streetAddress());
            t.setUsername(faker.name().username());
            t.setPassword(new BCryptPasswordEncoder().encode(faker.internet().password(8, 32)));
            t.setPhoneNumber(faker.phoneNumber().cellPhone());
            t.setHireDate(LocalDate.ofInstant(faker.date()
                    .between(Date.valueOf(LocalDate.of(2000, 1, 1)), Date.valueOf(LocalDate.now()))
                    .toInstant(), ZoneId.systemDefault()));

            trainerRepository.saveAndFlush(t);
        }
    }
}
