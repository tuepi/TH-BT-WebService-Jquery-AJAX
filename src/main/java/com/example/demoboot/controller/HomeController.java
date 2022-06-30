package com.example.demoboot.controller;

import com.example.demoboot.model.Home;
import com.example.demoboot.service.impl.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homes")
public class HomeController {
    @Autowired
    HomeServiceImpl homeService;

    @GetMapping
    public ResponseEntity<Page<Home>> findAll(@PageableDefault(value = 3) Pageable pageable) {
        Page<Home> products = homeService.findAll(pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Home> findById(@PathVariable Long id) {
        Optional<Home> product = homeService.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public ResponseEntity<Home> save(@Valid @RequestBody Home question) {
//        return new ResponseEntity<>(homeService.save(question), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Home> handleFileUpload(@Valid @RequestParam("file") MultipartFile file,Home home) {
        String fileName = file.getOriginalFilename();
        home.setImage(fileName);
        try {
            file.transferTo(new File("C:\\Users\\hongh\\IdeaProjects\\demoBoot\\src\\main\\resources\\templates\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(homeService.save(home), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Home> update(@PathVariable Long id, @RequestParam("file") MultipartFile file,@Valid Home home) {
        String fileName = file.getOriginalFilename();
        if (fileName.equals("")){
            home.setImage(homeService.findById(id).get().getImage());
        }else {
                home.setImage(fileName);
                try {
                    file.transferTo(new File("C:\\Users\\hongh\\IdeaProjects\\demoBoot\\src\\main\\resources\\templates\\image\\" + fileName));
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        }
        home.setId(id);
        return new ResponseEntity<>(homeService.save(home), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Home> questionOptional = homeService.findById(id);
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        homeService.remove(id);
        return new ResponseEntity<>(questionOptional.get(), HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
