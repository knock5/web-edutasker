package id.co.mii.clientapp.controllers.rest;

import id.co.mii.clientapp.models.Category;
import id.co.mii.clientapp.services.CategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class RestCategoryController {

  private CategoryService categoryService;

  @GetMapping
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @GetMapping("/{id}")
  public Category getById(@PathVariable("id") Integer id) {
    return categoryService.getById(id);
  }

  @PostMapping
  public Category create(@RequestBody Category category) {
    return categoryService.create(category);
  }

  @PutMapping("/update/{id}")
  public Category update(
    @PathVariable("id") Integer id,
    @RequestBody Category category
  ) {
    return categoryService.update(id, category);
  }

  @DeleteMapping("/{id}")
  public Category delete(@PathVariable("id") Integer id) {
    return categoryService.delete(id);
  }
}
