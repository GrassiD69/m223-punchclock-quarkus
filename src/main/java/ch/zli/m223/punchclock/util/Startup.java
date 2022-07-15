package ch.zli.m223.punchclock.util;

import java.time.LocalDateTime;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.ProjectService;
import ch.zli.m223.punchclock.service.UserService;

import io.quarkus.runtime.StartupEvent;


@Singleton
public class Startup {
    @Inject
    UserService userService;
    @Inject
    EntryService entryService;
    @Inject 
    CategoryService categoryService;
    @Inject 
    ProjectService projectService;

    @Transactional
    public void loadData(@Observes StartupEvent evt) {
        
        User user = new User();
        user.setUsername("user");
        user.setPasswort("123");
        userService.createUser(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPasswort("123");

        userService.createUser(admin);

        Category hardware = new Category();
        hardware.setname("hardware");

        Category software = new Category();
        software.setname("software");


        categoryService.createCategory(hardware);
        categoryService.createCategory(software);

        Entry entry = new Entry();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = LocalDateTime.now();

        entry.setCheckIn(now);
        entry.setCheckOut(later);

        entryService.createEntry(entry);

        Project project = new Project();

        project.setname("projekt");

        projectService.createCategory(project);




    }
}
