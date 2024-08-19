package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

  @NotBlank(message = "user name is required")
  @Size(min = 3, message = "min 3 character's is required")
  private String name;
  @Email(message = "Invalid email address")
  @NotBlank(message = "email is required")
  private String email;
  @NotBlank(message = "password is required")
  @Size(min = 8, message = "Min 8 Character's is Required")
  private String password;
  @NotBlank(message = "write something about yourself")
  private String about;
  @Size(min = 8, max = 12, message = "Invalid Phone Number")
  private String phoneNumber;

}
