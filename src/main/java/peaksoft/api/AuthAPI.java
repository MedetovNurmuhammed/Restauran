package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.RegisterResponse;
import peaksoft.dto.response.SiginResponse;
import peaksoft.dto.response.Simpleresponse;
import peaksoft.entity.User;
import peaksoft.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User API")
public class UserAPI {
    private final UserService userService;
    @PostMapping
    @Operation(description = "This is registration")
    public RegisterResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        return  userService.signUP(signUpRequest);
    }
    @GetMapping
    public SiginResponse signIn(@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }
    @PutMapping("/{userID}")
    @Operation(description = "This is updated")
     @PreAuthorize("hasAnyRole('ADMIN')")
    public Simpleresponse updateUser(@PathVariable Long userID, @RequestBody User user, Principal principal){
        return userService.update(principal, userID, user);
    }

}
