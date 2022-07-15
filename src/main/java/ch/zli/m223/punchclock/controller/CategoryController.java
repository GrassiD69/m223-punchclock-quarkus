package ch.zli.m223.punchclock.controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;

  /*
  *@Author:Damian Grassi
  *@Description: Service Class for Category Domainclass
  *@Version: 1.0
  */
@Path("/category")
@Tag(name = "Category", description = "Handling of Categories")
public class CategoryController {

    @Inject
    CategoryService categoryService;

    
    /** 
     * A Method to return all Categories
     * @return List<Category>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User", "Admin"})
    @Operation(summary = "List all Categories", description = "")
    public List<Category> list() {
        return categoryService.findAll();
    }
}
