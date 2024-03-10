package com.mastercode.fitmaster.dataloader;

import com.github.javafaker.Faker;
import com.mastercode.fitmaster.model.MemberEntity;
import com.mastercode.fitmaster.model.MembershipEntity;
import com.mastercode.fitmaster.model.PackageEntity;
import com.mastercode.fitmaster.model.TrainerEntity;
import com.mastercode.fitmaster.model.enums.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class DataLoaderImpl extends DataLoader implements CommandLineRunner {
    Faker faker = new Faker();
    private static final String PHONE_NUMBER_REGEX = "\\+\\(\\d{3}\\) \\d{2}-\\d{3}-\\d{4}";

    @Override
    public void run(String... args) throws Exception {
        //        loadTestData();
    }

    @Override
    void loadTestData() {
        loadPackages(5);
        loadMembers(8);
        loadTrainers(5);
    }

    private void loadPackages(int counter) {
        for (int i = 0; i < counter; i++) {
            PackageEntity p = new PackageEntity();

            p.setName(faker.lorem().word() + " #" + (i + 1));
            p.setDuration(30);
            p.setPrice(BigDecimal.valueOf(Double.valueOf(faker.commerce().price(20, 100).replace(',', '.'))));
            p.setCurrency(Currency.getInstance("EUR"));
            // Localization of PostgreSQL might cause issues with number formatting (eg. ',' and '.' characters).

            packageRepository.saveAndFlush(p);
        }
    }

    private void loadMembers(int counter) {
        for (int i = 0; i < counter; i++) {
            MemberEntity m = new MemberEntity();

            m.setFirstName(faker.name().firstName());
            m.setLastName(faker.name().lastName());
            m.setEmail(faker.internet().emailAddress());
            m.setEmailVerified(true);
            m.setUsername(faker.name().username());
            m.setPassword(new BCryptPasswordEncoder().encode(faker.internet().password(8, 32)));
            m.setGender(Gender.valueOf(faker.demographic().sex().toUpperCase()));
            m.setAddress(faker.address().streetAddress());
            m.setPhoneNumber(faker.regexify(PHONE_NUMBER_REGEX));
            m.setBirthDate(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));

            MembershipEntity ms = new MembershipEntity();
            ms.setMemberEntity(m);
            m.getMembershipEntities().add(ms);
            ms.setMembershipPackageEntity(
                    packageRepository.findAll().get((int) new Random().nextLong(packageRepository.count())));
            ms.setStartDate(
                    LocalDate.ofInstant(faker.date().past(30, TimeUnit.DAYS, Date.valueOf(LocalDate.now())).toInstant(),
                            ZoneId.systemDefault()));
            ms.setEndDate(LocalDate.ofInstant(
                    faker.date().future(30, TimeUnit.DAYS, Date.valueOf(LocalDate.now())).toInstant(),
                    ZoneId.systemDefault()));

            membershipRepository.saveAndFlush(ms);
            memberRepository.saveAndFlush(m);
        }
    }

    private void loadTrainers(int counter) {
        for (int i = 0; i < counter; i++) {
            TrainerEntity t = new TrainerEntity();

            t.setFirstName(faker.name().firstName());
            t.setLastName(faker.name().lastName());
            t.setGender(Gender.valueOf(faker.demographic().sex().toUpperCase()));
            t.setAddress(faker.address().streetAddress());
            t.setEmail(faker.internet().emailAddress());
            t.setEmailVerified(true);
            t.setUsername(faker.name().username());
            t.setPassword(new BCryptPasswordEncoder().encode(faker.internet().password(8, 32)));
            t.setPhoneNumber(faker.regexify(PHONE_NUMBER_REGEX));
            t.setHireDate(LocalDate.ofInstant(faker.date()
                    .between(Date.valueOf(LocalDate.of(2000, 1, 1)), Date.valueOf(LocalDate.now()))
                    .toInstant(), ZoneId.systemDefault()));

            trainerRepository.saveAndFlush(t);
        }
    }
}
