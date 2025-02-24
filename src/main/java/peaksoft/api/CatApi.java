package restaurant.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.request.CatSaveRequest;
import restaurant.dto.response.CatResponse;
import restaurant.dto.response.CategoryPagination;
import restaurant.dto.response.SimpleResponse;
import restaurant.services.CategoryService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cat")
public class CatApi {
    private final CategoryService categoryService;

    @Secured({"ADMIN", "CHEF", "WAITER"})
    @GetMapping
    public CategoryPagination findAll(@RequestParam int page,
                                      @RequestParam int size,
                                      Principal principal) {
        return categoryService.findAll(page, size, principal);
    }

    @Secured({"ADMIN", "CHEF"})
    @PostMapping("/{resId}")
    public SimpleResponse saveCat(@RequestBody @Valid CatSaveRequest catSaveRequest,
                                  Principal principal, @PathVariable Long resId) {
        return categoryService.saveCat(catSaveRequest, principal, resId);
    }

    @Secured({"ADMIN", "CHEF", "WAITER"})
    @GetMapping("/find/{catId}")
    public CatResponse findById(@PathVariable Long catId, Principal principal) {
        return categoryService.findById(catId, principal);
    }

    @Secured({"ADMIN", "CHEF"})
    @PutMapping("/{catId}")
    public SimpleResponse updateCat(@PathVariable Long catId,
                                    @RequestBody @Valid CatSaveRequest catSaveRequest,
                                    Principal principal) {
        return categoryService.updateCat(catId, catSaveRequest, principal);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{catId}")
    public SimpleResponse deleteCat(@PathVariable Long catId, Principal principal) {
        return categoryService.deleteCat(catId, principal);
    }
}
