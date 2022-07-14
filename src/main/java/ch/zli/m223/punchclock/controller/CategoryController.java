package ch.zli.m223.punchclock.controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;


@Path("/category")
@Tag(name = "Category", description = "Handling of Categories")
public class CategoryController {

    @Inject
    CategoryServie categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Categories", description = "")
    public List<Category> list() {
        return categoryService.findAll();
    }
}
