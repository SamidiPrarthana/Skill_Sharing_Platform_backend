package com.example.Recipe.Controller;

import com.example.Recipe.Service.RecipeService;
import com.example.Recipe.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/getAll")
    public Map<String, Object> getAllRecipes() {
        List<RecipeDto> recipeList = recipeService.getAllRecipes();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("recipe", recipeList);
        return response;
    }

    @PostMapping("/save")
    public RecipeDto saveRecipe(@RequestBody RecipeDto recipeDto,
                                @RequestPart List<MultipartFile> mediaFiles) {
        recipeDto.setMediaFiles(mediaFiles);
        return recipeService.saveRecipe(recipeDto);
    }

    @PutMapping("/update/{id}")
    public RecipeDto updateRecipe(
            @PathVariable String id,
            @RequestBody RecipeDto recipeDto) {
        recipeDto.setId(id);
        return recipeService.updateRecipe(recipeDto);
    }


    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteRecipe(@PathVariable String id) {
        boolean deleted = recipeService.deleteRecipe(id);
        return Map.of("deleted", deleted);
    }


    @GetMapping("/get/{id}")
    public RecipeDto getOne(@PathVariable String id) {
        return recipeService.getRecipeById(id);
    }
}

